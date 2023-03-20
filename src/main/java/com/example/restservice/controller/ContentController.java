package com.example.restservice.controller;

import com.example.restservice.model.Content;
import com.example.restservice.repository.ContentCollectionRepo;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController // Handel request mapping
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {
//Accepting req and return response

    private final ContentCollectionRepo repo;

    public ContentController(ContentCollectionRepo repo) {
        this.repo = repo;
    }

    @GetMapping("") //Lists all content
    public List<Content>findAll() {
        return repo.findAll();
    }

    //Create, Read, Update, Delete- C.R.U.D || filter-sorting

    //Method for getting by certain id
    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id){
        return repo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED) //Creates 201...
    @PostMapping("")
    public void create(@Valid @RequestBody Content content){
        repo.save(content);
    }


    //http://localhost:8080/api/content/2
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
        repo.delete(id);
    }
}


