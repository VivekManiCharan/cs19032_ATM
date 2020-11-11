package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class WithDraw implements ActionListener {


    JFrame DFrame = new JFrame();
    JPanel panel = new JPanel();
    JLabel Amount = new JLabel("Enter Valid Amount");
    JTextField amount = new JTextField();
    JButton Enter = new JButton("Enter");
    JButton Cancel = new JButton("Cancel");
    JButton Clear = new JButton("Clear");
    JLabel MessageLabel = new JLabel();
    JLabel MessageLabel2 = new JLabel();
    String AccountNumber;

    Get obj = new Get();
    WithDraw(JFrame frame, String accountNumber){
        DFrame = frame;
        AccountNumber = accountNumber;

        Amount.setBounds(20,50,350,50);
        Amount.setFont(new Font(null,0,25));
        amount.setBounds(20,100,210,25);


        Clear.setBounds(20,150,100,25);
        Clear.setFocusable(false);
        Clear.addActionListener(this);
        Clear.setBackground(Color.ORANGE);

        Cancel.setBounds(140,150,100,25);
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);
        Cancel.setBackground(Color.red);

        Enter.setBounds(80,200,100,25);
        Enter.setFocusable(false);
        Enter.addActionListener(this);
        Enter.setBackground(Color.green);

        MessageLabel.setBounds(25,270,350,35);
        MessageLabel.setFont(new Font(null,Font.ITALIC,25));

        MessageLabel2.setBounds(125,305,250,35);
        MessageLabel2.setFont(new Font(null,Font.ITALIC,25));

        DFrame.add(panel);
        panel.setSize(420,420);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        panel.setVisible(true);

        panel.add(Amount);
        panel.add(amount);
        panel.add(Enter);
        panel.add(Cancel);
        panel.add(Clear);
        panel.add(MessageLabel);
    }
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource() == Enter){
            String a = amount.getText();
            long k =Integer.parseInt(a);
            if(k == 0 && k %500 != 0){
                MessageLabel.setForeground(Color.RED);
                MessageLabel.setText("Enter a Valid Amount");

            }
            else {
                if(k <= obj.GetBalance(AccountNumber)){
                    long balance = obj.GetBalance(AccountNumber) - k;
                    try {
                        obj.Update(AccountNumber, balance);
                        obj.insert(obj.GetName(AccountNumber), k ,"-");
                        MessageLabel.setForeground(Color.BLACK);
                        MessageLabel.setText("Amount Debited");
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    java.util.Timer timer =  new Timer();
                    TimerTask exitApp = new TimerTask() {
                        @Override
                        public void run() {

                            DFrame.dispose();
                            panel.setVisible(false);
                            Login login = new Login();
                        }
                    };
                    timer.schedule(exitApp,new Date(System.currentTimeMillis()+1*1000));

                }
                else{
                    MessageLabel.setForeground(Color.BLACK);
                    MessageLabel.setText("Insufficient Funds");
                    java.util.Timer timer =  new Timer();
                    TimerTask exitApp = new TimerTask() {
                        @Override
                        public void run() {

                            DFrame.dispose();
                            panel.setVisible(false);
                            Login login = new Login();
                        }
                    };
                    timer.schedule(exitApp,new Date(System.currentTimeMillis()+1*1000));

                }
            }
        }
        if(e.getSource() == Cancel){
            MessageLabel.setForeground(Color.BLACK);
            MessageLabel.setText("Transaction Cancelled");
            java.util.Timer timer =  new Timer();
            TimerTask exitApp = new TimerTask() {
                @Override
                public void run() {

                    DFrame.dispose();
                    panel.setVisible(false);
                    Login login = new Login();
                }
            };
            timer.schedule(exitApp,new Date(System.currentTimeMillis()+3*1000));
        }
        if(e.getSource() == Clear){
            amount.setText("");
        }

    }

}
