package ImageController;

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
 * This class contains methods for the controller. It allows the reading a PPM file and creates
 * an image that can be modified. It also can write a PPM image from file to the res/ folder given
 * a file name and the contents of a PPM file.
 */
public class ImageController implements IController {

  private String filename;
  private IImage model;
  public ImageController() {
  }
  
  public IImage readTxt() throws IOException {
    String[] nameComps = filename.split("\\.");
    String fileExtension = nameComps[nameComps.length - 1].toLowerCase(Locale.ROOT);

    switch (fileExtension) {
      case "ppm":
        return readPPM(filename);
      case "txt":
        readFile(filename);
      case "jpeg":
      case "png":
      case "bmp":
      case "jpg":
        return readImg(filename);
      default:
        throw new UnsupportedOperationException("We cannot read that file type.");
    }
  }

  public IImage readFile(String filename) throws IOException {
    System.out.println("Reading file...");

    String[] nameComps = filename.split("\\.");
    String fileExtension = nameComps[nameComps.length - 1].toLowerCase(Locale.ROOT);

    switch (fileExtension) {
      case "ppm":
        return readPPM(filename);
      case "txt":
        readFile(filename);
      case "jpeg":
      case "png":
      case "bmp":
      case "jpg":
        return readImg(filename);
      default:
        throw new UnsupportedOperationException("We cannot read that file type.");
    }
  }

  private IImage readImg(String filename) throws IOException {
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

  /**
   * Read an image file in the PPM format and creates an IIMage object based on the file.
   *
   * @param filename the path of the file.
   * @return an IImage object
   */
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

  public void writeFile(String fileName, String fileType, String contents) throws IOException {
    System.out.println("Saving...");

    if (fileType.equalsIgnoreCase("ppm")) {
      writePPM(fileName, contents);
    } else {
      //TODO
    }

    System.out.println("Done!\n");
  }

  /**
   * This converts the image to a valid .ppm format and saves it to the /res folder with the given
   * file type.
   *
   * @param fName the file name
   * @param fType the file type
   * @throws IOException IO exception in the event that there is an error writing the file.
   */
  //void save(String fName, String fType) throws IOException;


  public void save(String fName, String fType, IImage image) throws IOException {
    writeFile("res/" + fName, fType, image.toString());
  }

  /**
   * Write a ppm image to the disk.
   *
   * @param fileName the file name
   * @param contents the contents of the file (exported image)
   * @throws IOException the IO exception in case there is an error writing to the disk
   */
  private void writePPM(String fileName, String contents) throws IOException {
    FileOutputStream out = new FileOutputStream(fileName + ".ppm");
    out.write(contents.getBytes(StandardCharsets.UTF_8));
    out.close();
  }
}

