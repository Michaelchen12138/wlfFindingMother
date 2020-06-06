package com.snake;

import javax.swing.*;

//游戏的主启动类
public class StartGame {
    public StartGame() {
        JFrame frame = new JFrame();

        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //adding components
        frame.add(new GamePanel());

        frame.setVisible(true);



    }


    public static void main(String[] args) {

        new StartGame();

    }
}
