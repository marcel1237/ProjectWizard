package com.projectwizard.view.explorer;

import java.io.File;
import javafx.geometry.Insets;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.material2.Material2AL;
import org.kordamp.ikonli.material2.Material2MZ;
import org.kordamp.ikonli.fontawesome5.FontAwesomeSolid;
import org.kordamp.ikonli.fontawesome5.FontAwesomeBrands;

/**
 * FileTreeCell with Professional Icons and Syntax Highlighting Colors
 */
public class FileTreeCell extends TreeCell<File> {

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            String displayName = item.getName().isBlank()
                    ? item.getAbsolutePath()
                    : item.getName();

            FontIcon icon = getFileIcon(item);
            String styleClass = getFileStyleClass(item);
            
            icon.getStyleClass().add(styleClass);
            icon.setIconSize(16);
            
            Text nameText = new Text(displayName);
            nameText.getStyleClass().add(styleClass);

            HBox hbox = new HBox(8, icon, nameText);
            hbox.setPadding(new Insets(2));

            setGraphic(hbox);
            setText(null);
        }
    }

    private String getFileStyleClass(File file) {
        if (file.isDirectory()) return "file-folder";
        
        String name = file.getName().toLowerCase();
        if (name.endsWith(".java")) return "file-java";
        if (name.endsWith(".xml") || name.endsWith(".fxml")) return "file-xml";
        if (name.endsWith(".md")) return "file-md";
        if (name.endsWith(".sh") || name.endsWith(".bat")) return "file-sh";
        if (name.endsWith(".js") || name.endsWith(".ts")) return "file-js";
        if (name.endsWith(".css") || name.endsWith(".scss")) return "file-css";
        if (name.endsWith(".json")) return "file-json";
        
        return "file-default";
    }

    private FontIcon getFileIcon(File file) {
        if (file.isDirectory()) {
            String name = file.getName().toLowerCase();
            if (name.equals("src")) return new FontIcon(Material2MZ.SOURCE);
            if (name.equals("test")) return new FontIcon(Material2MZ.SCIENCE);
            if (name.equals("resources")) return new FontIcon(Material2MZ.STORAGE);
            if (name.equals("target") || name.equals("build")) return new FontIcon(Material2AL.BUILD);
            if (name.equals(".git")) return new FontIcon(Material2AL.ACCOUNT_TREE);
            return new FontIcon(Material2AL.FOLDER);
        }

        String name = file.getName().toLowerCase();
        if (name.endsWith(".java")) return new FontIcon(FontAwesomeSolid.COFFEE);
        if (name.endsWith(".xml") || name.endsWith(".fxml")) return new FontIcon(Material2AL.CODE);
        if (name.endsWith(".md")) return new FontIcon(Material2AL.DESCRIPTION);
        if (name.endsWith(".sh") || name.endsWith(".bat")) return new FontIcon(FontAwesomeSolid.TERMINAL);
        if (name.endsWith(".js") || name.endsWith(".ts")) return new FontIcon(FontAwesomeBrands.JS);
        if (name.endsWith(".css") || name.endsWith(".scss")) return new FontIcon(Material2MZ.PALETTE);
        if (name.endsWith(".json")) return new FontIcon(Material2MZ.SETTINGS_ETHERNET);
        if (name.startsWith(".")) return new FontIcon(Material2AL.LOCK);

        return new FontIcon(Material2AL.INSERT_DRIVE_FILE);
    }
}
