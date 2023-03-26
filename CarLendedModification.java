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

public class CarLendedModification extends JDialog{

    // variables for the GUI window
    private JPanel panel1;
    private JPanel panel4;
    private JLabel registration;
    private JTextField registrationinput;
    private JLabel model;
    private JTextField modelinput;
    private JPanel panel2;
    private JButton back;
    private JButton submit;
    private JLabel brand;
    private JTextField brandinput;
    private JLabel garageadress;
    private JTextField garageadressinput;
    private JLabel avaibility;
    private JPanel panel3;
    private JComboBox<Integer> fromboxday;
    private JComboBox<Integer> fromboxmonth;
    private JComboBox<Integer> fromboxyear;
    private JLabel from;
    private JLabel to;
    private JComboBox<Integer> toboxday;
    private JComboBox<Integer> toboxmonth;
    private JComboBox<Integer> toboxyear;
    private JLabel price;
    private JTextField pricedailyinput;
    public CarLendedModification(JFrame parent,String registration,String username)
    {
        super(parent);
        setTitle("Car Modification");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600,300));
        setModal(true);
        setLocationRelativeTo(parent);

        //we display the information of the car we choose to modify in the correct fields
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
            PreparedStatement insert = con.prepareStatement("SELECT registration,model,make,garageadress,priceperday,DAY(date),MONTH(date),YEAR(date),DAY(due),MONTH(due),YEAR(due) FROM Car where registration=?");
            insert.setString(1,registration);
            ResultSet Rs = insert.executeQuery();
            Rs.next();

            registrationinput.setText(registration);
            modelinput.setText(Rs.getString("model"));
            brandinput.setText(Rs.getString("make"));
            garageadressinput.setText(Rs.getString("garageadress"));
            pricedailyinput.setText((Double.toString((double) Rs.getFloat(5))));

            fromboxday.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("DAY(date)"))));
            fromboxmonth.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("MONTH(date)"))));
            fromboxyear.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("YEAR(date)"))));
            toboxday.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("DAY(due)"))));
            toboxmonth.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("MONTH(due)"))));
            toboxyear.setSelectedIndex(Integer.valueOf(String.valueOf(Rs.getDate("YEAR(due)"))));

        } catch (SQLException e) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        }

        //if we click on back, we will be back on the Car Lended page
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CarLended carlended = new CarLended(null,username);
            }
        });

        //if we click on Submit, it will update the information in the database
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Registration = registrationinput.getText();
                    String Model = modelinput.getText();
                    String Brand = brandinput.getText();
                    String Garage_Address = garageadressinput.getText();
                    String PricePerDay = pricedailyinput.getText();
                    float PriceDaily = Float.parseFloat(PricePerDay);
                    String Date = fromboxyear.getSelectedItem().toString() + "-" + fromboxmonth.getSelectedItem().toString() + "-" + fromboxday.getSelectedItem().toString();
                    String Due = toboxyear.getSelectedItem().toString() + "-" + toboxmonth.getSelectedItem().toString() + "-" + toboxday.getSelectedItem().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
                    Date myDate = java.sql.Date.valueOf(Date);
                    Date myDue = java.sql.Date.valueOf(Due);
                    boolean availability = true;
                    //String picture = pictureinput.getText();

                    if (Registration.isEmpty() || Model.isEmpty() || Brand.isEmpty() || Garage_Address.isEmpty() || PricePerDay.isEmpty() || Date.isEmpty() || Due.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Field(s) empty");
                    } else {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");
                        PreparedStatement insert = con.prepareStatement("update Car set registration=?,model=?,make=?,garageadress=?,priceperday=?,date=?,due=?,availability=? where registration=?");
                        insert.setString(1, Registration);
                        insert.setString(2, Model);
                        insert.setString(3, Brand);
                        insert.setString(4, Garage_Address);
                        insert.setFloat(5, PriceDaily);
                        insert.setDate(6,  myDate);
                        insert.setDate(7,  myDue);
                        insert.setBoolean(8, availability);
                        insert.setString(9,registration);

                        insert.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Saved");
                        dispose();
                        CarLended carlended = new CarLended(null,username);
                    }


                }
                catch (ClassNotFoundException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        setVisible(true);
    }
}
