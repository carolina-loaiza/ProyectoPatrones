package com.graphics.Board;

import com.graphics.Board.Cell.*;
import com.graphics.user.*;
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
import java.util.ArrayList;
import javax.swing.Timer;

public class Board extends JFrame {
    private int windowWidth;      //with of the main frame
    private int windowHeight;     //height of the main frame
    private int boardWidth;      //with of the board
    private int boardHeight;     //height of the board
    private int horizontalPadding = 10;     //Horizontal padding added to the frame
    private int verticalPadding = 10;   //Vertical padding added to the frame
    private ArrayList<Cell> grid;    //Grid that represents the values of the actual grid on the frame
    private boolean isInitPaint;     //Flag that indicates if is the first time the frame is painted
    private User user1;
    private Dice movementDice;
    private BufferedImage imgDevil;
    private BufferedImage imgAngel;
    private Timer diceTimer;
    private int diceTimes;
    private JButton btnThrowDice;

    public Board(int width, int height) {
        super();

        this.setLayout(null);
        this.isInitPaint = true;
        this.windowWidth = width;
        this.windowHeight = height;
        this.boardWidth = (int) (this.windowWidth * 0.70);
        this.boardHeight = this.windowHeight - Math.round(this.verticalPadding * 2);
        setWindowSize(this.windowWidth, this.windowHeight);
        setVisible(true);

        this.grid = new ArrayList<>();

        //Init User Settings
        this.user1 = new User("Player 1","src/com/graphics/Board/images/icon.png");
        this.user1.setPosOnBoard(1);

        //Init Dice Settings
        this.movementDice = new Dice();
        this.diceTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceTimes--;

                btnThrowDice.setText(String.valueOf(movementDice.getNewValue()));
                btnThrowDice.repaint();

                if (diceTimes == 0) {
                    diceTimer.stop();
                    btnThrowDice.setText(String.valueOf(movementDice.getValue()));
                    processMovement();
                }
            }
        });

        try {
            this.imgDevil = ImageIO.read(new File("src/com/graphics/Board/images/devil.png"));
            this.imgAngel = ImageIO.read(new File("src/com/graphics/Board/images/angel.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Capture frame resize event
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                boardWidth = (int) Math.round(e.getComponent().getSize().width * 0.60);
                boardHeight = e.getComponent().getSize().height - Math.round(verticalPadding * 2);
                btnThrowDice.setBounds(boardWidth, verticalPadding, 100, 50);
                user1.setPosX(grid.get(user1.getPosOnBoard()-1).positionX);
                user1.setPosY(grid.get(user1.getPosOnBoard()-1).positionY);

                setWindowSize(e.getComponent().getSize().width, e.getComponent().getSize().height);
                repaint();
            }
        });

        //Add button to move icon on the cells
        this.btnThrowDice = new JButton("Throw Dice");
        this.btnThrowDice.setBounds(this.boardWidth + this.horizontalPadding, this.verticalPadding, 100, 50);
        this.btnThrowDice.setMaximumSize(new Dimension(100, 50));
        this.btnThrowDice.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                diceTimes = 50;
                diceTimer.start();
            }
        });

        this.add(this.btnThrowDice);
        repaint();
    }

    public void setWindowSize(int width, int height) {
        this.windowWidth = width;
        this.windowHeight = height;

        setSize(width, height);
    }

    public void processMovement() {
        int newPosition = user1.getPosOnBoard() + movementDice.getValue();
        System.out.println("Dice: " +  movementDice.getValue());
        btnThrowDice.setText("Throw Dice: " + movementDice.getValue());

        if (newPosition < 100) {
            switch (grid.get(newPosition).getType()) {
                /*case Angel: {
                    System.out.println("Congratulations! You have reached an Angel cell, you have advanced 11 cells forward!");

                    if ((newPosition + 11) == 99) {
                        newPosition = newPosition + 11;
                        System.out.println("Congratulations! You are the winner!");
                    } else if ((newPosition + 11) < 99) {
                        newPosition = newPosition + 11;
                    } else {
                        newPosition = user1.getPosOnBoard();
                    }

                    break;
                }
                
                case Devil: {
                    System.out.println("That\'s Bad! You have reached an Evil cell, you have moved 10 cells back!");

                    if ((newPosition - 10) >= 0)
                        newPosition = newPosition - 10;
                    else
                        newPosition = 0;
                    break;
                }*/

                case Empty: {

                }

               default: break;
            }

            user1.setPosOnBoard(newPosition);
            user1.setPosX(grid.get(100 - newPosition).positionX);
            user1.setPosY(grid.get(100 - newPosition).positionY);

            repaint();
        } else if (newPosition == 100) {
            user1.setPosOnBoard(newPosition);
            user1.setPosX(grid.get(0).positionX);
            user1.setPosY(grid.get(0).positionY);
            System.out.println("You are the winner!!!!");
            repaint();
        } else {
            System.out.println("Not a exact movement to last cell!!!!");
        }
    }

    public void paint(Graphics g) {
        super.paint(g);

        int cellWith = Math.round((this.boardWidth) / 10) - (this.horizontalPadding * 2);
        int cellHeight = Math.round((this.boardHeight) / 10) - (this.verticalPadding * 2);

        int cordX = 0;
        int cordY = this.verticalPadding + cellHeight;
        int paddingCell = 10;
        int spacingBetweenCells = 10;
        int numberBox = 100;

        // Creates the board grid
        for (int i=1; i<=10; i++) {
            cordX = this.horizontalPadding + cellHeight;

            for (int j=1; j<=10; j++) {
                g.setColor(Color.GRAY);
                g.fillRect(cordX, cordY, cellWith, cellHeight);
                g.setColor(Color.WHITE);
                g.drawString(String.valueOf(numberBox), cordX, cordY + paddingCell);

                if (numberBox % 8 == 0) {
                    g.drawImage(this.imgDevil,
                            cordX + paddingCell,
                            cordY + paddingCell,
                            cellWith - paddingCell - 10,
                            cellHeight - paddingCell - 10,
                            null);
                    this.grid.add(new Devil(cordX, cordY, numberBox).clone());
                } else if (numberBox % 7 == 0) {
                    g.drawImage(this.imgAngel,
                            cordX + paddingCell,
                            cordY + paddingCell,
                            cellWith - paddingCell - 10,
                            cellHeight - paddingCell - 10,
                            null);
                    this.grid.add(new Angel(cordX, cordY, numberBox).clone());
                } else {
                    this.grid.add(new Empty(cordX, cordY, numberBox).clone());
                }

                cordX = cordX + cellWith + spacingBetweenCells;
                numberBox--;
            }

            cordY = cordY + cellHeight + spacingBetweenCells;
        }

        if (this.isInitPaint) {
            this.user1.setPosX(this.grid.get(this.grid.size()-1).positionX);
            this.user1.setPosY(this.grid.get(this.grid.size()-1).positionY);

            this.isInitPaint = false;
        }

        g.drawImage(this.user1.getImage(),
                    this.user1.getPosX(),
                    this.user1.getPosY(),
                    cellWith - paddingCell - 10,
                    cellHeight - paddingCell - 10,
                    null);
    }
}
