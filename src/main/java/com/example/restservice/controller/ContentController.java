package com.example.restservice.controller;

import com.example.restservice.model.Content;
import com.example.restservice.repository.ContentCollectionRepo;
import jakarta.validation.Valid;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

/*
 * This class represents a REST controller for managing Content resources.
 * It handles incoming requests related to Content, and maps them to corresponding methods
 * in the ContentCollectionRepo repository for data manipulation.
 * The class includes methods for retrieving all Content, finding Content by id,
 * creating new Content, updating existing Content, and deleting Content.
 * Requests are validated with the help of the Content model and exception handling is implemented
 * to handle invalid requests and data access errors.
 */

// Mark this class as a controller and set the base URL mapping for all endpoints
@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    // Define a private variable to hold an instance of ContentCollectionRepo
    private final ContentCollectionRepo contentRepo;

    // Constructor that accepts an instance of ContentCollectionRepo as a parameter
    public ContentController(ContentCollectionRepo contentRepo) {
        this.contentRepo = contentRepo;
    }

    // Define a GET endpoint for retrieving all content
    @GetMapping("")
    public List<Content>findAll() {
        return contentRepo.findAll();
    }

    // Define a GET endpoint for retrieving content by ID
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return contentRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    // Define a POST endpoint for creating new content
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content) throws IOException, ParseException {
        contentRepo.save(content);
    }

    // Define a PUT endpoint for updating existing content by ID
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id) throws IOException, ParseException {

        if (contentRepo.existsById(id)){
            contentRepo.save(content);
        }
        else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }

    }

    // Define a DELETE endpoint for deleting content by ID
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws IOException {

        if (!contentRepo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        contentRepo.delete(id);
    }

}


