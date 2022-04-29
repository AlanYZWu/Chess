package org.cis120.chess;
public class ChessGame {
    private Square[][] board;
    private boolean whiteTurn;
    private boolean gameWon;

    public ChessGame() {
        this.reset();
    }

    public void reset() {
        this.board = new Square[8][8];
        this.whiteTurn = true;
        this.gameWon = false;

        for (int row = 0; row < this.board.length; row++) {
            for (int col = 0; col < this.board[0].length; col++) {
                if (row == 0) {
                    if (col == 0 || col == 7) {
                        this.board[row][col] = new Square(row, col, new Rook(false));
                    } else if (col == 1 || col == 6) {
                        this.board[row][col] = new Square(row, col, new Knight(false));
                    } else if (col == 2 || col == 5) {
                        this.board[row][col] = new Square(row, col, new Bishop(false));
                    } else if (col == 3) {
                        this.board[row][col] = new Square(row, col, new Queen(false));
                    } else {
                        this.board[row][col] = new Square(row, col, new King(false));
                    }
                } else if (row == 1) {
                    this.board[row][col] = new Square(row, col, new Pawn(false));
                } else if (row == 6) {
                    this.board[row][col] = new Square(row, col, new Pawn(true));
                } else if (row == 7) {
                    if (col == 0 || col == 7) {
                        this.board[row][col] = new Square(row, col, new Rook(true));
                    } else if (col == 1 || col == 6) {
                        this.board[row][col] = new Square(row, col, new Knight(true));
                    } else if (col == 2 || col == 5) {
                        this.board[row][col] = new Square(row, col, new Bishop(true));
                    } else if (col == 3) {
                        this.board[row][col] = new Square(row, col, new Queen(true));
                    } else {
                        this.board[row][col] = new Square(row, col, new King(true));
                    }
                } else {
                    this.board[row][col] = new Square(row, col);
                }
            }
        }
    }

    public void playTurn(int rStart, int cStart, int rEnd, int cEnd) {
        try {
            if (!this.board[rStart][cStart].containsPiece() || gameWon) {
                return;
            } else if (this.board[rStart][cStart].getPiece().getColor() != this.whiteTurn) {
                return;
            }

            Piece temp = this.board[rStart][cStart].getPiece();
            if (temp instanceof Pawn) {
                temp.setVision(board[rStart][cStart]);
                if (!temp.legalMove(board[rStart][cStart], board[rEnd][cEnd])) {
                    return;
                }
            } else if (temp instanceof Bishop || temp instanceof Rook || temp instanceof Queen) {
                temp.setVision(board[rStart][cStart], this.board);
                if (!temp.legalMove(board[rEnd][cEnd])) {
                    return;
                }
            } else {
                temp.setVision(board[rStart][cStart]);
                if (!temp.legalMove(board[rEnd][cEnd])) {
                    return;
                }
            }

            board[rStart][cStart].setPiece(null);
            board[rEnd][cEnd].setPiece(temp);

            if (!this.checkWinner()) {
                this.whiteTurn = !this.whiteTurn;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
    }

    public boolean checkWinner() {
        boolean whiteKing = false;
        boolean blackKing = false;

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c].containsPiece() && board[r][c].getPiece() instanceof King) {
                    if (board[r][c].getPiece().getColor()) {
                        whiteKing = true;
                    } else {
                        blackKing = true;
                    }
                }
            }
        }

        return !(whiteKing && blackKing);
    }

    public Square getSquare(int r, int c) {
        return board[r][c];
    }

    public void promote() {
        for (int c = 0; c < 8; c++) {
            if (board[0][c].getPiece() instanceof Pawn) {
                board[0][c].setPiece(new Queen(true));
            } else if (board[7][c].getPiece() instanceof Pawn) {
                board[7][c].setPiece(new Queen(false));
            }
        }
    }
}
