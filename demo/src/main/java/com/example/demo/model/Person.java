package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public class Person {

    private final UUID id;
    @NotBlank
    private final String name;
    private final String photoURL;



    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name, @JsonProperty("photoURL") String photoURL) {
        this.id = id;
        this.name = name;
        this.photoURL = photoURL;
    }

    public String getPhoto() {
        return photoURL;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
