package view.dialogs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import view.IDialogView;

public class DownscaleDetails extends JDialog implements IDialogView {

  private JPanel contentPane;
  private JButton buttonOK;
  private JButton buttonCancel;
  private JTextArea enterTheHeightOfTextArea;
  private JTextField widthField;
  private JTextArea enterTheWidthOfTextArea;
  private JTextField heightField;

  private final List<String> res;

  public DownscaleDetails() {
    setContentPane(contentPane);
    setModal(true);
    setResizable(false);
    getRootPane().setDefaultButton(buttonOK);

    res = new ArrayList<>();

    buttonOK.addActionListener(e -> onOK());

    buttonCancel.addActionListener(e -> onCancel());

    // call onCancel() when cross is clicked
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        onCancel();
      }
    });

    // call onCancel() on ESCAPE
    contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
        JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  private void onOK() {
    this.res.add(this.widthField.getText());
    this.res.add(this.heightField.getText());
    dispose();
  }

  private void onCancel() {
    dispose();
  }

  @Override
  public List<String> getResults() {
    return new ArrayList<>(this.res);
  }
}
