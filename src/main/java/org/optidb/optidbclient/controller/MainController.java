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
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class MainController {

    private static Logger myLog = Logger.getLogger("WarningLogging");

    @GetMapping({"/liste"})
    public String liste(Model model)
    {
        model.addAttribute("liste",this.getAllPlatforms());
        return "listePlateformes";
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        return "home";
    }


    @GetMapping({"/platform/{id}/{col}/{line}"})
    public String platformVersion(Model model,
                                  @PathVariable(value="id") final String name,
                                  @PathVariable(value="col") final int nbCol,
                                  @PathVariable(value="line") final int nbLine)
    {
        model.addAttribute("platform",this.getResultat(name,nbCol,nbLine));
        return "platform_infos";
    }

    @GetMapping({"/historique/{name}"})
    public String platformVersion(Model model, @PathVariable(value="name") final String name)
    {
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


    @GetMapping("/historique")
    public String historique(Model model)
    {
        List<Object> liste = new ArrayList<>();
        String histo_liste = "http://192.168.33.10:8080/media";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(histo_liste,String.class);

        try
        {
            JSONArray json  = new JSONArray(plt);
            for(int i=0;i<json.length();i++)
            {
                liste.add(json.get(i));
            }
        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        model.addAttribute("liste",liste);
        return "historique";
    }

    private   List<Platform> getAllPlatforms() {
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
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        return liste;
    }

    private Resultat getResultat(String name)
    {
        String URL_PLATEFORME = "http://192.168.33.10:8080/historique?name="+name;
        return this.readJson(URL_PLATEFORME);
    }

    private Resultat getResultat(String name, int nbCol, int nbLine)
    {
        String URL_PLATEFORME = "http://192.168.33.10:8080/platform?name="+name+"&col="+nbCol+"&line="+nbLine;
        return this.readJson(URL_PLATEFORME);
    }

    private Resultat readJson(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(url,String.class);
        Resultat res = null;
        ArrayList listeInsert;
        try
        {
            JSONObject obj = new JSONObject(plt);
            String liste = obj.getString("listeInsert");
            String s = "" ;
            for(int i=1;i<liste.length()-1;i++)
            {
                s = s+(liste.charAt(i));
            }
            listeInsert = new ArrayList(Arrays.asList(s.split(",")));
            res = new Resultat(obj.getString("platformName"),obj.getInt("nbCol"),obj.getInt("nbLine")
                    ,obj.getInt("tempsCreate"),listeInsert,obj.getInt("tempsUpdate"),
                    obj.getInt("tempsSelect"),obj.getInt("tempsSelectAll"),obj.getInt("tempsAlter")
                    ,obj.getInt("tempsDelete"),obj.getInt("tempsDrop"));
        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        return res;

    }
}