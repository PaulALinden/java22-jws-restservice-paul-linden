package com.example.restservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/*This is a record class representing the Content entity.
It contains two fields - id and content - which are annotated with Jakarta Bean Validation annotations.
The @NotNull annotation on the id field indicates that it must not be null, while the @NotBlank annotation on the content field
specifies that it must not be blank.
The record class is a concise way to declare a class with immutable properties, and it automatically generates a constructor,
equals(), hashCode(), and toString() methods.
This class is used by the ContentCollectionRepo class to represent the Content objects in the collection.*/

public record Content(
        @NotNull
        Integer id,
        @NotBlank(message = "must not be blank")
        String content) {

}
