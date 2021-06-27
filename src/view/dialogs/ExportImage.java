package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.IDialogView;

public class ExportImage extends JDialog implements IDialogView {

  private JPanel contentPane;
  List<String> res;

  public ExportImage() {
    JFileChooser fileChooser = new JFileChooser();

    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    res = new ArrayList<>();

    contentPane.add(fileChooser);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "ppm",
        "jpeg"));
    int result = fileChooser.showSaveDialog(this.contentPane);

    if (result == JFileChooser.APPROVE_OPTION) {
      System.out.println("File saved...");
      res.add(fileChooser.getSelectedFile().getAbsolutePath());
    } else if (result == JFileChooser.CANCEL_OPTION) {
      System.out.println("State not saved!");
    }

  }


  @Override
  public List<String> getResults() {
    return new ArrayList<>(this.res);
  }

  private void createUIComponents() {
    this.contentPane = new JPanel();
  }
}
