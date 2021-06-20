package FileController;

import ImageModel.IImage;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This interface allows for a file to be parsed and written into the res/ folder. It allows for
 * an IImage to be created based on the input file name .
 */
public interface IFileController {

  /**
   * Reads the input file.
   *
   * @param filename of the file to be read
   * @return an IImage
   * @throws IOException if file cannot be parsed
   */
  IImage readImage(String filename) throws IOException;

  /**
   * Returns the contents of a txt file.
   *
   * @param filename name of file
   * @return String
   */
  String readText(String filename) throws FileNotFoundException;

  /**
   * Writes a file into the res/ folder.
   *
   * @param filename of the file to be written
   * @param extension file extension
   * @param contents the information to be written in the file
   * @throws IOException if file cannot be made
   */
  void writeFile(String filename, String extension, String contents) throws IOException;

}
