package com.gorshkov.movieland.util;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    @SneakyThrows
    public static List<String> getRowsFromUrl(String urlString) {
        List<String> rows = new ArrayList<>();
        URL url = new URL(urlString);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                if (!inputLine.equals("")) {
                    rows.add(inputLine);
                }
            }
        }
        return rows;
    }
}