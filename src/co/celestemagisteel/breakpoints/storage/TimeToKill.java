package co.celestemagisteel.breakpoints.storage;

public class TimeToKill {

    private final Hero killer;
    private final Hero target;
    private int headShotsToKill = 0;
    private int headBulletsToKill = 0;
    private int shotsToKill = 0;
    private int bulletsToKill = 0;
    private int pureBulletsToKill = 0; // Individual bullets
    private int pureShotsToKill = 0; // Trigger pulls for burst/shotgun
    private double headShotTimeToKill = 0;
    private double timeToKill = 0;

    public TimeToKill(Hero killer, Hero target) {
        this.killer = killer;
        this.target = target;
        calculate();
    }

    private void calculate() {
        target.resetHealth();
        boolean isDead = false;
        System.out.println("\nBody Shots:");
        do {
            pureShotsToKill++;
            for (int bullet = 0; bullet < killer.getBulletPerShot(); bullet++) {
                if (!isDead) {
                    isDead = target.shoot(killer.getDamagePerInstance());
                    System.out.println(target.getCurrentHealth());
                    pureBulletsToKill++;
                }
            }
            if (!isDead) timeToKill += killer.getFireRate();
        } while (!isDead);

        if (killer.canHeadshot()) {
            System.out.println("\nHeadshots:");
            double damagePerBullet = killer.getDamagePerInstance() * killer.getHeadshotMultiplier();
            target.resetHealth();
            isDead = false;
            do {
                headShotsToKill++;
                for (int bullet = 0; bullet < killer.getBulletPerShot(); bullet++) {
                    if (!isDead) {
                        boolean testDead = target.willKill(killer.getDamagePerInstance());
                        if (testDead) {
                            isDead = target.shoot(killer.getDamagePerInstance());
                            System.out.println(target.getCurrentHealth());
                            bulletsToKill++;
                            shotsToKill++;
                        } else {
                            isDead = target.shoot(damagePerBullet);
                            System.out.println(target.getCurrentHealth());
                            headBulletsToKill++;
                        }
                    }
                }
                if (!isDead) headShotTimeToKill += killer.getFireRate();
            } while (!isDead);
        } else {
            headShotsToKill = pureShotsToKill;
            headBulletsToKill = pureBulletsToKill;
        }

    }

    public Hero getKiller() {
        return killer;
    }

    public Hero getTarget() {
        return target;
    }

    public int getPureBulletsToKill() {
        return pureBulletsToKill;
    }

    public int getPureShotsToKill() {
        return pureShotsToKill;
    }

    public int getHeadShotsToKill() {
        return headShotsToKill;
    }

    public int getHeadBulletsToKill() {
        return headBulletsToKill;
    }

    public int getShotsToKill() {
        return shotsToKill;
    }

    public int getBulletsToKill() {
        return bulletsToKill;
    }

    public double getTimeToKill() {
        return timeToKill;
    }
}
