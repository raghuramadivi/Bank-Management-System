package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener
{
    String pin;
    JButton button;
    mini(String pin)
    {
        this.pin=pin;
        getContentPane().setBackground(new Color(255,204,204));
        setSize(400,600);
        setLocation(20,20);
        setLayout(null);

        JLabel l1=new JLabel();
        l1.setBounds(20,140,400,200);
        add(l1);

        JLabel l2=new JLabel("TechCoder");
        l2.setFont(new Font("System",Font.BOLD,15));
        l2.setBounds(150,20,200,20);
        add(l2);

        JLabel l3=new JLabel();
        l3.setBounds(20,80,300,20);
        add(l3);

        JLabel l4=new JLabel();
        l4.setBounds(20,400,300,20);
        add(l4);

        try
        {
            int balance=0;
            Con c=new Con();
            ResultSet resultSet=c.statement.executeQuery("select * from login where pin='"+pin+"'");
            while (resultSet.next())
            {
                l3.setText("Card Number: "+resultSet.getString("card_number").substring(0,4)+"XXXXXXXX"+resultSet.getString("card_number").substring(12));

            }
            l4.setText("Your Total Balance is Rs "+balance);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try
        {
            Con c=new Con();
            int balance=0;
            ResultSet resultSet=c.statement.executeQuery("select * from bank where pin='"+pin+"'");//We have take the value frm the database
            while (resultSet.next())
            {

                l1.setText(l1.getText()+"<html>"+resultSet.getString("date")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("type")+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+resultSet.getString("amount")+"<br><br><html>");//What does &nbsp mean? &nbsp; is actually one of the most frequently used HTML entities. Nbsp stands for non-breaking space, meaning that strings separated with this entity will not be separated and put into separate lines.
                if(resultSet.getString("type").equals("Deposit")){
                    balance+=Integer.parseInt(resultSet.getString("amount")); //&nbsp indicates gap(5 means 5 empty spaces)
                }else{
                    balance-=Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        button=new JButton("Exit");
        button.setBounds(20,500,100,25);
        add(button);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
    }

    public static void main(String[] args)
    {
        new mini("");
    }
}
