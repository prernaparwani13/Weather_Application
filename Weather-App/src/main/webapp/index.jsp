<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Weather Report</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: linear-gradient(to right, #232526, #414345);
      color: white;
      text-align: center;
      padding: 30px;
    }

    .report-container {
      background: rgba(255, 255, 255, 0.1);
      padding: 25px;
      border-radius: 12px;
      max-width: 600px;
      margin: auto;
    }

    h1 {
      font-size: 2em;
      margin-bottom: 10px;
    }

    .info {
      font-size: 1.3em;
      margin: 8px 0;
    }
  </style>
</head>
<body>
  <div class="report-container">
    <h1>🌦 Weather Report</h1>
    <div class="info"><strong>Date:</strong> ${date}</div>
    <div class="info"><strong>City:</strong> ${cityName}</div>
    <div class="info"><strong>Temperature:</strong> ${temperature}°C</div>
    <div class="info"><strong>Condition:</strong> ${weatherCondition}</div>
    <div class="info"><strong>High / Low:</strong> ${highTemp}°C / ${lowTemp}°C</div>
  </div>
</body>
</html>
