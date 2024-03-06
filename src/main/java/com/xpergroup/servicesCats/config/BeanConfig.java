package com.xpergroup.servicesCats.config;

import com.xpergroup.servicesCats.application.in.BreedAllUseCase;
import com.xpergroup.servicesCats.application.in.BreedByIdUseCase;
import com.xpergroup.servicesCats.application.in.BreedSearchUseCase;
import com.xpergroup.servicesCats.application.out.rest.BreedApiService;
import com.xpergroup.servicesCats.application.service.BreedsServiceApplication;
import com.xpergroup.servicesCats.domain.services.BreedService;
import com.xpergroup.servicesCats.infrastructure.out.rest.BreedApi;
import com.xpergroup.servicesCats.utils.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public BreedApiService breedApiService() {
        return new BreedApi(restTemplateUtils());
    }
    @Bean
    public BreedService breedService(BreedApiService breedService){
        return new BreedService(breedService);
    }
    @Bean
    public BreedAllUseCase breedAllUseCase(BreedService breedService){
        return new BreedsServiceApplication(breedService);
    }
    @Bean
    public BreedByIdUseCase breedByIdUseCase(BreedService breedService){
        return new BreedsServiceApplication(breedService);
    }
    @Bean
    public BreedSearchUseCase breedSearchUseCase(BreedService breedService){
        return new BreedsServiceApplication(breedService);
    }
    @Bean
    RestTemplateUtils restTemplateUtils() {
        return new RestTemplateUtils(new RestTemplate());
    }
}
