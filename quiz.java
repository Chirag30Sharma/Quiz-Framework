import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class quiz extends JFrame {
    private JTextField tf,id;
    private JPasswordField pf;
    private JButton btn;
    private JLabel lbl1,lbl2,lbl3,lbl4;
    private JPanel ul;
    public static String idn;
    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root", "root");
        Statement stmt = conn.createStatement();
    ){
        String sql = "CREATE DATABASE IF NOT EXISTS login;";
        stmt.executeUpdate(sql);
        String sqltable = "USE login;";
        stmt.executeUpdate(sqltable);
        String user = "CREATE TABLE IF NOT EXISTS user(Name varchar(20),ID int(9) primary key,Password varchar(20),Score int(10));";
        stmt.executeUpdate(user);
        //Test Case
        String test = "INSERT IGNORE INTO user(Name,ID,Password) values('ABC',101,'XYZ');";
        stmt.executeUpdate(test);
    }
    catch(Exception ab){
        ab.printStackTrace();
    }
        quiz frame = new quiz();
        frame.setVisible(true);
    }

    /* Create the frame. */
    public quiz() {
        setBounds(0, 0, 1000, 600);
        setResizable(false);
        ul = new JPanel();
        setContentPane(ul);
        ul.setLayout(null);

        lbl1 = new JLabel("Login");
        lbl1.setForeground(Color.BLACK);
        lbl1.setFont(new Font("Times New Roman", Font.BOLD, 45));
        lbl1.setBounds(420, 15, 270, 90);
        ul.add(lbl1);

        tf = new JTextField();
        tf.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        tf.setBounds(480, 170, 280, 70);
        ul.add(tf);
        tf.setColumns(10);

        id = new JTextField();
        id.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        id.setBounds(480, 250, 280, 70);
        ul.add(id);

        pf = new JPasswordField();
        pf.setFont(new Font("Times New Roman", Font.PLAIN, 32));
        pf.setBounds(480, 350, 280, 70);
        ul.add(pf);

        lbl2 = new JLabel("Username");
        lbl2.setBackground(Color.BLACK);
        lbl2.setForeground(Color.BLACK);
        lbl2.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lbl2.setBounds(250, 165, 190, 50);
        ul.add(lbl2);

        lbl4 = new JLabel("ID");
        lbl4.setBackground(Color.BLACK);
        lbl4.setForeground(Color.BLACK);
        lbl4.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lbl4.setBounds(250, 250, 150, 50);
        ul.add(lbl4);

        lbl3 = new JLabel("Password");
        lbl3.setForeground(Color.BLACK);
        lbl3.setBackground(Color.CYAN);
        lbl3.setFont(new Font("Times New Roman", Font.PLAIN, 31));
        lbl3.setBounds(250, 350, 190, 50);
        ul.add(lbl3);

        btn = new JButton("Login");
        btn.setFont(new Font("Times New Roman", Font.PLAIN, 26));
        btn.setBounds(550, 450, 160, 70);
        ul.add(btn);
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                idn = id.getText();
                String userName = tf.getText();
                String password = pf.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root", "root");
                    PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select id,name,password from user where id=? and name=? and password=?");
                    st.setString(1,idn);
                    st.setString(2, userName);
                    st.setString(3, password);
                    ResultSet rs = st.executeQuery();
                    if (rs.next()) {
                        dispose();
                        JOptionPane.showMessageDialog(btn, "You have successfully logged in to the test");
                        new LTest();
                    } else {
                        JOptionPane.showMessageDialog(btn, "Wrong Username or Password, Try again");
                        tf.setText("");
                        id.setText("");
                        pf.setText("");
                    }
                } catch (Exception ea) {
                    ea.printStackTrace();
                }
            }
        });
    }
}

class LTest implements ActionListener {
    JFrame fr;
    JRadioButton rb1, rb2, rb3, rb4, rb5;
    JButton b1,b4, b5;
    JLabel lb1, lb2, lb3;
    ButtonGroup bg;
    String ques[] = {"1. How many types of access specifiers are provided in OOP?", "2. Which among the following can show polymorphism?", "3. Which access specifier is usually used for data members of a class?", "4. Which feature of OOP reduces the use of nested classes?", "5. Where is the memory allocated for the objects?", "6. Instance of which type of class can't be created?", "7. How many types of polymorphism in the Java programming language?", "8. Which of the following class is known as the generic class?", "9. The object cannot be ____?", "10. The principle of abstraction is?",""};
    String op1[] = {"4", "Overloading &&", "Protected", "Inheritance", "Cache", "Parent Class", "3", "Final Class", "Passed by Copy", "Is used to achieve OOPS",""};
    String op2[] = {"3 ", "Overloading <<", "Private", "Binding", "ROM", "Abstract Class", "2", "Template Class", "Passed as function", "Is used to avoid duplication",""};
    String op3[] = {"2", "Overloading ||", "Public", "Abstraction", "HDD", "Anonymous Class", "5", "Abstract Class", "Passed by value", "Use abstraction at its minimum",""};
    String op4[] = {"1", "Overloading +=", "Default", "Encapsulation", "RAM", "Nested Class", "4", "Efficient Code", "Passed by reference", "Is used to remove longer codes",""};
    String ans[] = {"3", "Overloading <<", "Private", "Inheritance", "RAM", "Abstract Class", "2", "Template Class", "Passed as function", "Is used to avoid duplication",""};
    String co[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",""};
    int cn = 0;
    int qc = 0;
    int ca = 0;
    int wa = 0;

    LTest() {
        fr = new JFrame();
        fr.setLayout(null);
        fr.setSize(1600, 1200);
        Container c = fr.getContentPane();
        c.setBackground(Color.WHITE);
        lb1 = new JLabel(ques[0]);
        lb1.setBounds(50, 50, 1500, 30);
        fr.add(lb1);
        lb1.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 30));
        lb1.setForeground(Color.BLACK);
        lb2 = new JLabel("Questions Completed:");
        lb2.setBounds(200, 600, 600, 30);
        lb2.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 30));
        lb2.setForeground(Color.BLACK);
        fr.add(lb2);
        lb3 = new JLabel("Score :");
        lb3.setBounds(0, 1100, 600, 30);
        lb3.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 30));
        lb3.setForeground(Color.WHITE);
        fr.add(lb3);
        rb1 = new JRadioButton(op1[cn]);
        rb1.setBounds(50, 220, 300, 50);
        rb1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fr.add(rb1);
        rb2 = new JRadioButton(op2[cn]);
        rb2.setBounds(600, 220, 300, 50);
        rb2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fr.add(rb2);
        rb3 = new JRadioButton(op3[cn]);
        rb3.setBounds(50, 400, 300, 50);
        rb3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fr.add(rb3);
        rb4 = new JRadioButton(op4[cn]);
        rb4.setBounds(600, 400, 300, 50);
        rb4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fr.add(rb4);
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        bg.add(rb3);
        bg.add(rb4);
        bg.add(rb5);
        rb1.addActionListener(this);
        rb2.addActionListener(this);
        rb3.addActionListener(this);
        rb4.addActionListener(this);
        b1 = new JButton("Submit >>");
        b1.setBounds(1000   , 500, 200, 40);
        b1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        fr.add(b1);
        b4 = new JButton(co[0]);
        b4.setBounds(600, 600, 50, 30);
        fr.add(b4);
        b5 = new JButton(co[0]);
        b5.setBounds(600, 1000, 50, 30);
        fr.add(b5);
        b1.addActionListener(this);
        fr.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String en = "";
            if (rb1.isSelected())
                en = rb1.getText();
            if (rb2.isSelected())
                en = rb2.getText();
            if (rb3.isSelected())
                en = rb3.getText();
            if (rb4.isSelected())
                en = rb4.getText();
            if (en.equals(ans[cn])) {
                JOptionPane.showMessageDialog(null, "Next Question!");
                ca++;
                b5.setText(co[ca]);
            } else {
                JOptionPane.showMessageDialog(null, "Next Question!");
                wa++;
            }
            qc++;
            b4.setText(co[qc]);
            cn++;
            lb1.setText(ques[cn]);
            rb1.setText(op1[cn]);
            rb2.setText(op2[cn]);
            rb3.setText(op3[cn]);
            rb4.setText(op4[cn]);
            
            if(qc == 10){
                JOptionPane.showMessageDialog(null, "Quiz has been captured successfully.");
                JOptionPane.showMessageDialog(null, "You have scored "+ca+" marks." );
                fr.dispose();
            }
            if(qc == 10){
                try{
                    Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root", "root");
                    PreparedStatement st = (PreparedStatement) conn.prepareStatement("Update user set Score = ? where ID = ?;");
                    st.setInt(1, ca);
                    st.setString(2, quiz.idn);
                    st.executeUpdate();
                    System.exit(0);
                }
                catch(Exception ea){
                    ea.printStackTrace();
                }
            }
        }
    }
}