package co.celestemagisteel.breakpoints.controllers;

import co.celestemagisteel.breakpoints.BreakpointCalculator;
import co.celestemagisteel.breakpoints.storage.HeroPool;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

import static co.celestemagisteel.breakpoints.BreakpointCalculator.loadFXML;

public class BlankCanvasController {

    @FXML Pane contentPane;

    public void loadScene(Parent parent) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(parent);
    }

    public void showAddHero() throws IOException {
        loadScene(loadFXML("AddHero").load());
    }

    public void showHeroList() throws IOException {
        loadScene(loadFXML("HeroList").load());
    }

    public void saveHeroPool() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pool Files", "*.pool");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showSaveDialog(BreakpointCalculator.primaryStage);

        if (file != null) {
            try {
                HeroPool.saveHeroPool(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadHeroPool() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Pool Files", "*.pool");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        File file = fileChooser.showOpenDialog(BreakpointCalculator.primaryStage);

        if (file != null) {
            try {
                HeroPool.loadHeroPool(file);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
