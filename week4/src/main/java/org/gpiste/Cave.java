package org.gpiste;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Cave implements Serializable{
    Player player;
    ArrayList<Monster> monsters = new ArrayList<Monster>();


    public Cave (Player player) {
        this.player = player;
    }

    public void addMonster(Monster monster) {
        monsters.add(monster);
    }

    public void deleteMonster(Monster monster) {
        monsters.remove(monster);
    }

    public void listMonsters() {
        int i = 1;
        System.out.println("Luolan hirviöt:");
        for (Monster monster : monsters) {
            System.out.print(i + ": ");
            monster.printInfo();
            i++;
        }
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    public void saveGame(String filename) {
        try {
            ObjectOutputStream gameSaver = new ObjectOutputStream(new FileOutputStream(filename));
            gameSaver.writeObject(this);
            gameSaver.close();
            System.out.println("Peli tallennettiin tiedostoon " + filename);
        
        } catch (IOException e) {
            System.out.println("Pelin tallentaminen ei onnistunut");
        }
    }

    public static Cave loadGame(String filename) {
        try {
            ObjectInputStream gameLoader = new ObjectInputStream(new FileInputStream(filename));
            Cave loadedCave = (Cave) gameLoader.readObject();
            gameLoader.close();
            System.out.println("Peli ladattu tiedostosta " + filename + ". Tervetuloa takaisin, " + loadedCave.player.getName() + ".");
            return loadedCave;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Pelin lataaminen ei onnistunut");
            return null;
        }
    }
}
