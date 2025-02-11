# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built WAR file into the container
COPY target/yourapp.war /app/yourapp.war

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/yourapp.war"]
