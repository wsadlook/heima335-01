package com.itheima;

import javax.swing.*;

/**
 * @author YangZhen
 * @date 2019-05-05 15:42
 */
public class Demo15  extends JFrame{
    public Demo15(){
        add(new JLabel("hello label"));
        add(new JTextField("hello  field "));
        add(new JButton("hello  button"));
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Demo15();
    }
}
