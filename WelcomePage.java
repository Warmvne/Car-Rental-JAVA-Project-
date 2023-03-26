package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage extends JDialog {

    //variables for the GUI window
    private JLabel title;
    private JButton signinclient;
    private JButton adminsignin;
    private JButton login;
    private JButton exit1;
    private JPanel panel1;
    public WelcomePage(JFrame parent) {
        super(parent);
        setTitle("Rent a Car");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800, 200));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on Exit it will close the application and the code will stop to run
        exit1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(1);
            }
        });

        //if we click on Sign In for a client, it will close the Welcome page, and it will open the Client Register page
        signinclient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ClientRegister client=new ClientRegister(null);
            }
        });

        //if we click on Sign In for an admin, it will close the Welcome page, and it will open the Admin Register page
        adminsignin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                AdminRegister admin=new AdminRegister(null);
            }
        });

        //if we click on Login, it will close the Welcome page, and it will open the Login page
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LogIn login = new LogIn(null);
            }
        });
        setVisible(true);
    }
}