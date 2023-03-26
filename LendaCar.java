package JavaProject;

import car.caradd;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.time.LocalDate;
//import java.time.format.DateTimeParseException;


public class LendaCar  extends JDialog{

    // variables for the GUI window
    private JTextField registrationinput;
    private JTextField modelinput;
    private JButton back;
    private JButton addCar;
    private JTextField brandinput;
    private JTextField garageadressinput;
    private JPanel panel1;
    private JLabel registration;
    private JLabel model;
    private JPanel panel2;
    private JLabel brand;
    private JLabel garageadress;
    private JLabel avaibility;
    private JPanel panel3;
    private JLabel from;
    private JLabel to;
    private JComboBox<Integer> fromboxday;
    private JComboBox<Integer> fromboxmonth;
    private JComboBox<Integer> fromboxyear;
    private JComboBox<Integer> toboxday;
    private JComboBox<Integer> toboxmonth;
    private JComboBox<Integer> toboxyear;
    private JPanel panel4;
    private JTextField pricedailyinput;
    private JLabel price;

    public LendaCar(JFrame parent,String username)
    {
        super(parent);
        setTitle("Lend a Car");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600,300));
        setModal(true);
        setLocationRelativeTo(parent);


        //if we click on back, we will back on the Admin Menu
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //if we click on Add Car, it will add a new car in the Car table and it will prepare the row in the Contract table
        addCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String Registration = registrationinput.getText();
                    String Model = modelinput.getText();
                    String Brand = brandinput.getText();
                    String Garage_Address = garageadressinput.getText();
                    String PricePerDay = pricedailyinput.getText();
                    float PriceDaily = Float.parseFloat(PricePerDay);
                    String Date = fromboxyear.getSelectedItem().toString()+"-"+fromboxmonth.getSelectedItem().toString()+"-"+fromboxday.getSelectedItem().toString();
                    String Due = toboxyear.getSelectedItem().toString()+"-"+toboxmonth.getSelectedItem().toString()+"-"+toboxday.getSelectedItem().toString();
                    System.out.println(Date);
                    System.out.println(Due);
                    SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
                    Date myDate = java.sql.Date.valueOf(Date);
                    Date myDue = java.sql.Date.valueOf(Due);
                    //LocalDate myDate = LocalDate.parse(Date, DateTimeFormatter.ISO_DATE);
                    //LocalDate myDue = LocalDate.parse(Due, DateTimeFormatter.ISO_DATE);
                    boolean availability = true;
                    //String picture = pictureinput.getText();

                    if(Registration.isEmpty()||Model.isEmpty()||Brand.isEmpty()||Garage_Address.isEmpty()||PricePerDay.isEmpty()||Date.isEmpty()||Due.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Field(s) empty");
                    }
                    else{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                        PreparedStatement insert1 = con.prepareStatement("insert into Car (registration,model,make,garageadress,priceperday,date,due,availability)values(?,?,?,?,?,?,?,?)");
                        PreparedStatement insert2 = con.prepareStatement("insert into contract (registration,Id_admin)values(?,?)");
                        PreparedStatement insertIDAdmin = con.prepareStatement("select Id_admin from Admin WHERE admin_username=?");
                        insertIDAdmin.setString(1,username);
                        ResultSet rs = insertIDAdmin.executeQuery();
                        rs.next();
                        Integer ID_ADMIN= rs.getInt(1);

                        insert1.setString(1,Registration);
                        insert1.setString(2,Model);
                        insert1.setString(3,Brand);
                        insert1.setString(4,Garage_Address);
                        insert1.setFloat(5,PriceDaily);
                        insert1.setDate(6, myDate);
                        insert1.setDate(7, myDue);
                        insert1.setBoolean(8,availability);

                        insert2.setString(1,Registration);
                        insert2.setInt(2,ID_ADMIN);

                        insert1.executeUpdate();
                        insert2.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Successfully Saved");
                        dispose();

                    }
                } catch (ClassNotFoundException ex) {
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
