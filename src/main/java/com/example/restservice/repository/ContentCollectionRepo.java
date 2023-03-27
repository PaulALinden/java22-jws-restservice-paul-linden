package com.example.restservice.repository;

import com.example.restservice.model.Content;
import com.example.restservice.model.JsonManager;
import jakarta.annotation.PostConstruct;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.IOException;
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
    private void init() throws IOException, ParseException { // add content to list
        JsonManager jsonManager = new JsonManager();
        jsonManager.addJson();

        for (int i = 0; i <jsonManager.getIdList().size(); i++) {
            int a = jsonManager.getIdList().get(i);
            String b = jsonManager.getContentList().get(i);

            Content c = new Content(a, b);
            contentList.add(c);
        }

    }

    public void save(Content content){
        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);
    }

    public boolean existsById(Integer id) {
        return contentList.stream().anyMatch(c -> Objects.equals(c.id(), id));
    }

    public void delete(Integer id) {
        contentList.removeIf(c -> c.id().equals(id));
    }

}
