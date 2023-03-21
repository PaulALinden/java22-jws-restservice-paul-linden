package com.example.restservice.model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class JsonManager {
    private final ArrayList<Integer> idList = new ArrayList<>();
    private final ArrayList<String> contentList = new ArrayList<>();

    public void addJson() throws IOException, ParseException {
        Object data = new JSONParser().parse(new FileReader("C:\\Users\\linde\\Downloads\\rest-service\\rest-service\\src\\main\\java\\com\\example\\restservice\\data.json"));
        JSONArray jsonData = (JSONArray) data;
        for(Object obj : jsonData) {
            JSONObject jsonObject = (JSONObject) obj;
            String content = (String) jsonObject.get("content");

            String idString = (String) jsonObject.get("id");
            int id = Integer.parseInt(idString);

            idList.add(id);
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
