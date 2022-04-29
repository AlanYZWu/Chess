package org.cis120.chess;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Pawn extends Piece {
    boolean moved;
    private BufferedImage img;

    public Pawn(boolean white) {
        super(white);
        this.moved = false;
        try {
            if (white) {
                String imgFileWhite = "files/WhitePawn.png";
                img = ImageIO.read(new File(imgFileWhite));
            } else {
                String imgFileBlack = "files/BlackPawn.png";
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
        if (this.getColor()) {
            if (start.getRow() > 0) {
                if (start.getColumn() > 0 && start.getColumn() < 7) {
                    this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() - 1));
                    this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() + 1));
                } else if (start.getColumn() == 0) {
                    this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() + 1));
                } else {
                    this.getVisionSet().add(new Square(start.getRow() - 1, start.getColumn() - 1));
                }
            }
        } else {
            if (start.getRow() < 6) {
                if (start.getColumn() > 0 && start.getColumn() < 7) {
                    this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() - 1));
                    this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() + 1));
                } else if (start.getColumn() == 0) {
                    this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() + 1));
                } else {
                    this.getVisionSet().add(new Square(start.getRow() + 1, start.getColumn() - 1));
                }
            }
        }
    }

    public boolean legalMove(Square start, Square end) {
        for (Square s : this.getVisionSet()) {
            if (end.containsPiece() && end.getPiece().getColor() != this.getColor()
                    && end.equals(s)) {
                return true;
            }
        }

        int r = end.getRow() - start.getRow();
        if (this.getColor()) {
            if (start.getColumn() == end.getColumn()) {
                if (!end.containsPiece()) {
                    if (!this.moved) {
                        return r == -1 || r == -2;
                    } else {
                        return r == -1;
                    }
                } else {
                    return false;
                }
            }
        } else {
            if (start.getColumn() == end.getColumn()) {
                if (!end.containsPiece()) {
                    if (!this.moved) {
                        return r == 1 || r == 2;
                    } else {
                        return r == 1;
                    }
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean legalMove(Square end) {
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor()) {
            return "White Pawn";
        }

        return "Black Pawn";
    }

    @Override
    public void setVision(Square start, Square[][] board) {
        return;
    }
}
