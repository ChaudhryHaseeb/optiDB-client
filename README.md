[![Build Status](https://img.shields.io/travis/ChaudhryHaseeb/optiDB-client/master.svg?style=flat-square)](https://travis-ci.org/ChaudhryHaseeb/optiDB-client)
[![License](https://img.shields.io/github/license/ChaudhryHaseeb/optiDB-client.svg?style=flat-square)](LICENSE)
[![Version](https://img.shields.io/github/tag/ChaudhryHaseeb/optiDB-client.svg?label=version&style=flat-square)](build.gradle)

[![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=coverage)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![SonarCloud Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=sqale_index)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=code_smells)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=org.optidb%3Aoptidb-client&metric=security_raclientting)](https://sonarcloud.io/dashboard?id=org.optidb%3Aoptidb-client)

[![Waffle.io - Columns and their card count](https://badge.waffle.io/ChaudhryHaseeb/optiDB-client.svg?columns=all)](https://waffle.io/ChaudhryHaseeb/optiDB-client)


# OptiDB - Projet Master

## Prérequis
* Git, gestionnaire de version - [Télécharger git](https://git-scm.com/downloads)
* Virtualbox, outil de virtualisation - [Télécharger Virtualbox](https://www.virtualbox.org/wiki/Downloads)
* Vagrant, gestionnaire de machine virtuelle - [Télécharger vagrant](https://www.vagrantup.com/downloads.html)
* Télécharger la box du serveur - [Télécharger la box](https://github.com/jose-lpa/packer-ubuntu_lts/releases/download/v3.1/ubuntu-16.04.box)

## Installation

Pour pouvoir lancer la machine virtuelle avec vagrant il faut d'abord récupérer l'image de la box (lien dans les prérequis)
Il faut aussi que vous ayez vagrant dans le path

```bash
# Installer l'image avec vagrant
cd $chemin_vers_la_box

vagrant box add --name optiDB ubuntu-16.04.box

git clone https://github.com/ChaudhryHaseeb/optiDB-client.git  

vagrant up
```

## Autres commandes

```
# Démarrrer la VM
vagrant up

# Eteindre la VM
vagrant halt

# Supprimer la VM
vagrant destroy

# Se connecter en ssh à la VM
vagrant ssh

# Supprimer une box
vagrant box remove nomDeLaBox

```
