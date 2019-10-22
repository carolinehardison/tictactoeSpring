package com.example.demo.Minimax;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MinimaxRepository extends CrudRepository<Minimax, Long> {

    @Query(value = "select * from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i AND u.move_row=:move_row AND u.move_col=:move_col AND u.score=:score", nativeQuery = true)
    Minimax findByAll(@Param("a") String a, @Param("b") String b, @Param("c") String c, @Param("d") String d, @Param("e") String e, @Param("f") String f,
                      @Param("g") String g, @Param("h") String h, @Param("i") String i, @Param("move_row") int move_row, @Param("move_col") int move_col, @Param("score") int score);

    @Query(value = "select id from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i AND u.score = :score", nativeQuery = true)
    Long[] findByBoard(@Param("a") String a, @Param("b") String b, @Param("c") String c, @Param("d") String d, @Param("e") String e, @Param("f") String f,
                       @Param("g") String g, @Param("h") String h, @Param("i") String i, @Param("score") int score);

    @Query(value = "select COUNT(*) from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i AND u.score = :score", nativeQuery = true)
    int countBoardsOfScore(@Param("a") String a, @Param("b") String b, @Param("c") String c, @Param("d") String d, @Param("e") String e, @Param("f") String f,
                           @Param("g") String g, @Param("h") String h, @Param("i") String i, @Param("score") int score);

    @Query(value = "select COUNT(*) from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i", nativeQuery = true)
    int countBoards(@Param("a") String a, @Param("b") String b, @Param("c") String c, @Param("d") String d, @Param("e") String e, @Param("f") String f,
                    @Param("g") String g, @Param("h") String h, @Param("i") String i);




    //    @Query(value="select * from Minimax u where u.id = :id", nativeQuery = true)default
//    Minimax findById(Long id) {
//        Minimax m = (Minimax) findById(id);
//        return m;
//    }

    //@Query(value="select * from Minimax u where u.id = :id", nativeQuery = true)
    //Minimax findById(@Param("id") Long id);


}

