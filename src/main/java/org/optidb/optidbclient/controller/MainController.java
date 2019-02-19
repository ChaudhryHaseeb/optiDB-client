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
    public String liste(Model model) {
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

    public   List<Platform> getAllPlatforms() {
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

    public Resultat getResultat(String name, int nbCol, int nbLine)
    {
        String URL_PLATEFORME = "http://192.168.33.10:8080/platform?name="+name+"&col="+nbCol+"&line="+nbLine;
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(URL_PLATEFORME,String.class);
        Resultat res = null;
        ArrayList listeInsert;
        try
        {
            JSONObject obj = new JSONObject(plt);
            String liste = obj.getString("listeInsert");
            System.out.println(liste);
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