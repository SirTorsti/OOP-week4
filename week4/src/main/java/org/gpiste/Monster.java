package org.gpiste;

import java.io.Serializable;

public class Monster implements Serializable {
    private String type;
    private int health;
    private Cave cave;

    public Monster(String type, int health, Cave cave) {
        this.type = type;
        this.health = health;
        this.cave = cave;
    }

    public Monster(String type, int health) {
        this.type = type;
        this.health = health;
    }

    public String getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public void setCave(Cave cave) {
        this.cave = cave;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health < 0) {
            health = 0;
        }
        if (health == 0) {
            System.out.println(type + " on kuollut.");
            cave.deleteMonster(this);
        } else {
            System.out.println("Hirviöllä on " + health + " elämää jäljellä.");
        }
    }

    public void printInfo(int index) {
        System.out.println(index + ": " + type + "/" + health + "HP");
    }
}
