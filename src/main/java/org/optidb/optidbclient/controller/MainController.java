package org.optidb.optidbclient.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.optidb.optidbclient.model.Platform;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping({"/", "/liste"})
    public String liste(Model model) {

        //model.addAttribute("liste", platforms);
        model.addAttribute("liste",getMessage());
        return "listePlateformes";
    }

    @GetMapping({"/home"})
    public String home(Model model) {
        return "home";
    }

    private List<Platform> getMessage()
    {
        List<Platform> liste = new ArrayList<>();
        String URL_LISTE = "http://192.168.33.10:8080/list";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_LISTE,String.class);
        //String plt = "[{\"name\":\"mongo\",\"currentVersion\":\"3.4.19\"},{\"name\":\"mysql\",\"currentVersion\":\"8.0.14\"}]";
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform(jsonObj.getString("name"),jsonObj.getString("currentVersion"));
                liste.add(obj);
            }

            //return root.getJSONObject(1).getString("name");
            //Platform obj = new Platform(result.getString("name"),result.getString("currentVersion"));
        }
        catch (JSONException e) {
        }
        return liste;

    }
}