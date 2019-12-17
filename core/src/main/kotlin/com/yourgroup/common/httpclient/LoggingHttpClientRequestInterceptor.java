package com.yourgroup.common.httpclient;

import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Gyuhyeon Lee (mark3236@gmail.com)
 * @since 2019. 12. 02.
 */
public class LoggingHttpClientRequestInterceptor implements ClientHttpRequestInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggingHttpClientRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		String transactionKey = UUID.randomUUID().toString().substring(0, 8);

		traceRequest(transactionKey, request, body);
		ClientHttpResponse response;
		try {
			response = execution.execute(request, body);
			traceResponse(transactionKey, response);
			return response;
		} catch (Exception ex) {
			LOGGER.error("(RestTemplate)(" + transactionKey + ")(" + request.getURI() + ")(Method:" + request.getMethod() + ")(Body: " + new String(body, "UTF-8")+ ")", ex);
			throw ex;
		}
	}

	private void traceRequest(String transactionKey, HttpRequest request, byte[] body) throws IOException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("(RestTemplate)(Req)(" + transactionKey + ")(" + request.getURI() + ")(Method:" + request.getMethod() + ")(Body: " + new String(body, "UTF-8")+ ")");
			LOGGER.debug("===========================Request begin================================================");
			LOGGER.debug("URI         : {}", request.getURI());
			LOGGER.debug("Method      : {}", request.getMethod());
			LOGGER.debug("Headers     : {}", request.getHeaders() );
			LOGGER.debug("Request body: {}", new String(body, "UTF-8"));
			LOGGER.debug("===========================Request end==================================================");
		} else if (LOGGER.isInfoEnabled()) {
			LOGGER.info("(RestTemplate)(Req)(" + transactionKey + ")(" + request.getURI() + ")(Method:" + request.getMethod() + ")(Headers:" + request.getHeaders() + ")(Body: " + new String(body, "UTF-8") + ")");
		}
	}

	private void traceResponse(String transactionKey, ClientHttpResponse response) throws IOException {
		if (LOGGER.isDebugEnabled()) {
			String bodyString = getBodyString(response);
			LOGGER.debug("(RestTemplate)(Res)(" + transactionKey + ")(Status:" + response.getStatusCode() + ")(Body: " + bodyString + ")");
			LOGGER.debug("============================Response begin===============================================");
			LOGGER.debug("Status code  : {}", response.getStatusCode());
			LOGGER.debug("Status text  : {}", response.getStatusText());
			LOGGER.debug("Headers      : {}", response.getHeaders());
			LOGGER.debug("Response body: {}", bodyString);
			LOGGER.debug("============================Response end=================================================");
		} else if (LOGGER.isInfoEnabled()) {
			String bodyString = getBodyString(response);
			LOGGER.info("(RestTemplate)(Res)(" + transactionKey + ")(Status:" + response.getStatusCode() + ")(Headers:" + response.getHeaders() + ")(Body: " + bodyString + ")");
		}
	}

	private String getBodyString(ClientHttpResponse response) {
		try {
			if (response != null && response.getBody() != null) {// &&
				return IOUtils.toString(IOUtils.toByteArray(response.getBody()), "UTF-8");
			} else {
				return null;
			}
		} catch (IOException e) {
			LOGGER.error("(RestTemplate)(Get Body Error)(" + e.getMessage() + ")", e);
			return null;
		}
	}
}
