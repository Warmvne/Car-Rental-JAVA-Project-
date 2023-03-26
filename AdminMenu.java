package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JDialog{

    // variables to create the GUI window
    private JButton lendACarButton;
    private JButton carLendedButton;
    private JButton accountInformationButton;
    private JButton logOutButton;
    private JLabel title;
    private JPanel panel1;
    public AdminMenu(JFrame parent,String username) {

        setTitle("Admin Menu");
        setContentPane(panel1);
        setMinimumSize(new Dimension(200,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on Lend A Car it will open the page
        lendACarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LendaCar lendacar = new LendaCar(null,username);
            }
        });

        //if we click on Car Lended it will open the page
        carLendedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarLended carlended=new CarLended(null,username);
            }
        });

        //if we click on Account Information it will open the page
        accountInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminAccountInformation adminaccountinformation= new AdminAccountInformation(null,username);
            }
        });

        //if we click on Log out it will close the menu and we will be back on the Welcome Page
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomePage welcomePage=new WelcomePage(null);
            }
        });
        setVisible(true);
    }
}
