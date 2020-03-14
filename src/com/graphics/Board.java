package com.graphics;

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
    private int width;  //with of the main frame
    private int height; //height of the main frame
    private Cell[] grid;    //Grid that represents the values of the actual grid on the frame
    private BufferedImage imgIcon = null;   //Image of a user icon

    public Board(int width, int height) {
        super();

        this.width = width;
        this.height = height;
        this.grid = new Cell[100];

        setSize(width, height);
        setVisible(true);

        //Capture form resize event
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            //e.getComponent().getSize().height
            }
        });

        //Add button foe to move icon on the cells
        JButton btnMoveUser = new JButton("Move user ");
        btnMoveUser.setBounds(0,0, 100, 30);
        btnMoveUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                grid[0].posX = grid[1].posX + 10;

                repaint();
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
            this.imgIcon = ImageIO.read(new File("icon.png"));
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

        g.drawImage(imgIcon, this.grid[0].posX, this.grid[0].posY, boxWidth - paddingBox - 10, boxHeight - paddingBox - 10, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
