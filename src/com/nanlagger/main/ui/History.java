package com.nanlagger.main.ui;

import com.nanlagger.main.controllers.Controller;
import com.nanlagger.main.entities.Commands;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by NaNLagger on 02.04.15.
 *
 * @author Stepan Lyashenko
 */
public class History extends JFrame {
    private JTextArea textArea1;
    private JButton closeButton;
    private JButton clearButton;
    private JPanel mainPanel;

    public History() {
        super();
        setTitle("История");
        setContentPane(mainPanel);
        setSize(240, 270);
        setVisible(true);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });
        textArea1.setText(Controller.doCommand(Commands.GET_HISTORY()));
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textArea1.setText(Controller.doCommand(Commands.CLEAR_HISTORY()));
            }
        });
    }
}
