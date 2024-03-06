package com.xpergroup.servicesCats.application.out.rest;

import com.xpergroup.servicesCats.domain.models.Breed;

import java.util.List;

public interface BreedApiService {
    List<Breed> breeds();

    Breed breedById(String id);

    List<Breed> breedsByQuery(String breedSearch, Integer referenceImage);
}
