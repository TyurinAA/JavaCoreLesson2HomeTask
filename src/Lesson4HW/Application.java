package Lesson4HW;

import javax.swing.*;

public class Application {
    private static MainWindow newMainWindow;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                newMainWindow = new MainWindow();
            }
        });
    }
}
