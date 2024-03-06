package com.xpergroup.servicesCats.application.service;

import com.xpergroup.servicesCats.application.in.BreedAllUseCase;
import com.xpergroup.servicesCats.application.in.BreedByIdUseCase;
import com.xpergroup.servicesCats.application.in.BreedSearchUseCase;
import com.xpergroup.servicesCats.domain.models.Breed;
import com.xpergroup.servicesCats.domain.services.BreedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class BreedsServiceApplication implements BreedAllUseCase, BreedByIdUseCase, BreedSearchUseCase {

    private final Logger log = LoggerFactory.getLogger(BreedsServiceApplication.class);
    private final BreedService breedService;

    public BreedsServiceApplication(BreedService breedService) {
        this.breedService = breedService;
    }

    @Override
    public List<Breed> breedsAll() {
        log.info("Fetching all breeds.");
        List<Breed> breeds = breedService.breedsAll();
        log.info("Fetched {} breeds successfully.", breeds.size());
        return breeds;
    }

    @Override
    public Breed breedById(String id) {
        log.info("Fetching breed by ID: {}.", id);
        Breed breed = breedService.breedById(id);

        if (breed != null) {
            log.info("Fetched breed by ID {}: {}", id, breed);
        } else {
            log.warn("Breed with ID {} not found.", id);
        }

        return breed;
    }

    @Override
    public List<Breed> breedSearch(String searchField, Integer attachImage) {
        log.info("Searching breeds with searchField: {} and attachImage: {}.", searchField, attachImage);
        List<Breed> breeds = breedService.breedSearch(searchField, attachImage);
        log.info("Found {} breeds matching the search.", breeds.size());
        return breeds;
    }
}