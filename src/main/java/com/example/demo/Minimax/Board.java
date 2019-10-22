package com.example.demo.Minimax;


import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Component
public class Board implements Serializable {


    protected char[][] board;

    public Board() {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        this.board = board;
    }

    public Board(String ... spots){
        board = new char[3][3];
        for(int r = 0;r<3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = spots[r * 3 + c].charAt(0);
            }
        }
    }

    public Board(char[][] board) {
        this.board = board;
    }

    public char get(int row, int col) {
        return board[row][col];
    }

    public void set(int row, int col, char val) {
        board[row][col] = val;
    }

    public void set(Location l, char val) {
        board[l.row][l.col] = val;
    }

    public void set(Location l){
        char val = 'O';
        if(moves()%2==0){
            val = 'X';
        }
        board[l.row][l.col] = val;
    }

    public void reset() {
        char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        this.board = board;
    }

    public void clearSpot(Location l) {
        board[l.row][l.col] = ' ';
    }

    public char gameResult() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' && board[i][1] != ' ' && board[i][2] != ' ') {
                if (board[i][0] == (board[i][1]) && board[i][0] == (board[i][2])) {
                    //System.out.println(board[i][0]+ "won: 80");
                    return board[i][0];
                }
            }
            if (board[0][i] != ' ' && board[1][i] != ' ' && board[2][i] != ' ') {
                if (board[0][i] == (board[1][i]) && board[0][i] == (board[2][i])) {
                    //System.out.println(board[0][i]+ "won: 86");
                    return board[0][i];
                }
            }

        }
        if (board[0][0] != ' ' && board[1][1] != ' ' && board[2][2] != ' ') {
            if (board[0][0] == (board[1][1]) && board[0][0] == (board[2][2]) && board[0][0] != ' ') {
                //System.out.println(board[0][0]+ "won: 94");
                return board[0][0];
            }
        }
        if (board[0][2] != ' ' && board[1][1] != ' ' && board[2][0] != ' ') {
            if (board[0][2] == (board[1][1]) && board[0][2] == (board[2][0]) && board[0][2] != ' ') {
                //System.out.println(board[0][2]+ "won :100");
                return board[0][2];
            }
        }
        if (this.full())
            return 'T';
        return 'N';
    }

    public int getScore() {
        char s = gameResult();
        if (s == 'O')
            return -1;
        else if (s == 'X')
            return 1;
        else
            return 0;
    }

    public boolean gameOver() {
        if (full() || getScore() != 0) {
            return true;
        }
        return false;
    }

    public boolean full() {
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] == ' ')
                    return false;
        return true;
    }

    public int moves() {
        int count = 0;
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (board[r][c] != ' ')
                    count++;
        return count;
    }

    public boolean validMove(Location l) {
        if (board[l.row][l.col] == ' ') return true;
        return false;
    }

    public boolean validMove(int row, int col){
        if(board[row][col]==' ') return true;
        return false;
    }

    public boolean Xspot(int row, int col){
        if(board[row][col]=='X') return true;
        return false;
    }

    public List<Location> availableMoves() {
        List<Location> l = new ArrayList<>();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == ' ')
                    l.add(new Location(r, c));
            }
        }
        return l;
    }

    /**
     * @param letter checking if this letter has 2 letters in a row that hasn't been blocked
     * @return locations that would make the letter win in one move
     */
    public List<Location> twoRow(char letter) {
        List<Location> l = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == letter && board[i][1] == letter && board[i][2] == ' ')
                l.add(new Location(i, 2));
            else if (board[i][0] == letter && board[i][1] == ' ' && board[i][2] == letter)
                l.add(new Location(i, 1));
            else if (board[i][0] == ' ' && board[i][1] == letter && board[i][2] == letter)
                l.add(new Location(i, 0));
            else if (board[0][i] == letter && board[1][i] == letter && board[2][i] == ' ')
                l.add(new Location(2, i));
            else if (board[0][i] == letter && board[1][i] == ' ' && board[2][i] == letter)
                l.add(new Location(1, i));
            else if (board[0][i] == ' ' && board[1][i] == letter && board[2][i] == letter)
                l.add(new Location(0, i));
        }
        if (board[0][0] == letter && board[1][1] == letter && board[2][2] == ' ')
            l.add(new Location(2, 2));
        else if (board[0][0] == letter && board[1][1] == ' ' && board[2][2] == letter)
            l.add(new Location(1, 1));
        else if (board[0][0] == ' ' && board[1][1] == letter && board[2][2] == letter)
            l.add(new Location(0, 0));
        else if (board[0][2] == letter && board[1][1] == letter && board[2][0] == ' ')
            l.add(new Location(2, 0));
        else if (board[0][2] == letter && board[1][1] == ' ' && board[2][0] == letter)
            l.add(new Location(1, 1));
        else if (board[0][2] == ' ' && board[1][1] == letter && board[2][0] == letter)
            l.add(new Location(0, 2));

        return l;
    }

    public String toString(){
        String x = "";
        x += "+———+———+———+\n";
        x += "| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |\n";
        x += "+———+———+———+\n";
        x += "| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |\n";
        x += "+———+———+———+\n";
        x += "| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |\n";
        x += "+———+———+———+\n";
        return x;
    }

    public void printMoveChoices() {
        String x = "";
        x += "+———+———+———+\n";
        x += "| " + (board[0][0] == ' ' ? "a" : board[0][0]) + " | " + (board[0][1] == ' ' ? "b" : board[0][1]) + " | " + (board[0][2] == ' ' ? "c" : board[0][2]) + " |\n";
        x += "+———+———+———+\n";
        x += "| " + (board[1][0] == ' ' ? "d" : board[1][0]) + " | " + (board[1][1] == ' ' ? "e" : board[1][1]) + " | " + (board[1][2] == ' ' ? "f" : board[1][2]) + " |\n";
        x += "+———+———+———+\n";
        x += "| " + (board[2][0] == ' ' ? "g" : board[2][0]) + " | " + (board[2][1] == ' ' ? "h" : board[2][1]) + " | " + (board[2][2] == ' ' ? "i" : board[2][2]) + " |\n";
        x += "+———+———+———+\n";
        System.out.println(x);
    }

    public void print() {
        System.out.println(this.toString());
    }




}
