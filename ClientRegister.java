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

public class ClientRegister extends JDialog {

    // variables to create the GUI window
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
    private JRadioButton businessBtn;
    private JRadioButton individualBtn;
    private JLabel number;
    private JLabel statut;

    //method of the Client Register window
    public ClientRegister(JFrame parent)
    {
        super(parent);
        setTitle("Client Register");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on back it will close this window and we will be back on the Welcome page
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage=new WelcomePage(null);
            }
        });

        //if we click on submit it will save the data in the database
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // saving the data entered
                    String FirstName = firstnameinput.getText();
                    String LastName = lastnameinput.getText();
                    String Email = emailinput.getText();
                    String PhoneNumber = numberinput.getText();
                    String id = identifiantinput.getText();
                    String statut = new String();
                    String password = passwordinput.getText();

                    // saving buttons value
                    businessBtn.setActionCommand("Business");
                    individualBtn.setActionCommand("Individual");
                    ButtonGroup group = new ButtonGroup();
                    group.add(businessBtn);
                    group.add(individualBtn);
                    //add(businessBtn);
                    //add(individualBtn);
                    if(e.getActionCommand().equals("Submit")){
                        statut=group.getSelection().getActionCommand();
                    }

                    // checking if the data is not empty
                    if(FirstName.isEmpty()){
                        JOptionPane.showMessageDialog(null, "First Name Field Empty !");
                    }
                    if(LastName.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Last Name Field Empty !");
                    }
                    if(Email.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Email Field Empty !");
                    }
                    if(PhoneNumber.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Phone Number Field Empty !");
                    }
                    if(id.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Identifiant Field Empty !");
                    }
                    if(statut.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Statut Field Empty !");
                    }
                    if(password.isEmpty()){
                        JOptionPane.showMessageDialog(null, "Password Field Empty !");
                    }
                    //connection to the database and saving the data
                    else{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                        PreparedStatement insert = con.prepareStatement("insert into Client (Prenom_client,Nom_client,phone_number_client,mail_client,statut_client,client_username,client_password)values(?,?,?,?,?,?,?)");
                        insert.setString(1,FirstName);
                        insert.setString(2,LastName);
                        insert.setString(3,PhoneNumber);
                        insert.setString(4,Email);
                        insert.setString(5,statut);
                        insert.setString(6,id);
                        insert.setString(7,password);

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
        ClientRegister bloc1 = new ClientRegister(null);
    }
}
