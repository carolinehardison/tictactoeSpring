package com.example.demo.tictactoe;

/**
 * this class is meant as a shortcut for writng out the row and col of tic tac toe moves
 */
public class Location {


    public int row;
    public int col;
    private String correspondingLetter;
    /**
     * the name in the mysql database
     */

    /**
     * @param row row on tic tac toe board
     * @param col col on tic tac toe board
     */
    public Location(int row, int col) {
        this.row = row;
        this.col = col;
        setCorrespondingLetter();
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
