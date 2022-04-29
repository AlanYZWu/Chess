package org.cis120.chess;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Knight extends Piece {
    private BufferedImage img;

    public Knight(boolean white) {
        super(white);
        try {
            if (white) {
                String imgFileWhite = "files/WhiteKnight.png";
                img = ImageIO.read(new File(imgFileWhite));
            } else {
                String imgFileBlack = "files/BlackKnight.png";
                img = ImageIO.read(new File(imgFileBlack));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public BufferedImage getImage() {
        return this.img;
    }

    @Override
    public void setVision(Square start) {
        this.getVisionSet().clear();
        try {
            this.getVisionSet().add(new Square(start.getRow() - 2, start.getColumn() - 1));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() - 2, start.getColumn() + 1));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() - 2));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() + 2));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() + 2, start.getColumn() - 1));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() + 2, start.getColumn() + 1));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() - 2));
        } catch (IllegalArgumentException ignored) {

        }

        try {
            this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() + 2));
        } catch (IllegalArgumentException ignored) {

        }
    }

    @Override
    public boolean legalMove(Square end) {
        if (!end.containsPiece()) {
            return !this.getVisionSet().add(end);
        } else {
            if (end.getPiece().getColor() != this.getColor()) {
                return !this.getVisionSet().add(end);
            }

            return false;
        }
    }

    @Override
    public boolean legalMove(Square start, Square end) {
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor()) {
            return "White Knight";
        }

        return "Black Knight";
    }

    @Override
    public void setVision(Square start, Square[][] board) {
        return;
    }
}
