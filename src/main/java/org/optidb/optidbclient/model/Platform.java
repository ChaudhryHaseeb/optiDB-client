package org.optidb.optidbclient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Platform implements Serializable {
    private String name;
    private String currentVersion;
    private String description;
    private String logo;
    private String typeModele;
    private String requetage;
    private String site;

    public Platform(String name, String description, String logo, String typeModele, String site) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.typeModele = typeModele;
        this.site = site;

    }

    public String getRequetage() {
        return requetage;
    }

    public void setRequetage(String requetage) {
        this.requetage = requetage;
    }

    public Platform(String name) {
        this.name = name;
        this.currentVersion = this.version();
    }

    public Platform(String name, String curr) {
        this.name = name;
        this.currentVersion = curr;
    }

    public Platform(String name, String currentVersion, String description, String logo, String typeModele, String requetage, String site) {
        this.name = name;
        this.currentVersion = currentVersion;
        this.description = description;
        this.logo = logo;
        this.typeModele = typeModele;
        this.requetage = requetage;
        this.site = site;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getDescription() {
        int longueur = description.length();
        if(longueur > 180) longueur = 180;
        return description.substring(0,longueur)+"...";

        //return description.substring(0,description.indexOf(" ",longueur))+"...";
        //return description.indexOf(mots[nbmots])+" mots";
        //return description.substring(0,description.indexOf(mots[nbmots]))+"...";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTypeModele() {
        return typeModele;
    }

    public void setTypeModele(String typeModele) {
        this.typeModele = typeModele;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public String version()
    {
        Logger logger = Logger.getLogger("logger");
        try
        {
            // Création de la commande
            Process process = Runtime.getRuntime().exec("mvn -v");
            // On récup le résultat
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            int exitVal = process.waitFor();
            if (exitVal != 0)
            {
                logger.log(Level.INFO,"ERROR : Read version");
            }
            return line;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return null;
        }
    }


}