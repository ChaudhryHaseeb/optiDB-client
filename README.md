[![Build Status](https://img.shields.io/travis/ChaudhryHaseeb/optiDB-client/master.svg?style=flat-square)](https://travis-ci.org/chaudhryHaseeb/optiDB-client)
[![License](https://img.shields.io/github/license/ChaudhryHaseeb/optiDB-client.svg?style=flat-square)](LICENSE)
[![Version](https://img.shields.io/github/tag/ChaudhryHaseeb/optiDB-client.svg?label=version&style=flat-square)](build.gradle)

[![SonarCloud Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=coverage)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![SonarCloud Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=sqale_index)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=code_smells)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=security_rating)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)

[![Waffle.io - Columns and their card count](https://badge.waffle.io/ChaudhryHaseeb/optiDB-client.svg?columns=all)](https://waffle.io/ChaudhryHaseeb/optiDB-client)


# OptiDB-Client - Projet Master

## Prérequis
* OptiDB-server, la partie serveur du projet - [Télécharger optiDB-server](https://github.com/DaJaime/optiDB-server)
* Virtualbox, outil de virtualisation - [Télécharger Virtualbox](https://www.virtualbox.org/wiki/Downloads)
* Vagrant, gestionnaire de machine virtuelle - [Télécharger vagrant](https://www.vagrantup.com/downloads.html)
* Télécharger la box du serveur - [Télécharger la box](https://github.com/jose-lpa/packer-ubuntu_lts/releases/download/v3.1/ubuntu-16.04.box)

## Description

OptiDB-Client est la partie graphique du projet. C'est avec cette interface que vous aller interagir avec l'api rest (optiDB-server) et tester trois base de données :
* Mysql 
* MariaDB
* Postgres


# Installer l'image avec vagrant

>Pour pouvoir lancer la machine virtuelle avec vagrant il faut d'abord récupérer l'image de la box (lien dans les prérequis)
Il faut aussi que vous ayez vagrant dans le path

1. cd $chemin_vers_la_box
2. vagrant box add --name optiDB ubuntu-16.04.box
3. Cloner ou télécharger le projet
    - git clone https://github.com/ChaudhryHaseeb/optiDB-client.git 
    - télécharger directement le dépôt en cliquant [ici](https://github.com/ChaudhryHaseeb/optiDB-client/archive/master.zip)
4. vagrant up

# Installer le serveur
>Pour passer à la prochaine étape, vous devez installer le serveur (lien dans les prérequis)  

# Lancer le projet

```bash
# Accéder au shell
vagrant ssh

# Accéder au répertoire du projet
cd /vagrant

# Build et installer les dépendances
mvn clean install

# Executer le projet
java -jar /target

# Lancer le serveur
Executez optiDB-server (voir dépôt optiDB-server)

# Accéder au site
http://localhost:8080/home
```


