package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import img.IImage;
import img.Image;
import img.Pixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static IImage readPPM(String filename) throws FileNotFoundException {
    System.out.println("Reading file...");
    Scanner sc;
    List<Pixel> pix = new ArrayList<>();

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new FileNotFoundException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    for (int i = 0; i < height; i++) { //COL
      for (int j = 0; j < width; j++) { //ROW
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();

        pix.add(new Pixel(j, i, r, g, b));
      }
    }

    return new Image(pix, width, height, maxValue);
  }

  public static void writePPM(String fileName, String contents) throws IOException {
    FileOutputStream out = new FileOutputStream(fileName);
    out.write(contents.getBytes(StandardCharsets.UTF_8));
    out.close();
  }
}

