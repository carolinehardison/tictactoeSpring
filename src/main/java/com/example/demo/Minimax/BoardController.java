package com.example.demo.Minimax;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board")
public class BoardController {

    int wins, losses, ties;
    private Board board;
    private Computer computer;
    private boolean PlayerFirst;

    @Autowired
    public BoardController(Computer computer){
        this.computer = computer;
        init();
    }

    public void init(){
        PlayerFirst = false;
        wins = 0;
        losses = 0;
        ties = 0;
        board = new Board();
        computer.setDifficulty(3);
    }

    @RequestMapping("/")
    public String board(Model model){
        if(board==null)
            this.init();
        if(board.moves()==0 && !PlayerFirst)
            board = computer.move(board);
        model.addAttribute("wins", wins);
        model.addAttribute("ties", ties);
        model.addAttribute("losses", losses);
        model.addAttribute("board", board);
        return "board";
    }

    @RequestMapping("/reset")
    public String reset(){
        board.reset();
        return  "redirect:/board/";
    }

    @RequestMapping("/modalPop")
    public String newGame(int difficulty, boolean isPlayerFirst) {
        board.reset();
        computer.setDifficulty(difficulty);
        System.out.println("Set difficulty to "+ difficulty);
        System.out.println("Player is first: "+ isPlayerFirst);
        this.PlayerFirst = isPlayerFirst;
        if(!isPlayerFirst){
            board = computer.move(board);
        }
        return "redirect:/board/";
    }


    @RequestMapping(value ="/play", method = {RequestMethod.PUT, RequestMethod.GET})
    public String play(String playerMove) {
        Location l = Location.findByLetter(playerMove);
        if(!board.gameOver() && board.validMove(l)) //move if game not over and spot is valid
            board.set(l);

        if(!board.gameOver()){ //after move, if game isn't over, computer move
            board = computer.move(board);
            if(board.gameOver()){
                if(board.getScore()==0)
                    ties++;
                else
                    losses++;
            }
        }else if(board.gameOver() && board.getScore()==0){
            ties++;
        }else if(!board.full()){ //game is over and board isn't full
            wins++;
        }
        return "redirect:/board/";
    }

    @RequestMapping(value="/difficulty", method = {RequestMethod.PUT, RequestMethod.GET})
    public String update(int difficulty) {
        System.out.println("difficulty changed to "+difficulty);
        computer.setDifficulty(difficulty);
        return "redirect:/board/";
    }
}

