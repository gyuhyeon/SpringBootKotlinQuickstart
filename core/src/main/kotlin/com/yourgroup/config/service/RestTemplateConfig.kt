package com.yourgroup.config.service

import com.yourgroup.common.httpclient.LoggingHttpClientRequestInterceptor
import org.apache.http.client.HttpClient
import org.apache.http.client.config.RequestConfig
import org.apache.http.conn.HttpClientConnectionManager
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 12. 02.
 */
@Configuration
class RestTemplateConfig {

    @Bean
    fun poolingHttpClientConnectionManager(): HttpClientConnectionManager {
        val cm = PoolingHttpClientConnectionManager()
        cm.maxTotal = 100
        cm.defaultMaxPerRoute = 40
        return cm
    }

    @Bean
    fun closeableHttpClient(@Autowired @Qualifier("poolingHttpClientConnectionManager") cm: HttpClientConnectionManager): CloseableHttpClient {
        val defaultRequestConfig = RequestConfig.custom()
            .setConnectTimeout(2000)
            .setSocketTimeout(2000)
            .setConnectionRequestTimeout(1000)
            .build()

        return HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).setConnectionManager(cm).build()
    }

    @Bean
    fun generalRestTemplate(@Autowired @Qualifier("closeableHttpClient") httpClient: HttpClient): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.requestFactory = BufferingClientHttpRequestFactory(HttpComponentsClientHttpRequestFactory(httpClient));
        restTemplate.interceptors.add(LoggingHttpClientRequestInterceptor())
        return restTemplate
    }
}
