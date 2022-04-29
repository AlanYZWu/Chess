package org.cis120.chess;
import javax.swing.*;
import java.awt.*;


public class RunChess implements Runnable {
    @Override
    public void run() {
        final JFrame frame = new JFrame("Chess");
        frame.setLocation(400, 0);

        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("");
        status_panel.add(status);

        final JTextArea rules = new JTextArea("Rules:\n" +
                "King: The king can only move one square in any\n" +
                "direction - up, down, to the sides, and diagonally.\n" +
                "You lose if your king is captured.\n\n" +
                "Queen: can move in any one straight direction,\n" +
                "forward, backward, sideways, or diagonally, as\n" +
                "far as possible as long as she does not move\n" +
                "through any of her own pieces.\n\n" +
                "Rook: Can move as far as it wants, but only forward,\n" +
                "backward, and to the sides.\n\n" +
                "Bishop: Can may move as far as it wants, but only \n" +
                "diagonally.\n\n" +
                "Knight: Moves two squares in one direction, and\n" +
                "then one more move at a 90-degree angle like the\n" +
                "shape of an L.\n\n" +
                "Pawn: Moves and capture in different ways; moves\n" +
                "forward but captures diagonally. Pawns can only move\n" +
                "forward one square at a time, except for their very\n" +
                "first move where they can move forward two squares.\n\n\n" +
                "How to play: Click the piece you wish to move, then\n" +
                "click the square you want to move the piece to. You\n" +
                "can deselect pieces by clicking an illegal square.\n" +
                "If you first click on an empty square, you can deselect\n" +
                "in the same way.\n\n\n" +
                "Special Rules:\n" +
                "No castling.\n" +
                "Game ends when a king is captured, not when checkmate is \n" +
                "delivered. Kings can be moved into check, and don't have to\n" +
                "move out of check.");
        frame.add(rules, BorderLayout.EAST);


        final Board board = new Board(status);
        frame.add(board, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);

        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        board.reset();
    }
}
