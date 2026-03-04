package com.yeswanth.commitanalyser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LLMClient {

    private static final String API_URL = "https://ollama.com/api/generate";
    private static final String API_KEY = "a741bb42845845d8a9fe5ea5b8b86fc0.krJ50VHBaWiHwkY9u67KfvKz";

    public String callOllama(String prompt) throws Exception {

        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
        conn.setDoOutput(true);

        ObjectMapper mapper = new ObjectMapper();

        OllamaRequest request = new OllamaRequest("qwen3-coder-next", prompt);

        String payload = mapper.writeValueAsString(request);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(payload.getBytes());
        }

        BufferedReader br = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
        );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }
}