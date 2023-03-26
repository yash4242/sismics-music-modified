export JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-amd64
mvn clean -DskipTests install
cd music-web
mvn jetty:run
