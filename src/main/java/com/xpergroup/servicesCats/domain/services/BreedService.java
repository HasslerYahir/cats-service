package com.xpergroup.servicesCats.domain.services;

import com.xpergroup.servicesCats.application.out.rest.BreedApiService;
import com.xpergroup.servicesCats.domain.models.Breed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BreedService {

    private final BreedApiService breedApiService;
    private final Logger log = LoggerFactory.getLogger(BreedService.class);

    public BreedService(BreedApiService breedApiService) {
        this.breedApiService = breedApiService;
    }

    public List<Breed> breedsAll() {
        log.info("Fetching all breeds.");
        List<Breed> breeds = breedApiService.breeds();
        log.info("Fetched {} breeds successfully.", breeds.size());
        return breeds;
    }

    public Breed breedById(String id) {
        log.info("Fetching breed by ID: {}.", id);
        Breed breed = breedApiService.breedById(id);

        if (breed != null) {
            log.info("Fetched breed by ID {}: {}", id, breed);
        } else {
            log.warn("Breed with ID {} not found.", id);
        }

        return breed;
    }

    public List<Breed> breedSearch(String search, Integer searchImage) {
        log.info("Searching breeds with search: {} and searchImage: {}.", search, searchImage);
        List<Breed> breeds = breedApiService.breedsByQuery(search, searchImage);
        log.info("Found {} breeds matching the search.", breeds.size());
        return breeds;
    }
}

