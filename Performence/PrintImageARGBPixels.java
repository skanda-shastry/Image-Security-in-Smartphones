import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
 
public class PrintImageARGBPixels {
 
 
   public static void main(String args[]) {
        /**
         * Read a sample image from the filesystem
         */
        BufferedImage image = readImage("c:/a.jpg");
 
        /**
         * Call the method that prints out ARGB color Information.
         * ARGB = Alpha, Red, Green and Blue
         */
        printAllARGBDetails(image);
             }
 
    public static void printAllARGBDetails(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
	
 	byte[] p= null;
        System.out.println("Image Dimension: Height-" + height + ", Width-"
                + width);
        System.out.println("Total Pixels: " + (height * width));
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                 int pixel = image.getRGB(i, j);
               // image.getRGB(0, 0, width, height, p, 0, width);

 	
		 System.out.println("Pixel Location(" + i + "," + j + ")- ["
                        + getARGBPixelData(pixel) + "]");
            }
        }

             p = (byte[]) image.getData().getDataElements(0, 0, width, height, null);
	getImageFromArray(p,10,10);
    }
 
    /**
     * Image Pixels are Arrays of Integers [32 bits/4Bytes]
     * Consider a 32 pixel as 11111111-00110011-00111110-00011110
     *
     * First Byte From Left [11111111]= Alpha
     * Second Byte From Left[00110011]= Red
     * Third Byte From Left [00111110]= Green
     * Fourth Byte From Left[00011110]= Blue
     *
     * The following method will do a proper bit shift and
     * logical AND operation to extract the correct values
     * of different color/alpha components.
     *
     */
    public static String getARGBPixelData(int pixel) {
        String pixelARGBData = "";
        /**
         * Shift all pixels 24 bits to the right.
         * Do a logical and with 0x000000FF
         * i.e. 0000 0000 0000 0000 0000 0000 1111 1111
         * You will get the alpha value for the pixel
         */
        int alpha = (pixel >> 24) & 0x000000FF;
 
        /**
         * Shift all pixels 16 bits to the right.
         * Do a logical and with 0x000000FF
         * i.e. 0000 0000 0000 0000 0000 0000 1111 1111
         * You will get the red value for the pixel
         */
 
        int red = (pixel >> 16) & 0x000000FF;
 
        /**
         * Shift all pixels 8 bits to the right.
         * Do a logical and with 0x000000FF
         * i.e. 0000 0000 0000 0000 0000 0000 1111 1111
         * You will get the green value for the pixel
         */
        int green = (pixel >>8 ) & 0x000000FF;
 
        /**
         * Dont do any shift.
         * Do a logical and with 0x000000FF
         * i.e. 0000 0000 0000 0000 0000 0000 1111 1111
         * You will get the blue value for the pixel
         */
        int blue = (pixel) & 0x000000FF;
 
        pixelARGBData = "Alpha: " + alpha + ", " + "Red: " + red + ", "
                + "Green: " + green + ", " + "Blue: " + blue;

        
 
        return pixelARGBData;
    }
 
    /**
     * This method reads an image from the file
     * @param fileLocation -- > eg. "C:/testImage.jpg"
     * @return BufferedImage of the file read
     */
    public static BufferedImage readImage(String fileLocation) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileLocation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
 

 public static void getImageFromArray(byte[] p,int w, int h) {
            try {
            BufferedImage edgesImage = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
            edgesImage.getWritableTile(0, 0).setDataElements(0, 0, w, h, p);
            ImageIO.write(edgesImage, "jpg", new File("E:/ab.jpg"));
        } catch (IOException e) {
        }
        }
}