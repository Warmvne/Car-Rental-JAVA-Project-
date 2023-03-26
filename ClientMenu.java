package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMenu extends JDialog{

    // variables to create the GUI window
    private JButton RentACarButton;
    private JButton carRentedButton;
    private JButton accountInformationButton;
    private JButton logOutButton;
    private JLabel title;
    private JPanel panel1;
    public ClientMenu(JFrame parent,String username) {

        setTitle("Client Menu");
        setContentPane(panel1);
        setMinimumSize(new Dimension(200,500));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on Rent A Car it will open the page Rent a Car (step 1)
        RentACarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RentaCar1 rentaCar1=new RentaCar1(null,username);
            }
        });

        //if we click on Car Rented it will open the page Car Rented
        carRentedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CarRented carrented=new CarRented(null,username);
            }
        });

        //if we click on Account Information it will open the page Account Information
        accountInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientAccountInformation clientaccountinformation= new ClientAccountInformation(null,username);
            }
        });

        //if we click on Log out it will close the menu, and we will be back on the Welcome Page
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
