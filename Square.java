package org.cis120.chess;
import java.awt.*;

public class Square implements Comparable<Square> {
    private final int row;
    private final int column;
    private Piece piece;

    public Square(int r, int c, Piece p) {
        if (this.outOfBounds()) {
            throw new IllegalArgumentException();
        }

        this.row = r;
        this.column = c;
        this.piece = p;
    }

    public Square(int r, int c) {
        if (r < 0 || r > 7 || c > 7 || c < 0) {
            throw new IllegalArgumentException();
        }

        this.row = r;
        this.column = c;
        this.piece = null;
    }

    // Getters
    public int getRow() {
        return this.row;
    }

    public int getColumn() {
        return this.column;
    }

    public Piece getPiece() {
        return this.piece;
    }


    // Setters
    public void setPiece(Piece p) {
        this.piece = p;
    }

    // Other Methods
    public boolean containsPiece() {
        return !(this.piece == null);
    }

    public boolean equals(Square s) {
        return this.getRow() == s.getRow() && this.getColumn() == s.getColumn();
    }

    public boolean outOfBounds() {
        return this.row > 7 || this.row < 0 || this.column > 7 || this.column < 0;
    }

    public String toString() {
        if (this.piece != null) {
            return this.piece.toString();
        } else {
            return "No Piece";
        }
    }

    public void draw(Graphics g) {
        Color light = new Color(241,217,181);
        Color dark = new Color(181,136,99);

        if ((this.row + this.column) % 2 == 0) {
            g.setColor(light);
        } else {
            g.setColor(dark);
        }

        g.fillRect(this.column * 100, this.row * 100, 100, 100);
        if (this.piece != null) {
            g.drawImage(this.piece.getImage(), column * 100 + 10, this.row * 100 + 10,
                    80, 80, null);
        }
    }

    @Override
    public int compareTo(Square s) {
        if (this.row > s.row) {
            return 1;
        } else if (this.row < s.row) {
            return -1;
        } else if (this.column > s.column) {
            return 1;
        } else if (this.column < s.column) {
            return -1;
        }

        return 0;
    }
}
