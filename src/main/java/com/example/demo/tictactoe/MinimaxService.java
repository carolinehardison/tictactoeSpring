package com.example.demo.tictactoe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class MinimaxService {

    private final MinimaxRepository repository;

    private Location bestMove;

    @Autowired
    public MinimaxService(MinimaxRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public boolean allMovesStored(Board b) {
        int depth = b.moves();
        char player = depth%2==0? 'X':'O';
        int count = 0;
        for(int r = 0; r<3; r++) {
            for (int c = 0; c < 3; c++) {
                if (b.validMove(r, c)) {
                    b.set(r, c, player);
                    int stored = repository.countBoards(b.get(0, 0), b.get(0, 1), b.get(0, 2),
                            b.get(1, 0), b.get(1, 1), b.get(1, 2),
                            b.get(2, 0), b.get(2, 1), b.get(2, 2)) > 0 ? 1 : 0;
                    count = count + stored;
                    b.set(r,c,' ');
                }

            }
        }
        if(count == 9 - b.moves())
            return true;
        return false;

    }

    public List<Minimax> getAll(){
        return (List<Minimax>) repository.findAll();
    }

    @Transactional
    public int bestPossibleScore(Board b, boolean forX) {
        if(forX) {
            int best = -10;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (b.validMove(r, c)) {
                        b.set(r, c, 'X');
                        int score = repository.getScore(b.get(0, 0), b.get(0, 1), b.get(0, 2),
                                b.get(1, 0), b.get(1, 1), b.get(1, 2),
                                b.get(2, 0), b.get(2, 1), b.get(2, 2));
                        if(score>best)
                            bestMove = new Location(r,c);
                        best = Math.max(best, score);
                        b.set(r, c, ' ');
                    }
                }
            }
            return best;
        }else{
            int best = 10;
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    if (b.validMove(r, c)) {
                        b.set(r, c, 'O');
                        int score = repository.getScore(b.get(0, 0), b.get(0, 1), b.get(0, 2),
                                b.get(1, 0), b.get(1, 1), b.get(1, 2),
                                b.get(2, 0), b.get(2, 1), b.get(2, 2));
                        if(score < best)
                            bestMove = new Location(r,c);
                        best = Math.min(best, score);
                        b.set(r, c, ' ');
                    }
                }
            }
            return best;
        }
    }

    public boolean store(Board b, int score) {
        int count = repository.countBoards(b.get(0, 0), b.get(0, 1), b.get(0, 2),
                b.get(1, 0), b.get(1, 1), b.get(1, 2),
                b.get(2, 0), b.get(2, 1), b.get(2, 2));
        if (count==0) {
            Minimax m = new Minimax(b, score);
            repository.save(m);
            return true;
        }
        return false;
    }

    @Transactional
    public Location bestMove(Board b) {
        boolean optimizeForX = (b.moves() % 2 == 0);
        bestPossibleScore(b, optimizeForX);
        return bestMove;
    }

}
