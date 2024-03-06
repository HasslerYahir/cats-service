package com.xpergroup.servicesCats.infrastructure.out.rest.mappers;

import com.xpergroup.servicesCats.application.mappers.dto.BreedDTO;
import com.xpergroup.servicesCats.domain.models.Breed;

public class BreedMapper {
    private BreedMapper() {
    }

    public static Breed toModel(BreedDTO breedDTO){
        return new Breed(breedDTO.getId(), breedDTO.getName());
    }
}
