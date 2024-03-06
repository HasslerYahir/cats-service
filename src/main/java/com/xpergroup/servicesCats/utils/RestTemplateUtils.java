package com.xpergroup.servicesCats.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Component
public class RestTemplateUtils {

    private final RestTemplate restTemplate;

    @Autowired
    public RestTemplateUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> ResponseEntity<T[]> getAll(String url, Class<T[]> responseType, HttpHeaders headers) {
        return restTemplate.getForEntity(url, responseType);
    }

    public <T> ResponseEntity<T> getWithPathVariable(String url, Class<T> responseType, HttpHeaders headers, Map<String, ?> pathVariables) {
        return restTemplate.getForEntity(url, responseType, pathVariables);
    }

    public <T> ResponseEntity<T[]> getWithQueryParams(String url, Class<T[]>  responseType, HttpHeaders headers, Map<String, ?> queryParams) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(url);

        queryParams.forEach(uriBuilder::queryParam);

        return restTemplate.getForEntity(uriBuilder.build().toUri(), responseType);
    }
}

