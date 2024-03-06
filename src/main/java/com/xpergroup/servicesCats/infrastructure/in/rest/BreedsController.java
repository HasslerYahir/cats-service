package com.xpergroup.servicesCats.infrastructure.in.rest;

import com.xpergroup.servicesCats.application.in.BreedAllUseCase;
import com.xpergroup.servicesCats.application.in.BreedByIdUseCase;
import com.xpergroup.servicesCats.application.in.BreedSearchUseCase;
import com.xpergroup.servicesCats.application.mappers.mapper.BreadServiceApplicationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/breeds")
public class BreedsController {

    private final BreedAllUseCase breedAllUseCase;
    private final BreedByIdUseCase breedByIdUseCase;
    private final BreedSearchUseCase breedSearchUseCase;
    private final Logger log = LoggerFactory.getLogger(BreedsController.class);

    public BreedsController(BreedAllUseCase breedAllUseCase, BreedByIdUseCase breedByIdUseCase, BreedSearchUseCase breedSearchUseCase) {
        this.breedAllUseCase = breedAllUseCase;
        this.breedByIdUseCase = breedByIdUseCase;
        this.breedSearchUseCase = breedSearchUseCase;
        log.info("BreedsController initialized with UseCases.");
    }

    @GetMapping
    public ResponseEntity<?> breedsAll() {
        log.info("Received request for all breeds.");
        return ResponseEntity.ofNullable(breedAllUseCase.breedsAll().stream().map(BreadServiceApplicationMapper::toDTO).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> breedById(@PathVariable String id) {
        log.info("Received request for breed by ID: {}.", id);
        return ResponseEntity.ofNullable(Optional.ofNullable(breedByIdUseCase.breedById(id)).map(BreadServiceApplicationMapper::toDTO).get());
    }

    @GetMapping("/search")
    public ResponseEntity<?> breedQueryParams(@RequestParam(name = "query") String query, @RequestParam(name = "attach_image", required = false) Integer attachImage) {
        log.info("Received request for breed search with query: {} and attachImage: {}.", query, attachImage);
        return ResponseEntity.ofNullable(breedSearchUseCase.breedSearch(query, attachImage).stream().map(BreadServiceApplicationMapper::toDTO).toList());
    }
}