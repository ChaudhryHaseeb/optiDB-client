
Vagrant.configure("2") do |config|
  config.vm.box = "optiDB"
  config.vm.hostname = "projetMaster"
  config.vm.network "public_network", ip: "192.168.1.97"
  config.vm.network "forwarded_port", guest: 3000, host: 3000, host_ip: "127.0.0.1"
  config.vm.network "forwarded_port", guest: 5000, host: 5000, host_ip: "127.0.0.1"
  config.vm.network "forwarded_port", guest: 80, host: 8000, host_ip: "127.0.0.1"
  config.vm.provider "virtualbox" do |vb|
      vb.name = "projetMaster"
      vb.memory = "2048"
  end
   config.vm.provision "shell", path: 'scripts/install.sh'
end
