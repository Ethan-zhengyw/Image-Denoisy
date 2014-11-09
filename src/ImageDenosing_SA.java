import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.EventQueue;
import java.awt.image.*;
import javax.imageio.*;
import java.lang.*;

public class ImageDenosing_SA {

    public static double ImageDenosing(double h, double b, double n, String nPic) {
        int[][] y = new int[HEIGHT][WIDTH];
        int[][] x = new int[HEIGHT][WIDTH];
        int[][] origin = new int[HEIGHT][WIDTH];
        origin = readImage("images/origin/" + nPic);
        y = readImage("images/noisied/" + nPic);
        for(int i = 0; i < HEIGHT; ++i) {
            for(int j = 0; j < WIDTH; ++j) {
                x[i][j] = y[i][j] = (y[i][j] == 255) ? -1 : 1;
            }
        }

        double minEnergy = calculateEnergy(h, b, n, x, y);
        double curEnergy, temp;
        curEnergy = minEnergy;
        int pos = 0, row, col, sum = 0, xx, yy, times = 0;
        while(true) {
            row = pos / WIDTH;
            col = pos % WIDTH;
            temp = curEnergy;
            x[row][col] *= -1;
            curEnergy += 2.0 * h * (double)x[row][col];
            curEnergy -= 2.0 * n * (double)y[row][col] * (double)x[row][col];
            sum = 0;
            for(int i = 0; i < 4; ++i) {
                xx = row + adjacent4[i][0];
                yy = col + adjacent4[i][1];
                if(xx >= 0 && xx < HEIGHT && yy >= 0 && yy < WIDTH) {
                    sum += x[row][col] * x[xx][yy];
                }
            }
            curEnergy -= 4.0 * b * (double)sum;
            if(curEnergy >= temp) {
                x[row][col] *= -1;
                curEnergy = temp;
            }
            ++pos;
            if(pos == TOTAL) {
                if(times == 1) {
                    break;
                }
                else {
                   pos = 0;
                   ++times;
                }
            }
        }

        for(int i = 0; i < HEIGHT; ++i) {
            for(int j = 0; j < WIDTH; ++j) {
                x[i][j] = (x[i][j] == -1) ? 255 : 0;
            }
        }

        double cnt = 0.0;
        for(int i = 0; i < HEIGHT; ++i) {
            for(int j = 0; j < WIDTH; ++j) {
                if(origin[i][j] == x[i][j]) {
                    ++cnt;
                }
            }
        }
        cnt = cnt * 100.0 / (HEIGHT * WIDTH);
        return cnt;
    }

    public static double calculateEnergy(double h, double b, double n, int[][] x, int[][] y) {
        int sumh = 0, sumb = 0, sumn = 0, xx, yy;
        for(int i = 0; i < HEIGHT; ++i) {
            for(int j = 0; j < WIDTH; ++j) {
                sumh += x[i][j];
                sumn += y[i][j] * x[i][j];
                for(int k = 0; k < 4; ++k) {
                    xx = i + adjacent4[k][0];
                    yy = j + adjacent4[k][1];
                    if(xx >= 0 && xx < HEIGHT && yy >= 0 && yy < WIDTH) {
                        sumb += x[i][j] * x[xx][yy];
                    }
                }
            }
        }
        return h * (double)sumh - b * (double)sumb - n * (double)sumn;
    }

    public static void compareImage(int[][] origin, int[][] denoised) {
        double cnt = 0.0;
        for(int i = 0; i < HEIGHT; ++i) {
            for(int j = 0; j < WIDTH; ++j) {
                if(origin[i][j] == denoised[i][j]) {
                    ++cnt;
                }
            }
        }
        cnt = cnt * 100.0 / (HEIGHT * WIDTH);
        result += cnt;
        System.out.println(cnt);
    }

	public static int[][] readImage(String name) {
        File filePath = new File(name);
        try {
            BufferedImage imgBuffer = ImageIO.read(filePath);
            int height = imgBuffer.getHeight();
            int width = imgBuffer.getWidth();
            int[][] inputImage = new int[width][height];
            int rgb, gray;
            for(int i = 0; i < width; ++i) {
                for(int j = 0; j < height; ++j) {
                    rgb = imgBuffer.getRGB(i, j) & 0xffffff;
                    gray = (int)(((rgb & 0x00ff0000) >> 16) * 0.299 + ((rgb & 0x0000ff00) >> 8) * 0.587 + (rgb & 0x000000ff) * 0.114);
                    inputImage[i][j] = gray;
                }
            }
            return inputImage;
        }
        catch (IOException err) {
            err.printStackTrace();
        }
        return new int[1][1];
    }

    public static void writeImage(int[][] input_img, int row, int col, String name)  {
        try {
            BufferedImage outFile = new BufferedImage(row, col, BufferedImage.TYPE_INT_RGB);
            int temp;
            for(int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    temp = ((255 & 0xff) << 24)
                           | (((int)input_img[i][j]  & 0xff) << 16)
                           | (((int)input_img[i][j] & 0xff) << 8)
                           | ((int)input_img[i][j]  & 0xff);
                    outFile.setRGB(i, j, temp);
                }
            }
            ImageIO.write(outFile, "png", new File(name));
         }
         catch (IOException err) {
            err.printStackTrace();
        }
    }

    private static int WIDTH = 256;
    private static int HEIGHT = 384;
    private static int TOTAL = 384 * 256;
    private static double result = 0.0;
    private static int[][] adjacent4 = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int[][] adjacent8 = {{-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}, {1, 0}, {0, -1}, {0, 1}};
}
