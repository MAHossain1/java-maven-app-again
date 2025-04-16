#!/bin/bash

# 1. Update packages
sudo yum update -y

# 2. Install Docker
sudo yum install docker -y

# 3. Start & Enable Docker
sudo systemctl start docker
sudo systemctl enable docker

# 4. Run Jenkins container
sudo docker run --restart=always -p 8080:8080 -p 50000:50000 -d \
  -v jenkins_home:/var/jenkins_home \
  -v /var/run/docker.sock:/var/run/docker.sock \
  -v $(which docker):/usr/bin/docker \
  jenkins/jenkins:lts

# 5. Give docker.sock permission (Optional - not recommended in prod)
sudo chmod 666 /var/run/docker.sock

# 6. Restart Docker again to ensure all changes apply
sudo systemctl restart docker

# 7. Show running containers
docker ps
