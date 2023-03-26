package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RentaCar3 extends JDialog{

    // variables for the GUI window
    private JTextField textField1;
    private JTextField textField2;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel title;
    private JTextField textField3;
    private JButton backButton;
    private JButton payButton;
    private JPanel panel1;

    public RentaCar3(JFrame parent,String username, String Date,String Due) {
        super(parent);
        setTitle("Rent a Car (Step 3)");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600,300));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on back, we will be back on the Rent a Car (Step 2) page
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RentaCar2 rentaCar2 =new RentaCar2(null,username,Date,Due);

            }
        });

        //if we click on Pay it will show a message for your booking and we will be back on the Client Menu
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"Congratulations for your booking !!");
                dispose();
            }
        });
        setVisible(true);
    }
}
