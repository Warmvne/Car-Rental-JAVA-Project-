package JavaProject;

import car.caradd;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// variables to create the GUI window
public class LogIn extends JDialog {
    private JTextField usernameinput;
    private JPasswordField passwordinput;
    private JLabel title;
    private JLabel username;
    private JLabel password;
    private JButton back;
    private JButton submit;
    private JPanel panel1;
    private JPanel panel2;
    private JRadioButton clientinput;
    private JRadioButton adminInput;

    //method of the login window
    public LogIn(JFrame parent)
    {
        super(parent);
        setTitle("Login");
        setContentPane(panel1);
        setMinimumSize(new Dimension(400,200));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on back it will close the window, and we will be back on the Welcome page
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomepage=new WelcomePage(null);
            }
        });

        //if we click on submit it will check the information entered, and it will say if the account exists or not
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //saving the data entered
                    String username = usernameinput.getText();
                    String password = passwordinput.getText();
                    String statut = new String();

                    adminInput.setActionCommand("Admin");
                    clientinput.setActionCommand("Client");
                    ButtonGroup group = new ButtonGroup();
                    group.add(adminInput);
                    group.add(clientinput);
                    //add(adminInput);
                    //add(clientinput);

                    //when we click on submit we save the statut of the person who is logging
                    if(e.getActionCommand().equals("Submit")){
                        statut=group.getSelection().getActionCommand();
                    }

                    //checking if the data is not empty
                    if(statut.isEmpty() || username.isEmpty() || password.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Field(s) Empty");
                    }

                    else{
                        //checking if it's a client, we check if it exists in the database, and it opens the client menu, otherwise we have an error message
                        if(statut.equals("Client")){
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                            PreparedStatement test = con.prepareStatement("select * from Client where client_username=? and client_password=?");
                            test.setString(1,username);
                            test.setString(2,password);
                            ResultSet rs=test.executeQuery();
                                if(!rs.isBeforeFirst()){
                                    JOptionPane.showMessageDialog(null,"User doesn't exist !");
                                    usernameinput.setText("");
                                    passwordinput.setText("");
                                }
                                else{
                                    while(rs.next()){
                                        dispose();
                                        //it creates and display the client menu
                                        JOptionPane.showMessageDialog(null,"Welcome "+username+" !");
                                        ClientMenu client = new ClientMenu(null,username);
                                    }
                                }
                        }
                        //checking if it's an Admin, we check if it exists in the database and it opens the admin menu, otherwise we have an error message
                        else{
                            Class.forName("com.mysql.jdbc.Driver");
                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                            PreparedStatement test = con.prepareStatement("select * from Admin where admin_username=? and admin_password=?");
                            test.setString(1,username);
                            test.setString(2,password);
                            ResultSet rs=test.executeQuery();
                            if(!rs.isBeforeFirst()){
                                JOptionPane.showMessageDialog(null,"User doesn't exist !");
                                usernameinput.setText("");
                                passwordinput.setText("");
                            }
                            else{
                                while(rs.next()){
                                    dispose();
                                    //it creates and display the admin menu
                                    JOptionPane.showMessageDialog(null,"Welcome "+username+" !");
                                    AdminMenu admin = new AdminMenu(null,username);
                                }
                            }
                        }
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
}
