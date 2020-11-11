package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Balance  {
    Get obj = new Get();
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel MessageLabel = new JLabel();
    JLabel MessageLabel2 = new JLabel("Balance : ");
    String AccountNumber;
    Balance(JFrame BFrame , String accountNumber){
        frame = BFrame ;
         AccountNumber = accountNumber ;

        MessageLabel2.setBounds(25,0,420,30);
        MessageLabel2.setFont(new Font(null,Font.ITALIC,25));

        MessageLabel.setBounds(25,50,420,40);
        MessageLabel.setFont(new Font(null,Font.ITALIC,25));

        long Balance = obj.GetBalance(accountNumber);
        System.out.println(obj.GetBalance(accountNumber));
        MessageLabel.setText(String.valueOf(Balance));

        frame.add(panel);
        panel.setSize(420,420);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        panel.setVisible(true);

        panel.add(MessageLabel);
        panel.add(MessageLabel2);

        java.util.Timer timer =  new Timer();
        TimerTask exitApp = new TimerTask() {
            @Override
            public void run() {

                frame.dispose();
                Login login = new Login();
            }
        };
        timer.schedule(exitApp,new Date(System.currentTimeMillis()+2*1000));

    }

}
