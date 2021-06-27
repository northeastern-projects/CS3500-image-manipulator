package view;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import layermodel.IROLayer;
import view.dialogs.AlertBox;
import view.dialogs.DownscaleDetails;
import view.dialogs.LoadFile;
import view.dialogs.MosaicDetails;

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
  private JButton setCurrentButton;
  private JButton toggleVisibilityButton;
  private JButton blendAllLayersButton;
  private JTextField layerToolsTextField;
  private JTextArea imageModifiersTextArea;
  private JFrame frame;
  private ActionListener listener;

  private final IROLayer roLayer;

  public GraphicalView(String title, IROLayer roLayer) {
    this.initialise(title);
    this.roLayer = roLayer;
  }

  private void initialise(String title) {
    frame = new JFrame(title);
    frame.setContentPane(this.panel1);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.pack();
  }

  @Override
  public void display() {
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  @Override
  public void refresh() {
    this.imageDisplay.setViewportView(new JLabel(new ImageIcon(this.roLayer.getCurrent().createImage())));
  }

  @Override
  public void alert(String message) {
    AlertBox dialog = new AlertBox(message);
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
  }

  @Override
  public List<String> mosaicDetailsPane() {
    MosaicDetails dialog = new MosaicDetails();
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
    return dialog.getResults();
  }

  @Override
  public List<String> downscaleDetailsPane() {
    DownscaleDetails dialog = new DownscaleDetails();
    dialog.pack();
    dialog.setLocationRelativeTo(null);
    dialog.setVisible(true);
    return dialog.getResults();
  }

  @Override
  public List<String> loadFileDialog() {
    LoadFile dialog = new LoadFile();
    dialog.pack();
    return dialog.getResults();
  }

  @Override
  public void setListener(ActionListener listener) {
    this.listener = listener;
    this.setActionListeners();
  }

  private void setActionListeners() {
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
    this.imageDisplay = new JScrollPane();
  }
}
