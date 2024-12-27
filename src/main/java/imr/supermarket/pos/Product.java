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

public class Product extends javax.swing.JPanel {

    public Product() {
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
        NAme = new javax.swing.JLabel();
        NAme1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        NAme4 = new javax.swing.JLabel();
        NAme5 = new javax.swing.JLabel();
        NAme6 = new javax.swing.JLabel();
        p_name = new javax.swing.JTextField();
        p_barcode = new javax.swing.JTextField();
        p_price = new javax.swing.JTextField();
        p_qty = new javax.swing.JTextField();
        p_sid = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        p_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        NAme2 = new javax.swing.JLabel();
        NAme3 = new javax.swing.JLabel();
        p_search = new javax.swing.JTextField();
        NAme7 = new javax.swing.JLabel();
        p_search_tbl = new javax.swing.JTextField();

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NAme.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme.setText("Name :");

        NAme1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme1.setText("Bar Code :");

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

        NAme4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme4.setText("Price :");

        NAme5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme5.setText("Qty :");

        NAme6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme6.setText("Supplier ID :");

        p_sid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_sidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(NAme1)
                            .addComponent(NAme)
                            .addComponent(NAme4)
                            .addComponent(NAme5)
                            .addComponent(NAme6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_sid, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_price, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme)
                    .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NAme1)
                    .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(NAme4)
                    .addComponent(p_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme5)
                    .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme6)
                    .addComponent(p_sid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        p_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Barcode", "Price", "Qty", "SID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        p_table.setToolTipText("");
        p_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                p_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(p_table);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        NAme2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme2.setText("Search ID :");

        NAme3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        NAme3.setText("Product info");

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
                .addGap(18, 18, 18)
                .addComponent(p_search, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(NAme3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );

        NAme7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        NAme7.setText("Search record :");

        p_search_tbl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_search_tblActionPerformed(evt);
            }
        });
        p_search_tbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_search_tblKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(NAme7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_search_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NAme7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(p_search_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // save

    String name = p_name.getText();
    String barcode = p_barcode.getText();
    String price = p_price.getText();
    String qty = p_qty.getText();
    String sid = p_sid.getText();

    try {
        // Use the DatabaseConnection class to establish a connection
        Connection conn = dbMSSQL.dbMSSQL();
        Statement stmt = conn.createStatement();
        
        // Insert query
        String query = "INSERT INTO products (product_name, product_barcode, product_price, product_qty, sid) VALUES ('" + name + "', '" + barcode + "', '" + price + "', '" + qty + "', '" + sid + "')";
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
        String search = p_search.getText();
        try {

            // Use the DatabaseConnection class to establish a connection
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();

            ResultSet rs = s.executeQuery(" SELECT * FROM products WHERE pid = '"+search+"'");
            JOptionPane.showMessageDialog(null, "found");

            if (rs.next()) {

                p_name.setText(rs.getString("product_name"));
                p_barcode.setText(rs.getString("product_barcode"));
                p_price.setText(rs.getString("product_price"));
                p_qty.setText(rs.getString("product_qty"));
                p_sid.setText(rs.getString("sid"));

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
            
            String id = p_search.getText();
            
             String name = p_name.getText();
    String barcode = p_barcode.getText();
    String price = p_price.getText();
    String qty = p_qty.getText();
    String sid = p_sid.getText();
            
            try {
                
                Connection conn = dbMSSQL.dbMSSQL();
                Statement s = conn.createStatement();
                s.executeUpdate(" UPDATE products SET product_name ='"+name+"'"
                        + " ,product_barcode ='"+barcode+"'"
                                + " ,product_price ='"+price+"'"
                                        + " ,product_qty ='"+qty+"'"
                                                + " ,sid ='"+sid+"'"
                                + " WHERE pid = '"+id+"' ");
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
               
        //delete
        String id = p_search.getText();
        try {

            Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
            s.executeUpdate("DELETE FROM products WHERE pid = '"+id+"'");
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
    }//GEN-LAST:event_jButton4ActionPerformed

    private void p_sidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_sidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_sidActionPerformed

    private void p_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_p_tableMouseClicked
        // table click 
        int r = p_table.getSelectedRow();

        String id = p_table.getValueAt(r, 0).toString();
        String name = p_table.getValueAt(r, 1).toString();
        String barcode = p_table.getValueAt(r, 2).toString();
        String price = p_table.getValueAt(r, 3).toString();
        String qty = p_table.getValueAt(r, 4).toString();
        String sid = p_table.getValueAt(r, 5).toString();

        p_search.setText(id);
        p_name.setText(name);
        p_barcode.setText(barcode);
        p_price.setText(price);
        p_qty.setText(qty);
        p_sid.setText(sid); 
    }//GEN-LAST:event_p_tableMouseClicked

    private void p_search_tblActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_search_tblActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_search_tblActionPerformed

    private void p_search_tblKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_search_tblKeyReleased
        // search table

        String name= p_search_tbl.getText();
        try{

            DefaultTableModel dt = (DefaultTableModel) p_table.getModel();
            dt.setRowCount(0);

            Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();

            ResultSet rs= s.executeQuery("SELECT * FROM products WHERE product_name LIKE '%"+name+"%' ");

            while (rs.next()){
                Vector v=new Vector();

                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));

                dt.addRow(v);
            }

        }catch (Exception e) {
            try {
                tb_load();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_p_search_tblKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NAme;
    private javax.swing.JLabel NAme1;
    private javax.swing.JLabel NAme2;
    private javax.swing.JLabel NAme3;
    private javax.swing.JLabel NAme4;
    private javax.swing.JLabel NAme5;
    private javax.swing.JLabel NAme6;
    private javax.swing.JLabel NAme7;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField p_barcode;
    private javax.swing.JTextField p_name;
    private javax.swing.JTextField p_price;
    private javax.swing.JTextField p_qty;
    private javax.swing.JTextField p_search;
    private javax.swing.JTextField p_search_tbl;
    private javax.swing.JTextField p_sid;
    private javax.swing.JTable p_table;
    // End of variables declaration//GEN-END:variables

private void clearText() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void tb_load() throws ClassNotFoundException{
  
  
      try {
          
          DefaultTableModel dt = (DefaultTableModel) p_table.getModel();
          dt.setRowCount(0);
          
          Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
          ResultSet rs = s.executeQuery(" SELECT * FROM products");
          
          while (rs.next()) {              
              
              Vector v = new Vector();
              
              v.add(rs.getString(1));
              v.add(rs.getString(2));
              v.add(rs.getString(3));
              v.add(rs.getString(4));
              v.add(rs.getString(5));
              v.add(rs.getString(6));
              
              dt.addRow(v);
                          
              
              
              
          }
          
      } catch (SQLException e) {
          System.out.println(e);
      }
  
  } 

}
