package FileController;

import ImageModel.IImage;
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

  String readText(String filename);

  void writeFile(String filename, String extension, String contents) throws IOException;

}
