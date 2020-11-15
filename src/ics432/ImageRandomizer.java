package ics432;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Random;

public class ImageRandomizer {

    private static void process(BufferedImage inputImage,
                                BufferedImage outputImage) {

        for (int i = 0; i < inputImage.getWidth(); i++) {
            for (int j = 0; j < inputImage.getHeight(); j++) {
                outputImage.setRGB(i, j, processPixel(inputImage, i, j));
            }
        }
    }

    private static int processPixel(BufferedImage image, int x, int y) {

        Random rng = new Random();
        int rgb = image.getRGB(x, y);
        byte[] bytes = RGB.intToBytes(rgb);
        bytes[0] = (byte) ((bytes[0] + rng.nextInt(10000)) % 255);
        bytes[1] = bytes[1];
        bytes[2] = bytes[2];
        return RGB.bytesToInt(bytes);
    }

    public static void main(String args[]) {

        if (args.length != 1) {
            System.err.println("Usage: java ImageRandomizer <input file>");
            System.exit(1);
        }

        try {
            BufferedImage inputImage = ImageIO.read(new File(args[0]));
            BufferedImage outputImage = new BufferedImage(
                inputImage.getWidth(), inputImage.getHeight(),
                inputImage.getType());
            process(inputImage, outputImage);
            ImageIO.write(outputImage, "jpg", new File("randomized.jpg"));
        } catch (IOException e) {
            System.err.println(e.toString());
            System.exit(1);
        }
    }
}
