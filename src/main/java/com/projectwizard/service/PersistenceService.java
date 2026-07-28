package com.projectwizard.service;

import java.io.*;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class PersistenceService {

    private static final String CONFIG_FILE = System.getProperty("user.home") + File.separator + ".projectwizard.properties";
    private static final String LAST_PROJECT_KEY = "last.project.path";
    private static final String OPEN_FILES_KEY = "open.files";
    private static final String GEMINI_API_KEY = "gemini.api.key";
    private static final String USAGE_DAILY_KEY = "usage.daily";

    public void saveLastProjectPath(String path) {
        Properties props = loadProperties();
        props.setProperty(LAST_PROJECT_KEY, path);
        saveProperties(props);
    }

    public void saveOpenFiles(List<String> filePaths) {
        Properties props = loadProperties();
        props.setProperty(OPEN_FILES_KEY, String.join(",", filePaths));
        saveProperties(props);
    }

    public String getLastProjectPath() {
        return loadProperties().getProperty(LAST_PROJECT_KEY);
    }

    public List<String> getOpenFiles() {
        String files = loadProperties().getProperty(OPEN_FILES_KEY);
        if (files == null || files.isEmpty()) return Collections.emptyList();
        return Arrays.asList(files.split(","));
    }

    public String getGeminiApiKey() {
        return loadProperties().getProperty(GEMINI_API_KEY, "");
    }

    public void saveGeminiApiKey(String key) {
        Properties props = loadProperties();
        props.setProperty(GEMINI_API_KEY, key);
        saveProperties(props);
    }

    public void incrementUsage() {
        Properties props = loadProperties();
        int current = Integer.parseInt(props.getProperty(USAGE_DAILY_KEY, "0"));
        props.setProperty(USAGE_DAILY_KEY, String.valueOf(current + 1));
        saveProperties(props);
    }

    public int getDailyUsage() {
        return Integer.parseInt(loadProperties().getProperty(USAGE_DAILY_KEY, "0"));
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            try (InputStream in = new FileInputStream(file)) {
                props.load(in);
            } catch (IOException e) {}
        }
        return props;
    }

    private void saveProperties(Properties props) {
        try (OutputStream out = new FileOutputStream(CONFIG_FILE)) {
            props.store(out, "ProjectWizard Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
