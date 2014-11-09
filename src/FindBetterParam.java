import java.util.*;

public class FindBetterParam {

    public static void main(String args[]) {

        double h = Double.parseDouble(args[0]), b = Double.parseDouble(args[1]), n = Double.parseDouble(args[2]);
        double H = Double.parseDouble(args[3]), B = Double.parseDouble(args[4]), N = Double.parseDouble(args[5]);
        double step = Double.parseDouble(args[6]);

        for (b = 0.1; b <= 10; b += 0.1) {
            for (n = 0.1; n <= 10; n += 0.1) {
                String NPic = "";
                double sum = 0, average_okRate = 0, okRate;

                for (int nPic = 10; nPic < 100; nPic++) {

                    NPic = String.valueOf(nPic) + ".png";
                    okRate = ImageDenosing_SA.ImageDenosing(h, b, n, NPic);
                    sum += okRate;

                    System.out.println(
                            "h=" + String.valueOf(h) + " " + 
                            "b=" + String.valueOf(b) + " " + 
                            "n=" + String.valueOf(n) + "|" + 
                            NPic + "|" +
                            "okRate=" + String.valueOf(okRate));
                }

                average_okRate = sum / 90;

                System.out.println(
                        "h=" + String.valueOf(h) + " " + 
                        "b=" + String.valueOf(b) + " " + 
                        "n=" + String.valueOf(n) + "|" + 
                        "average_okRate=" + String.valueOf(average_okRate));
            }
        }
    }
}
