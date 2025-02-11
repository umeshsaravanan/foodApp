# Use Tomcat as the base image
FROM tomcat:9.0-jdk17

# Set working directory inside the container
WORKDIR /usr/local/tomcat/webapps/

# Copy the built WAR file into Tomcat's webapps directory
COPY foodApplication/target/foodApplication.war /usr/local/tomcat/webapps/foodApplication.war

# Expose port 8080
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
