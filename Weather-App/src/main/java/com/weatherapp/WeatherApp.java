package com.weatherapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@WebServlet("/WeatherApp")
public class WeatherApp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public WeatherApp() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
        String city = request.getParameter("city");

        if (city == null || city.trim().isEmpty()) {
            request.setAttribute("error", "City name cannot be empty.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        String apiKey = "f72afdba35a196323dd06d371983cdf3"; // Replace with your API key
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.connect();

            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                request.setAttribute("error", "City not found or API error.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            JsonParser parser = new JsonParser(); // required in 2.8.5
            JsonObject json = parser.parse(jsonBuilder.toString()).getAsJsonObject();


            String cityName = json.get("name").getAsString();

            JsonObject main = json.getAsJsonObject("main");
            double temperature = main.get("temp").getAsDouble();
            double tempMin = main.get("temp_min").getAsDouble();
            double tempMax = main.get("temp_max").getAsDouble();

            JsonArray weatherArray = json.getAsJsonArray("weather");
            JsonObject weather = weatherArray.get(0).getAsJsonObject();
            String weatherCondition = weather.get("description").getAsString();

            request.setAttribute("cityName", cityName);
            request.setAttribute("temperature", (int) temperature);
            request.setAttribute("highTemp", (int) tempMax);
            request.setAttribute("lowTemp", (int) tempMin);
            request.setAttribute("weatherCondition", weatherCondition);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}