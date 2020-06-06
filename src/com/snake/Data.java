package com.snake;

import javax.swing.*;
import java.net.URL;

//data repositories
public class Data {


    // images Data
    public static URL up_URL = Data.class.getResource("statics/imageFiles/up.png");
    public static URL down_URL = Data.class.getResource("statics/imageFiles/down.png");
    public static URL left_URL = Data.class.getResource("statics/imageFiles/left.png");
    public static URL right_URL = Data.class.getResource("statics/imageFiles/right.png");
    public static ImageIcon up = new ImageIcon(up_URL);
    public static ImageIcon down = new ImageIcon(down_URL);
    public static ImageIcon left = new ImageIcon(left_URL);
    public static ImageIcon right = new ImageIcon(right_URL);

    public static URL body_URL = Data.class.getResource("statics/imageFiles/body.png");
    public static ImageIcon body = new ImageIcon(body_URL);

    public static URL food_URL = Data.class.getResource("statics/imageFiles/food.png");
    public static ImageIcon food = new ImageIcon(food_URL);

    public static URL tailR_URL = Data.class.getResource("statics/imageFiles/tailR.png");
    public static ImageIcon tailR = new ImageIcon(tailR_URL);
    public static URL tailL_URL = Data.class.getResource("statics/imageFiles/tailL.png");
    public static ImageIcon tailL = new ImageIcon(tailL_URL);
    public static URL tailU_URL = Data.class.getResource("statics/imageFiles/tailU.png");
    public static ImageIcon tailU = new ImageIcon(tailU_URL);
    public static URL tailD_URL = Data.class.getResource("statics/imageFiles/tailD.png");
    public static ImageIcon tailD = new ImageIcon(tailD_URL);

}
