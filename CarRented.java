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

public class CarRented extends JDialog{

    // variables for the GUI window
    private JLabel title;
    private JButton backButton;
    private JPanel panel1;
    private JButton modifyACarButton;
    private JPanel panel2;
    private JTable table1;
    private JScrollPane jpanel1;

    public CarRented(JFrame parent,String username) {

        super(parent);
        setTitle("List of Rented Car");
        setContentPane(panel1);
        setMinimumSize(new Dimension(800,500));
        setModal(true);
        setLocationRelativeTo(parent);

        // we create the model of the table we will use to display the information of the car(s) rented by the client
        int CC;
        String[] columnNames = {"Contract Statut", "Admin Number", "Registration", "Model", "Brand", "Garage Adress", "Paid Price", "Avaibility: From", "Avaibility: To", "Car in use"};
        Object[][] data={
        };
        table1.setModel(new DefaultTableModel(data,columnNames));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8889/FinalJavaBDD","root","root");
            PreparedStatement insert = con.prepareStatement("SELECT contract.ID_contract,contract.Id_admin,Car.registration,Car.model,Car.make,Car.garageadress,contract.date,contract.due,Car.availability,contract.total_due FROM Car INNER JOIN Client ON Car.ID_client=Client.ID_client INNER JOIN contract ON Car.ID_client=contract.ID_client AND Car.date=contract.date AND Car.due=contract.due WHERE Client.client_username=? ");
            insert.setString(1,username);
            ResultSet Rs = insert.executeQuery();
            //Rs.next();

            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) table1.getModel();
            DFT.setRowCount(0);

            while (Rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= CC; ii++) {
                    v2.add(Rs.getString("ID_contract"));
                    v2.add(Rs.getString("Id_admin"));
                    v2.add(Rs.getString("registration"));
                    v2.add(Rs.getString("model"));
                    v2.add(Rs.getString("make"));
                    v2.add(Rs.getString("garageadress"));
                    v2.add(Rs.getString("total_due"));
                    v2.add(Rs.getString("date"));
                    v2.add(Rs.getString("due"));
                    v2.add(Rs.getString("availability"));
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

        //if we click on back, we will be back on the Client Menu
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }


}
