package com.my.blog.website.utils;

/**
 * Created by march on 2018/6/27.
 * 图片工具类
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.OutputStream;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
    public static int MIN_FONT_SIZE = 15; //最小字体大小
    public static int REDUCE_SCALE = 20;  //文字虽小比例，根据图片大小进行动态设置字体大小
    public static String MARK_TEXT = "http://kooola.com";
    public static String FONT_NAME = "华为楷体";
    public static int FONT_STYLE = 5;
    private static Color TEXT_COLOR = Color.GRAY;


    /**
     *
     * @param img 目标图标path
     */
    public static void makeWatermark(String img){
        File file = new File(img);
        Image src = null;
        try{
            src = ImageIO.read(file);
        }catch (Exception e){
            e.printStackTrace();
        }
        int width = src.getWidth(null);
        int height = src.getHeight(null);

        int size = width > height ? height : width;
        //根据图片大小动态设置字体大小
        int fontSize = size / REDUCE_SCALE < MIN_FONT_SIZE ? MIN_FONT_SIZE : size / REDUCE_SCALE;


        // x,y 为文字开始的相对位置
//        int x = width - fontSize * text.length();
        int x = 0;  //x 设置到左下角开头
        int y = height - fontSize;


        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(src, 0, 0, width, height, null);
        graphics.setFont(new Font(FONT_NAME, FONT_STYLE, fontSize));
        graphics.setColor(TEXT_COLOR);
        graphics.drawString(MARK_TEXT, x, y);
        graphics.dispose();

        try{
            OutputStream out = new FileOutputStream(file);
            ImageIO.write(image, "jpeg", out);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args){
        makeWatermark("/Users/march/Downloads/t9gg6570quh5koe6fqkdil2sqm.jpg");
    }
}
