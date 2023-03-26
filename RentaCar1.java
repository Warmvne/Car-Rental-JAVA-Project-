package JavaProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RentaCar1 extends JDialog{

    // variables for the GUI window
    private JLabel title;
    private JButton backButton;
    private JButton nextStepButton;
    private JPanel panel3;
    private JComboBox<Integer> fromboxday;
    private JComboBox<Integer> fromboxmonth;
    private JComboBox<Integer> fromboxyear;
    private JLabel from;
    private JLabel to;
    private JComboBox<Integer> toboxday;
    private JComboBox<Integer> toboxmonth;
    private JComboBox<Integer> toboxyear;
    private JPanel panel1;

    public RentaCar1(JFrame parent,String username) {
        super(parent);
        setTitle("Rent a Car (Step 1)");
        setContentPane(panel1);
        setMinimumSize(new Dimension(400,300));
        setModal(true);
        setLocationRelativeTo(parent);

        //if we click on back, we will be back on the Client Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();
            }
        });

        //if we click on Next Step it will save the dates, and it will open the Rent a Car (step 2)
        nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Date = fromboxyear.getSelectedItem().toString() + "-" + fromboxmonth.getSelectedItem().toString() + "-" + fromboxday.getSelectedItem().toString();
                String Due = toboxyear.getSelectedItem().toString() + "-" + toboxmonth.getSelectedItem().toString() + "-" + toboxday.getSelectedItem().toString();
                //SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
                //java.util.Date myDate = sdf.parse(Date);
                //java.util.Date myDue = sdf.parse(Due);
                if(Date.equals(Due)){
                    JOptionPane.showMessageDialog(null,"Date and Due are the same !");
                }
                else {
                    dispose();
                    RentaCar2 rentaCar2 = new RentaCar2(null, username,Date,Due);
                }


            }
        });
        setVisible(true);
    }
}
