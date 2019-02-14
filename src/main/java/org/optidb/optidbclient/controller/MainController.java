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

    @GetMapping({"/platform/{id}/{col}"})
    public String platformVersion(Model model, @PathVariable(value="id") final String name,
                                  @PathVariable(value="col") final int col)
    {
            model.addAttribute("platform",this.getResultat(name,col));
        return "platform_infos";
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

    public Resultat getResultat(String name, int col)
    {
        String URL_PLATEFORME = "http://192.168.33.10:8080/platform?name="+name+"&col="+col;
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_PLATEFORME,String.class);
        Resultat res = null;
        try
        {
            JSONObject obj = new JSONObject(plt);
            res = new Resultat(obj.getString("platformName"),obj.getInt("tempsCreate"),obj.getInt("tempsInsert")
                    ,obj.getInt("tempsUpdate"),obj.getInt("tempsSelect"),obj.getInt("tempsSelectAll"),
                    obj.getInt("tempsAlter"),obj.getInt("tempsDelete"),obj.getInt("tempsDrop"));
        }
        catch (JSONException e)
        {

        }
        return res;
    }
}