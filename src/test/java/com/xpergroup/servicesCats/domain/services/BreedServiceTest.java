package com.xpergroup.servicesCats.domain.services;

import com.xpergroup.servicesCats.application.out.rest.BreedApiService;
import com.xpergroup.servicesCats.domain.models.Breed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BreedServiceTest {


    private BreedApiService breedApiService;

    private BreedService breedService;

    @BeforeEach
    public void setUp() {
        breedApiService = mock(BreedApiService.class);
        breedService = new BreedService(breedApiService);
    }

    @Test
    public void testBreedsAllSuccess() {
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Persian"));
        when(breedApiService.breeds()).thenReturn(expectedBreeds);

        List<Breed> result = breedService.breedsAll();

        assertThat(result).isEqualTo(expectedBreeds);
        verify(breedApiService, times(1)).breeds();
    }

    @Test
    public void testBreedById_ExistingId() {
        String id = "1";
        Breed expectedBreed = new Breed(id, "Siamese");
        when(breedApiService.breedById(id)).thenReturn(expectedBreed);

        Breed result = breedService.breedById(id);

        assertThat(result).isEqualTo(expectedBreed);
        verify(breedApiService, times(1)).breedById(id);
    }

    @Test
    public void testBreedById_NonExistingId() {
        String id = "nonexistent";
        when(breedApiService.breedById(id)).thenReturn(null);

        Breed result = breedService.breedById(id);

        assertThat(result).isNull();
        verify(breedApiService, times(1)).breedById(id);
    }

    @Test
    public void testBreedSearch_Success() {
        String searchField = "Siamese";
        Integer attachImage = 1;
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Balinese"));
        when(breedApiService.breedsByQuery(searchField, attachImage)).thenReturn(expectedBreeds);

        List<Breed> result = breedService.breedSearch(searchField, attachImage);

        assertThat(result).isEqualTo(expectedBreeds);
        verify(breedApiService, times(1)).breedsByQuery(searchField, attachImage);
    }
}