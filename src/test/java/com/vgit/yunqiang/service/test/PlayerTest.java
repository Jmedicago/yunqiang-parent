package com.vgit.yunqiang.service.test;

import javafx.scene.input.KeyCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerTest implements KeyListener {

    private int orr = 100;

    public int getOrr() {
        return orr;
    }

    public void setOrr(int orr) {
        this.orr = orr;
    }

    class Car implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    orr--;
                    if (orr <= 0) {
                        break;
                    }
                    System.out.println("car user orr : " + orr);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_KP_UP:
                System.out.println("up");
                break;
            case KeyEvent.VK_KP_DOWN:
                System.out.println("down");
                break;
            case KeyEvent.VK_KP_LEFT:
                System.out.println("left");
                break;
            case KeyEvent.VK_KP_RIGHT:
                System.out.println("right");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    class Bus implements Runnable {

        @Override
        public void run() {
            while (true) {
                orr--;
                if (orr <= 0) {
                    break;
                }
                System.out.println("bus user orr : " + orr);
            }
        }
    }

    public void player() {
        Thread t1 = new Thread(new Car());
        t1.start();
        /*Thread t2 = new Thread(new Bus());
        t2.start();*/
    }

    class BasePanel extends JPanel {
        public BasePanel() {
            this.setBackground(Color.BLACK);
        }

        @Override
        public void print(Graphics g) {
            g.setColor(Color.GREEN);
            g.drawString("Tet",0, 0);
        }
    }

    class MainFrame extends JFrame implements KeyListener {

        public MainFrame() {
            this.setSize(300, 150);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.addKeyListener(this);
            this.add(new BasePanel());
            this.setVisible(true);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    public void show() {
        new MainFrame();
    }

    public static void main(String[] args) {
        new PlayerTest().show();

    }

}
