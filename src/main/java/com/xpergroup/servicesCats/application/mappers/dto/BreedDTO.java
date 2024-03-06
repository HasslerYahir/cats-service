package com.xpergroup.servicesCats.application.mappers.dto;

public class BreedDTO {
    private String id;
    private String name;

    public BreedDTO() {
    }

    public BreedDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
