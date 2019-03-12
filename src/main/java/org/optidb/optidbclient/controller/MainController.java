package org.optidb.optidbclient.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.optidb.optidbclient.model.Platform;
import org.optidb.optidbclient.model.Resultat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    @GetMapping({"/liste"})
    public String liste(Model model) {
        model.addAttribute("liste",this.getAllPlatforms());
        return "listePlateformes";
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }

    @GetMapping({"/simple"})
    public String simple(Model model) {
        List<Platform> liste = new ArrayList<>();
        String URL_LISTE = "http://192.168.33.10:8080/list";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_LISTE,String.class);
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform(jsonObj.getString("name"),jsonObj.getString("currentVersion"),jsonObj.getString("description"),
                        jsonObj.getString("typeModel"),jsonObj.getString("logo"), jsonObj.getString("website"),
                        jsonObj.getString("developer"),jsonObj.getString("initialRelease"),jsonObj.getString("license"),
                        jsonObj.getString("requetage"));
                liste.add(obj);
            }
        }
        catch (JSONException e) {
        }

        model.addAttribute("liste",liste);
        model.addAttribute("listePlateformes",plt);
        return "comparatif_simple";
    }

    @GetMapping({"/platform/{id}"})
    public String platformVersion(Model model, @PathVariable(value="id") final String name){
        model.addAttribute("platform",this.getResultat(name));
        return "platform_infos";
    }
    
    @GetMapping({"/infos/{id}"})
    public String infos(Model model, @PathVariable(value="id") final String name){
        Platform platforme = null;
        List<Platform> liste = new ArrayList<>();
        String URL_LISTE = "http://192.168.33.10:8080/list";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_LISTE,String.class);
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform(jsonObj.getString("name"),jsonObj.getString("currentVersion"),jsonObj.getString("description"),
                        jsonObj.getString("typeModel"),jsonObj.getString("logo"), jsonObj.getString("website"),
                        jsonObj.getString("developer"),jsonObj.getString("initialRelease"),jsonObj.getString("license"),
                        jsonObj.getString("requetage"));
                liste.add(obj);
            }
            platforme = getPlateformeDescriptif(liste,name);
        }
        catch (JSONException e) {
        }
        model.addAttribute("platform",platforme);
        return "platform_descriptif";
    }

    public Platform getPlateformeDescriptif(List<Platform> liste, String name) {
        Platform plateforme = null;
        int i=0; boolean trouve=false;
        while(i<liste.size() && !trouve) {
            Platform it = liste.get(i);
            if(it.getName().toLowerCase().equals(name)) trouve=true;
        }
        if(trouve) plateforme = liste.get(i);
        return plateforme;
    }



    public List<Platform> getAllPlatforms() {
        List<Platform> liste = new ArrayList<>();
        String URL_LISTE = "http://192.168.33.10:8080/list";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_LISTE,String.class);
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform(jsonObj.getString("name"),jsonObj.getString("currentVersion"));
                liste.add(obj);
            }
        }
        catch (JSONException e) {
        }
        return liste;
    }

    public Resultat getResultat(String name) {
        String URL_PLATEFORME = "http://192.168.33.10:8080/platform?name="+name;
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_PLATEFORME,String.class);
        Resultat res = null;
        try {
            JSONObject obj = new JSONObject(plt);
            res = new Resultat(obj.getString("platformName"),obj.getInt("tempsCreate"),obj.getInt("tempsInsert"),obj.getInt("tempsDelete"));
        }
        catch (JSONException e) {

        }
        return res;
    }
}