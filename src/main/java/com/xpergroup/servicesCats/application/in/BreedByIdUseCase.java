package com.xpergroup.servicesCats.application.in;

import com.xpergroup.servicesCats.domain.models.Breed;

public interface BreedByIdUseCase {
    Breed breedById(String id);
}
