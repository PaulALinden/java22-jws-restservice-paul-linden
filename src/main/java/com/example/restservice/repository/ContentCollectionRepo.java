package com.example.restservice.repository;

import com.example.restservice.model.Content;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class ContentCollectionRepo {
    private final List<Content> contentList = new ArrayList<>();

    public ContentCollectionRepo(){}

    public List<Content> findAll(){

        return contentList;
    }

    public Optional<Content> findById(Integer id){ //throws null
        return contentList.stream().filter(c -> Objects.equals(c.id(), id)).findFirst();
    }

    @PostConstruct
    private void init(){ // add content to list
        Content c = new Content(1, "Paul");
        contentList.add(c);
    }

    public void save(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().filter(c -> Objects.equals(c.id(), id)).count() == 1;
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

}
