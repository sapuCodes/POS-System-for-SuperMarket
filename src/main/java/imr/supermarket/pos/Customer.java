package imr.supermarket.pos;


import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class Customer extends javax.swing.JPanel {

    public Customer() {
        initComponents();
        try {
            tb_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        NAme1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        c_name = new javax.swing.JTextField();
        c_contact = new javax.swing.JTextField();
        NAme = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        c_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        NAme2 = new javax.swing.JLabel();
        NAme3 = new javax.swing.JLabel();
        c_search = new javax.swing.JTextField();
        NAme4 = new javax.swing.JLabel();
        c_search_tbl = new javax.swing.JTextField();

        setBackground(new java.awt.Color(162, 114, 141));

        jPanel1.setBackground(new java.awt.Color(234, 203, 195));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NAme1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme1.setText("Contact No :");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.png"))); // NOI18N
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search.png"))); // NOI18N
        jButton2.setText("Search");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/delete.png"))); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        c_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_nameActionPerformed(evt);
            }
        });

        c_contact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_contactActionPerformed(evt);
            }
        });

        NAme.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme.setText("Name :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NAme1)
                            .addComponent(NAme))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NAme))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme1)
                    .addComponent(c_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        c_table.setAutoCreateRowSorter(true);
        c_table.setBackground(new java.awt.Color(234, 203, 195));
        c_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Customer Name", "Contact No."
            }
        ));
        c_table.setToolTipText("");
        c_table.setShowGrid(true);
        c_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                c_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(c_table);

        jPanel2.setBackground(new java.awt.Color(234, 203, 195));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NAme2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme2.setText("Search ID :");

        NAme3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        NAme3.setForeground(new java.awt.Color(51, 51, 51));
        NAme3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customers.png"))); // NOI18N
        NAme3.setText("Customer info");

        c_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(NAme3)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(NAme2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(NAme3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        NAme4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme4.setText("Search record :");

        c_search_tbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_search_tblActionPerformed(evt);
            }
        });
        c_search_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_search_tblKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(NAme4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c_search_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(c_search_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // save

    String name = c_name.getText();
    String contact = c_contact.getText();

    try {
        // Use the DatabaseConnection class to establish a connection
        Connection conn = dbMSSQL.dbMSSQL();
        Statement stmt = conn.createStatement();
        
        // Insert query
        String query = "INSERT INTO customers (customer_name, customer_contact) VALUES ('" + name + "', '" + contact + "')";
        stmt.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Data Saved!");
        
        
        System.out.println("Record inserted successfully");
        conn.close(); // Close the connection
    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    }
    try {
            tb_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        
        // search btn code
        String search = c_search.getText();
        try {

            // Use the DatabaseConnection class to establish a connection
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(" SELECT * FROM customers WHERE cid = '"+search+"'");
            JOptionPane.showMessageDialog(null, "found");

            if (rs.next()) {

                c_name.setText(rs.getString("customer_name"));
                c_contact.setText(rs.getString("customer_contact"));

            }

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {                                         
            // update btn
            
            String id = c_search.getText();
            
            String name = c_name.getText();
            String contact = c_contact.getText();
            
            try {
                
                Connection conn = dbMSSQL.dbMSSQL();
                Statement s = conn.createStatement();
                s.executeUpdate(" UPDATE customers SET customer_name ='"+name+"'"
                        + " ,customer_contact ='"+contact+"'"
                                + " WHERE cid = '"+id+"' ");
                JOptionPane.showMessageDialog(null, "Data Updated");
                
            } catch (HeadlessException | SQLException e) {
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
            tb_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null,ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // delete btn 

        String id = c_search.getText();
        try {

            Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
            s.executeUpdate("DELETE FROM customers WHERE cid = '"+id+"'");
            JOptionPane.showMessageDialog(null, "Record Deleted!");

        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            tb_load();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

        clearText();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void c_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_searchActionPerformed

    private void c_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_nameActionPerformed

    private void c_contactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_contactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_contactActionPerformed

    private void c_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_c_tableMouseClicked
        // table click 
        int r = c_table.getSelectedRow();

        String id = c_table.getValueAt(r, 0).toString();
        String name = c_table.getValueAt(r, 1).toString();
        String contact = c_table.getValueAt(r, 2).toString();

        c_search.setText(id);
        c_name.setText(name);
        c_contact.setText(contact);
    }//GEN-LAST:event_c_tableMouseClicked

    private void c_search_tblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_search_tblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_search_tblActionPerformed

    private void c_search_tblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_search_tblKeyReleased
        // search table
        
        String name= c_search_tbl.getText();
        try{
        
        DefaultTableModel dt = (DefaultTableModel) c_table.getModel();
        dt.setRowCount(0);
                
                
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        
        ResultSet rs= s.executeQuery("SELECT * FROM customers WHERE customer_name LIKE '%"+name+"%' ");
        
        while (rs.next()){
            Vector v=new Vector();
            
            v.add(rs.getString(1));
            v.add(rs.getString(2));
            v.add(rs.getString(3));
            
            dt.addRow(v);
        }
        
        }catch (Exception e) {
            try {
                tb_load();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_c_search_tblKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NAme;
    private javax.swing.JLabel NAme1;
    private javax.swing.JLabel NAme2;
    private javax.swing.JLabel NAme3;
    private javax.swing.JLabel NAme4;
    private javax.swing.JTextField c_contact;
    private javax.swing.JTextField c_name;
    private javax.swing.JTextField c_search;
    private javax.swing.JTextField c_search_tbl;
    private javax.swing.JTable c_table;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables

    private void clearText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void tb_load() throws ClassNotFoundException{
  
  
      try {
          
          DefaultTableModel dt = (DefaultTableModel) c_table.getModel();
          dt.setRowCount(0);
          
          Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
          ResultSet rs = s.executeQuery(" SELECT * FROM customers");
          
          while (rs.next()) {              
              
              Vector v = new Vector();
              
              v.add(rs.getString(1));
              v.add(rs.getString(2));
              v.add(rs.getString(3));
              
              dt.addRow(v);
                          
              
              
              
          }
          
      } catch (SQLException e) {
          System.out.println(e);
      }
  
  }           
}
