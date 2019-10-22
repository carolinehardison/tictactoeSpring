package com.example.demo.tictactoe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface MinimaxRepository extends CrudRepository<Minimax, Long> {

    @Query(value = "select COUNT(*) from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i", nativeQuery = true)
    int countBoards(@Param("a") char a, @Param("b") char b, @Param("c") char c, @Param("d") char d, @Param("e") char e, @Param("f") char f,
                    @Param("g") char g, @Param("h") char h, @Param("i") char i);

    @Query(value = "select score from Minimax u where u.a = :a AND u.b =:b AND u.c=:c AND u.d= :d AND u.e=:e AND u.f=:f AND u.g=:g AND u.h=:h AND u.i=:i",nativeQuery = true)
    int getScore(@Param("a") char a, @Param("b") char b, @Param("c") char c, @Param("d") char d, @Param("e") char e, @Param("f") char f,
                 @Param("g") char g, @Param("h") char h, @Param("i") char i);

}

