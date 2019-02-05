package Lesson4HW;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() throws HeadlessException {
    setTitle("New net Chart");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setBounds(200,200,500,500);
    setVisible(true);
    }
}
