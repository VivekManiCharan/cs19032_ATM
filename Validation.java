package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Validation implements ActionListener {

    JButton loginButton = new JButton("Enter");
    JButton resetButton = new JButton("Clear");
    JPasswordField Password = new JPasswordField();
    JLabel UserPasswordLabel = new JLabel("Password");
    JLabel MessageLabel = new JLabel();
    JPanel panel= new JPanel();
    JFrame frame2 = new JFrame();
    String accountNumber;
    Get obj = new Get();
    Validation(JFrame frame, String AccountNumber){

        accountNumber = AccountNumber;
        frame2 = frame;
        frame2.setTitle("Hello "+obj.GetName(accountNumber));
        frame2.add(panel);

        MessageLabel.setBounds(125,250,250,35);
        MessageLabel.setFont(new Font(null,Font.ITALIC,25));

        UserPasswordLabel.setBounds(20,50,350,50);
        UserPasswordLabel.setFont(new Font(null,Font.ITALIC,25));

        Password.setBounds(20,100,210,25);
        //Password.setBounds(20,100,210,25);

        loginButton.setBounds(20,150,100,25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(140,150,100,25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);



        panel.setSize(400,400);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        panel.setVisible(true);


        panel.add(UserPasswordLabel);

        panel.add(MessageLabel);
        panel.add(Password);

        panel.add(loginButton);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == resetButton){
            Password.setText("");

        }
        if(e.getSource()== loginButton) {

            String passwordText = String.valueOf(Password.getPassword());
            String  encrypted = Base64.getEncoder().encodeToString(passwordText.getBytes());

            System.out.println(passwordText);
            System.out.println(encrypted);
            if(encrypted.equals(obj.GetPin(accountNumber))){
                Verification  verification = new Verification(frame2,accountNumber);
                panel.setVisible(false);
            }
            else{
                MessageLabel.setForeground(Color.RED);
                MessageLabel.setText("Invalid Pin");
                panel.add(MessageLabel);
                java.util.Timer timer =  new Timer();
                TimerTask exitApp = new TimerTask() {
                    @Override
                    public void run() {


                        panel.setVisible(false);
                         new Login();
                        frame2.dispose();
                    }
                };
                timer.schedule(exitApp,new Date(System.currentTimeMillis()+2*1000));

            }




        }
    }
}
