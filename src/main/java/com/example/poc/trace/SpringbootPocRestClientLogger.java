package com.example.poc.trace;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpResponse;

import com.example.poc.util.SpringbootPOCUtil;
import com.google.common.io.ByteStreams;
import org.springframework.http.client.ClientHttpRequestInterceptor;

public class SpringbootPocRestClientLogger implements ClientHttpRequestInterceptor {

	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
    	logger.info("===========REQUEST START============");
    	logger.info("URI      : {}", request.getURI());
    	logger.info("Method   : {}", request.getMethod());
    	logger.info("Headers  : {}", request.getHeaders());  
    	logger.info("Body     : {}", new String(body));  
    	logger.info("===========REQUEST END============");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        
    	logger.info("===========RESPONSE START============");
    	logger.info("Status code  : {}", response.getStatusCode());
    	logger.info("Status text  : {}", response.getStatusText());
    	logger.info("Headers      : {}", response.getHeaders()); 
    	logger.info("Body         : {}", SpringbootPOCUtil.ConvertStreamToString(response.getBody())); 	
    	logger.info("===========RESPONSE END============");
    }
}
