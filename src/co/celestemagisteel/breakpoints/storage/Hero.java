package co.celestemagisteel.breakpoints.storage;

import java.io.Serializable;

public class Hero implements Serializable, Comparable<Hero> {

    private int bulletPerShot;
    private String name;
    private String valuesAsOf = "1.53";
    private boolean canHeadshot;
    private double damagePerInstance;
    private double fireRate;
    private ShotType shotType;
    private Health maximumHealth;
    private Health currentHealth;
    private double damageReduction; // Should be between 0 and 1
    private double headshotMultiplier = 2;
    private double reloadTime;
    private ReloadType reloadType;
    private int clipSize;

    public Hero(String name, boolean canHeadshot, ShotType shotType, double damagePerInstance, double fireRate,
                int bulletsPerShot, Health health, double damageReduction, ReloadType reloadType, double reloadTime, int clipSize) {
        this.name = name;
        this.canHeadshot = canHeadshot;
        this.damagePerInstance = damagePerInstance;
        this.shotType = shotType;
        this.fireRate = fireRate;
        this.maximumHealth = health;
        this.currentHealth = health;
        this.damageReduction = damageReduction;
        this.bulletPerShot = bulletsPerShot;
        this.reloadTime = reloadTime;
        this.reloadType = reloadType;
        this.clipSize = clipSize;
    }

    public Hero(String name, String valuesAsOf, boolean canHeadshot, ShotType shotType, double damagePerInstance,
                double fireRate, int bulletsPerShot, double headshotMultiplier, Health health, double damageReduction,
                ReloadType reloadType, double reloadTime, int clipSize) {
        this.name = name;
        this.valuesAsOf = valuesAsOf;
        this.shotType = shotType;
        this.canHeadshot = canHeadshot;
        this.damagePerInstance = damagePerInstance;
        this.fireRate = fireRate;
        this.maximumHealth = health;
        this.currentHealth = health;
        this.damageReduction = damageReduction;
        this.bulletPerShot = bulletsPerShot;
        this.headshotMultiplier = headshotMultiplier;
        this.reloadTime = reloadTime;
        this.reloadType = reloadType;
        this.clipSize = clipSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValuesAsOf() {
        return valuesAsOf;
    }

    public void setValuesAsOf(String valuesAsOf) {
        this.valuesAsOf = valuesAsOf;
    }

    public boolean canHeadshot() {
        return canHeadshot;
    }

    public void setCanHeadshot(boolean canHeadshot) {
        this.canHeadshot = canHeadshot;
    }

    public double getDamagePerInstance() {
        return damagePerInstance;
    }

    public void setDamagePerInstance(double damagePerInstance) {
        this.damagePerInstance = damagePerInstance;
    }

    public double getFireRate() {
        return fireRate;
    }

    public void setFireRate(double fireRate) {
        this.fireRate = fireRate;
    }

    public Health getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(Health maximumHealth) {
        this.maximumHealth = maximumHealth;
    }

    public Health getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Health currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void resetHealth() {
        this.currentHealth = new Health(this.maximumHealth);
    }

    public ShotType getShotType() {
        return shotType;
    }

    public void setShotType(ShotType shotType) {
        this.shotType = shotType;
    }

    public double getDamageReduction() {
        return damageReduction;
    }

    public void setDamageReduction(double damageReduction) {
        this.damageReduction = damageReduction;
    }

    public int getBulletPerShot() {
        return bulletPerShot;
    }

    public void setBulletPerShot(int bulletPerShot) {
        this.bulletPerShot = bulletPerShot;
    }

    public double getHeadshotMultiplier() {
        return headshotMultiplier;
    }

    public void setHeadshotMultiplier(double headshotMultiplier) {
        this.headshotMultiplier = headshotMultiplier;
    }

    public double getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(double reloadTime) {
        this.reloadTime = reloadTime;
    }

    public ReloadType getReloadType() {
        return reloadType;
    }

    public void setReloadType(ReloadType reloadType) {
        this.reloadType = reloadType;
    }

    public int getClipSize() {
        return clipSize;
    }

    public void setClipSize(int clipSize) {
        this.clipSize = clipSize;
    }

    public boolean shoot(double damage) {
        damage = damage * (1 - damageReduction);
        return currentHealth.damage(damage);
    }

    public boolean willKill(double damage) {
        Health tempHealth = new Health(currentHealth);
        damage = damage * (1 - damageReduction);
        return tempHealth.damage(damage);
    }

    @Override
    public int compareTo(Hero o) {
        return this.name.compareTo(o.getName());
    }
}
