package com.projectwizard;

import com.projectwizard.view.MainWindow;
//import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.projectwizard.theme.ThemeManager;
import com.projectwizard.theme.ThemeType;
import com.projectwizard.view.sidebar.Sidebar;
import com.projectwizard.view.dashboard.DashboardView;
import com.projectwizard.service.PersistenceService;
import com.projectwizard.core.navigation.NavigationController;
import com.projectwizard.core.navigation.NavigationTarget;
import java.io.File;


public class App {

    public void start(Stage stage) {

    	ThemeManager.apply(ThemeType.PRIMER_DARK);
        MainWindow window = new MainWindow();
        window.show(stage);

        // Restaurar último projeto aberto (Estilo Navegador)
        restoreLastProject();

        Scene scene = stage.getScene();

        if (scene != null) {

            scene.getStylesheets().add(
                    getClass()
                            .getResource("/themes/projectwizard.css")
                            .toExternalForm()
            );

        }

    }

    private void restoreLastProject() {
        PersistenceService persistence = new PersistenceService();
        String lastPath = persistence.getLastProjectPath();
        if (lastPath != null) {
            File projectDir = new File(lastPath);
            if (projectDir.exists() && projectDir.isDirectory()) {
                NavigationController.getInstance()
                        .navigateWithProject(NavigationTarget.WORKSPACE, projectDir);
            }
        }
    }

}
