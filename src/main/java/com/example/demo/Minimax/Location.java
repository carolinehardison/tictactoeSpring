package com.example.demo.Minimax;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is meant as a shortcut for writng out the row and col of tic tac toe moves
 */
public class Location {


    public int row;
    public int col;
    public boolean valid;
    /**
     * the name in the mysql database
     */
    String correspondingLetter;

    /**
     * @param row row on tic tac toe board
     * @param col col on tic tac toe board
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
        valid = row >= 0 && row <= 2 && col >= 0 && col <= 2;
        setCorrespondingLetter();
    }

    public Location(String l){
        switch (l) {
            case "a":
                row = 0;
                col = 0;
            case "b":
                row = 0;
                col =  1;
            case "c":
                row = 0;
                col = 2;
            case "d":
                row = 1;
                col = 0;
            case "e":
                row = 1;
                col = 1;
            case "f":
                row = 1;
                col = 2;
            case "g":
                row = 2;
                col = 0;
            case "h":
                row = 2;
                col = 1;
            case "i":
                row = 2;
                col = 2;
            default:
                row = -1;
                col = -1;
        }
    }

    public static Location findByLetter(String l) {
        switch (l) {
            case "a":
                return new Location(0, 0);
            case "b":
                return new Location(0, 1);
            case "c":
                return new Location(0, 2);
            case "d":
                return new Location(1, 0);
            case "e":
                return new Location(1, 1);
            case "f":
                return new Location(1, 2);
            case "g":
                return new Location(2, 0);
            case "h":
                return new Location(2, 1);
            case "i":
                return new Location(2, 2);
            default:
                return new Location(-1, -1);
        }
    }

    /**
     * this method takes in what has been played and spits out what hasn't
     *
     * @param a all the Locations that have been selected
     * @return all locations that have not been selected
     */
    public static List<Location> Available(Location... a) {

        List<Location> loc = new ArrayList<>();
        loc.add(new Location(0, 0));
        loc.add(new Location(0, 1));
        loc.add(new Location(0, 2));
        loc.add(new Location(1, 0));
        loc.add(new Location(1, 1));
        loc.add(new Location(1, 2));
        loc.add(new Location(2, 0));
        loc.add(new Location(2, 1));
        loc.add(new Location(2, 2));
        for (Location temp : a) {

            for (int i = 0; i < loc.size(); i++) {
                Location check = loc.get(i);
                if (temp.col == check.col && temp.row == check.row) {
                    loc.remove(check);
                }

            }
        }
        return loc;


    }

    public void setCorrespondingLetter() {
        switch (row * 10 + col) {
            case 0:
                correspondingLetter = "a";
                break;
            case 1:
                correspondingLetter = "b";
                break;
            case 2:
                correspondingLetter = "c";
                break;
            case 10:
                correspondingLetter = "d";
                break;
            case 11:
                correspondingLetter = "e";
                break;
            case 12:
                correspondingLetter = "f";
                break;
            case 20:
                correspondingLetter = "g";
                break;
            case 21:
                correspondingLetter = "h";
                break;
            case 22:
                correspondingLetter = "i";
                break;
        }
    }


}
