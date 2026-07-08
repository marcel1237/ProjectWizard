package com.projectwizard.service;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemService {

    public boolean exists(Path path) {

        return Files.exists(path);

    }

}
