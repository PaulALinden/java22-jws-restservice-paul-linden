package com.example.restservice.controller;

import com.example.restservice.model.Content;
import com.example.restservice.repository.ContentCollectionRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentCollectionRepo repo;

    public ContentController(ContentCollectionRepo repo) {
        this.repo = repo;
    }

    @GetMapping("")
    public List<Content>findAll() {

        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repo.save(content);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@Valid @RequestBody Content content, @PathVariable Integer id){

        if (!repo.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Content not found!");
        }
        repo.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found!");
        }
        repo.delete(id);
    }
}


