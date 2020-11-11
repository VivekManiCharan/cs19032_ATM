package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Options implements ActionListener {

    JButton Deposit = new JButton("Deposit");
    JButton Withdraw = new JButton("Withdraw");
    JButton PinChange = new JButton("PinChange");
    JButton Balance = new JButton("Balance");
    JButton Cancel = new JButton("Cancel");
    JPanel panel= new JPanel();
    JFrame OptionsFrame  = new JFrame();
    String accountNumber;
    Options(JFrame frame, String AccountNumber){
        OptionsFrame = frame;
        accountNumber = AccountNumber;

        OptionsFrame.add(panel);

        panel.setSize(420,420);
        panel.setLayout(null);
        panel.setBackground(Color.cyan);
        panel.setVisible(true);

        Deposit.setBounds(30,30,200,40);
        Deposit.setFocusable(false);
        Deposit.addActionListener(this);

        Withdraw.setBounds(30,90,200,40);
        Withdraw.setFocusable(false);
        Withdraw.addActionListener(this);

        Balance.setBounds(30,150,200,40);
        Balance.setFocusable(false);
        Balance.addActionListener(this);

        PinChange.setBounds(30,210,200,40);
        PinChange.setFocusable(false);
        PinChange.addActionListener(this);



        Cancel.setBounds(30,270,200,40);
        Cancel.setFocusable(false);
        Cancel.addActionListener(this);

        panel.add(Deposit);
        panel.add(Withdraw);
        panel.add(Balance);
        panel.add(PinChange);
        panel.add(Cancel);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Deposit) {
            Deposit deposit = new Deposit(OptionsFrame,accountNumber);
            panel.setVisible(false);
        }
        else if(e.getSource() == Withdraw){

            WithDraw withDraw = new WithDraw(OptionsFrame , accountNumber);
            panel.setVisible(false);
        }
        else if(e.getSource() == Balance){
            Balance balance = new Balance(OptionsFrame , accountNumber);
            panel.setVisible(false);

        }
        else if(e.getSource() == PinChange){
            panel.setVisible(false);
            PinChange pinChange = new PinChange(OptionsFrame,accountNumber);

        }

        else if(e.getSource() == Cancel){
            java.util.Timer timer =  new Timer();
            TimerTask exitApp = new TimerTask() {
                @Override
                public void run() {

                    OptionsFrame.dispose();
                    panel.setVisible(false);
                    Login login = new Login();
                }
            };
            timer.schedule(exitApp,new Date(System.currentTimeMillis()+3*1000));

        }
    }
}
