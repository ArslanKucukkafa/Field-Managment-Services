# THİS SHELL COMMANDS NOT YET TESTED

mvn clean install -f ./eureka-services/pom.xml
mvn clean install -f ./config-services/pom.xml
mvn clean install -f ./job-services/pom.xml
mvn clean install -f ./fieldcore-services/pom.xml
mvn clean install -f ./idm-services/pom.xml
mvn clean install -f ./gateway-services/pom.xml


java -jar eureka-services/target/eureka-services-1.0-SNAPSHOT.jar  &
java -jar config-services/target/config-services-1.0-SNAPSHOT.jar  &
java -jar job-services/target/job-services-1.0-SNAPSHOT.jar &
java -jar fieldcore-services/target/fieldcore-services-1.0-SNAPSHOT.jar  &
java -jar idm-services/target/idm-services-1.0-SNAPSHOT.jar  &
java -jar gateway-services/target/gateway-services-1.0-SNAPSHOT.jar  &
