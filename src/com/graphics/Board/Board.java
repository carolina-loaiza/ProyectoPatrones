package com.graphics.Board;

import com.graphics.user.User;

import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;

public class Board extends JFrame implements ActionListener {
    private int width;      //with of the main frame
    private int height;     //height of the main frame
    private Cell[] grid;    //Grid that represents the values of the actual grid on the frame
    private boolean isInitGrid;
    private User user1;
    private Dice movementDice;

    public Board(int width, int height) {
        super();

        this.isInitGrid = true;
        this.width = width;
        this.height = height;
        this.grid = new Cell[100];
        this.user1 = new User("Player 1","icon.png");
        this.user1.setPosOnBoard(1);
        this.movementDice = new Dice();

        setSize(width, height);
        setVisible(true);

        //Capture form resize event
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            //e.getComponent().getSize().height
            }
        });

        //Add button foe to move icon on the cells
        JButton btnMoveUser = new JButton("Throw dice");
        btnMoveUser.setBounds(0,0, 100, 30);
        btnMoveUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int diceValue = movementDice.getNewValue();
                int newPosition = user1.getPosOnBoard() + diceValue;
                System.out.println("Dado: " +  diceValue);

                if (newPosition <= 100) {
                    user1.setPosOnBoard(newPosition);
                    user1.setPosX(grid[newPosition - 1].posX);
                    user1.setPosY(grid[newPosition - 1].posY);

                    repaint();
                }
            }
        });

        this.add(btnMoveUser);
    }

    public void paint(Graphics g) {
        super.paint(g);

        int boxWidth = Math.round((this.width - 200) / 10);
        int boxHeight = Math.round((this.height - 200) / 10);
        int paddingFormX = 10;
        int paddingFormY = 10;
        int cordX = paddingFormX + boxHeight;
        int cordY = paddingFormY + boxWidth;
        int paddingBox = 10;
        int spacingBetweenBox = 10;
        int numberBox = 100;
        BufferedImage imgDevil = null;
        BufferedImage imgAngel = null;

        try {
            imgDevil = ImageIO.read(new File("devil.png"));
            imgAngel = ImageIO.read(new File("angel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10; i++) {
            cordX = paddingFormX + boxHeight;

            for (int j = 1; j <= 10; j++) {
                Cell currentCell = new Cell();
                currentCell.posX = cordX;
                currentCell.posY = cordY;

                g.setColor(Color.GRAY);
                g.fillRect(cordX, cordY, boxWidth, boxHeight);
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(numberBox), cordX + paddingBox, cordY + paddingBox);

                if (numberBox % 8 == 0) {
                    g.drawImage(imgDevil,
                            cordX + paddingBox,
                            cordY + paddingBox,
                            boxWidth - paddingBox - 10,
                            boxHeight - paddingBox - 10,
                            null);
                    currentCell.hasDevil = true;
                }

                if (numberBox % 7 == 0) {
                    g.drawImage(imgAngel,
                            cordX + paddingBox,
                            cordY + paddingBox,
                            boxWidth - paddingBox - 10,
                            boxHeight - paddingBox - 10,
                            null);
                    currentCell.hasAngel = true;
                }

                this.grid[numberBox - 1] = currentCell;
                cordX = cordX + boxWidth + spacingBetweenBox;
                numberBox--;
            }

            cordY = cordY + boxHeight + spacingBetweenBox;
        }

        if (this.isInitGrid) {
            this.user1.setPosX(this.grid[0].posX);
            this.user1.setPosY(this.grid[0].posY);

            this.isInitGrid = false;
        }

        g.drawImage(this.user1.getImage(),
                    this.user1.getPosX(),
                    this.user1.getPosY(),
                    boxWidth - paddingBox - 10,
                    boxHeight - paddingBox - 10,
                    null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
