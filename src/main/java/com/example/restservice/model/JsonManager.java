package com.example.restservice.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* This is a utility class that manages the JSON data used by the ContentCollectionRepo class.
It reads data from a JSON file and converts it into two ArrayLists - one for the ids and another for the content.
The addJson() method is responsible for parsing the JSON data, extracting the necessary information, and populating the ArrayLists.
The getIdList() and getContentList() methods return the populated ArrayLists.
This class is used by the ContentCollectionRepo class in its init() method to initialize the contentList with Content objects.
Note that this class is not a Spring-managed bean and is not annotated with any Spring annotations.*/

public class JsonManager {

    private final ArrayList<Integer> idList = new ArrayList<>();
    private final ArrayList<String> contentList = new ArrayList<>();

    public void addJson() throws IOException, ParseException {

        Object data = new JSONParser().parse(new FileReader("rest-service/src/main/java/com/example/restservice/data/data.json"));
        JSONArray jsonData = (JSONArray) data;
        for(Object obj : jsonData) {
            JSONObject jsonObject = (JSONObject) obj;
            String content = (String) jsonObject.get("content");

            Long id = (Long) jsonObject.get("id");
            int intValue = id.intValue();

            idList.add(intValue);
            contentList.add(content);
        }
    }

    public ArrayList<Integer> getIdList() {
        return idList;
    }
    public ArrayList<String> getContentList() {
        return contentList;
    }
}
