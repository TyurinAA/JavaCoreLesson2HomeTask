package Lesson4HW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private JButton button;
    private JTextField textField;
    private JList<String> list;
    private DefaultListModel <String> model;

    public MainWindow() throws HeadlessException {

        setTitle("New net Chart");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200,200,500,500);
        textField = new JTextField();
        button = new JButton("Button");
        model = new DefaultListModel<String>();
        list = new JList<String>(model);
        add(textField,BorderLayout.NORTH);
        add(button, BorderLayout.SOUTH);
        add(list, BorderLayout.CENTER);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.add(model.size(),textField.getText());
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.add(model.size(),textField.getText());
            }
        });
        setVisible(true);

    }
}
