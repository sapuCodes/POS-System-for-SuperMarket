package imr.supermarket.pos;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Discounts extends javax.swing.JPanel {

    public Discounts() {
        initComponents();
        try {
            dis_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Discounts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dis_tbl = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dis_name = new javax.swing.JTextField();
        dis_value = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dis_type = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dis_add = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        dis_id = new javax.swing.JLabel();

        setBackground(new java.awt.Color(162, 114, 141));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        dis_tbl.setBackground(new java.awt.Color(234, 203, 195));
        dis_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Discount Name", "Type", "Value"
            }
        ));
        dis_tbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dis_tblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(dis_tbl);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        jPanel3.setBackground(new java.awt.Color(234, 203, 195));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/discount.png"))); // NOI18N
        jLabel1.setText("Add Discount");

        jPanel1.setBackground(new java.awt.Color(234, 203, 195));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Discount Name :");

        dis_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_nameActionPerformed(evt);
            }
        });

        dis_value.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_valueActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Discount Value :");

        dis_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Percentage", "Fixed" }));
        dis_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_typeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Type :");

        dis_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/done.png"))); // NOI18N
        dis_add.setText("Add Discount");
        dis_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_addActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(dis_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(dis_name, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dis_value, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dis_id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dis_add)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(dis_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dis_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dis_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(dis_value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(dis_add, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 314, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(178, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dis_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dis_nameActionPerformed

    private void dis_valueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_valueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dis_valueActionPerformed

    private void dis_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dis_typeActionPerformed

    private void dis_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_addActionPerformed
        // add discount button

    // Get inputs from UI components
    String discountName = dis_name.getText().trim();
    String discountValueText = dis_value.getText().trim();
    String discountType = dis_type.getSelectedItem().toString();

    // Validation: Ensure all fields are filled out and discount value is numeric
    if (discountName.isEmpty() || discountValueText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        return;
    }

    double discountValue;
    try {
        discountValue = Double.parseDouble(discountValueText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid discount value.");
        return;
    }

    // Decide the discount type and store it accordingly
    String discountTypeCode = discountType.equals("Percentage") ? "Percentage" : "Fixed";

    // Insert into the database
    String query = "INSERT INTO Discounts (discount_name, discount_type, discount_value, active) " +
                   "VALUES (?, ?, ?, 1)";
    
    try (Connection conn = dbMSSQL.dbMSSQL(); // Auto-closes connection
         PreparedStatement pstmt = conn.prepareStatement(query)) {

        // Set parameters for the prepared statement
        pstmt.setString(1, discountName);
        pstmt.setString(2, discountTypeCode);
        pstmt.setDouble(3, discountValue);

        // Execute the query
        pstmt.executeUpdate();

        JOptionPane.showMessageDialog(this, "Discount added successfully.");

        // Clear input fields after successful insertion
        dis_name.setText("");
        dis_value.setText("");
        dis_type.setSelectedIndex(0); // Reset the discount type to the default

    } catch (SQLException e) {
        // Log exception and show a message
        JOptionPane.showMessageDialog(this, "Error while saving the discount: " + e.getMessage());
        e.printStackTrace(); // Log the exception for debugging
    } catch (ClassNotFoundException ex) {
        // Handle exception for missing class
        JOptionPane.showMessageDialog(this, "Database driver not found.");
        ex.printStackTrace(); // Log the exception for debugging
    }
        try {
            dis_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Discounts.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dis_addActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // delete btn

        String name = dis_name.getText();
        try {

            Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
            s.executeUpdate("DELETE FROM discounts WHERE discount_name = '"+name+"'");
            JOptionPane.showMessageDialog(null, "Discount Deleted!");

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dis_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

            // update btn
try {
        // Update button action

        String id = dis_id.getText();  // e.g., 'ID:1007'
        String name = dis_name.getText();
        String value = dis_value.getText(); // Assuming this is a numeric value, validate it if needed
        String type = dis_type.getSelectedItem().toString();

        if (id.isEmpty() || name.isEmpty() || value.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all fields.");
            return;
        }

        // Extract numeric ID part from the string (e.g., 'ID:1007' -> '1007')
        String numericId = id.replaceAll("[^0-9]", "");  // This removes non-numeric characters

        // Validate the extracted numeric ID
        if (numericId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid discount ID format.");
            return;
        }

        // Validate if value is a valid number
        double discountValue = 0.0;
        try {
            discountValue = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Discount value must be a number.");
            return;
        }

        // Database connection and update query
        try {
            Connection conn = dbMSSQL.dbMSSQL();
            String query = "UPDATE discounts SET discount_name = ?, discount_value = ?, discount_type = ? WHERE discount_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, name); // Set the discount name
            ps.setDouble(2, discountValue); // Set the discount value (numeric)
            ps.setString(3, type); // Set the discount type
            ps.setInt(4, Integer.parseInt(numericId)); // Set the discount ID as integer
            
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data Updated Successfully");
            } else {
                JOptionPane.showMessageDialog(null, "No records updated, please check the ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Reload the data after the update
        dis_load();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dis_tblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dis_tblMouseClicked
        // table click 
        int r = dis_tbl.getSelectedRow();

        String id = dis_tbl.getValueAt(r, 0).toString();
        String name = dis_tbl.getValueAt(r, 1).toString();
        String type = dis_tbl.getValueAt(r, 2).toString();
        String value = dis_tbl.getValueAt(r, 3).toString();

        dis_id.setText("ID:"+id);
        dis_name.setText(name);
        dis_value.setText(value);
    }//GEN-LAST:event_dis_tblMouseClicked

    public void dis_load() throws ClassNotFoundException{
  
  
      try {
          
          DefaultTableModel dt = (DefaultTableModel) dis_tbl.getModel();
          dt.setRowCount(0);
          
          Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
          ResultSet rs = s.executeQuery(" SELECT * FROM discounts");
          
          while (rs.next()) {              
              
              Vector v = new Vector();
              
              v.add(rs.getString(1));
              v.add(rs.getString(2));
              v.add(rs.getString(3));
              v.add(rs.getString(4));
              dt.addRow(v);
                          
              
              
              
          }
          
      } catch (SQLException e) {
          System.out.println(e);
      }
  
  } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dis_add;
    private javax.swing.JLabel dis_id;
    private javax.swing.JTextField dis_name;
    private javax.swing.JTable dis_tbl;
    private javax.swing.JComboBox<String> dis_type;
    private javax.swing.JTextField dis_value;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
