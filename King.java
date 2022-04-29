package org.cis120.chess;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class King extends Piece {
    private BufferedImage img;

    // Constructor
    public King(boolean white) {
        super(white);
        try {
            if (white) {
                String imgFileWhite = "files/WhiteKing.png";
                img = ImageIO.read(new File(imgFileWhite));
            } else {
                String imgFileBlack = "files/BlackKing.png";
                img = ImageIO.read(new File(imgFileBlack));
            }
        } catch (IOException e) {
            System.out.println("Internal Error:" + e.getMessage());
        }
    }

    public BufferedImage getImage() {
        return this.img;
    }

    // Other Methods
    @Override
    public void setVision(Square start) {
        this.getVisionSet().clear();

        for (int r = start.getRow() - 1; r < start.getRow() + 2; r++) {
            for (int c = start.getColumn() - 1; c < start.getColumn() + 2; c++) {
                if (r >= 0 && r < 8 && c >= 0 && c < 8 &&
                        (r != start.getRow() || c != start.getColumn())) {
                    this.getVisionSet().add(new Square(r, c));
                }
            }
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
            return "White King";
        }

        return "Black King";
    }

    @Override
    public void setVision(Square start, Square[][] board) {
        return;
    }
}
