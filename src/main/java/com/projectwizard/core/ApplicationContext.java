package com.projectwizard.core;

import com.projectwizard.service.DialogService;
import com.projectwizard.service.FileSystemService;
import com.projectwizard.service.NavigationService;
import com.projectwizard.service.ProjectService;
import com.projectwizard.service.SettingsService;

public class ApplicationContext {

    private static final NavigationService navigationService = new NavigationService();

    private final DialogService dialogService = new DialogService();
    private final FileSystemService fileSystemService = new FileSystemService();
    private final ProjectService projectService = new ProjectService();
    private final SettingsService settingsService = new SettingsService();

    public String getApplicationName() {
        return Constants.APPLICATION_NAME;
    }

    public String getVersion() {
        return Version.VERSION;
    }

    public DialogService getDialogService() {
        return dialogService;
    }

    public FileSystemService getFileSystemService() {
        return fileSystemService;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public SettingsService getSettingsService() {
        return settingsService;
    }

    public NavigationService getNavigationService() {
        return navigationService;
    }

}
