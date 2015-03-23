package com.nanlagger.main.ui;

import com.nanlagger.main.controllers.Controller;
import com.nanlagger.main.entities.Commands;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by NaNLagger on 19.03.15.
 *
 * @author Stepan Lyashenko
 */
public class MainForm extends JFrame {
    private JTextField resTextField;

    private JButton sqrButton;
    private JButton revButton;
    private JButton divButton;
    private JButton mulButton;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton minusButton;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton plusButton;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a0Button;
    private JButton resButton;
    private JButton delButton;
    private JButton MCButton;
    private JButton MRButton;
    private JButton MSButton;
    private JButton MPButton;
    private JButton BSButton;
    private JButton CLButton;

    private JPanel mainPanel;
    private JButton czButton;
    private JButton CEButton;

    private JMenuBar topMenu = new JMenuBar();

    private ArrayList<JButton> arrayButtons = new ArrayList<JButton>();

    public MainForm() {
        super();
        setTitle("Калькулейтэд!!!");
        setContentPane(mainPanel);
        initButton();
        initTopMenu();
        setSize(240, 300);
        setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        resTextField.setFocusable(false);
    }

    private void initButton() {
        arrayButtons.add(a0Button);
        arrayButtons.add(a1Button);
        arrayButtons.add(a2Button);
        arrayButtons.add(a3Button);
        arrayButtons.add(a4Button);
        arrayButtons.add(a5Button);
        arrayButtons.add(a6Button);
        arrayButtons.add(a7Button);
        arrayButtons.add(a8Button);
        arrayButtons.add(a9Button);
        arrayButtons.add(Commands.DEL(), delButton);
        arrayButtons.add(Commands.RESULT(), resButton);
        arrayButtons.add(Commands.PLUS(), plusButton);
        arrayButtons.add(Commands.MINUS(), minusButton);
        arrayButtons.add(Commands.MUL(), mulButton);
        arrayButtons.add(Commands.DIV(), divButton);
        arrayButtons.add(Commands.REV(), revButton);
        arrayButtons.add(Commands.SQR(), sqrButton);
        arrayButtons.add(Commands.CL(), CLButton);
        arrayButtons.add(Commands.BS(), BSButton);
        arrayButtons.add(Commands.CZ(), czButton);
        arrayButtons.add(Commands.CE(), CEButton);
        arrayButtons.add(Commands.MC(), MCButton);
        arrayButtons.add(Commands.MR(), MRButton);
        arrayButtons.add(Commands.MS(), MSButton);
        arrayButtons.add(Commands.MP(), MPButton);

        for (int i=0; i<arrayButtons.size(); i++) {
            arrayButtons.get(i).setActionCommand(String.valueOf(i));
            arrayButtons.get(i).setFocusable(false);
            arrayButtons.get(i).addActionListener(speedButtonClick);
        }
    }

    public void initTopMenu() {
        setJMenuBar(topMenu);
        requestFocus();
        JMenu file = new JMenu("<html><u>Ф</u>айл</html>");
        JMenuItem exit = new JMenuItem("Выход");
        JMenuItem help = new JMenuItem("Справка");
        JMenuItem history = new JMenuItem("<html><u>И</u>стория</html>");
        topMenu.add(file);
        topMenu.add(history);
        file.add(help);
        file.addSeparator();
        file.add(exit);

        file.setMnemonic(KeyEvent.VK_A);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK));
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Event.CTRL_MASK));
        history.setMnemonic(KeyEvent.VK_B);

        //exit.addActionListener(exitListener);
        //help.addActionListener(helpListener);
        //history.addActionListener(historyListener);
    }

    private ActionListener speedButtonClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int command = Integer.valueOf(actionEvent.getActionCommand());
            doCommand(command);
        }
    };

    private void doCommand(int command) {
        resTextField.setText(Controller.doCommand(command));
    }
}
