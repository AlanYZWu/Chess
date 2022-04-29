package org.cis120.chess;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Board extends JPanel {
    private final ChessGame chess;
    private final JLabel status;
    private Point start;

    private static final int BOARD_HEIGHT = 800;
    private static final int BOARD_WIDTH = 800;

    public Board(JLabel status) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);

        this.chess = new ChessGame();
        this.status = status;
        this.start = null;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (start == null) {
                    start = e.getPoint();
                    updateStatus(start.y / 100, start.x / 100);
                } else {
                    Point p = e.getPoint();


                    chess.playTurn(start.y / 100, start.x / 100,
                            p.y / 100, p.x / 100);
                    start = null;
                    updateStatus();
                    chess.promote();
                }

                repaint();
            }
        });
    }

    public void reset() {
        this.chess.reset();
        this.status.setText("");
        repaint();
        requestFocusInWindow();
    }

    public void updateStatus(int r, int c) {
        if (r > 7 || r < 0 || c > 7 || c < 0) {
            return;
        }

        if (this.chess.checkWinner()) {
            this.status.setText("Murdered!");
        } else {
            if (this.start == null) {
                this.status.setText("");
            } else {
                this.status.setText(chess.getSquare(r, c).toString() + " selected");
            }
        }
    }

    public void updateStatus() {
        if (this.chess.checkWinner()) {
            this.status.setText("Murdered!");
        } else {
            if (this.start == null) {
                this.status.setText("");
            } else {
                this.status.setText("");
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int r = 0; r < 8; r ++) {
            for (int c = 0; c < 8; c++) {
                this.chess.getSquare(r, c).draw(g);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(BOARD_WIDTH, BOARD_HEIGHT);
    }
}