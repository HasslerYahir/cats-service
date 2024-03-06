package com.xpergroup.servicesCats.application.in;

import com.xpergroup.servicesCats.domain.models.Breed;

import java.util.List;

public interface BreedSearchUseCase {
    List<Breed> breedSearch(String searchField,Integer attachImage);
}
