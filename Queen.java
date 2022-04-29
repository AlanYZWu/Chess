package org.cis120.chess;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Queen extends Piece {
    private BufferedImage img;

    public Queen(boolean white) {
        super(white);
        try {
            if (white) {
                String imgFileWhite = "files/WhiteQueen.png";
                img = ImageIO.read(new File(imgFileWhite));
            } else {
                String imgFileBlack = "files/BlackQueen.png";
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
        return;
    }

    @Override
    public void setVision(Square start, Square[][] board) {
        this.getVisionSet().clear();

        try {
            this.setVisionHelperUpRight(board[start.getRow() - 1][start.getColumn() + 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperUpLeft(board[start.getRow() - 1][start.getColumn() - 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperDownRight(board[start.getRow() + 1][start.getColumn() + 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperDownLeft(board[start.getRow() + 1][start.getColumn() - 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperLeft(board[start.getRow()][start.getColumn() - 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperRight(board[start.getRow()][start.getColumn() + 1], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            this.setVisionHelperUp(board[start.getRow() - 1][start.getColumn()], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

        try {
            this.setVisionHelperDown(board[start.getRow() + 1][start.getColumn()], board);
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperUpLeft(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                } else {
                    this.getVisionSet().add(start);
                }
            } else {
                this.getVisionSet().add(start);
                setVisionHelperUpLeft(board[start.getRow() - 1][start.getColumn() - 1], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperUpRight(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }

                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperUpRight(board[start.getRow() - 1][start.getColumn() + 1], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperDownLeft(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }

                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperDownLeft(board[start.getRow() + 1][start.getColumn() - 1], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperDownRight(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }

                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperDownRight(board[start.getRow() + 1][start.getColumn() + 1], board);

            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperLeft(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }

                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperLeft(board[start.getRow()][start.getColumn() - 1], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperRight(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }
                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperRight(board[start.getRow()][start.getColumn() + 1], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperUp(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }
                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperUp(board[start.getRow() - 1][start.getColumn()], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    private void setVisionHelperDown(Square start, Square[][] board) {
        try {
            if (start.containsPiece()) {
                if (start.getPiece().getColor() == this.getColor()) {
                    return;
                }
                this.getVisionSet().add(start);
            } else {
                this.getVisionSet().add(start);
                setVisionHelperDown(board[start.getRow() + 1][start.getColumn()], board);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    @Override
    public boolean legalMove(Square end) {
        for (Square s : this.getVisionSet()) {
            if (s.equals(end)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean legalMove(Square start, Square end) {
        return false;
    }

    @Override
    public String toString() {
        if (this.getColor()) {
            return "White Queen";
        }

        return "Black Queen";
    }
}