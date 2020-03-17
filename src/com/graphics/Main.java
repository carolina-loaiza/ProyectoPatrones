package com.graphics;

import com.graphics.Board.Board;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        Board board = new Board(1000, 600);
        JFrame application = new JFrame();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
        application.setResizable(true);
    }
}
