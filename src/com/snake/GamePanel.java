package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {

    //蛇的数据结构
    int snake_length; // 小蛇长度
    int[] snakeX = new int[600], snakeY = new int[600]; //小蛇x和y坐标
    String dir; //小蛇蛇头的面朝方向

    //小蛇的食物
    int foodX,foodY;
    Random random = new Random();

    //开始游戏
    boolean isStart = false;
    boolean isFailed = false;

    //定时器
    Timer timer = new Timer(100,this);

    //音效
    SoundEffects pig = new SoundEffects();




    //积分
    int score;

    public GamePanel() {
        init();
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
    }

    //初始化界面
    public void init() {
        snake_length = 3;
        snakeX[0] = 100; snakeY[0] = 100;
        snakeX[1] = 75; snakeY[1] = 100;
        snakeX[2] = 50; snakeY[2] = 100;
        dir = "R";

        //积分系统
        score = 0;

        //食物初始化
        foodX = 25 + 25*random.nextInt(34);
        foodY = 75 + 25*random.nextInt(24);

        //按空格键开始游戏

    }


    //绘制面板
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //绘制静态面板
        this.setBackground(Color.WHITE);
        g.setColor(Color.BLACK);
        g.setFont(new Font("DengXian", Font.BOLD, 40));
        g.drawString("王林峰找妈妈",330,50);
        g.setColor(new Color(165, 184, 232));
        g.fillRect(25,75,850,600);


        //绘制积分系统
        g.setColor(Color.RED);
        g.setFont(new Font("DengXian", Font.BOLD, 18));
        g.drawString("长度: " + snake_length, 750, 35);
        g.drawString("分数: " + score, 750, 50);


        //画小蛇
        switch(dir){
            case "R": Data.right.paintIcon(this,g,snakeX[0],snakeY[0]); break;
            case "L": Data.left.paintIcon(this,g,snakeX[0],snakeY[0]); break;
            case "U": Data.up.paintIcon(this,g,snakeX[0],snakeY[0]); break;
            case "D": Data.down.paintIcon(this,g,snakeX[0],snakeY[0]); break;
        }

        ////////小蛇身体
        for (int i = 1; i < snake_length - 1; i++) {
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
            switch(dir){
                case "R": Data.tailR.paintIcon(this,g,snakeX[snake_length-1],snakeY[snake_length-1]); break;
                case "L": Data.tailL.paintIcon(this,g,snakeX[snake_length-1],snakeY[snake_length-1]); break;
                case "U": Data.tailU.paintIcon(this,g,snakeX[snake_length-1],snakeY[snake_length-1]); break;
                case "D": Data.tailD.paintIcon(this,g,snakeX[snake_length-1],snakeY[snake_length-1]); break;
            }

        }


        //画食物
        Data.food.paintIcon(this,g,foodX,foodY);

        //游戏状态：
        if(!isStart){
            g.setColor(Color.WHITE);
            g.setFont(new Font("DengXian", Font.BOLD,40));
            g.drawString("按下空格键开始游戏", 300,300);
        }
        if(isFailed){
            g.setColor(Color.RED);
            g.setFont(new Font("DengXian", Font.BOLD, 40));
            g.drawString("游戏失败按空格键重新开始",300,300);


        }


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE){
            if(isFailed){
                isFailed = false;
                init();
            } else {isStart = !isStart;}

        }
        repaint();

        switch(keyCode){
            case KeyEvent.VK_UP: dir = "U"; break;
            case KeyEvent.VK_DOWN: dir = "D"; break;
            case KeyEvent.VK_RIGHT: dir = "R"; break;
            case KeyEvent.VK_LEFT: dir = "L"; break;
        }

    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && !isFailed){
            //判定吃食物
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                snake_length++; // 增加小蛇长度
                score+=10; //积分+10
               // pig.playMusic("pig_snort");
                //重新生成食物 
                foodX = 25 + 25*random.nextInt(34);
                foodY = 75 + 25*random.nextInt(24);
            }

            //小蛇的移动
            for (int i = snake_length - 1; i > 0 ; i--) {
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1]; // 小蛇向前移动
            }

            //小蛇移动范围
            switch(dir){
                case "R": snakeX[0] = snakeX[0] + 25; if(snakeX[0] > 850){isFailed = true;} break;
                case "L": snakeX[0] = snakeX[0] - 25; if(snakeX[0] < 25){isFailed = true;} break;
                case "U": snakeY[0] = snakeY[0] - 25; if(snakeY[0] < 75){isFailed = true;} break;
                case "D": snakeY[0] = snakeY[0] + 25; if(snakeY[0] > 650){isFailed = true;} break;
            }

            repaint();
        }


//        for (int i = 1; i < snake_length; i++) {
//            if(snakeX[0] == snakeX[i]&&snakeY[0]==snakeY[i]){
//                isFailed = true;
//            }
//        }
        for (int i = 1; i < snake_length; i++) {
            if (snakeY[0] == snakeY[i] && snakeX[0] == snakeX[i]) {
                boolean flag = false;
                for (int r = 2; r < snake_length; r++) {
                    if (snakeX[1] == snakeX[r] && snakeY[1] == snakeY[r]) {
                        flag = true;//判断是否有重叠
                    }
                }
                if ((snakeY[0] == snakeY[i - 2] && snakeX[0] == snakeX[i - 2])||flag) {
                } else {
                    isFailed = true;
                }
            }
        }
        timer.start();
    }
}
