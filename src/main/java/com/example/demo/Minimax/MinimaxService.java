package com.example.demo.Minimax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MinimaxService {

    private final MinimaxRepository repository;


    @Autowired
    public MinimaxService(MinimaxRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean exists(Board b, Location loc, int score) {
        Minimax m = repository.findByAll("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2),
                loc.row, loc.col, score);
        if (m != null) {
            return true;
        }
        return false;
    }

    @Transactional
    public List<Minimax> getAll(){
        return (List<Minimax>) repository.findAll();
    }

    /**
     * @param b a Board state
     * @return whether or not all possible Locations have been worked out through minimax algorithm
     */
    @Transactional
    public boolean allMovesStored(Board b) {
        int possibleMoves = 9 - b.moves(); //free spaces on the board
        if (count(b) > possibleMoves) {
            deleteDuplicated(getBoards(b, 1));
            deleteDuplicated(getBoards(b, 0));
            deleteDuplicated(getBoards(b, -1));
        }

        if (count(b) == possibleMoves)
            return true;
        return false;

    }

    /**
     * Delete all duplicate Minimaxes stored
     *
     * @param mins Array of Minimax Ids
     */
    @Transactional
    protected void deleteDuplicated(Long[] mins) {
        List<Minimax> toDelete = new ArrayList<>();
        for (int i = 0; i < mins.length; i++) {
            Minimax temp1 = getMinimax(mins[i]);
            for (int z = i + 1; z < mins.length; z++) {
                Minimax temp2 = getMinimax(mins[z]);
                if (duplicate(temp1, temp2)) {
                    toDelete.add(temp1);
                    System.out.println("Deleted a duplicate");
                    break;
                }
            }
        }
        for (Minimax m : toDelete)
            repository.delete(m);

    }

    private boolean duplicate(Minimax m1, Minimax m2) {
        if (m1.getA().equals(m2.getA()) && m1.getB().equals(m2.getB()) && m1.getC().equals(m2.getC()) &&
                m1.getD().equals(m2.getD()) && m1.getE().equals(m2.getE()) && m1.getF().equals(m2.getF()) &&
                m1.getG().equals(m2.getG()) && m1.getH().equals(m2.getH()) && m1.getI().equals(m2.getI()) &&
                m1.getMoveRow() == m2.getMoveRow() && m1.getMoveCol() == m2.getMoveCol())
            return true;
        return false;
    }

    /**
     * @param id a minimax object ID
     * @return the minimax with that ID or null
     */
    @Transactional
    public Minimax getMinimax(Long id) {
        Optional<Minimax> m = repository.findById(id);
        if (m.isPresent()) return m.get();
        return null;
    }

    /**
     * @param b     The Board we want to find in database
     * @param score the score of the board
     * @return Ids of boards with that score in db
     */
    @Transactional
    public Long[] getBoards(Board b, int score) {
        Long[] m = repository.findByBoard("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2), score);
        return m;

    }

    /**
     * @param b     The Board that we want to check if in database
     * @param score The score of the Board that we want to find
     * @return how many boards with that score are in database
     */
    @Transactional
    public int count(Board b, int score) {
        int m = repository.countBoardsOfScore("" + b.get(0, 0),
                "" + b.get(0, 1),
                "" + b.get(0, 2),
                "" + b.get(1, 0),
                "" + b.get(1, 1),
                "" + b.get(1, 2),
                "" + b.get(2, 0),
                "" + b.get(2, 1),
                "" + b.get(2, 2), score);
        return m;
    }

    /**
     * @param b a board state
     * @return how many times the board is in database
     */
    @Transactional
    public int count(Board b) {
        return count(b, 1) + count(b, 0) + count(b, -1);
    }

    /**
     * Get a random Minimax Location from a given List of Minimaxes
     *
     * @param ident a list of Minimax IDs
     * @return one of the IDs from a random minimax in the list or an invalid location (if the parameter list was empty)
     */
    public Location getLocation(Long[] ident) {
        int ind = (int) (Math.random() * ident.length);
        Optional<Minimax> m = repository.findById(ident[ind]);
        if (m.isPresent()) {
            Minimax s = m.get();
            return new Location(s.getMoveRow(),s.getMoveCol());
        }
        return new Location(-1, -1);

    }

    /**
     * @param b a board state
     * @return a location that maximizes players chance of winning
     */
    @Transactional
    public Location bestMove(Board b) {
        boolean optimizeForX = (b.moves() % 2 == 0);
        Long[] ident = getBoards(b, bestPossibleScore(b, optimizeForX));
        return getLocation(ident);
    }

    /**
     * @param b            a board state
     * @param optimizeForX true if it is X's turn
     * @return maximum score that player can achieve
     */
    @Transactional
    public int bestPossibleScore(Board b, Boolean optimizeForX) {
        if (optimizeForX) {
            if (count(b, 1) > 0)
                return 1;
            if (count(b, 0) > 0)
                return 0;
            return -1;
        } else { //strategizing for O
            if (count(b, -1) > 0)
                return -1;
            if (count(b, 0) > 0)
                return 0;
            return 1;
        }
    }

    public boolean store(Board b, Location l, int score) {
        if (!exists(b, l, score)) {
            Minimax m = new Minimax(b, l, score);
            repository.save(m);
            return true;
        }

        return false;
    }


}
