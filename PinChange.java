package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PinChange implements ActionListener {

    JPanel PinChangePanel= new JPanel();
    JPasswordField NewPin = new JPasswordField();
    JLabel UserPinLabel = new JLabel("New Pin");
    JPasswordField ConfirmPin = new JPasswordField();
    JLabel UserConfirmPinLabel = new JLabel("Confirm New Pin");
    JFrame PinChangeFrame = new JFrame();
    JButton ChangeButton = new JButton("Enter");
    JLabel MessageLabel = new JLabel();

    Get obj = new Get();

    String accountNumber ;
    PinChange(JFrame frame, String AccountNumber){

        PinChangeFrame = frame;
        accountNumber = AccountNumber;

        UserPinLabel.setBounds(20,50,350,50);
        UserPinLabel.setFont(new Font(null,0,25));
        NewPin.setBounds(20,100,210,25);

        UserConfirmPinLabel.setBounds(20,150,350,50);
        UserConfirmPinLabel.setFont(new Font(null,0,25));
        ConfirmPin.setBounds(20,200,210,25);


        ChangeButton.setBounds(20,250,100,45);
        ChangeButton.setFocusable(false);
        ChangeButton.addActionListener(this);

        MessageLabel.setBounds(125,270,250,35);
        MessageLabel.setFont(new Font(null,Font.ITALIC,25));

        PinChangeFrame.add(PinChangePanel);
        PinChangePanel.setSize(420,420);
        PinChangePanel.setLayout(null);
        PinChangePanel.setBackground(Color.cyan);
        PinChangePanel.setVisible(true);

        PinChangePanel.add(UserConfirmPinLabel);
        PinChangePanel.add(UserPinLabel);
        PinChangePanel.add(NewPin);
        PinChangePanel.add(ConfirmPin);
        PinChangePanel.add(ChangeButton);
        PinChangePanel.add(MessageLabel);


    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == ChangeButton){
            String password = String.valueOf(NewPin.getPassword());
            String ConfirmPassword = String.valueOf(ConfirmPin.getPassword());
            if(password.equals(ConfirmPassword) & password.length() != 0){
                MessageLabel.setForeground(Color.RED);
                MessageLabel.setText(" Successful");
                java.util.Timer timer =  new Timer();
                TimerTask exitApp = new TimerTask() {
                    @Override
                    public void run() {

                        PinChangeFrame.dispose();
                        Login login = new Login();
                    }
                };
                timer.schedule(exitApp,new Date(System.currentTimeMillis()+5*1000));


                try {
                    obj.ChangePin(accountNumber, Base64.getEncoder().encodeToString(password.getBytes()));
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


            }
            else {
                MessageLabel.setForeground(Color.RED);
                MessageLabel.setText("Un Successful");
            }

        }


}
}
