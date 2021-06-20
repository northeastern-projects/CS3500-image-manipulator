package FileController;

import ImageModel.IImage;
import ImageModel.IPixel;
import ImageModel.Image;
import ImageModel.Pixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;


/**
 * This class contains methods for the FileController. This class allows for creation of images
 * based on the input image files, such as (ppm, bmp, jpeg, and png). It results in am image
 * that can be modified. It also can write an image to a file in the res/ folder when given a
 * file name and the contents of the file.
 */
public class FileController implements IFileController {

  @Override
  public IImage readImage(String filename) throws IOException {
    System.out.println("Reading file...");

    String[] nameComps = filename.split("\\.");
    String fileExtension = nameComps[nameComps.length - 1].toLowerCase(Locale.ROOT);

    switch (fileExtension) {
      case "ppm":
        return readPPM(filename);
      case "jpeg":
      case "png":
      case "bmp":
      case "jpg":
        return readOtherImg(filename);
      default:
        throw new UnsupportedOperationException("We cannot read that file type.");
    }
  }

  private IImage readPPM(String filename) throws FileNotFoundException {
    Scanner sc;
    List<IPixel> pix = new ArrayList<>();

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

  private IImage readOtherImg(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));

    int height = img.getHeight();
    int width = img.getWidth();
    int depth = (img.getColorModel().getPixelSize() * 8) - 1;

    List<IPixel> pixels = new ArrayList<>();

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        Color c = new Color(img.getRGB(i, j));
        pixels.add(new Pixel(i, j, c.getRed(), c.getGreen(), c.getBlue()));
      }
    }

    return new Image(pixels, width, height, depth);
  }

  @Override
  public String readText(String filename) throws FileNotFoundException {
    File f = new File(filename + ".txt");
    Scanner s;

    try {
      s = new Scanner(f);
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + filename + " not found!");
    }
    StringBuilder contents = new StringBuilder();

    while(s.hasNext()) {
      contents.append(s.nextLine()).append(System.lineSeparator());
    }

    return contents.toString();
  }

  @Override
  public void writeFile(String filename, String extension, String contents) throws IOException {
    System.out.println("Saving...");

    FileOutputStream out = new FileOutputStream(filename + "." + extension);
    out.write(contents.getBytes(StandardCharsets.UTF_8));
    out.close();

    System.out.println("Done!\n");
  }
}