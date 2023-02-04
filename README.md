![windsurfer-app-logo](https://user-images.githubusercontent.com/105795682/216633907-fff803c6-fb0d-4639-830f-f52dc723d303.jpg)

<h2>Description</h2>
The application finds the best windsurfing location for given date when taking into consideration very specific weather conditions. 
There are couple of default locations for weather-check, but it can be extended simply by changing the application.properties file and providing city name and/or geographical coordinates.

<hr>
<strong>Limitations</strong>

User can search for a location along with the weather for up to the next 16 consecutive days including present day. Checking later or past dates will lead to an error.

<h2>Technologies</h2>
<ul>
  <li>Java 17</li>
  <li>RestTemplate</li>
  <li>Java Stream API</li>
  <li>Spring Boot</li>
  <li>Spring Validation</li>
  <li>WeatherBit API</li>
  <li>Unit tests (JUnit 5)</li>
  <li>Integration tests (MockMVC)</li>
</ul>

<h2>Installation</h2>
<p>1. Clone the project from the repository.</p>
<p>2. Use the following command to build the project: <strong>mvn clean install</strong></p>
<p>3. Use the following command to run the application: <strong>mvn spring-boot:run</strong></p>

<h2>Endpoints</h2>
Here are some endpoints you can call:

```
http://localhost:8081/api/v1/windsurfing-location?date={requestedDate}
```

where you should replace {requestedDate} with your desired date in format <strong>yyyy-MM-dd</strong>

<h2>Future development</h2>
Planning to add:
<ul>
  <li>Swagger</li>
</ul>
