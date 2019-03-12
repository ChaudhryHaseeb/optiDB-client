[![Build Status](https://img.shields.io/travis/ChaudhryHaseeb/optiDB-client/master.svg?style=flat-square)](https://travis-ci.org/ChaudhryHaseeb/optiDB-client)
[![License](https://img.shields.io/github/license/ChaudhryHaseeb/optiDB-client.svg?style=flat-square)](LICENSE)
[![Version](https://img.shields.io/github/tag/ChaudhryHaseeb/optiDB-client.svg?label=version&style=flat-square)](build.gradle)

[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=coverage)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![SonarCloud Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=sqale_index)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=code_smells)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=security_rating)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)

[![Waffle.io - Columns and their card count](https://badge.waffle.io/ChaudhryHaseeb/optiDB-client.svg?columns=all)](https://waffle.io/ChaudhryHaseeb/optiDB-client)


# OptiDB-Client - Projet Master

## Prérequis
* OptiDB-server, la partie serveur du projet - [Accéder au dépôt optiDB-server](https://github.com/DaJaime/optiDB-server)
* Virtualbox, outil de virtualisation - [Télécharger Virtualbox](https://www.virtualbox.org/wiki/Downloads)
* Vagrant, gestionnaire de machine virtuelle - [Télécharger vagrant](https://www.vagrantup.com/downloads.html)
* Télécharger la box du serveur - [Télécharger la box](https://github.com/jose-lpa/packer-ubuntu_lts/releases/download/v3.1/ubuntu-16.04.box)

## Description

OptiDB-Client est la partie client du projet. C'est avec cette interface que vous aller interagir avec l'api rest (optiDB-server) et tester un ensemble de base de données.


# Installer l'image avec vagrant

>Pour pouvoir lancer la machine virtuelle avec vagrant il faut d'abord récupérer l'image de la box (lien dans les prérequis)
Il faut aussi que vous ayez vagrant dans le path

```bash
# Ouvrez un terminal et aller dans le répertoire de la box
1. cd $chemin_vers_la_box

# Ajouter la box dans la liste des box de vagrant
2. vagrant box add --name optiDB ubuntu-16.04.box

# Cloner ou télécharger le dépôt
3. Cloner ou télécharger le projet
    - git clone https://github.com/ChaudhryHaseeb/optiDB-client.git 
    - télécharger directement le dépôt en cliquant [ici](https://github.com/ChaudhryHaseeb/optiDB-client/archive/master.zip)

# Executez cette commande dans le répertoire du projet
4. vagrant up 
```

# Installer le serveur
>Pour passer à la prochaine étape, vous devez installer le serveur (lien dans les prérequis)  

# Lancer le projet

```bash
# Accéder au shell
5. vagrant ssh

# Accéder au répertoire du projet
6. cd /vagrant

# Dans un nouvel onglet, Executez le serveur
7. Executez optiDB-server (voir dépôt optiDB-server)

# Lancer le client
8. ./scripts/run.sh

# Accéder au site
9. http://localhost:8080/home
```


