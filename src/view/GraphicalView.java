package view;

import imagemodel.IImage;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import layermodel.ILayer;

public class GraphicalView implements IGraphicalView {

  private JPanel panel1;
  private JButton blurBtn;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton downscaleButton;
  private JButton mosaicButton;
  private JButton loadButton;
  private JButton saveButton;
  private JButton exportButton;
  private JScrollPane imageDisplay;
  private JFrame frame;
  private ILayer layer;

  public GraphicalView(String title) {
    this.initialise();
  }

  private void initialise() {
    frame = new JFrame("App");
    frame.setContentPane(this.panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
  }

  @Override
  public void display() {
    frame.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    blurBtn.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    downscaleButton.addActionListener(listener);
    mosaicButton.addActionListener(listener);
    loadButton.addActionListener(listener);
    saveButton.addActionListener(listener);
    exportButton.addActionListener(listener);
  }

  private void createUIComponents() {
    // TODO: place custom component creation code here
  }
}
