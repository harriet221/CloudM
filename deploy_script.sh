#!/bin/bash

# 도커 컨테이너 실행
sudo docker pull harriet99/cloudm-aws-test-ci
sudo docker run -d -p 8080:8080 harriet99/cloudm-aws-test-ci

# 필요한 다른 명령을 추가하세요. 예: Spring Boot 애플리케이션 실행 등
# 예시:
# cd /path/to/your/project
# java -jar cloudM.jar