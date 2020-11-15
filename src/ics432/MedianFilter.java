package ics432;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.util.Arrays;

public class MedianFilter implements BufferedImageOp {
    @Override
    public BufferedImage filter(BufferedImage src, BufferedImage dest) {

        RGB converter = new RGB();

        byte[] bytesTopLeft;
        byte[] bytesMiddleLeft;
        byte[] bytesBottomLeft;
        byte[] bytesTopMiddle;
        byte[] bytesCurrent;
        byte[] bytesBottomMiddle;
        byte[] bytesTopRight;
        byte[] bytesMiddleRight;
        byte[] bytesBottomRight;

        int counter = 0;
        int medianIndex;

        byte[] rValue = new byte[9];
        byte[] gValue = new byte[9];
        byte[] bValue = new byte[9];

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {

                try {
                    // the top left pixel
                    bytesTopLeft = converter.intToBytes(src.getRGB(x - 1, y - 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopLeft = null;
                }

                try {
                    // the middle left pixel
                    bytesMiddleLeft = converter.intToBytes(src.getRGB(x - 1, y));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesMiddleLeft = null;
                }

                try {
                    // the bottom left pixel
                    bytesBottomLeft = converter.intToBytes(src.getRGB(x - 1, y + 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomLeft = null;
                }

                try {
                    // the top middle pixel
                    bytesTopMiddle = converter.intToBytes(src.getRGB(x, y - 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopMiddle = null;
                }

                // the current pixel
                // should never throw ArrayIndexOutOfBoundsException
                bytesCurrent = converter.intToBytes(src.getRGB(x, y));
                counter++;

                try {
                    // the bottom middle pixel
                    bytesBottomMiddle = converter.intToBytes(src.getRGB(x, y + 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomMiddle = null;
                }

                try {
                    // the top right pixel
                    bytesTopRight = converter.intToBytes(src.getRGB(x + 1, y - 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopRight = null;
                }

                try {
                    // the middle right pixel
                    bytesMiddleRight = converter.intToBytes(src.getRGB(x + 1, y));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesMiddleRight = null;
                }

                try {
                    // the bottom right pixel
                    bytesBottomRight = converter.intToBytes(src.getRGB(x + 1, y + 1));
                    counter++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomRight = null;
                }

                // get median index
                medianIndex = counter / 2;

                // reset counter to zero
                counter = 0;

                if (bytesTopLeft != null) {
                    rValue[counter] = bytesTopLeft[0];
                    gValue[counter] = bytesTopLeft[1];
                    bValue[counter] = bytesTopLeft[2];
                    counter++;
                }

                if (bytesMiddleLeft != null) {
                    rValue[counter] = bytesMiddleLeft[0];
                    gValue[counter] = bytesMiddleLeft[1];
                    bValue[counter] = bytesMiddleLeft[2];
                    counter++;
                }

                if (bytesBottomLeft != null) {
                    rValue[counter] = bytesBottomLeft[0];
                    gValue[counter] = bytesBottomLeft[1];
                    bValue[counter] = bytesBottomLeft[2];
                    counter++;
                }

                if (bytesTopMiddle != null) {
                    rValue[counter] = bytesTopMiddle[0];
                    gValue[counter] = bytesTopMiddle[1];
                    bValue[counter] = bytesTopMiddle[2];
                    counter++;
                }

                rValue[counter] = bytesCurrent[0];
                gValue[counter] = bytesCurrent[1];
                bValue[counter] = bytesCurrent[2];
                counter++;

                if (bytesBottomMiddle != null) {
                    rValue[counter] = bytesBottomMiddle[0];
                    gValue[counter] = bytesBottomMiddle[1];
                    bValue[counter] = bytesBottomMiddle[2];
                    counter++;
                }

                if (bytesTopRight != null) {
                    rValue[counter] = bytesTopRight[0];
                    gValue[counter] = bytesTopRight[1];
                    bValue[counter] = bytesTopRight[2];
                    counter++;
                }

                if (bytesMiddleRight != null) {
                    rValue[counter] = bytesMiddleRight[0];
                    gValue[counter] = bytesMiddleRight[1];
                    bValue[counter] = bytesMiddleRight[2];
                    counter++;
                }

                if (bytesBottomRight != null) {
                    rValue[counter] = bytesBottomRight[0];
                    gValue[counter] = bytesBottomRight[1];
                    bValue[counter] = bytesBottomRight[2];
                    counter++;
                }

                // reset counter to zero
                counter = 0;

                // sort the arrays
                Arrays.sort(rValue);
                Arrays.sort(gValue);
                Arrays.sort(bValue);

                // get the new rgb values
                bytesCurrent[0] = rValue[medianIndex];
                bytesCurrent[1] = gValue[medianIndex];
                bytesCurrent[2] = bValue[medianIndex];

                // reset the rgb value arrays for next usage
                rValue = new byte[9];
                gValue = new byte[9];
                bValue = new byte[9];

                // set new pixel value of destination buffered image
                dest.setRGB(x, y, converter.bytesToInt(bytesCurrent));
            }
        }
        return dest;
    }

    // do nothing
    @Override
    public Rectangle2D getBounds2D(BufferedImage src) {
        return null;
    }

    // do nothing
    @Override
    public BufferedImage createCompatibleDestImage(BufferedImage src, ColorModel destCM) {
        return null;
    }

    // do nothing
    @Override
    public Point2D getPoint2D(Point2D srcPt, Point2D dstPt) {
        return null;
    }

    // do nothing
    @Override
    public RenderingHints getRenderingHints() {
        return null;
    }
}
