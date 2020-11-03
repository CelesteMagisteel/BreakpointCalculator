package co.celestemagisteel.breakpoints;

import co.celestemagisteel.breakpoints.controllers.BlankCanvasController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BreakpointCalculator extends Application {

    public static BlankCanvasController canvasController;
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BreakpointCalculator.primaryStage = primaryStage;
        FXMLLoader loader = loadFXML("BlankCanvas");
        Scene scene = new Scene(loader.load());
        canvasController = loader.getController();
        canvasController.loadHeroPool();
        BreakpointCalculator.primaryStage.setScene(scene);
        BreakpointCalculator.primaryStage.show();
        BreakpointCalculator.primaryStage.setOnCloseRequest((WindowEvent event) -> {
            canvasController.saveHeroPool();
            Platform.exit();
            System.exit(0);
        });
        canvasController.showHeroList();
    }

    public static FXMLLoader loadFXML(String fxml) {
        return new FXMLLoader(BreakpointCalculator.class.getResource("scenes/" + fxml + ".fxml"));
    }
}
