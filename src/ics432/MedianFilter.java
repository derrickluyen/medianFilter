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

        int notNullPixels = 0;
        int medianIndex;
        int arrayIndexCounter = 0;

        byte[] rValue = new byte[9];
        byte[] gValue = new byte[9];
        byte[] bValue = new byte[9];

        for (int x = 0; x < src.getWidth(); x++) {
            for (int y = 0; y < src.getHeight(); y++) {

                try {
                    // the top left pixel
                    bytesTopLeft = converter.intToBytes(src.getRGB(x - 1, y - 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopLeft = null;
                }

                try {
                    // the middle left pixel
                    bytesMiddleLeft = converter.intToBytes(src.getRGB(x - 1, y));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesMiddleLeft = null;
                }

                try {
                    // the bottom left pixel
                    bytesBottomLeft = converter.intToBytes(src.getRGB(x - 1, y + 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomLeft = null;
                }

                try {
                    // the middle left pixel
                    bytesTopMiddle = converter.intToBytes(src.getRGB(x, y - 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopMiddle = null;
                }

                // the current pixel
                // should never throw ArrayIndexOutOfBoundsException
                bytesCurrent = converter.intToBytes(src.getRGB(x, y));
                notNullPixels++;

                try {
                    // the middle right pixel
                    bytesBottomMiddle = converter.intToBytes(src.getRGB(x, y + 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomMiddle = null;
                }

                try {
                    // the top right pixel
                    bytesTopRight = converter.intToBytes(src.getRGB(x + 1, y - 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesTopRight = null;
                }

                try {
                    // the middle right pixel
                    bytesMiddleRight = converter.intToBytes(src.getRGB(x + 1, y));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesMiddleRight = null;
                }

                try {
                    // the bottom right pixel
                    bytesBottomRight = converter.intToBytes(src.getRGB(x + 1, y + 1));
                    notNullPixels++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    bytesBottomRight = null;
                }

                // get median index
                medianIndex = notNullPixels / 2;

                // reset counter to zero
                notNullPixels = 0;

                if (bytesTopLeft != null) {
                    rValue[arrayIndexCounter] = bytesTopLeft[0];
                    gValue[arrayIndexCounter] = bytesTopLeft[1];
                    bValue[arrayIndexCounter] = bytesTopLeft[2];
                    arrayIndexCounter++;
                }

                if (bytesMiddleLeft != null) {
                    rValue[arrayIndexCounter] = bytesMiddleLeft[0];
                    gValue[arrayIndexCounter] = bytesMiddleLeft[1];
                    bValue[arrayIndexCounter] = bytesMiddleLeft[2];
                    arrayIndexCounter++;
                }

                if (bytesBottomLeft != null) {
                    rValue[arrayIndexCounter] = bytesBottomLeft[0];
                    gValue[arrayIndexCounter] = bytesBottomLeft[1];
                    bValue[arrayIndexCounter] = bytesBottomLeft[2];
                    arrayIndexCounter++;
                }

                if (bytesTopMiddle != null) {
                    rValue[arrayIndexCounter] = bytesTopMiddle[0];
                    gValue[arrayIndexCounter] = bytesTopMiddle[1];
                    bValue[arrayIndexCounter] = bytesTopMiddle[2];
                    arrayIndexCounter++;
                }

                if (bytesCurrent != null) {
                    rValue[arrayIndexCounter] = bytesCurrent[0];
                    gValue[arrayIndexCounter] = bytesCurrent[1];
                    bValue[arrayIndexCounter] = bytesCurrent[2];
                    arrayIndexCounter++;
                }

                if (bytesBottomMiddle != null) {
                    rValue[arrayIndexCounter] = bytesBottomMiddle[0];
                    gValue[arrayIndexCounter] = bytesBottomMiddle[1];
                    bValue[arrayIndexCounter] = bytesBottomMiddle[2];
                    arrayIndexCounter++;
                }

                if (bytesTopRight != null) {
                    rValue[arrayIndexCounter] = bytesTopRight[0];
                    gValue[arrayIndexCounter] = bytesTopRight[1];
                    bValue[arrayIndexCounter] = bytesTopRight[2];
                    arrayIndexCounter++;
                }

                if (bytesMiddleRight != null) {
                    rValue[arrayIndexCounter] = bytesMiddleRight[0];
                    gValue[arrayIndexCounter] = bytesMiddleRight[1];
                    bValue[arrayIndexCounter] = bytesMiddleRight[2];
                    arrayIndexCounter++;
                }

                if (bytesBottomRight != null) {
                    rValue[arrayIndexCounter] = bytesBottomRight[0];
                    gValue[arrayIndexCounter] = bytesBottomRight[1];
                    bValue[arrayIndexCounter] = bytesBottomRight[2];
                    arrayIndexCounter++;
                }

                // reset counter to zero
                arrayIndexCounter = 0;

                // sort the arrays
                Arrays.sort(rValue);
                Arrays.sort(gValue);
                Arrays.sort(bValue);

                // get the new rgb values
                bytesCurrent[0] = rValue[medianIndex];
                bytesCurrent[1] = gValue[medianIndex];
                bytesCurrent[2] = bValue[medianIndex];

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
