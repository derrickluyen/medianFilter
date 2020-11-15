package ics432;

import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class MedianFilterTest {

    private final static String output_file_name = "processed_image.jpg";

    // Method to save a BufferedImage to a jpg file
    private static void saveImage(BufferedImage image, String filename){
        try {
            ImageIO.write(image, "jpg", new File(filename));
        } catch (IOException e) {
            System.out.println("Cannot write file "+filename);
            System.exit(1);
        }
    }

    // Main method
    public static void main(String args[]) {

        // Parse command-line arguments
        if (args.length != 1) {
            System.err.println("Usage: java MedianFilterTest <jpg file path>");
            System.err.println("       (output image saved to ./" + output_file_name);
            System.exit(1);
        }

        BufferedImage input=null;
        BufferedImage output;
        BufferedImageOp filter;

        // Read input image
        try {
            input = ImageIO.read(new File(args[0]));
        } catch (IOException e) {
            System.err.println("Error: " + e.toString());
            System.exit(1);
        }

        // Create the output image
        output = new BufferedImage(input.getWidth(), input.getHeight(), input.getType());

        // Apply the Median filter
        filter = new MedianFilter();
        filter.filter(input,output);

        // Save the image
        saveImage(output, output_file_name);

    }
}
