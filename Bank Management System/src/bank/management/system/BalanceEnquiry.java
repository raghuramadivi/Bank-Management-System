package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class BalanceEnquiry extends JFrame implements ActionListener
{
    String pin;
    JLabel l2;//We are declaring globally becoz we want to store the data(current balance) from mysql into l2.
    JButton b1;
    BalanceEnquiry(String pin)
    {
        this.pin=pin;

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2=i1.getImage().getScaledInstance(1550,730,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(0,0,1550,830);
        add(l3);

        JLabel l1=new JLabel("Your Current Balance is Rs. ");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System",Font.BOLD,16));
        l1.setBounds(430,180,700,35);
        l3.add(l1);//We want show the text on the image(l1) that's y we are l1 to l3

        l2=new JLabel();
        l2.setForeground(Color.WHITE);
        l2.setFont(new Font("System",Font.BOLD,16));
        l2.setBounds(430,220,400,35);
        l3.add(l2);//We want show the text on the image(l1) that's y we are l1 to l3

        b1=new JButton("BACK");
        b1.setBounds(700,406,150,35);
        b1.setBackground(new Color(65,125,128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        int balance=0;
        try
        {
            Con c=new Con();// I have connected to the database by creating object
            ResultSet resultSet=c.statement.executeQuery("select * from bank where pin='"+pin+"'");
            while (resultSet.next())
            {
                if(resultSet.getString("type").equals("Deposit"))
                {
                    balance+=Integer.parseInt(resultSet.getString("amount"));
                }else
                {
                    balance-=Integer.parseInt(resultSet.getString("amount"));
                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        l2.setText(""+balance);

        setLayout(null);
        setSize(1550,1080);
        setLocation(0,0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args)
    {
        new BalanceEnquiry("");
    }
}
