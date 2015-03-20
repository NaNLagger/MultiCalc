package com.nanlagger.main.ui;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by NaNLagger on 19.03.15.
 *
 * @author Stepan Lyashenko
 */
public class MainForm extends JFrame {
    private JTextField a0TextField;
    private JButton sqrButton;
    private JButton revButton;
    private JButton button3;
    private JButton button4;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton button8;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton button12;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton button17;
    private JButton aBButton;
    private JButton MCButton;
    private JButton MRButton;
    private JButton MSButton;
    private JButton mButton;
    private JButton BSButton;
    private JButton CLButton;
    private JPanel mainPanel;

    public MainForm() {
        super();
        setTitle("Калькулейтэд!!!");
        setContentPane(mainPanel);
        setSize(240, 300);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });
    }
}