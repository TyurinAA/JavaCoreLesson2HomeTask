package Lesson4HW;

import Lesson4HW.Message;
import Lesson4HW.MessageCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MainWindow extends JFrame implements MessageSender {

    private JTextField textField;
    private JButton button;
    private JScrollPane scrollPane;
    private JList<Message> list;
    private DefaultListModel<Message> listModel;
    private JPanel panel;
    private Network network;


    public MainWindow() {

        //создание самого окна сетевого чата
        setTitle("Сетевой чат");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(200, 200, 500, 500);
        setLayout(new BorderLayout());   // выбор компоновщика элементов

        //создание объекта где храняться сообщения
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setCellRenderer(new MessageCellRenderer());

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(list, BorderLayout.SOUTH);
        panel.setBackground(list.getBackground());
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);

        textField = new JTextField();
        button = new JButton("Send");

        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitMessage("user",textField.getText());

                network.sentMessage(textField.getText());

                textField.setText(null);
                textField.requestFocus();


            }
        });

        textField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitMessage("user",textField.getText());
                textField.setText(null);
                textField.requestFocus();
            }
        });

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                list.ensureIndexIsVisible(listModel.size() - 1);
            }
        });

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.EAST);
        panel.add(textField, BorderLayout.CENTER);

        add(panel, BorderLayout.SOUTH);

        try {
            network = new Network("localhost", 7777, this);
        }catch (IOException ex){
            ex.printStackTrace();
        }


        setVisible(true);
        textField.requestFocus();

    }


    @Override
    public void submitMessage(String user, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Message msg = new Message(user, message);
        listModel.add(listModel.size(), msg);
        list.ensureIndexIsVisible(listModel.size() - 1);
    }
}
