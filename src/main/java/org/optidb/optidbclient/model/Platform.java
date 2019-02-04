package org.optidb.optidbclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonDeserialize(as = Platform.class)
public class Platform implements Serializable {
    //@JsonProperty("name")
    private String name;
    //@JsonProperty("currentVersion")
    private String currentVersion;

    public Platform(String name) {
        this.name = name;
        this.currentVersion = this.version();
    }

    public Platform(String name, String curr) {
        this.name = name;
        this.currentVersion = curr;
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