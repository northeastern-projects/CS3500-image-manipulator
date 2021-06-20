package FileController;

import ImageModel.IImage;
import java.io.IOException;

public interface IFileController {

  IImage readImage(String filename) throws IOException;

  String readText(String filename);

  void writeFile(String filename, String extension, String contents) throws IOException;

}
