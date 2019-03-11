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
    private String typeModel;
    private String logo;
    private String website;
    private String developer;
    private String initialRelease;
    private String license;
    private String requetage;

    public Platform(String name) {
        this.name = name;
        this.currentVersion = this.version();
    }

    public Platform(String name, String curr) {
        this.name = name;
        this.currentVersion = curr;
    }

    public Platform(PlatformBuilder builder) {
        this.name = builder.name;
        this.currentVersion = builder.currentVersion;
        this.description = builder.description;
        this.typeModel = builder.typeModel;
        this.logo = builder.logo;
        this.website = builder.website;
        this.developer = builder.developer;
        this.initialRelease = builder.initialRelease;
        this.license = builder.license;
        this.requetage = builder.requetage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getDescription() {
        return description;
    }

    public String getShortDescription() {
        int longueur = description.length();
        if(longueur > 180) longueur = 180;
        return description.substring(0,longueur)+"...";
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(String typeModel) {
        this.typeModel = typeModel;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getInitialRelease() {
        return initialRelease;
    }

    public void setInitialRelease(String initialRelease) {
        this.initialRelease = initialRelease;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getRequetage() {
        return requetage;
    }

    public void setRequetage(String requetage) {
        this.requetage = requetage;
    }

    public static class PlatformBuilder {
        private final String name;
        private final String currentVersion;
        private String description;
        private String typeModel;
        private String logo;
        private String website;
        private String developer;
        private String initialRelease;
        private String license;
        private String requetage;

        public PlatformBuilder(String name, String currentVersion) {
            this.name = name;
            this.currentVersion = currentVersion;
        }

        public PlatformBuilder description(String desc) {
            this.description = desc;
            return this;
        }

        public PlatformBuilder typeModel(String typeModel) {
            this.typeModel = typeModel;
            return this;
        }

        public PlatformBuilder logo(String logo) {
            this.logo = logo;
            return this;
        }

        public PlatformBuilder website(String website) {
            this.website = website;
            return this;
        }

        public PlatformBuilder developer(String developer) {
            this.developer = developer;
            return this;
        }

        public PlatformBuilder initialRelease(String initialRelease) {
            this.initialRelease = initialRelease;
            return this;
        }

        public PlatformBuilder license(String license) {
            this.license = license;
            return this;
        }

        public PlatformBuilder requetage(String requetage) {
            this.requetage = requetage;
            return this;
        }


        public Platform build() {
            return new Platform(this);
        }

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
                logger.log(Level.SEVERE,"ERROR : Read version");
            }
            return line;
        }
        catch (IOException e)
        {
            logger.log(Level.WARNING,e.toString());
            return null;
        } catch (InterruptedException e) {
            logger.log(Level.WARNING,e.toString());
            Thread.currentThread().interrupt();
            return null;
        }
    }
}