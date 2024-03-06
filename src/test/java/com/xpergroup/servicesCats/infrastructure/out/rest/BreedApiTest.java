package com.xpergroup.servicesCats.infrastructure.out.rest;

import com.xpergroup.servicesCats.application.mappers.dto.BreedDTO;
import com.xpergroup.servicesCats.domain.models.Breed;
import com.xpergroup.servicesCats.utils.RestTemplateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@SpringBootTest
public class BreedApiTest {
    private RestTemplateUtils restTemplateUtils;
    private BreedApi breedApi;

    @BeforeEach
    public void setUp() {
        restTemplateUtils = mock(RestTemplateUtils.class);
        breedApi = new BreedApi(restTemplateUtils);
    }

    @Test
    public void testBreedsSuccess() {

        when(restTemplateUtils.getAll(anyString(), eq(BreedDTO[].class), any()))
          .thenReturn(new ResponseEntity<>(new BreedDTO[]{new BreedDTO("1", "Siamese")}, HttpStatus.OK));

        List<Breed> result = breedApi.breeds();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getName()).isEqualTo("Siamese");
    }

    @Test
    public void testBreedsError() {
        when(restTemplateUtils.getAll(anyString(), eq(BreedDTO[].class), any()))
          .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        List<Breed> result = breedApi.breeds();

        assertThat(result).isEmpty();
    }

    @Test
    public void testBreedByIdSuccess() {
        when(restTemplateUtils.getWithPathVariable(anyString(), eq(BreedDTO.class), any(), anyMap()))
          .thenReturn(new ResponseEntity<>(new BreedDTO("1", "Siamese"), HttpStatus.OK));

        Breed result = breedApi.breedById("1");

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo("1");
        assertThat(result.getName()).isEqualTo("Siamese");
    }

    @Test
    public void testBreedByIdError() {
        when(restTemplateUtils.getWithPathVariable(anyString(), eq(BreedDTO.class), any(), anyMap()))
          .thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        Breed result = breedApi.breedById("1");

        assertThat(result.getId()).isNull();
    }

    @Test
    public void testBreedsByQuerySuccess() {
        when(restTemplateUtils.getWithQueryParams(anyString(), eq(BreedDTO[].class), any(), anyMap()))
          .thenReturn(new ResponseEntity<>(new BreedDTO[]{new BreedDTO("1", "Siamese")}, HttpStatus.OK));

        List<Breed> result = breedApi.breedsByQuery("Siamese", 1);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getName()).isEqualTo("Siamese");
    }

}
