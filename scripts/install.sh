echo "mise à jour de la vm"
sudo apt-get update -y
sudo apt -y install openjdk-8-jdk
# Maven
sudo apt -y install maven
sudo chmod 755 -R /vagrant/scripts/run.sh

echo "########"
