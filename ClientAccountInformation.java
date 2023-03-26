package JavaProject;

import car.caradd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientAccountInformation extends JDialog{

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
    private JRadioButton businessbutton;
    private JRadioButton individualbutton;
    private JLabel statut;
    private JTextField businessadressoutput;
    private JButton modifynumber;

    public ClientAccountInformation(JFrame parent,String username) {
        super(parent);
        setTitle("Client Account Information");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //connection to the database and we display in each field the information of the client who is connected
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
            PreparedStatement insert = con.prepareStatement("SELECT * FROM Client WHERE client_username=?");
            insert.setString(1,username);
            ResultSet Rs = insert.executeQuery();
            Rs.next();
            firstnameoutput.setText(Rs.getString("Prenom_client"));
            lastnameoutput.setText(Rs.getString("Nom_client"));
            numberoutput.setText(Rs.getString("phone_number_client"));
            emailoutput.setText(Rs.getString("mail_client"));
            usernameoutput.setText(username);
            passwordoutput.setText(Rs.getString("client_password"));
            if(Rs.getString("statut_client").equals("Business")) {
                businessbutton.setSelected(true);
            }
            else{
                individualbutton.setSelected(true);
            }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
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
                    String statut = new String();
                    businessbutton.setActionCommand("Business");
                    individualbutton.setActionCommand("Individual");
                    ButtonGroup group = new ButtonGroup();
                    group.add(businessbutton);
                    group.add(individualbutton);

                    if(e.getActionCommand().equals("Modify Information")){
                        statut=group.getSelection().getActionCommand();
                    }

                    //String picture = pictureinput.getText();

                    if (FirstName.isEmpty() || LastName.isEmpty() || Email.isEmpty() || PhoneNumber.isEmpty() || NewUsername.isEmpty() || password.isEmpty()||statut.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Field(s) empty");
                    } else {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");
                        PreparedStatement insert = con.prepareStatement("update Client set Prenom_client=?,Nom_client=?,mail_client=?,phone_number_client=?,client_username=?,client_password=?,statut_client=? where client_username=?");
                        insert.setString(1, FirstName);
                        insert.setString(2, LastName);
                        insert.setString(3, Email);
                        insert.setString(4, PhoneNumber);
                        insert.setString(5, NewUsername);
                        insert.setString(6, password);
                        insert.setString(7, statut);
                        insert.setString(8, username);

                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Saved");
                        dispose();

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

        //if we click on back, we will be back on the Client Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
