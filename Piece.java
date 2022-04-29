package org.cis120.chess;
import java.awt.image.BufferedImage;
import java.util.TreeSet;

public abstract class Piece {
    private final boolean white;
    private final TreeSet<Square> vision;
    private BufferedImage img;

    // Constructor
    public Piece(boolean color) {
        this.white = color;
        this.vision = new TreeSet<Square>();
    }

    // Getters
    public boolean getColor() {
        return white;
    }

    public TreeSet<Square> getVisionSet() {
        return this.vision;
    }

    public BufferedImage getImage() {
        return this.img;
    }

    // Setters
    public abstract void setVision(Square start);

    public abstract void setVision(Square start, Square[][] board);

    // Other methods
    public abstract boolean legalMove(Square end);

    public abstract boolean legalMove(Square start, Square end);

    public abstract String toString();
}
