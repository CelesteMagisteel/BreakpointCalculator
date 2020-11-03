package co.celestemagisteel.breakpoints.storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

public class HeroPool {

    private static ObservableList<Hero> heroPool = FXCollections.observableList(new ArrayList<>());

    public static ObservableList<Hero> getHeroPool() {
        return heroPool;
    }

    public static void addHero(Hero hero) {
        heroPool.add(hero);
    }

    public static void removeHero(Hero hero) {
        heroPool.remove(hero);
    }

    public static void saveHeroPool(File fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(new ArrayList<>(heroPool));
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public static void loadHeroPool(File fileName) throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        heroPool = FXCollections.observableList((ArrayList<Hero>) in.readObject());
        in.close();
        fileIn.close();
    }
}
