package com.graphics.user;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class User {
    private String name;
    private int posX;
    private int posY;
    private BufferedImage image;
    private int posOnBoard;

    public User() {

    }

    public User(String name, String image) {
        try {
            this.image = ImageIO.read(new File(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getPosOnBoard() {
        return posOnBoard;
    }

    public void setPosOnBoard(int posOnBoard) {
        this.posOnBoard = posOnBoard;
    }
}
