package JavaProject;

import car.caradd;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarLended extends JDialog{

    // variables for the GUI window
    private JLabel title;
    private JButton backButton;
    private JPanel panel1;
    private JButton modifyACarButton;
    private JTextField idinput;
    private JLabel idmodifycar;
    private JPanel panel2;
    private JTable table1;
    private JScrollPane jpanel1;
    private JLabel removecar;
    private JButton deletebutton;
    private JTextField deletecarinput;

    public CarLended(JFrame parent,String username) {

        super(parent);
        setTitle("List of Lended Car");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        // we create the model of the table we will use to display the information of the car(s) rented by the client
        int CC;
        String[] columnNames= {"Registration","Model","Brand","Garage Adress","Price/Day","Avaibility: From","Avaibility: To"};
        Object[][] data={
        };
        table1.setModel(new DefaultTableModel(data,columnNames));
        try{

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
            PreparedStatement insert = con.prepareStatement("SELECT Car.registration,Car.model,Car.make,Car.garageadress,Car.priceperday,Car.date,Car.due FROM Car,contract WHERE Car.registration=contract.registration");
            ResultSet Rs = insert.executeQuery();
            //Rs.next();
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
                    v2.add(Rs.getString("date"));
                    v2.add(Rs.getString("due"));
                }

                DFT.addRow(v2);
            }


        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex) {
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
            Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, e);
        }

        //if we click on back, we will be back on the Admin Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //if we click on Modify the Car, it will open the Car Modify Information page
        modifyACarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                String registration = idinput.getText();
                if(registration.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Registration Field Empty !!");
                }
                else {
                    CarLendedModification carlendedmodification = new CarLendedModification(null, registration,username);
                }
            }
        });

        //if we click on Delete, it will delete the car we choose in the field in the Car table and in the Contract table
        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String registration = deletecarinput.getText();
                if(registration.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Registration Field Empty !!");
                }
                else {
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD", "root", "root");
                        PreparedStatement insert1 = con.prepareStatement("delete from Car where registration=?");
                        PreparedStatement insert2 = con.prepareStatement("delete from contract where registration=?");
                        insert2.setString(1,registration);
                        insert2.executeUpdate();
                        insert1.setString(1,registration);
                        insert1.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Deletion done !");
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(caradd.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        setVisible(true);



    }


}
