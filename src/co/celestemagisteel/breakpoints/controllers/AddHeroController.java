package co.celestemagisteel.breakpoints.controllers;

import co.celestemagisteel.breakpoints.storage.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.io.IOException;

import static co.celestemagisteel.breakpoints.BreakpointCalculator.canvasController;

public class AddHeroController {

    @FXML private ChoiceBox<ShotType> damageTypeSelector;
    @FXML private TextField healthTextField;
    @FXML private TextField armorTextField;
    @FXML private TextField shieldTextField;
    @FXML private TextField heroNameField;
    @FXML private TextField damageTextField;
    @FXML private TextField fireRateTextField;
    @FXML private TextField versionTextField;
    @FXML private TextField bulletsPerShotTextField;
    @FXML private TextField damageReductionTextField;
    @FXML private TextField reloadTimeTextField;
    @FXML private TextField headshotMultiplierTextField;
    @FXML private ChoiceBox<ReloadType> reloadTypeSelector;
    @FXML private ToggleButton canHeadshotToggle;
    private Hero editingHero = null;

    public void initialize() {
        damageTypeSelector.setItems(FXCollections.observableArrayList(ShotType.values()));
        damageTypeSelector.setValue(ShotType.BULLET);
        reloadTypeSelector.setItems(FXCollections.observableArrayList(ReloadType.values()));
        reloadTypeSelector.setValue(ReloadType.CLIP);
    }

    public void setValuesToHero(Hero hero) {
        damageTypeSelector.setValue(hero.getShotType());
        healthTextField.setText(hero.getMaximumHealth().getHealth() + "");
        armorTextField.setText(hero.getMaximumHealth().getArmor() != 0 ? hero.getMaximumHealth().getArmor() + "" : "");
        shieldTextField.setText(hero.getMaximumHealth().getShields() != 0 ? hero.getMaximumHealth().getShields() + "" : "");
        heroNameField.setText(hero.getName());
        damageTextField.setText(hero.getDamagePerInstance() + "");
        fireRateTextField.setText(hero.getFireRate() + "");
        versionTextField.setText(hero.getValuesAsOf());
        bulletsPerShotTextField.setText(hero.getBulletPerShot() + "");
        damageReductionTextField.setText(hero.getDamageReduction() != 0 ? hero.getDamageReduction() + "" : "");
        canHeadshotToggle.setSelected(hero.canHeadshot());
        canHeadshotToggle.setText(canHeadshotToggle.isSelected() ? "Can Headshot" : "Cannot Headshot");
        reloadTimeTextField.setText(hero.getReloadTime() + "");
        reloadTypeSelector.setValue(hero.getReloadType());
        headshotMultiplierTextField.setText(hero.getHeadshotMultiplier() + "");
        editingHero = hero;
    }

    public void addHero() {
        int health; int armor; int shield; double damageReduction;
        double fireRate; double damage;
        int bulletsPerShot; double headshotMultiplier;
        double reloadTime;

        try {
            health = (int) Double.parseDouble(healthTextField.getText());
        } catch (NumberFormatException exception) {
            System.out.println("Health value does not look right");
            return;
        }
        try {
            armor = !armorTextField.getText().equals("") ? (int) Double.parseDouble(armorTextField.getText()) : 0;
        } catch (NumberFormatException exception) {
            System.out.println("Armor value does not look right");
            return;
        }
        try {
            shield = !shieldTextField.getText().equals("") ? (int) Double.parseDouble(shieldTextField.getText()) : 0;
        } catch (NumberFormatException exception) {
            System.out.println("Armor value does not look right");
            return;
        }
        try {
            headshotMultiplier = !headshotMultiplierTextField.getText().equals("") ?  Double.parseDouble(headshotMultiplierTextField.getText()) : 2;
        } catch (NumberFormatException exception) {
            System.out.println("Headshot Multiplier value does not look right");
            return;
        }
        try {
            damage = Double.parseDouble(damageTextField.getText());
            ;
        } catch (NumberFormatException exception) {
            System.out.println("Damage value does not look right");
            return;
        }
        try {
            fireRate = Double.parseDouble(fireRateTextField.getText());
        } catch (NumberFormatException exception) {
            System.out.println("Fire Rate value does not look right");
            return;
        }
        try {
            damageReduction = !damageReductionTextField.getText().equals("") ? Double.parseDouble(damageReductionTextField.getText()) / 100 : 0;
        } catch (NumberFormatException exception) {
            System.out.println("Damage Reduction value does not look right");
            return;
        }
        try {
            bulletsPerShot = !bulletsPerShotTextField.getText().equals("") ? Integer.parseInt(bulletsPerShotTextField.getText()) : 1;
        } catch (NumberFormatException exception) {
            System.out.println("Bullets per Shot value does not look right");
            return;
        }
        try {
            reloadTime = Double.parseDouble(reloadTimeTextField.getText());
        } catch (NumberFormatException exception) {
            System.out.println("Reload Time value does not look right");
            return;
        }

        String name = heroNameField.getText();
        String version = versionTextField.getText();
        ShotType shotType = damageTypeSelector.getValue();
        ReloadType reloadType = reloadTypeSelector.getValue();
        boolean canHeadshot = canHeadshotToggle.isSelected();
        if (editingHero != null) {
            editingHero.setName(name);
            editingHero.setMaximumHealth(new Health(health, armor, shield));
            editingHero.setFireRate(fireRate);
            editingHero.setDamagePerInstance(damage);
            editingHero.setDamageReduction(damageReduction);
            editingHero.setBulletPerShot(bulletsPerShot);
            editingHero.setValuesAsOf(version);
            editingHero.setShotType(shotType);
            editingHero.setCanHeadshot(canHeadshot);
            editingHero.setHeadshotMultiplier(headshotMultiplier);
            editingHero.setReloadTime(reloadTime);
            editingHero.setReloadType(reloadType);
        } else {
            Hero hero = new Hero(name, version, canHeadshot, shotType, damage, fireRate, bulletsPerShot, headshotMultiplier,
                    new Health(health, armor, shield), damageReduction, reloadType, reloadTime);
            HeroPool.addHero(hero);
        }
        try {
            canvasController.showHeroList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeToggleValue(ActionEvent actionEvent) {
        canHeadshotToggle.setText(canHeadshotToggle.isSelected() ? "Can Headshot" : "Cannot Headshot");
    }
}
