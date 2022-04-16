package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private JFrame frame = new JFrame();
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    public SimpleGUIWithFileChooser(Controller controller) {
        
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel p= new JPanel();
    p.setLayout(new BorderLayout());
    JTextArea text = new JTextArea();
    JButton b = new JButton("Save");
   
    b.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent event) {
            controller.writeOnFile(text.getText());
        }
    });
    
    p.add(text, BorderLayout.CENTER);
    p.add(b, BorderLayout.SOUTH);
    
    
    JTextField path = new JTextField(controller.getPath());
    path.setEditable(false);
    JButton b2 = new JButton("Browse...");
    b2.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(final ActionEvent e) {
            JFileChooser fc = new JFileChooser("save file");
            fc.setSelectedFile(controller.getCurrentFile());
            final int result = fc.showSaveDialog(frame);
            switch (result) {
            case JFileChooser.APPROVE_OPTION:
                 File newpath = fc.getSelectedFile();
                controller.setAsCurrent(newpath);
                path.setText(newpath.getPath());
                break;
            case JFileChooser.CANCEL_OPTION:
                break;
            default:
                JOptionPane.showMessageDialog(frame, result, "error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
    
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(b, BorderLayout.CENTER);
    p2.add(b2, BorderLayout.LINE_END);
    p.add(p2, BorderLayout.NORTH);
    frame.setContentPane(p);
    final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    final int sw = (int) screen.getWidth();
    final int sh = (int) screen.getHeight();
    frame.setSize(sw / 4, sh / 4);
    frame.setLocationByPlatform(true);
   frame.setVisible(true);
}
    
    
    public static void main(final String... a) {
       new SimpleGUIWithFileChooser(new Controller());
        //gui.display();
      
    }

}
