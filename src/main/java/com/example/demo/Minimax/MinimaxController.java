//package com.example.demo.Minimax;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/minimax")
//public class MinimaxController {
//
//
////
////    @Autowired
////    private MinimaxService minimaxService;
//
//
//
//    MinimaxController (){
//
//    }
//
//
//
//
////    @RequestMapping("/getAll")
////    public String getAll(Model model) {
////        List<Minimax> minimaxes = repository.findAll();
////        model.addAttribute("minimaxes", minimaxes);
////        return "minimaxes";
////    }
//
//    @RequestMapping("/getAll")
//    public String getAll(Model model) {
//        List<Minimax> students = minimaxService.getAll();
//        model.addAttribute("minimaxes", students);
//        return "minimax";
//    }
//
////    @GetMapping("/{id}")
////    public Resource<Minimax> one(@PathVariable Long id) {
////
////        Minimax minimax = repository.findById(id)
////                .orElseThrow(() -> new MinimaxNotFoundException(id));
////
////        return assembler.toResource(minimax);
////    }
//
//
//
//
//
//
//
////    @PostMapping("")
////    Minimax newMinimax(@RequestBody Minimax newMinimax) {
////        model.addAttribute("minimaxes", minimaxService.getAll());
////        return repository.save(newMinimax);
////    }
////
////    // Single item
////
////
////
////    @PutMapping("/{id}")
////    Minimax replaceMinimax(@RequestBody Minimax newMinimax, @PathVariable Long id) {
////
////        return repository.findById(id)
////                .map(minimax -> {
////                    minimax.setA(newMinimax.getA());
////                    minimax.setB(newMinimax.getB());
////                    minimax.setC(newMinimax.getC());
////                    minimax.setD(newMinimax.getD());
////                    minimax.setE(newMinimax.getE());
////                    minimax.setF(newMinimax.getF());
////                    minimax.setG(newMinimax.getG());
////                    minimax.setH(newMinimax.getH());
////                    minimax.setI(newMinimax.getI());
////                    minimax.setMoveCol(newMinimax.getMoveCol());
////                    minimax.setMoveRow(newMinimax.getMoveRow());
////                    minimax.setScore(newMinimax.getScore());
////                    return repository.save(minimax);
////                })
////                .orElseGet(() -> {
////                    newMinimax.setId(id);
////                    return repository.save(newMinimax);
////                });
////    }
////
////    @DeleteMapping("/{id}")
////    void deleteMinimax(@PathVariable Long id) {
////        repository.deleteById(id);
////    }
//
//}
