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

        //компановка элементов интерфейса
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(list, BorderLayout.SOUTH);
        panel.setBackground(list.getBackground());
        scrollPane = new JScrollPane(panel);
        add(scrollPane, BorderLayout.CENTER);
        textField = new JTextField();
        button = new JButton("Send");
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(button, BorderLayout.EAST);
        panel.add(textField, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);


        setVisible(true);
        textField.requestFocus();
        System.out.println("Form paineted");

        try {
            network = new Network("localhost", 7777, this);
            System.out.println("network created");
        } catch (IOException ex) {
            ex.printStackTrace();
        }


        //событае по нажатию кнопки
        button.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitMessage("user", textField.getText()); //отрисоквка сообщения

                network.sentMessage(textField.getText());//отправка сообщения на сервер

                textField.setText(null);
                textField.requestFocus();


            }
        });

        //событие по нажатию enter
        textField.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitMessage("user", textField.getText());//отрисоквка сообщения
                network.sentMessage(textField.getText());//отправка сообщения на сервер
                textField.setText(null);
                textField.requestFocus();
            }
        });

        //отрисоква при изменении размера??
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                list.ensureIndexIsVisible(listModel.size() - 1);
            }
        });
    }


    @Override//отрисовка самого сообщения на форме
    public void submitMessage(String user, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }
        Message msg = new Message(user, message);
        listModel.add(listModel.size(), msg);
        list.ensureIndexIsVisible(listModel.size() - 1);
    }
}
