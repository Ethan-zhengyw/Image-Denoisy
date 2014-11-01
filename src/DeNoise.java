import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.*;

public class DeNoise {
    // public static int[] direction = {0};

    // -: white; +: black
    public static int method1(java.util.List<Integer> neighbors) {
        int flag = 0;
        for (Integer neighbor : neighbors) {
            if (neighbor == 0)
                flag--;
            else
                flag++;
        }

        // true means white more than black
        return flag;
    }

    public static void main(String[] args) {

        ImageProcess imgIO = new ImageProcess();

                for(int i = 10; i < 100; ++i) {

        for (int jump = 1; jump <= 2; jump++) {
            for (double ran = 0.2; ran < 1; ran += 0.1) {
                java.util.List<Integer> direction = new ArrayList<Integer>();
                direction.add(0);
                for (int ju = 1; ju <= jump; ju++) {
                    direction.add(ju);
                    direction.add(0 - ju);
                }

                    int[][] temp = new int[384][256];
                    temp = imgIO.readImage("images/noisied/" + String.valueOf(i) + ".png");
                    temp = imgIO.quantize(temp, 384, 256, 2);

                    for(int j = 0; j < 384; ++j) {
                        for(int k = 0; k < 256; ++k) {
                            // if(Math.random() <= 0.1) {

                            java.util.List<Integer> neighbors = new ArrayList<Integer>();
                            for (Integer p : direction) {
                                for (Integer q : direction) {
                                    int x = j + p, y = k + q;
                                    if (x >= 0 && x < 384 && y >= 0 && y < 256 && Math.random() <= ran) {
                                    // if (!(x == j && y == k) && x >= 0 && x < 384 && y >= 0 && y < 256 && Math.random() <= ran) {
                                        int neighbor = temp[x][y];
                                        neighbors.add(neighbor); 
                                    }
                                }
                            }

                            //temp[j][k] = temp[j][k] == 0 ? 255 : 0;

                            int key = method1(neighbors);
                            if(key < 1) {
                                temp[j][k] = 0;
                            } else if (key > 1){
                                temp[j][k] = 255;
                            }
                        }
                    }

                    System.out.println("Denoising picture " + String.valueOf(i) + " jump=" + String.valueOf(jump) + "&ran=" + String.valueOf(ran)  + String.valueOf(i));
                    imgIO.writeImage(temp, 384, 256, "images/denoisied/" + String.valueOf(i) + "_jump=" + String.valueOf(jump) + "&ran=" + String.valueOf(ran) + ".png");
                }
            }
        }
    }
}
