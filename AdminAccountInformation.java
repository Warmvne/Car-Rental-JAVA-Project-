package JavaProject;

import car.caradd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminAccountInformation extends JDialog{

    // variables to create the GUI window
    private JTextField firstnameoutput;
    private JTextField emailoutput;
    private JTextField lastnameoutput;
    private JTextField usernameoutput;
    private JButton modifyfirstname;
    private JButton modifypassword;
    private JTextField passwordoutput;
    private JButton backButton;
    private JPanel panel1;
    private JLabel title;
    private JLabel firstname;
    private JLabel lastname;
    private JLabel email;
    private JLabel username;
    private JLabel password;
    private JButton modifyusername;
    private JButton modifylastname;
    private JButton modifyemail;
    private JTextField numberoutput;
    private JButton modifyInformationButton;
    private JButton modifynumber;

    public AdminAccountInformation(JFrame parent,String username) {
        super(parent);
        setTitle("Admin Account Information");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //connection to the database and we display in each field the information of the admin who is connected
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
            PreparedStatement insert = con.prepareStatement("SELECT * FROM Admin WHERE admin_username=?");
            insert.setString(1,username);
            ResultSet Rs = insert.executeQuery();
            Rs.next();
            firstnameoutput.setText(Rs.getString("Prenom_Admin"));
            lastnameoutput.setText(Rs.getString("Nom_Admin"));
            numberoutput.setText(Rs.getString("phone_number_admin"));
            emailoutput.setText(Rs.getString("mail_admin"));
            usernameoutput.setText(username);
            passwordoutput.setText(Rs.getString("admin_password"));


        }
        catch (SQLException e) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        }

        //if we click on Modify Information it will take the information and update the data of the client
        modifyInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String FirstName = firstnameoutput.getText();
                    String LastName = lastnameoutput.getText();
                    String Email = emailoutput.getText();
                    String PhoneNumber = numberoutput.getText();
                    String NewUsername = usernameoutput.getText();
                    String password = passwordoutput.getText();

                    //String picture = pictureinput.getText();

                    if (FirstName.isEmpty() || LastName.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty() || NewUsername.isEmpty() || password.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Field(s) empty");
                    } else {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");
                        PreparedStatement insert = con.prepareStatement("update Admin set Prenom_Admin=?,Nom_Admin=?,mail_admin=?,phone_number_admin=?,admin_username=?,admin_password=? where admin_username=?");
                        insert.setString(1, FirstName);
                        insert.setString(2, LastName);
                        insert.setString(3, Email);
                        insert.setString(4, PhoneNumber);
                        insert.setString(5, NewUsername);
                        insert.setString(6, password);
                        insert.setString(7, username);

                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Saved");
                        dispose();

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //if we click on back, we will be back on the Admin Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

}
