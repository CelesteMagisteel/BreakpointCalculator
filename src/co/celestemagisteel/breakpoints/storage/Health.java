package co.celestemagisteel.breakpoints.storage;

import java.io.Serializable;

public class Health implements Serializable {

    private double health;
    private double armor = 0;
    private double shields = 0;
    private double temporaryArmor = 0;
    private double temporaryShields = 0;

    public Health(int health) {
        this.health = health;
    }

    public Health(Health clone) {
        this.health = clone.getHealth();
        this.armor = clone.getArmor();
        this.shields = clone.getShields();
        this.temporaryShields = clone.getTemporaryShields();
        this.temporaryArmor = clone.getTemporaryArmor();
    }

    public Health(int health, int armor, int shields) {
        this.health = health;
        this.armor = armor;
        this.shields = shields;
    }

    public Health(int health, int armor, int shields, int temporaryArmor, int temporaryShields) {
        this.health = health;
        this.armor = armor;
        this.shields = shields;
        this.temporaryArmor = temporaryArmor;
        this.temporaryShields = temporaryShields;
    }

    public double getHealth() { return health; }

    public double getArmor() { return armor; }

    public double getShields() { return shields; }

    public double getTemporaryArmor() { return temporaryArmor; }

    public double getTemporaryShields() { return temporaryShields; }

    public void setHealth(double health) { this.health = health; }

    public void setArmor(double armor) { this.armor = armor; }

    public void setShields(double shields) { this.shields = shields; }

    public void setTemporaryArmor(double temporaryArmor) { this.temporaryArmor = temporaryArmor; }

    public void setTemporaryShields(double temporaryShields) { this.temporaryShields = temporaryShields; }

    public boolean damage(double damageAmount) {
        double remainingDamage = damageAmount;
        boolean hasModifiedForArmor = false;
        if (temporaryShields > 0) {
            if (temporaryShields >= remainingDamage) {
                temporaryShields -= remainingDamage;
                return false;
            } else {
                remainingDamage -= temporaryShields;
                temporaryShields = 0;
            }
        }
        if (shields > 0) {
            if (shields >= remainingDamage) {
                shields -= remainingDamage;
                return false;
            } else {
                remainingDamage -= shields;
                shields = 0;
            }
        }
        if (temporaryArmor > 0) {
            if (remainingDamage < 10) {
                remainingDamage = remainingDamage/2;
            } else {
                remainingDamage -= 5;
            }
            hasModifiedForArmor = true;
            if (temporaryArmor >= remainingDamage) {
                temporaryArmor -= remainingDamage;
                return false;
            } else {
                remainingDamage -= temporaryArmor;
                temporaryArmor = 0;
            }
        }
        if (armor > 0) {
            if (!hasModifiedForArmor) {
                if (remainingDamage < 10) {
                    remainingDamage = remainingDamage / 2;
                } else {
                    remainingDamage -= 5;
                }
            }
            if (armor >= remainingDamage) {
                armor -= remainingDamage;
                return false;
            } else {
                remainingDamage -= armor;
                armor = 0;
            }
        }
        if (health > 0) {
            if (health > remainingDamage) {
                health -= remainingDamage;
                return false;
            } else {
                health = 0;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String string = "H: " + health;
        if (armor > 0) string += ", A: " + armor;
        if (shields > 0) string += ", S: " + shields;
        if (temporaryArmor > 0) string += ", TA: " + temporaryArmor;
        if (temporaryShields > 0) string += ", TS: " + temporaryShields;

        return string;
    }
}