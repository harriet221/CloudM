#!/bin/bash

# Stop any container running on port 8080
sudo docker ps -q --filter "publish=8080" | xargs -r sudo docker stop

# Remove any container that might be using the port (optional)
sudo docker ps -aq --filter "publish=8080" | xargs -r sudo docker rm

# Pull and run the new Docker image
sudo docker pull harriet99/cloudm-aws-test-ci
sudo docker run -d -p 8080:8080 harriet99/cloudm-aws-test-ci