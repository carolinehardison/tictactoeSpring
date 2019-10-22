package com.example.demo.tictactoe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/minimax")
public class MinimaxController {

    private MinimaxService minimaxService;

    @Autowired
    MinimaxController (MinimaxService minimaxService){
        this.minimaxService = minimaxService;
    }

    @RequestMapping("/getAll")
    public String getAll(Model model) {
        List<Minimax> students = minimaxService.getAll();
        model.addAttribute("minimaxes", students);
        return "minimax";
    }

}
