package com.example.demo.Minimax;



import javax.persistence.*;


/**
 * The persistent class for the minimax database table.
 */
@Entity
@NamedQuery(name = "Minimax.findAll", query = "SELECT m FROM Minimax m")
public class Minimax {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column
    private String a;
    @Column
    private String b;
    @Column
    private String c;
    @Column
    private String d;
    @Column
    private String e;
    @Column
    private String f;
    @Column
    private String g;
    @Column
    private String h;
    @Column
    private String i;
    @Column
    private int moveCol;

    @Column
    private int moveRow;

    @Column
    private int score;


    public Minimax() {

    }

    public Minimax(Board board, Location loc, int score) {
        a = "" + board.get(0, 0);
        b = "" + board.get(0, 1);
        c = "" + board.get(0, 2);
        d = "" + board.get(1, 0);
        e = "" + board.get(1, 1);
        f = "" + board.get(1, 2);
        g = "" + board.get(2, 0);
        h = "" + board.get(2, 1);
        i = "" + board.get(2, 2);
        moveCol = loc.col;
        moveRow = loc.row;
        this.score = score;
    }

    public Board getBoard(){
        String[] spots = {a,b,c,d,e,f,g,h,i};
        char[][] b = new char[3][3];
        for(int r = 0;r<3; r++) {
            for (int c = 0; c < 3; c++) {
                b[r][c] = spots[r * 3 + c].charAt(0);
            }
        }
        return new Board(b);
    }

    public Long getId() {
        return id;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public String getG() {
        return g;
    }

    public void setG(String g) {
        this.g = g;
    }

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public int getMoveCol() {
        return moveCol;
    }

    public void setMoveCol(int moveCol) {
        this.moveCol = moveCol;
    }

    public int getMoveRow() {
        return moveRow;
    }

    public void setMoveRow(int moveRow) {
        this.moveRow = moveRow;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

