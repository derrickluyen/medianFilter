package ics432;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Collections;

public class MedianFilter implements BufferedImageOp {
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {

        RGB converter = new RGB();

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {

                ArrayList<Byte> red = new ArrayList<>();
                ArrayList<Byte> green = new ArrayList<>();
                ArrayList<Byte> blue = new ArrayList<>();

                for (int i = x - 1; i < x + 2; i++) {
                    for (int j = y - 1; j < y + 2; j++) {

                        try {
                            red.add(converter.intToBytes(src.getRGB(i, j))[0]);
                            green.add(converter.intToBytes(src.getRGB(i, j))[1]);
                            blue.add(converter.intToBytes(src.getRGB(i, j))[2]);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            // do nothing, go to next iteration
                        }

                    }
                }

                Collections.sort(red);
                Collections.sort(green);
                Collections.sort(blue);

                dest.setRGB(x, y, converter.bytesToInt(new byte[] {red.get(red.size()/2), green.get(green.size()/2), blue.get(blue.size()/2)}));
            }
        }

        return dest;
    }

    @Override
    public Rectangle2D getBounds2D(BufferedImage src) {
        return null;
    }

    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
        return null;
    }

    @Override
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        return null;
    }

    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
