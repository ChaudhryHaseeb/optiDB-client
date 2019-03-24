package org.optidb.optidbclient.controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.optidb.optidbclient.model.Platform;
import org.optidb.optidbclient.model.Resultat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;


@Controller
public class MainController {

    private static Logger myLog = Logger.getLogger("WarningLogging");
    private String urlListe = "http://192.168.33.10:8080/list";
    private String varPlatform = "platform";
    private String currentVersion = "currentVersion";
    private Resultat res = null;
    private ArrayList listeInsert;

    @GetMapping({"/liste"})
    public String liste(Model model)
    {
        String varListePlateformes = "listePlateformes";
        model.addAttribute(varListePlateformes,this.getAllPlatforms());
        return varListePlateformes;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model)
    {
        return "home";
    }


    @GetMapping("/formMultiPlatform")
    public String formMultiPlatform(Model model)
    {
        return "formMultiPlatform";
    }


    @GetMapping({"/platform/{id}/{col}/{line}/{cle}"})
    public String platformVersion(Model model,
                                  @PathVariable(value="id") final String name,
                                  @PathVariable(value="col") final int nbCol,
                                  @PathVariable(value="line") final int nbLine,
                                  @PathVariable(value="cle") final int cle)
    {
        model.addAttribute(varPlatform,this.getResultat(name,nbCol,nbLine,cle));
        return "platform_infos";
    }

    @GetMapping({"/historique/{name}"})
    public String platformVersion(Model model, @PathVariable(value="name") final String name)
    {
        model.addAttribute(varPlatform,this.getResultat(name));
        return "platform_infos";
    }

    @GetMapping({"/historique/{name1}/{name2}"})
    public String platformVersion(Model model, @PathVariable(value="name1") final String name1, @PathVariable(value="name2") final String name2)
    {
        model.addAttribute("platform1",this.getResultat(name1));
        model.addAttribute("platform2",this.getResultat(name2));
        return "platform_compare_infos";
    }

    @PostMapping(value = "/compareHistorique")
    public ModelAndView compareHisto(@RequestParam(required=false,name="compareHisto")String[] checkboxValue) {
        if (checkboxValue == null) {
            //throw new NullPointerException("Null");
            return new ModelAndView("redirect:/historique");
        }
        else {
            if(checkboxValue.length == 2) {
                return new ModelAndView("redirect:/historique/"+checkboxValue[0]+"/"+checkboxValue[1]);
            }
            return new ModelAndView("redirect:/historique");
        }

    }

    @GetMapping({"/simple"})
    public String simple(Model model) {
        List<Platform> liste = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(urlListe,String.class);
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform.PlatformBuilder(jsonObj.getString("name"),jsonObj.getString("currentVersion"))
                        .description(jsonObj.getString("description")).typeModel(jsonObj.getString("typeModel"))
                        .logo(jsonObj.getString("logo")).website(jsonObj.getString("website"))
                        .developer(jsonObj.getString("developer")).initialRelease(jsonObj.getString("initialRelease"))
                        .license(jsonObj.getString("license")).requetage(jsonObj.getString("requetage")).build();
                liste.add(obj);
            }
        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }

        model.addAttribute("liste",liste);
        return "comparatif_simple";
    }


    @GetMapping("/historique")
    public String historique(Model model)
    {
        List<Object> liste = new ArrayList<>();
        String histoListe = "http://192.168.33.10:8080/media";
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(histoListe,String.class);

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
        model.addAttribute("listeHistorique",liste);
        return "historique";
    }

    @GetMapping({"/infos/{id}"})
    public String infos(Model model, @PathVariable(value="id") final String name){
        Platform platforme = null;
        List<Platform> liste = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(urlListe,String.class);
        try {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++) {
                JSONObject jsonObj = root.getJSONObject(i);
                Platform obj = new Platform.PlatformBuilder(jsonObj.getString("name"),jsonObj.getString("currentVersion"))
                        .description(jsonObj.getString("description")).typeModel(jsonObj.getString("typeModel"))
                        .logo(jsonObj.getString("logo")).website(jsonObj.getString("website"))
                        .developer(jsonObj.getString("developer")).initialRelease(jsonObj.getString("initialRelease"))
                        .license(jsonObj.getString("license")).requetage(jsonObj.getString("requetage")).build();
                liste.add(obj);
            }
            for (Platform it : liste) {
                if (it.getName().equalsIgnoreCase(name)) {
                    platforme = it;
                    break;
                }
            }
        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        model.addAttribute("platform",platforme);
        model.addAttribute("liste",liste);
        return "platform_descriptif";
    }


    @GetMapping({"/compare/{db1}/{db2}/{col}/{line}/{cle}"})
    public String comparePlatforme(Model model,
                                  @PathVariable(value="db1") final String db1,
                                  @PathVariable(value="db2") final String db2,
                                  @PathVariable(value="col") final int col,
                                  @PathVariable(value="line") final int line,
                                  @PathVariable(value="cle") final int cle)
    {
        Resultat r1 = null;
        Resultat r2 = null;
        List<Resultat> listeResultat;
        listeResultat = this.getResultatCompare(db1,db2,col,line,cle);
        if(listeResultat != null)
        {
            r1 = listeResultat.get(0);
            r2 = listeResultat.get(1);
        }

        model.addAttribute("res1",r1);
        model.addAttribute("res2",r2);
        return "platform_comparaison";
    }

    private List<Platform> getAllPlatforms() {
        List<Platform> liste = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        String plt = restTemplate.getForObject(urlListe,String.class);
        try
        {
            JSONArray root = new JSONArray(plt);
            for(int i=0;i<root.length();i++)
            {
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


    /**
     *
     * @param name nom de la BD
     * @return le Resultat Json
     */
    private Resultat getResultat(String name)
    {
        String urlPlateforme = "http://192.168.33.10:8080/historique?name="+name;
        return this.readJson(urlPlateforme);
    }


    /**
     *
     * @param name nom de la BD
     * @param nbCol nombre de ligne
     * @param nbLine nombre de colonne
     * @param cle si clé primaire 1 ou 0
     * @return le Resultat Json
     */
    private Resultat getResultat(String name, int nbCol, int nbLine, int cle)
    {
        String urlPlateforme = "http://192.168.33.10:8080/platform?name="+name+"&col="+nbCol+"&line="+nbLine+"&cle="+cle;
        return this.readJson(urlPlateforme);
    }


    /**
     * Crée une instance de restTemplate et récupere le json
     * @param url l'url dans lequel il va chercher le resultat
     * @return le Json du serveur
     */
    private String getTemplateJson(String url)
    {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url,String.class);
    }


    /**
     *
     * @param url du serveur qui va chercher le json
     * @return le Resultat du json
     */
    private Resultat readJson(String url)
    {
        String resultatJson = getTemplateJson(url);
        return collectDateJson(resultatJson);
    }


    /**
     * Récupere la listeInsert de l'api
     * @param liste la clé du json
     * @return la liste de valeur qui à pour clé @liste
     */
    public ArrayList getListeInsert(String liste)
    {
        StringBuilder s = new StringBuilder();
        for(int i=1;i<liste.length()-1;i++)
        {
            s = s.append(liste.charAt(i));
        }
        return new ArrayList(Arrays.asList(s.toString().split(",")));
    }


    /**
     * Lis le fichier json pour un résultat
     * @param resJson le json en type String
     * @return le Resultat du json
     */
    private Resultat collectDateJson(String resJson)
    {
        try
        {
            JSONObject obj = new JSONObject(resJson);
            String liste = obj.getString("listeInsert");
            this.listeInsert = getListeInsert(liste);
            this.res = new Resultat(obj.getString("platformName"),obj.getInt("nbCol"),obj.getInt("nbLine")
                    ,obj.getInt("tempsCreate"),this.listeInsert,obj.getInt("tempsUpdate"),
                    obj.getInt("tempsSelect"),obj.getInt("tempsSelectAll"),obj.getInt("tempsAlter")
                    ,obj.getInt("tempsDelete"),obj.getInt("tempsDrop"));
        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        return this.res;
    }


    /**
     *
     * @param bd1 le nom de la 1er BD
     * @param bd2 le nom de la 2eme BD
     * @param nbCol nombre de ligne
     * @param nbLine nombre de colonne
     * @param cle si clé primaire 1 ou 0
     * @return une liste de Resultat du Json
     */
    private List<Resultat> getResultatCompare(String bd1, String bd2, int nbCol, int nbLine, int cle)
    {
        List<Resultat> listeRes = new ArrayList<>();
        String url = "http://192.168.33.10:8080/compare?bda="+bd1+"&bdb="+bd2+"&col="+nbCol+"&line="+nbLine+"&cle="+cle;
        String resultatJson = getTemplateJson(url);
        try
        {
            JSONObject root = new JSONObject(resultatJson);
            JSONArray listeResu = root.getJSONArray("listResu");

            for(int j=0;j<listeResu.length();j++)
            {
                JSONObject obj = listeResu.getJSONObject(j);
                String liste = obj.getString("listeInsert");
                this.listeInsert = getListeInsert(liste);
                this.res = new Resultat(obj.getString("platformName"),obj.getInt("nbCol"),obj.getInt("nbLine")
                        ,obj.getInt("tempsCreate"),this.listeInsert,obj.getInt("tempsUpdate"),
                        obj.getInt("tempsSelect"),obj.getInt("tempsSelectAll"),obj.getInt("tempsAlter")
                        ,obj.getInt("tempsDelete"),obj.getInt("tempsDrop"));
                listeRes.add(this.res);
            }
            return listeRes;

        }
        catch (JSONException e)
        {
            myLog.warning(e.toString());
        }
        return Collections.emptyList();
    }

}