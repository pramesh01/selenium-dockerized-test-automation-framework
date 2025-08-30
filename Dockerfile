FROM bellsoft/liberica-openjdk-alpine:23
RUN apk add curl jq
# Set working directory
WORKDIR /home/MyProject
# Copy compiled classes
ADD target/docker-resources ./
ADD target/test-classes ./
# Copy TestNG XML
ADD src/test/resources/testng.xml ./
ADD runner.sh runner.sh
#RUN chmod +x runner.sh
# Copy Maven dependencies (TestNG + Selenium jars)
# Agar aapne 'mvn dependency:copy-dependencies' kiya hai to jars 'target/dependency' me honge
COPY target/dependency ./libs
#ENTRYPOINT java -DbrowserName=${browserName} -DisGridEnabled=${isGridEnabled} -Dselenium.grid.hubHost=192.168.31.10 -cp "libs/*:classes:." org.testng.TestNG testng.xml
ENTRYPOINT sh runner.sh