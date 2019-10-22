package com.example.demo.Minimax;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Computer {

    private MinimaxService ms;

    public int difficulty;

    @Autowired
    public Computer(MinimaxService ms) {
        this.ms = ms;
        difficulty = 0;
    }

    public Computer(MinimaxService ms, int difficulty){
        this.ms = ms;
        this.difficulty = difficulty;
    }

    public void setDifficulty(Integer difficulty){
        this.difficulty = difficulty;
    }

    public Board move(Board b) {

        if(b.gameOver())
            return b;
        char letter = 'O';
        if (b.moves() % 2 == 0)
            letter = 'X';


        if (difficulty == 1)
            return medMove(b, letter);
        else if (difficulty == 2)
            return hardMove(b, letter);
        else if(difficulty==3) {
            if (ms.allMovesStored(b)) {
                Location l = ms.bestMove(b);
                b.set(l, letter);
                return b;
            } else {
                minimax(b, letter == 'X', b.moves());
                return move(b);
            }
        }
        else{
            return easyMove(b, letter);
        }
    }

    public Board easyMove(Board b, char letter) {
        double r = Math.random() * 3;
        double c = Math.random() * 3;
        if (b.get((int) r, (int) c) == ' ') {
            b.set((int) r, (int) c, letter);
            return b;
        }
        return easyMove(b, letter);
    }

    public Board medMove(Board b, char letter) {
        // 50% chance of a strategic move
        double random = Math.random() * 2;
        boolean favorableOdds = ((int) random) == 0;
        if (favorableOdds)
            return hardMove(b, letter);
        return easyMove(b, letter);
    }

    public Board hardMove(Board b, char letter) {
        if (offense(b).size() > 0) {
            double r = Math.random() * offense(b).size();
            Location l = offense(b).get((int) r);
            b.set(l.row, l.col, letter);
            return b;
        } else if (defend(b).size() > 0) {
            double r = Math.random() * defend(b).size();
            Location l = defend(b).get((int) r);
            b.set(l.row, l.col, letter);
            return b;
        }
        return easyMove(b, letter);
    }

    public int minimax(Board board, Boolean optimizeForX, int depth) {
        if (ms.allMovesStored(board)) {
            return ms.bestPossibleScore(board, optimizeForX);
        } else if (board.gameOver()) {
            if (board.getScore() == 0) {
                return board.getScore();
            }
            return board.getScore();
        }
        if (optimizeForX) {
            int bestVal = -20;
            List<Location> possibleMoves = board.availableMoves();
            for (Location move : possibleMoves) {
                board.set(move, 'X');
                int value = minimax(board, false, depth + 1);
                board.clearSpot(move);
                ms.store(board, move, value);
                bestVal = Math.max(bestVal, value);
            }
            return bestVal;
        } else {
            int bestVal = 20;
            List<Location> possibleMoves = board.availableMoves();
            for (Location move : possibleMoves) {
                board.set(move, 'O');
                int value = minimax(board, true, depth + 1);
                board.clearSpot(move);
                ms.store(board, move, value);
                bestVal = Math.min(bestVal, value);
            }
            return bestVal;
        }

    }


    /**
     * @param b a board state
     * @return locations that prevent oponent from winning in their next turn
     */
    public List<Location> defend(Board b) {
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<Location> toReturn = new ArrayList();
        char opponent = 'O';
        if (b.moves() % 2 == 1)
            opponent = 'X';
        return b.twoRow(opponent);
    }

    /**
     * @param b a board state
     * @return locations that would make player win in one move
     */
    public List<Location> offense(Board b) {
        @SuppressWarnings({"unchecked", "rawtypes"})
        List<Location> toReturn = new ArrayList();
        char letter = 'O';
        if (b.moves() % 2 == 0)
            letter = 'X';
        return b.twoRow(letter);
    }


}
