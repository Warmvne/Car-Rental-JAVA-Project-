package JavaProject;

import car.caradd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

// variables to create the GUI window
public class AdminRegister extends JDialog {
    private JTextField firstnameinput;
    private JTextField lastnameinput;
    private JPanel panel1;
    private JLabel lastname;
    private JLabel firstname;
    private JTextField emailinput;
    private JTextField identifiantinput;
    private JPasswordField passwordinput;
    private JLabel email;
    private JLabel identifiant;
    private JLabel password;
    private JLabel title;
    private JButton back;
    private JButton submit;
    private JTextField numberinput;

    //method of the Admin Register window
    public AdminRegister(JFrame parent)
    {
        super(parent);
        setTitle("Client Register");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on back it will close this window
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage=new WelcomePage(null);
            }
        });

        //if we click on submit it will save the data on the database
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // saving the data entered
                    String FirstName = firstnameinput.getText();
                    String LastName = lastnameinput.getText();
                    String Email = emailinput.getText();
                    String number = numberinput.getText();
                    String identifiant = identifiantinput.getText();
                    String password = passwordinput.getText();

                    //checking if the data is not empty
                    if(FirstName.isEmpty()){
                        JOptionPane.showMessageDialog(null, "First Name Field Empty !");
                    }
                    if(LastName.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Last Name Field Empty !");
                    }
                    if(Email.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Email Field Empty !");
                    }
                    if(number.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Phone number Field Empty !");
                    }
                    if(identifiant.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Identifiant Field Empty !");
                    }
                    if(password.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Password Field Empty !");
                    }
                    //connection to the database and saving the data
                    else{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                        PreparedStatement insert = con.prepareStatement("insert into Admin (Prenom_Admin,Nom_Admin,mail_admin,phone_number_admin,admin_username,admin_password)values(?,?,?,?,?,?)");
                        insert.setString(1,FirstName);
                        insert.setString(2,LastName);
                        insert.setString(3,Email);
                        insert.setString(4,number);
                        insert.setString(5,identifiant);
                        insert.setString(6,password);

                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Saved");

                    }
                }
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch (SQLException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setVisible(true);
    }
    public static void main(String[] args)
    {
        AdminRegister bloc1 = new AdminRegister(null);
    }
}
