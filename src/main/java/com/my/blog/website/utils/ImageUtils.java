package com.my.blog.website.utils;

/**
 * Created by march on 2018/6/27.
 * 图片工具类
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageUtils {
    public static int MIN_FONT_SIZE = 10; //最小字体大小
    public static int REDUCE_SCALE = 20;  //文字虽小比例，根据图片大小进行动态设置字体大小

    /**
     *
     * @param text  文本
     * @param img 目标图标
     * @param fontName 字体名称
     * @param fontStyle 字体样式
     */
    public static void makeWatermarkWithText(String text, String img, String fontName, int fontStyle){
        int fontSize = 40;


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
        fontSize = size < MIN_FONT_SIZE ? MIN_FONT_SIZE : size / REDUCE_SCALE;


        // x,y 为文字开始的相对位置
//        int x = width - fontSize * text.length();
        int x = 0;  //x 设置到左下角开头
        int y = height - fontSize;


        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.createGraphics();
        graphics.drawImage(src, 0, 0, width, height, Color.GRAY, null);
        graphics.setFont(new Font(fontName, fontStyle, fontSize));
        graphics.drawString(text, x, y);
        graphics.dispose();
        FileOutputStream fileOutputStream = null;
        try{
            fileOutputStream = new FileOutputStream(img);
        }catch (Exception e){
            e.printStackTrace();
        }
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
        try{
            encoder.encode(image);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            fileOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        makeWatermarkWithText("http://kooola.com", "/Users/march/6.jpg", "华为楷体", 5);
    }
}
