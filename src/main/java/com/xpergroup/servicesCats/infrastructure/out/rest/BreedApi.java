package com.xpergroup.servicesCats.infrastructure.out.rest;

import com.xpergroup.servicesCats.application.mappers.dto.BreedDTO;
import com.xpergroup.servicesCats.application.out.rest.BreedApiService;
import com.xpergroup.servicesCats.domain.models.Breed;
import com.xpergroup.servicesCats.infrastructure.out.rest.mappers.BreedMapper;
import com.xpergroup.servicesCats.utils.RestTemplateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class BreedApi implements BreedApiService {
    private final RestTemplateUtils restTemplateUtils;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${spring.rest.cats-api-url-base}")
    private  String catsApiBaseUrl ="";
    @Value("${spring.rest.cats-api-url-breeds}")
    private  String catsBreedsAll ="";
    @Value("${spring.rest.cats-api-url-breed-by-id}")
    private  String catsBreedsById="";

    @Value("${spring.rest.cats-api-url-breed-search}")
    private  String catsBreedsSearch="";
    private final int BREED_ALL = 1;
    private final int BREED_BY_ID = 2;
    private final int BREED_SEARCH = 3;

    public BreedApi(RestTemplateUtils restTemplateUtils) {
        this.restTemplateUtils = restTemplateUtils;
    }

    @Override
    public List<Breed> breeds() {
        log.info("Starting API call to fetch all breeds");
        return Optional.of(restTemplateUtils.getAll(buildUrl(BREED_ALL), BreedDTO[].class, HttpHeaders.EMPTY))
          .map(responseEntity -> {
              if (responseEntity.getStatusCode() == HttpStatus.OK) {
                  BreedDTO[] breedDTOS = responseEntity.getBody();
                  log.info("API call to fetch all breeds completed. Body: {} Status: {}", breedDTOS, responseEntity.getStatusCode());
                  return Arrays.stream(Objects.requireNonNull(breedDTOS)).toList();
              } else {
                  log.error("Error in API call to fetch all breeds. Body: {} Status: {}", responseEntity.getBody(), responseEntity.getStatusCode());
                  return new ArrayList<BreedDTO>();
              }
          })
          .orElse(new ArrayList<>())
          .stream().map(BreedMapper::toModel)
          .toList();
    }

    @Override
    public Breed breedById(String id) {
        String url = buildUrl(BREED_BY_ID);
        log.info("Starting API call to fetch breed by id {} at url {}", id, url);
        return Optional.of(restTemplateUtils.getWithPathVariable(url, BreedDTO.class, HttpHeaders.EMPTY, Map.of("id",id)))
          .map(responseEntity -> {
              if (responseEntity.getStatusCode() == HttpStatus.OK) {
                  BreedDTO breedDTOS = responseEntity.getBody();
                  log.info("API call to fetch breed by id completed. Body: {} Status: {}", breedDTOS, responseEntity.getStatusCode());
                  return breedDTOS;
              } else {
                  log.error("Error in API call to fetch breed by id. Body: {} Status: {}", responseEntity.getBody(), responseEntity.getStatusCode());
                  return new BreedDTO();
              }
          })
          .map(BreedMapper::toModel)
          .get();
    }

    @Override
    public List<Breed> breedsByQuery(String breedSearch, Integer referenceImage) {
        Map<String,Object> params = new HashMap<>();
        params.put("q", breedSearch);
        if (referenceImage != null && referenceImage != 0) {
            params.put("attach_image", referenceImage);
        }
        return Optional.of(restTemplateUtils.getWithQueryParams(buildUrl(BREED_SEARCH), BreedDTO[].class, HttpHeaders.EMPTY, params))
          .map(responseEntity -> {
              if (responseEntity.getStatusCode() == HttpStatus.OK) {
                  BreedDTO[] breedDTOS = responseEntity.getBody();
                  log.info("API call to fetch breeds by query completed. Body: {} Status: {}", breedDTOS, responseEntity.getStatusCode());
                  return Arrays.stream(Objects.requireNonNull(breedDTOS)).toList();
              } else {
                  log.error("Error in API call to fetch breeds by query. Body: {} Status: {}", responseEntity.getBody(), responseEntity.getStatusCode());
                  return new ArrayList<BreedDTO>();
              }
          })
          .orElse(new ArrayList<>())
          .stream().map(BreedMapper::toModel)
          .toList();
    }

    private final String buildUrl(int typeURL) {
        switch (typeURL) {
            case BREED_ALL -> {
                return catsApiBaseUrl.concat(catsBreedsAll);
            }
            case BREED_BY_ID -> {
                return catsApiBaseUrl.concat(catsBreedsById);
            }
            case BREED_SEARCH -> {
                return catsApiBaseUrl.concat(catsBreedsSearch);
            }
            default -> {
                log.error("Invalid URL type: {}", typeURL);
                throw new IllegalArgumentException("Invalid URL type: " + typeURL);
            }
        }
    }
}
