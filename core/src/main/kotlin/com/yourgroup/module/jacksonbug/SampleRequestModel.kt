package com.yourgroup.module.jacksonbug

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 12. 02.
 */
class SampleRequestModel (
    _enumParam: SomeEnum? = null // non-field constructor parameters of enum type breaks json->object mapping when used as response type of RestTemplate for some reason.
) {
    var someId: Long? = null
    var someCode: String? = null
    var someText: String? = null
    var someEnum: SomeEnum? = null
}