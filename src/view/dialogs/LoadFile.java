package view.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.IDialogView;

public class LoadFile extends JDialog implements IDialogView {

  private JPanel contentPane;
  private final List<String> res = new ArrayList<>();

  public LoadFile() {
    JFileChooser fileChooser = new JFileChooser();

    fileChooser.setPreferredSize(contentPane.getPreferredSize());
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);

    contentPane.add(fileChooser);
    fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Image", "jpg", "png", "ppm",
        "jpeg"));
    fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("State", "txt"));
    res.add(fileChooser.getFileFilter().getDescription());

    int result = fileChooser.showOpenDialog(this.contentPane);

    if (result == JFileChooser.APPROVE_OPTION) {
      System.out.println("File loaded...");
      res.add(fileChooser.getSelectedFile().getAbsolutePath());
    } else if (result == JFileChooser.CANCEL_OPTION) {
      System.out.println("No file loaded!");
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
