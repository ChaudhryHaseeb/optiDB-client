# optiDB - Projet Master

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
vagrant box add --name nomDeLaBox cheminDeLaBox

vagrant up
```