package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Verification implements  ActionListener {

    JFrame Vframe = new JFrame();
    JPanel panel = new JPanel();
    JButton loginButton = new JButton("Enter");
    JButton resetButton = new JButton("Clear");
    JTextField UserId = new JTextField();
    // JPasswordField Password = new JPasswordField();

    JLabel UserIdLabel = new JLabel("OTP");
    //JLabel UserPasswordLabel = new JLabel("password");

    JLabel MessageLabel = new JLabel();
    String OTP = String.valueOf(OTPGenerator());
    String accountNumber;
    public Verification(JFrame frame, String AccountNumber) {

        accountNumber = AccountNumber;
        Vframe = frame;

        UserIdLabel.setBounds(20, 50, 350, 50);
        UserIdLabel.setFont(new Font(null, Font.ITALIC, 25));


        MessageLabel.setBounds(125, 250, 250, 35);
        MessageLabel.setFont(new Font(null, Font.ITALIC, 25));
        //MessageLabel.setVisible(true);

        UserId.setBounds(20, 100, 210, 25);
        //Password.setBounds(20,100,210,25);

        loginButton.setBounds(20, 150, 100, 25);
        loginButton.setFocusable(false);
        loginButton.addActionListener(this);

        resetButton.setBounds(140, 150, 100, 25);
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);


        panel.setSize(400, 400);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        panel.setVisible(true);

        Vframe.add(panel);

        panel.add(UserIdLabel);
        panel.add(MessageLabel);
        panel.add(UserId);
        panel.add(loginButton);
        panel.add(resetButton);
        MessageLabel.setText(String.valueOf(OTP));

    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == resetButton) {
            UserId.setText("");
            // Password.setText("");

        }
        if (e.getSource() == loginButton) {

            String userID = UserId.getText();
            System.out.println(userID);
            System.out.println(OTP);
            // String password = String.valueOf(Password.getPassword());
            if (userID.equals(OTP)) {

                MessageLabel.setForeground(Color.GREEN);
                MessageLabel.setText("Login successful");
                panel.add(MessageLabel);
                panel.setVisible(false);
                Options options = new Options(Vframe, accountNumber);


            } else {
                UserId.setText("");
                MessageLabel.setForeground(Color.RED);
                MessageLabel.setText("Invalid Number");
                java.util.Timer timer =  new Timer();
                TimerTask exitApp = new TimerTask() {
                    @Override
                    public void run() {

                        MessageLabel.setText(String.valueOf(OTP));
                    }
                };
                timer.schedule(exitApp,new Date(System.currentTimeMillis()+1*1000));
                panel.add(MessageLabel);

            }


        }
    }
    public static  int OTPGenerator(){
        Random OTPGenerator = new Random();
        OTPGenerator.setSeed(System.currentTimeMillis());
        int  OTP = OTPGenerator.nextInt(99999)+ 99999;
        while(OTP < 100000 || OTP > 999999){
            OTP = OTPGenerator.nextInt(99999)+ 99999;
        }
        return  OTP;
    }
}

