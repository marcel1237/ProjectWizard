package com.projectwizard.service;

import java.io.*;
import java.util.Properties;

public class PersistenceService {

    private static final String CONFIG_FILE = System.getProperty("user.home") + File.separator + ".projectwizard.properties";
    private static final String LAST_PROJECT_KEY = "last.project.path";
    private static final String OPEN_FILES_KEY = "open.files";

    public void saveLastProjectPath(String path) {
        Properties props = loadProperties();
        props.setProperty(LAST_PROJECT_KEY, path);
        saveProperties(props);
    }

    public void saveOpenFiles(java.util.List<String> filePaths) {
        Properties props = loadProperties();
        props.setProperty(OPEN_FILES_KEY, String.join(",", filePaths));
        saveProperties(props);
    }

    public String getLastProjectPath() {
        return loadProperties().getProperty(LAST_PROJECT_KEY);
    }

    public java.util.List<String> getOpenFiles() {
        String files = loadProperties().getProperty(OPEN_FILES_KEY);
        if (files == null || files.isEmpty()) return java.util.Collections.emptyList();
        return java.util.Arrays.asList(files.split(","));
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(CONFIG_FILE)) {
            props.load(in);
        } catch (IOException e) {
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
