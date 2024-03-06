package com.xpergroup.servicesCats.application.mappers.mapper;

import com.xpergroup.servicesCats.application.mappers.dto.BreedDTO;
import com.xpergroup.servicesCats.domain.models.Breed;

public class BreadServiceApplicationMapper {
    private BreadServiceApplicationMapper() {
    }
    public static BreedDTO toDTO(Breed breed){
        return new BreedDTO(breed.getId(), breed.getName());
    }
}
