package co.celestemagisteel.breakpoints.controllers;

import co.celestemagisteel.breakpoints.storage.Hero;
import co.celestemagisteel.breakpoints.storage.HeroPool;
import co.celestemagisteel.breakpoints.storage.TimeToKill;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class TimeToKillController {

    @FXML private Label nameLabel;
    @FXML private Label healthLabel;
    @FXML private Label versionLabel;
    @FXML private Label dpsLabel;
    @FXML private Label fireRateLabel;
    @FXML private Label headshotLabel;
    @FXML private Label bpsLabel;
    @FXML private Label dmgReductionLabel;
    @FXML private Label shotTypeLabel;
    @FXML private TableView<TimeToKill> timeToKillYouTableView;
    @FXML private TableView<TimeToKill> timeToKillThemTableView;
    private final ObservableList<TimeToKill> timeToKillYou = FXCollections.observableList(new ArrayList<>());
    private final ObservableList<TimeToKill> timeToKillThem = FXCollections.observableList(new ArrayList<>());

    public void initialize() {
        timeToKillThemTableView.setItems(timeToKillThem);
        timeToKillYouTableView.setItems(timeToKillYou);

        timeToKillThemTableView.getColumns().addAll(getColumns(false));
        timeToKillYouTableView.getColumns().addAll(getColumns(true));
    }

    public void setQuestionedHero(Hero hero) {
        nameLabel.setText(hero.getName());
        healthLabel.setText(hero.getMaximumHealth() + "");
        headshotLabel.setText(hero.canHeadshot() ? "Yes" : "No");
        dpsLabel.setText(hero.getDamagePerInstance() + "");
        fireRateLabel.setText(hero.getFireRate() + "");
        versionLabel.setText(hero.getValuesAsOf());
        bpsLabel.setText(hero.getBulletPerShot() + "");
        shotTypeLabel.setText(hero.getShotType() + "");
        dmgReductionLabel.setText(hero.getDamageReduction() != 0 ? hero.getDamageReduction() + "" : "");
        for (Hero altHero : HeroPool.getHeroPool()) {
            timeToKillYou.add(new TimeToKill(altHero, hero));
            timeToKillThem.add(new TimeToKill(hero, altHero));
        }
    }

    public List<TableColumn<TimeToKill, String>> getColumns(boolean killer) {
        List<TableColumn<TimeToKill, String>> list = new ArrayList<>();
        TableColumn<TimeToKill, String> nameColumn = new TableColumn<>("Hero");
        nameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(killer ? cellData.getValue().getKiller().getName() : cellData.getValue().getTarget().getName()));
        TableColumn<TimeToKill, String> headShotsColumn = new TableColumn<>("Head Shots");
        headShotsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getHeadShotsToKill() + " (" + cellData.getValue().getHeadBulletsToKill() + ")"));
        TableColumn<TimeToKill, String> shotsColumn = new TableColumn<>("Body Shots");
        shotsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getShotsToKill() + " (" + cellData.getValue().getBulletsToKill() + ")"));
        TableColumn<TimeToKill, String> pureShotsColumn = new TableColumn<>("Pure Body Shots");
        pureShotsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPureShotsToKill() + " (" + cellData.getValue().getPureBulletsToKill() + ")"));
        TableColumn<TimeToKill, String> secondsColumn = new TableColumn<>("Seconds");
        secondsColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(Math.round(cellData.getValue().getTimeToKill()*100)/100d + ""));
        list.add(nameColumn);
        list.add(headShotsColumn);
        list.add(shotsColumn);
        list.add(pureShotsColumn);
        list.add(secondsColumn);
        return list;
    }
}
