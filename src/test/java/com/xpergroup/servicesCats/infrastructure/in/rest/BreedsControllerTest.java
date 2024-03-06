package com.xpergroup.servicesCats.infrastructure.in.rest;


import com.xpergroup.servicesCats.application.in.BreedAllUseCase;
import com.xpergroup.servicesCats.application.in.BreedByIdUseCase;
import com.xpergroup.servicesCats.application.in.BreedSearchUseCase;
import com.xpergroup.servicesCats.domain.models.Breed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BreedsControllerTest {


    private BreedAllUseCase breedAllUseCase;
    private BreedByIdUseCase breedByIdUseCase;
    private BreedSearchUseCase breedSearchUseCase;
    private BreedsController breedsController;

    @BeforeEach
    public void setUp() {
        breedAllUseCase = mock(BreedAllUseCase.class);
        breedByIdUseCase = mock(BreedByIdUseCase.class);
        breedSearchUseCase = mock(BreedSearchUseCase.class);
        breedsController = new BreedsController(breedAllUseCase, breedByIdUseCase, breedSearchUseCase);
    }

    @Test
    public void testBreedsAll_Success() {
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Persian"));
        when(breedAllUseCase.breedsAll()).thenReturn(expectedBreeds);

        ResponseEntity<?> result = breedsController.breedsAll();
        assertThat(result.getBody()).isNotNull();
        verify(breedAllUseCase, times(1)).breedsAll();
    }

    @Test
    public void testBreedById_ExistingId() {
        String id = "1";
        Breed expectedBreed = new Breed(id, "Siamese");
        when(breedByIdUseCase.breedById(id)).thenReturn(expectedBreed);

        ResponseEntity<?> result = breedsController.breedById(id);

        assertThat(result.getBody()).isNotNull();
        verify(breedByIdUseCase, times(1)).breedById(id);
    }


    @Test
    public void testBreedQueryParams_Success() {
        String query = "Siamese";
        Integer attachImage = 1;
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Balinese"));
        when(breedSearchUseCase.breedSearch(query, attachImage)).thenReturn(expectedBreeds);

        ResponseEntity<?> result = breedsController.breedQueryParams(query, attachImage);

        assertThat(result.getBody()).isNotNull();
        verify(breedSearchUseCase, times(1)).breedSearch(query, attachImage);
    }
}