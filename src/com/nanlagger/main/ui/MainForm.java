package com.nanlagger.main.ui;

import com.nanlagger.main.controllers.Controller;
import com.nanlagger.main.entities.Commands;

import javax.swing.*;
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
    private JLabel memoryLabel;

    private JMenuBar topMenu = new JMenuBar();

    private ArrayList<JButton> arrayButtons = new ArrayList<JButton>();

    public MainForm() {
        super();
        setTitle("Калькулейтэд!!!");
        setContentPane(mainPanel);
        initButton();
        initTopMenu();
        setSize(340, 300);
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
        JMenu file = new JMenu("<html><u>П</u>равка</html>");
        JMenuItem copy = new JMenuItem("Копировать");
        JMenuItem insert = new JMenuItem("Вставить");
        JMenu settings = new JMenu("<html><u>Н</u>астройки</html>");
        JMenu view = new JMenu("Вид");
        JMenuItem viewVar1 = new JMenuItem("Целое");
        JMenuItem viewVar2 = new JMenuItem("Дробное");
        JMenuItem history = new JMenuItem("<html><u>И</u>стория</html>");
        file.add(copy);
        file.add(insert);
        topMenu.add(file);
        topMenu.add(settings);
        settings.add(view);
        view.add(viewVar1);
        view.add(viewVar2);
        topMenu.add(history);

        copy.setActionCommand("copy");
        insert.setActionCommand("insert");
        copy.addActionListener(topMenuButtonClick);
        insert.addActionListener(topMenuButtonClick);
        viewVar1.setActionCommand("var1");
        viewVar2.setActionCommand("var2");
        viewVar1.addActionListener(topMenuButtonClick);
        viewVar2.addActionListener(topMenuButtonClick);
        history.addActionListener(historyListener);
    }

    private ActionListener historyListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            new History();
        }
    };

    private ActionListener speedButtonClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            int command = Integer.valueOf(actionEvent.getActionCommand());
            doCommand(command);
        }
    };

    private ActionListener topMenuButtonClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getActionCommand().equals("copy") || actionEvent.getActionCommand().equals("insert")) {
                resTextField.setText(Controller.clipboardCommand(actionEvent.getActionCommand()));
            }
            if(actionEvent.getActionCommand().equals("var1")) {
                resTextField.setText(Controller.changeState(false));
            }
            if(actionEvent.getActionCommand().equals("var2")) {
                resTextField.setText(Controller.changeState(true));
            }
        }
    };

    private void doCommand(int command) {
        resTextField.setText(Controller.doCommand(command));
        if(command >= Commands.MC() && command<= Commands.MP()) {
            memoryLabel.setText("Memory: " + Controller.getStateMemory());
        }
    }
}
