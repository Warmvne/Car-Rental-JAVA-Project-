package JavaProject;

import car.caradd;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentaCar2 extends JDialog {

    // variables for the GUI window
    private JButton backToStep1Button;
    private JButton nextStepButton;
    private JPanel panel1;
    private JTextField registrationinput;
    private JLabel registration;
    private JTable table1;
    private JComboBox priceBox;
    private JButton Validate;


    private float totalcost;

    public RentaCar2(JFrame parent,String username,String Date,String Due) {
        super(parent);
        setTitle("Rent a Car (Step 2)");
        setContentPane(panel1);
        setMinimumSize(new Dimension(600, 300));
        setModal(true);
        setLocationRelativeTo(parent);
        boolean availability = true;

        // we create the model of the table we will use to display the information of the cars which are available
        int CC;
        String[] columnNames = {"Registration", "Model", "Brand", "Garage Adress", "Price/Day", "Admin Number"};
        Object[][] data = {
        };
        table1.setModel(new DefaultTableModel(data, columnNames));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");

                String priceDisplay = priceBox.getSelectedItem().toString();
                if(priceDisplay.equals("cheaper to expensive")){

                    PreparedStatement insert = con.prepareStatement("SELECT Car.registration,Car.model,Car.make,Car.garageadress,Car.priceperday FROM Car WHERE Car.availability=? ORDER BY priceperday ASC");
                    insert.setBoolean(1, availability);
                    ResultSet Rs = insert.executeQuery();


                    ResultSetMetaData RSMD = Rs.getMetaData();
                    CC = RSMD.getColumnCount();
                    DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
                    DFT.setRowCount(0);

                    while (Rs.next()) {
                        Vector v2 = new Vector();

                        for (int ii = 1; ii <= CC; ii++) {
                            v2.add(Rs.getString("registration"));
                            v2.add(Rs.getString("model"));
                            v2.add(Rs.getString("make"));
                            v2.add(Rs.getString("garageadress"));
                            v2.add(Rs.getString("priceperday"));
                            //v2.add(Rs.getString("Id_admin"));
                            v2.add("");

                        }

                        DFT.addRow(v2);
                    }
                }
                else{

                    PreparedStatement insert = con.prepareStatement("SELECT Car.registration,Car.model,Car.make,Car.garageadress,Car.priceperday FROM Car WHERE Car.availability=? ORDER BY priceperday DESC");
                    insert.setBoolean(1, availability);
                    ResultSet Rs = insert.executeQuery();


                    ResultSetMetaData RSMD = Rs.getMetaData();
                    CC = RSMD.getColumnCount();
                    DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
                    DFT.setRowCount(0);

                    while (Rs.next()) {
                        Vector v2 = new Vector();

                        for (int ii = 1; ii <= CC; ii++) {
                            v2.add(Rs.getString("registration"));
                            v2.add(Rs.getString("model"));
                            v2.add(Rs.getString("make"));
                            v2.add(Rs.getString("garageadress"));
                            v2.add(Rs.getString("priceperday"));
                            //v2.add(Rs.getString("Id_admin"));
                            v2.add("");

                        }

                        DFT.addRow(v2);
                    }

                }


        } catch (SQLException e) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        } catch (ClassNotFoundException e){
                Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        }

        //if we click on back, we will be back at the STEP 1
        backToStep1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                RentaCar1 rentacar1=new RentaCar1(null,username);
            }
        });

        //if we click on Next Step it will save the data in the database, and it will open the Rent a Car (step 3)
        nextStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Registration = registrationinput.getText();
                    boolean new_availability = false;

                    if(Registration.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Registration Field Empty !");
                    }
                    else {
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
                        PreparedStatement insert1 = con.prepareStatement("update Car set date=?,due=?,availability=?,ID_client=? WHERE registration=? ");
                        PreparedStatement insert2 = con.prepareStatement("update contract set total_due=?,date=?,due=?,ID_client=? WHERE Id_admin=? AND registration=? ");

                        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
                        Date myDate = java.sql.Date.valueOf(Date);
                        Date myDue = java.sql.Date.valueOf(Due);

                        PreparedStatement insertClient = con.prepareStatement("select ID_client,statut_client from Client WHERE client_username=?");
                        insertClient.setString(1,username);
                        ResultSet rsClient = insertClient.executeQuery();
                        rsClient.next();
                        int ID_client= rsClient.getInt(1);
                        String statutClient = rsClient.getString(2);

                        PreparedStatement insert3 = con.prepareStatement("select priceperday from Car where registration=?");
                        insert3.setString(1,Registration);
                        ResultSet rsCar = insert3.executeQuery();
                        rsCar.next();
                        float priceperday=rsCar.getFloat(1);

                        PreparedStatement insertAdmin = con.prepareStatement("select Id_admin from contract where registration=?");
                        insertAdmin.setString(1,Registration);
                        ResultSet rsAdmin = insertAdmin.executeQuery();
                        rsAdmin.next();
                        int ID_ADMIN=rsAdmin.getInt(1);

                        setTotalCost(statutClient,priceperday,Date,Due);

                        insert1.setDate(1, myDate);
                        insert1.setDate(2, myDue);
                        insert1.setBoolean(3,new_availability);
                        insert1.setInt(4,ID_client);
                        insert1.setString(5,Registration);

                        insert2.setFloat(1,totalcost);
                        insert2.setDate(2,  myDate);
                        insert2.setDate(3,  myDue);
                        insert2.setString(6,Registration);
                        insert2.setInt(4,ID_client);
                        insert2.setInt(5,ID_ADMIN);

                        insert1.executeUpdate();
                        insert2.executeUpdate();

                        dispose();
                        RentaCar3 rentaCar3 = new RentaCar3(null, username, Date, Due);
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
        //setVisible(true);

        //it will display the information according to the price filter
        priceBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent evt) {
                System.out.println("test");
                int CC;
                String[] columnNames = {"Registration", "Model", "Brand", "Garage Adress", "Price/Day", "Admin Number"};
                Object[][] data = {
                };
                table1.setModel(new DefaultTableModel(data, columnNames));
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");

                    String priceDisplay = priceBox.getSelectedItem().toString();
                    if(priceDisplay.equals("cheaper to expensive")){

                        PreparedStatement insert = con.prepareStatement("SELECT Car.registration,Car.model,Car.make,Car.garageadress,Car.priceperday FROM Car WHERE Car.availability=? ORDER BY priceperday ASC");
                        insert.setBoolean(1, availability);
                        ResultSet Rs = insert.executeQuery();


                        ResultSetMetaData RSMD = Rs.getMetaData();
                        CC = RSMD.getColumnCount();
                        DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
                        DFT.setRowCount(0);

                        while (Rs.next()) {
                            Vector v2 = new Vector();

                            for (int ii = 1; ii <= CC; ii++) {
                                v2.add(Rs.getString("registration"));
                                v2.add(Rs.getString("model"));
                                v2.add(Rs.getString("make"));
                                v2.add(Rs.getString("garageadress"));
                                v2.add(Rs.getString("priceperday"));
                                //v2.add(Rs.getString("Id_admin"));
                                v2.add("");

                            }

                            DFT.addRow(v2);
                        }
                    }
                    else{

                        PreparedStatement insert = con.prepareStatement("SELECT Car.registration,Car.model,Car.make,Car.garageadress,Car.priceperday FROM Car WHERE Car.availability=? ORDER BY priceperday DESC");
                        insert.setBoolean(1, availability);
                        ResultSet Rs = insert.executeQuery();


                        ResultSetMetaData RSMD = Rs.getMetaData();
                        CC = RSMD.getColumnCount();
                        DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
                        DFT.setRowCount(0);

                        while (Rs.next()) {
                            Vector v2 = new Vector();

                            for (int ii = 1; ii <= CC; ii++) {
                                v2.add(Rs.getString("registration"));
                                v2.add(Rs.getString("model"));
                                v2.add(Rs.getString("make"));
                                v2.add(Rs.getString("garageadress"));
                                v2.add(Rs.getString("priceperday"));
                                //v2.add(Rs.getString("Id_admin"));
                                v2.add("");

                            }

                            DFT.addRow(v2);
                        }

                    }


                } catch (SQLException e) {
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
                } catch (ClassNotFoundException e){
                    Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        });
        setVisible(true);
    }

    //method to calcul the total cost of the rent
    public void setTotalCost(String statut,float priceperday,String myDate,String myDue){
        LocalDate Before = LocalDate.parse(myDate);
        LocalDate After = LocalDate.parse(myDue);
        long diff= ChronoUnit.DAYS.between(Before,After);
        if(statut.equals("Individual")){

            totalcost=diff*priceperday;
        }
        else{
            totalcost= diff *priceperday;
            totalcost= (float) (totalcost-(totalcost*0.2));
        }
    }
}
