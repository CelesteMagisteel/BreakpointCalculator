package co.celestemagisteel.breakpoints.controllers;

import co.celestemagisteel.breakpoints.storage.Hero;
import co.celestemagisteel.breakpoints.storage.HeroPool;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;

import static co.celestemagisteel.breakpoints.BreakpointCalculator.canvasController;
import static co.celestemagisteel.breakpoints.BreakpointCalculator.loadFXML;

public class HeroListController {

    @FXML TableView<Hero> heroTableView;

    public void initialize() {
        heroTableView.setItems(HeroPool.getHeroPool());

        TableColumn<Hero, String> nameColumn = new TableColumn<>("Hero Name");
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getName())));

        TableColumn<Hero, String> healthColumn = new TableColumn<>("Health");
        healthColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getMaximumHealth())));

        TableColumn<Hero, String> damageColumn = new TableColumn<>("Damage");
        damageColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getDamagePerInstance())));

        TableColumn<Hero, String> fireRateColumn = new TableColumn<>("Fire Rate");
        fireRateColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getFireRate())));

        TableColumn<Hero, String> versionColumn = new TableColumn<>("Version");
        versionColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(String.valueOf(cellData.getValue().getValuesAsOf())));

        heroTableView.getColumns().addAll(nameColumn, healthColumn, damageColumn, fireRateColumn, versionColumn);
    }

    public void editHero() throws IOException {
        Hero hero = heroTableView.getSelectionModel().getSelectedItem();
        if (hero == null) return;
        FXMLLoader loader = loadFXML("AddHero");
        canvasController.loadScene(loader.load());
        AddHeroController controller = loader.getController();
        controller.setValuesToHero(hero);
    }

    public void calculateHero() throws IOException {
        Hero hero = heroTableView.getSelectionModel().getSelectedItem();
        if (hero == null) return;
        FXMLLoader loader = loadFXML("TimeToKill");
        canvasController.loadScene(loader.load());
        TimeToKillController controller = loader.getController();
        controller.setQuestionedHero(hero);
    }

    public void deleteHero() {
        Hero hero = heroTableView.getSelectionModel().getSelectedItem();
        if (hero == null) return;
        HeroPool.removeHero(hero);
    }

    public void addHero() throws IOException {
        canvasController.loadScene(loadFXML("AddHero").load());
    }

    public void saveHeroes() {
        canvasController.saveHeroPool();
    }

    public void loadHeroes() {
        canvasController.loadHeroPool();
    }
}
