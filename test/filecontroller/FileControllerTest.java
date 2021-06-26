package filecontroller;

import imagemodel.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This class allows for the testing of the FileController which reads and parses, and writes image
 * and text files.
 */
public class FileControllerTest {
  FileController ut;
  String flower;

  @Before
  public void setUp() {
    ut = new FileController();
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testReadImageThatsNotAnImageFile() throws IOException {
    ut.readImage("loud.doc");
  }

  @Test(expected = FileNotFoundException.class)
  public void testReadStateThatDoesNotExistFile() throws FileNotFoundException {
    ut.readState("loud.doc");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWriteImageWithUnknownExtension() throws IOException {
    ut.writeImage("cow", "doc",
            new Image(new ArrayList<>(), 12,13,255));
  }

}
