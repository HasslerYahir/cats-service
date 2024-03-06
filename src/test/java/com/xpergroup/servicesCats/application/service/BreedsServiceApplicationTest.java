package com.xpergroup.servicesCats.application.service;

import com.xpergroup.servicesCats.domain.models.Breed;
import com.xpergroup.servicesCats.domain.services.BreedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BreedsServiceApplicationTest {


    private BreedService breedService;


    private BreedsServiceApplication breedsServiceApplication;

    @BeforeEach
    public void setUp() {
        breedService = mock(BreedService.class);
        breedsServiceApplication = new BreedsServiceApplication(breedService);
    }

    @Test
    public void testBreedsAllSuccess() {
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Persian"));
        when(breedService.breedsAll()).thenReturn(expectedBreeds);

        List<Breed> result = breedsServiceApplication.breedsAll();

        assertThat(result).isEqualTo(expectedBreeds);
        verify(breedService, times(1)).breedsAll();
    }

    @Test
    public void testBreedByIdExistingId() {
        String id = "1";
        Breed expectedBreed = new Breed(id, "Siamese");
        when(breedService.breedById(id)).thenReturn(expectedBreed);

        Breed result = breedsServiceApplication.breedById(id);

        assertThat(result).isEqualTo(expectedBreed);
        verify(breedService, times(1)).breedById(id);
    }

    @Test
    public void testBreedByIdNonExistingId() {
        String id = "nonexistent";
        when(breedService.breedById(id)).thenReturn(null);

        Breed result = breedsServiceApplication.breedById(id);

        assertThat(result).isNull();
        verify(breedService, times(1)).breedById(id);
    }

    @Test
    public void testBreedSearchSuccess() {
        String searchField = "Siamese";
        Integer attachImage = 1;
        List<Breed> expectedBreeds = Arrays.asList(new Breed("1", "Siamese"), new Breed("2", "Balinese"));
        when(breedService.breedSearch(searchField, attachImage)).thenReturn(expectedBreeds);

        List<Breed> result = breedsServiceApplication.breedSearch(searchField, attachImage);

        assertThat(result).isEqualTo(expectedBreeds);
        verify(breedService, times(1)).breedSearch(searchField, attachImage);
    }
}
