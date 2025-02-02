package org.gpiste;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Syötä pelaajan nimi: ");
        String playerName = sc.nextLine();
        Player player = new Player(playerName);
        Cave cave = new Cave(player);

        boolean exit = false;
        
        while (!exit) {
            System.out.println("1) Lisää luolaan hirviö\n2) Listaa hirviöt\n3) Hyökkää hirviöön\n4) Tallenna peli\n5) Lataa peli\n0) Lopeta peli");
            int i = 0;
            String stringInput = sc.nextLine();
            i = Integer.parseInt(stringInput);
            
            switch(i) {
                case 1:
                    System.out.println("Anna hirviön tyyppi: ");
                    String monsterType = sc.nextLine();
                    System.out.println("Anna hirviön elämän määrä numerona: ");
                    int monsterHealth = Integer.parseInt(sc.nextLine());
                    Monster monster = new Monster(monsterType, monsterHealth);
                    cave.addMonster(monster);
                    break;

                case 2:
                    cave.listMonsters();
                    break;

                case 3:
                    System.out.println("Valitse hirviö, johon hyökätä: ");
                    cave.listMonstersWithoutHeader();
                    int choice = Integer.parseInt(sc.nextLine());
                    if (choice > 0 && choice <= cave.getMonsters().size()) {
                        Monster targetMonster = cave.getMonsters().get(choice - 1);
                        player.attack(targetMonster);
                    } else {
                        System.out.println("Virheellinen valinta.");
                    }
                    
                    break;

                case 4:
                    System.out.println("Anna tiedoston nimi, johon peli tallentaa: ");
                    String saveFilename = sc.nextLine();
                    cave.saveGame(saveFilename);
                    break;

                case 5:
                    System.out.println("Anna tiedoston nimi, josta peli ladataan: ");
                    String loadFilename = sc.nextLine();
                    Cave loadedCave = Cave.loadGame(loadFilename);
                    if (loadedCave != null) {
                        cave = loadedCave;
                        player = cave.player;
                    }
                    break;

                case 0:
                    System.out.println("Peli päättyy. Kiitos pelaamisesta!");
                    exit = true;
                    break;

                default:
                    System.out.println("Virheellinen valinta.");
                    break;
            }
        }
        sc.close();
    }


}
