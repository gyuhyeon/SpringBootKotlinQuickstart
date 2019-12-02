package com.yourgroup.frontend.controller

import com.yourgroup.module.basics.service.SampleService
import com.yourgroup.module.datetimeformatbug.POJO
import com.yourgroup.module.datetimeformatbug.POJOPrimaryConstructor
import com.yourgroup.module.interopdemo.JavaInteropClass
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 11. 20.
 */
@Controller
@RequestMapping("/test")
class SampleController (
    private val sampleService: SampleService
) {
    @RequestMapping("/working")
    @ResponseBody
    fun working(pojo: POJO): String {
        return "non primary constructor DateTimeFormat annotation binding works: ${pojo.someDate}"
    }

    @RequestMapping("/notworking")
    @ResponseBody
    fun notworking(@ModelAttribute pojoPrimaryConstructor: POJOPrimaryConstructor): String {
        // this didn't work on other projects, I don't know why it works here...
        return "primary constructor declared fields don't get bound by DateTimeFormat annotation. This message is not shown. ${pojoPrimaryConstructor.someDate}"
    }

    @RequestMapping("/binding")
    @ResponseBody
    fun binding(javaInteropClass: JavaInteropClass): String {
        return javaInteropClass.id.toString()
    }

    @RequestMapping("autowired")
    @ResponseBody
    fun autowired(): String {
        return sampleService.getPropValue()
    }

    @RequestMapping("select")
    @ResponseBody
    fun repositorySelect(@RequestParam(required = false, defaultValue = "1") id: Long): Any {
        return sampleService.getRepositoryValue(id)
    }
}