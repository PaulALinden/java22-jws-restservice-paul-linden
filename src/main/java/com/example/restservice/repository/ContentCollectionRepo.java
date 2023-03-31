package com.example.restservice.repository;

import com.example.restservice.model.Content;
import com.example.restservice.model.JsonManager;
import jakarta.annotation.PostConstruct;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/* This is a repository class for managing the collection of Content objects.
It contains methods for finding, saving, deleting, and checking if a Content object exists by its id.
It also has an init() method annotated with @PostConstruct, which is called after the bean is initialized,
and it reads the data from a JSON file and initializes the contentList with Content objects.
The save() and delete() methods also update the JSON file with the updated contentList.
The repository is annotated with @Repository, indicating that it is a Spring-managed bean.*/

@Repository
public class ContentCollectionRepo {
    private final List<Content> contentList = new CopyOnWriteArrayList<>();

    public ContentCollectionRepo(){}

    public List<Content> findAll(){
        return contentList;
    }

    public Optional<Content> findById(Integer id){
        return contentList.stream().filter(c -> Objects.equals(c.id(), id)).findFirst();
    }

    @PostConstruct
    private void init() throws IOException, ParseException {
        JsonManager jsonManager = new JsonManager();
        jsonManager.addJson();

        for (int i = 0; i <jsonManager.getIdList().size(); i++) {
            int id = jsonManager.getIdList().get(i);
            String content = jsonManager.getContentList().get(i);
            Content contentRecord = new Content(id, content);
            contentList.add(contentRecord);
        }
    }

    public void save(Content content) throws IOException {

        contentList.removeIf(c -> c.id().equals(content.id()));
        contentList.add(content);

        // Write the updated JSON data to the file
        JSONArray jsonArray = new JSONArray();
        for (Content c : contentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", c.id());
            jsonObject.put("content", c.content());
            jsonArray.add(jsonObject);
            System.out.println(jsonArray);
        }
        FileWriter fileWriter = new FileWriter("rest-service/src/main/java/com/example/restservice/data/data.json");
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }

    public boolean existsById(Integer id) {
        return contentList.stream().anyMatch(c -> Objects.equals(c.id(), id));
    }


    public void delete(Integer id) throws IOException {
        contentList.removeIf(c -> c.id().equals(id));

        // Write the updated JSON data to the file
        JSONArray jsonArray = new JSONArray();
        for (Content c : contentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", c.id());
            jsonObject.put("content", c.content());
            jsonArray.add(jsonObject);
            System.out.println(jsonArray);
        }
        FileWriter fileWriter = new FileWriter("rest-service/src/main/java/com/example/restservice/data/data.json");
        fileWriter.write(jsonArray.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
}
