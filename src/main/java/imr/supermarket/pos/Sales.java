package imr.supermarket.pos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Sales extends javax.swing.JPanel {
    
    public static String barcode_c;
    public static String cus_id = "0";
    
    public double Stock_qty = 0;
    
    private boolean isDiscountApplied = false; // Flag to check if a discount has been applied
private double originalCartTotal = 0; // Store the original cart total before any discount is applied


    public Sales() {
        initComponents();
       data_load();
       loadDiscounts(); 
        
    }
    
    private void loadDiscounts() {
     // Load active discounts into the combo box
    try {
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        
        // Query to fetch active discounts
        ResultSet rs = s.executeQuery("SELECT discount_name FROM Discounts WHERE active = 1");
        Vector<String> v = new Vector<>();
        
        while (rs.next()) {
            // Add the discount names to the vector
            v.add(rs.getString("discount_name"));
        }
        
        // Set the model for the combo box
        DefaultComboBoxModel combo = new DefaultComboBoxModel(v);
        dis_select.setModel(combo);
        
    } catch (SQLException e) {
        System.out.println("Error loading discounts: " + e.getMessage());
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    private void resetDiscount() {
    System.out.println("Resetting Discount...");

    // Clear the added discount field (set to empty string)
    added_dis.setText("0.00"); // Clear the discount field

    // Reset the discount flag
    isDiscountApplied = false;

    System.out.println("Discount Reset. Original total: " + originalCartTotal);
}


    
    public void data_load(){
        //load customers to combo box
        
        try{
            Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
                    
           ResultSet rs= s.executeQuery("SELECT * FROM customers");
           Vector v=new Vector();
           
           while (rs.next()){
               v.add(rs.getString("customer_name"));
               
               DefaultComboBoxModel combo=new DefaultComboBoxModel(v);
               combo_cus.setModel(combo);
           }
                    
        }catch (Exception e) {
            System.out.println(e);
        }
        
        // load last invoice number
      
      try {
          
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
          ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid =1");
          
          if (rs.next()) {
              
              inid.setText(rs.getString("val"));
              
          }
          
      } catch (Exception e) {
      }
        // pluss new invoice
try {
    int i = Integer.valueOf(inid.getText().trim());
    i++;
    inid.setText(String.valueOf(i));
} catch (NumberFormatException e) {
    System.err.println("Invalid input in text field: '" + inid.getText() + "'");
    e.printStackTrace();

    // Optionally reset the field to a default value
    inid.setText("0");
}

        
        
        //load products to combo box
        try{
            Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
                    
           ResultSet rs= s.executeQuery("SELECT * FROM products");
           Vector v=new Vector();
           
           while (rs.next()){
               v.add(rs.getString("product_name"));
               
               DefaultComboBoxModel combo=new DefaultComboBoxModel(v);
               combo_pro.setModel(combo);
           }
                    
        }catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void pro_total_cal(){
//product cal
        
        Double qt= Double.valueOf(p_qty.getText());
        Double price = Double.valueOf(u_price.getText());
        Double tot;
        
        tot = qt * price;
        
        totalp.setText(String.valueOf(tot));
}
    public void cart_total() {
    int numofrows = cart_tbl.getRowCount();
    double totals = 0;

    // Sum the item totals from the cart
    for (int i = 0; i < numofrows; i++) {
        try {
            double values = Double.valueOf(cart_tbl.getValueAt(i, 3).toString()); // Assuming column 3 is item price
            totals += values;
        } catch (NumberFormatException e) {
            // Handle empty or invalid values in the table
            System.out.println("Error calculating total: " + e.getMessage());
        }
    }
    tot_qty.setText(String.format("%.2f", totals)); // Format to 2 decimal places

    // Sum the discount-adjusted totals from the cart (assuming column 5 is the final price after discount)
    double total = 0;
    for (int i = 0; i < numofrows; i++) {
        try {
            double value = Double.valueOf(cart_tbl.getValueAt(i, 5).toString()); // Assuming column 5 is final price after discount
            total += value;
        } catch (NumberFormatException e) {
            // Handle empty or invalid values in the table
            System.out.println("Error calculating discount-adjusted total: " + e.getMessage());
        }
    }

    // Set the total to the cart_tot label
    cart_tot.setText(String.format("%.2f", total)); // Format to 2 decimal places
}

    
   
public void paidtot() {
    // Default values for paid and total
    double paid = 0.0;
    double total = 0.0;
    
    // Get paid amount and handle invalid/empty values
    String paidText = paid_amt.getText();
    if (paidText != null && !paidText.trim().isEmpty()) {
        try {
            paid = Double.valueOf(paidText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid paid amount, defaulting to 0.0");
            paid = 0.0;  // Use default value if invalid input
        }
    }

    // Get cart total and handle invalid/empty values
    String cartTotalText = cart_tot.getText();
    if (cartTotalText != null && !cartTotalText.trim().isEmpty()) {
        try {
            total = Double.valueOf(cartTotalText);
        } catch (NumberFormatException e) {
            System.out.println("Invalid cart total, defaulting to 0.0");
            total = 0.0;  // Use default value if invalid input
        }
    }

    // Calculate due amount
    double due = paid - total;

    // Update the balance label
    balance.setText(String.format("%.2f", due));  // Format to 2 decimal places
}

    
    public void stockup(){
        // get all tabale prdct id and sell qty
        DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();
        int rc = dt.getRowCount();
        
        for (int i=0; i<rc; i++){
            String bcode = dt.getValueAt(i,2) .toString(); //id or barcode
            String sell_qty = dt.getValueAt(i,3) .toString(); //id or barcode
            
            System.out.println(bcode);
            System.out.println(sell_qty);
            
            try{
            Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
                    
           ResultSet rs= s.executeQuery("SELECT product_qty FROM products WHERE product_barcode = '"+bcode+"'  ");
           
           if (rs.next()){
               Stock_qty = Double.valueOf (rs.getString("product_qty"));
               
           }
                    
        }catch (Exception e) {
            System.out.println(e);
        }
            Double st_qty = Stock_qty;
            Double sel_qty = Double.valueOf(sell_qty);
            
            Double new_qty = st_qty - sel_qty; //new qty = stock qty - sell qty
            
            String nqty = String.valueOf(new_qty);
            
            try{
                Connection conn = dbMSSQL.dbMSSQL();
                Statement ss = conn.createStatement();
                ss.executeUpdate("UPDATE products SET product_qty = '"+nqty+"' WHERE product_barcode = '"+bcode+"' "); // updatee new qty in products table
            }catch (Exception e) {
                System.out.println(e);
            }
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_cus = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        combo_pro = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        u_price = new javax.swing.JLabel();
        p_qty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        totalp = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        stk_qty = new javax.swing.JLabel();
        bar_code = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cart_tbl = new javax.swing.JTable();
        tot_qty = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        removeAll = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        addtocart = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        dis_select = new javax.swing.JComboBox<>();
        dis_remove = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        cart_tot = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        added_dis = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        paid_amt = new javax.swing.JTextField();
        payNprint = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();

        setBackground(new java.awt.Color(162, 114, 141));

        jPanel1.setBackground(new java.awt.Color(234, 203, 195));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Customer : ");

        combo_cus.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        combo_cus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        combo_cus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_cusActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Product :");

        combo_pro.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        combo_pro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        combo_pro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        combo_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_proActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Qty :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Unit Price :");

        u_price.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        u_price.setForeground(new java.awt.Color(0, 0, 0));
        u_price.setText("00.00");

        p_qty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        p_qty.setText("1");
        p_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_qtyActionPerformed(evt);
            }
        });
        p_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_qtyKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Total Price :");

        totalp.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        totalp.setForeground(new java.awt.Color(0, 0, 0));
        totalp.setText("00");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Stock Qty:");

        stk_qty.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        stk_qty.setForeground(new java.awt.Color(0, 0, 0));
        stk_qty.setText("00");

        bar_code.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Product Barcode:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_cus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stk_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(u_price, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bar_code, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combo_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(totalp))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(combo_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(u_price)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stk_qty))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bar_code, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(234, 203, 195));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cart_tbl.setBackground(new java.awt.Color(234, 203, 195));
        cart_tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "Name", "Bar Code", "Qty", "Unit Price", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(cart_tbl);

        tot_qty.setForeground(new java.awt.Color(0, 0, 0));
        tot_qty.setText("00");

        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Total Qty: ");

        removeAll.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        removeAll.setText("Remove All");
        removeAll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        removeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllActionPerformed(evt);
            }
        });

        remove.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        remove.setText("Remove");
        remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        addtocart.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addtocart.setText("Add to cart");
        addtocart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addtocart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addtocartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removeAll)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(remove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addtocart, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(9, 9, 9))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tot_qty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(addtocart)
                .addGap(18, 18, 18)
                .addComponent(remove)
                .addGap(18, 18, 18)
                .addComponent(removeAll)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tot_qty)))
        );

        jPanel4.setBackground(new java.awt.Color(234, 203, 195));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/discount.png"))); // NOI18N
        jLabel12.setText("Discounts :");

        dis_select.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        dis_select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select" }));
        dis_select.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dis_select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_selectActionPerformed(evt);
            }
        });

        dis_remove.setText("Remove Discount");
        dis_remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dis_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dis_removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dis_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dis_remove)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dis_select, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dis_remove, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
        );

        jPanel5.setBackground(new java.awt.Color(234, 203, 195));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Paid Amount :");

        jPanel6.setBackground(new java.awt.Color(234, 203, 195));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cart_tot.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cart_tot.setForeground(new java.awt.Color(0, 0, 0));
        cart_tot.setText("00.00");
        cart_tot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Sub Total:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Balance/ Due :");

        balance.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        balance.setForeground(new java.awt.Color(0, 0, 0));
        balance.setText("00.00");
        balance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        added_dis.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        added_dis.setForeground(new java.awt.Color(0, 0, 0));
        added_dis.setText("00.00");
        added_dis.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Discounts:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cart_tot, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(added_dis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cart_tot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(added_dis))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(balance))
                .addContainerGap())
        );

        paid_amt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        paid_amt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paid_amtActionPerformed(evt);
            }
        });
        paid_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paid_amt, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        payNprint.setText("Pay & Print");
        payNprint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        payNprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                payNprintActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Invoice No:");

        inid.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        inid.setForeground(new java.awt.Color(0, 0, 102));
        inid.setText("1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(payNprint, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inid, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inid))
                .addGap(27, 27, 27)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 12, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(payNprint, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void payNprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_payNprintActionPerformed
        // PAY & PRINT
    
    // Check if paid amount is entered or zero
    String paidAmountText = paid_amt.getText();
    if (paidAmountText.isEmpty() || Double.valueOf(paidAmountText) == 0.0) {
        JOptionPane.showMessageDialog(balance, "Please enter a valid paid amount.");
        return; // Stop further processing if paid amount is invalid
    }
    
    // cart data fetching to database
    try {
        DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();
        int rc = dt.getRowCount();

        for (int i = 0; i < rc; i++) {
            String inid = dt.getValueAt(i, 0).toString(); // get inid
            String P_name = dt.getValueAt(i, 1).toString(); // get product name
            String bar_code = dt.getValueAt(i, 2).toString(); // get barcode
            String qty = dt.getValueAt(i, 3).toString(); // get product qty
            String un_price = dt.getValueAt(i, 4).toString(); // get product unit price
            String tot_price = dt.getValueAt(i, 5).toString(); // get product total Price
            
            // cart DB
            Connection conn = dbMSSQL.dbMSSQL();
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO cart (INID, product_name, product_barcode, qty, unit_price, total_price) VALUES ('" + inid + "','" + P_name + "','" + bar_code + "','" + qty + "','" + un_price + "','" + tot_price + "')");
        }

        JOptionPane.showMessageDialog(null, "Data Saved");
        
    } catch (Exception e) {
        System.out.println(e);
    }
    
    try {
        // sales DB
        String inv_id = inid.getText();
        String cname  = combo_cus.getSelectedItem().toString();
        String totqty = tot_qty.getText();
        String tot_bil = cart_tot.getText();           
        String blnc = balance.getText();
        
        // paid check
        Double tot = Double.valueOf(cart_tot.getText());
        Double pid = Double.valueOf(paid_amt.getText());
        String Status = null;
        if (pid.equals(0.0)) {
            Status = "UnPaid";
        } else if (tot > pid) {
            Status = "Partial";
        } else if (tot <= pid) {
            Status = "Paid";
        }

        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        s.executeUpdate("INSERT INTO sales(INID, Cid, Customer_Name, Total_Qty, Total_Bill, Status, Balance) VALUES('" + inv_id + "','" + cus_id + "','" + cname + "','" + totqty + "','" + tot_bil + "','" + Status + "','" + blnc + "')");

    } catch (NumberFormatException | SQLException e) {
        System.out.println(e);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
    }

    // save last inid number
    try {
        String id = inid.getText(); 
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        s.executeUpdate("UPDATE extra SET val='" + id + "' WHERE exid =1");

    } catch (SQLException e) {
        System.out.println(e);
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
    }

    // Print or view ireport bill
    try {
        HashMap para = new HashMap();
        para.put("inv_id", inid.getText());  // inv_id is ireport parameter name
        ReportView r = new ReportView("src\\reports\\print.jasper", para);
        r.setVisible(true);  

    } catch (Exception e) {
    }

    stockup(); // stock update
        
    }//GEN-LAST:event_payNprintActionPerformed

    private void addtocartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addtocartActionPerformed
        // add to cart
        
       // Add to cart with stock quantity check and real-time stock reduction
    double sell_qty = Double.valueOf(p_qty.getText()); // Quantity user wants to add

    // Check if the quantity is valid (greater than 0)
    if (sell_qty <= 0) {
        JOptionPane.showMessageDialog(balance, "Please select a valid quantity greater than 0.");
        return; // Stop further processing
    }

    double currentStockQty = Double.valueOf(stk_qty.getText()); // Real-time displayed stock
    double originalStockQty = currentStockQty + getTotalCartQuantity(); // True stock from original state
    String selectedProduct = combo_pro.getSelectedItem().toString();

    DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();

    // Validate: Check if adding this quantity exceeds the stock
    if (sell_qty <= currentStockQty) {
        // Add the product to the cart
        Vector v = new Vector();
        v.add(inid.getText());
        v.add(selectedProduct);
        v.add(bar_code.getText());
        v.add(p_qty.getText());
        v.add(u_price.getText());
        v.add(totalp.getText());
        dt.addRow(v);

        // Update stock quantity in real-time
        double updatedStockQty = currentStockQty - sell_qty;
        stk_qty.setText(String.valueOf(updatedStockQty));

        // Update cart totals
        cart_total();
        paidtot();
    } else {
        JOptionPane.showMessageDialog(balance, "Stock has only " + originalStockQty + " Qty available. "
                + "You already added " + getTotalCartQuantity() + " Qty to the cart.");
    }
    // Reset or update the discount label
    resetDiscount();  // Make sure discount is recalculated
}

// Helper method to calculate total quantity of the product in the cart
private double getTotalCartQuantity() {
    DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();
    String selectedProduct = combo_pro.getSelectedItem().toString();

    double totalCartQty = 0;
    for (int i = 0; i < dt.getRowCount(); i++) {
        String productInCart = dt.getValueAt(i, 1).toString(); // Assuming product name is in column 1
        if (productInCart.equals(selectedProduct)) {
            totalCartQty += Double.valueOf(dt.getValueAt(i, 3).toString()); // Assuming quantity is in column 3
        }
    }
    return totalCartQty;
        
    }//GEN-LAST:event_addtocartActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        // remove item from cart
        
        // Remove selected item from cart and update stock quantity
    try {
        DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();
        int rw = cart_tbl.getSelectedRow(); // Get the selected row
        
        if (rw != -1) {
            // Retrieve the removed item's quantity and product name
            String removedProduct = dt.getValueAt(rw, 1).toString(); // Assuming product name is in column 1
            double removedQty = Double.valueOf(dt.getValueAt(rw, 3).toString()); // Assuming quantity is in column 3
            double removedPrice = Double.valueOf(dt.getValueAt(rw, 5).toString()); // Assuming the final price is in column 5
            
            // Update the stock quantity in real-time
            if (combo_pro.getSelectedItem().toString().equals(removedProduct)) {
                double currentStockQty = Double.valueOf(stk_qty.getText());
                double updatedStockQty = currentStockQty + removedQty;
                stk_qty.setText(String.valueOf(updatedStockQty));
            }
            
            // Remove the row from the cart
            dt.removeRow(rw);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to remove.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }
    
    // Reset the discount and total values after removing the item
    added_dis.setText("0.00");  // Reset the discount label
    cart_tot.setText("0.00");  // Reset the cart total label
    
    // Recalculate the cart total and paid amount after item removal
    cart_total();
    paidtot();
    
    // Reset or update the discount label
    resetDiscount();  // Make sure discount is recalculated
    
    }//GEN-LAST:event_removeActionPerformed

    private void removeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAllActionPerformed
        // remove all from cart
        
        /// remove all from cart
    DefaultTableModel dt = (DefaultTableModel) cart_tbl.getModel();
    
    try {
        // Iterate through all rows to sum up quantities for each product
        String selectedProduct = combo_pro.getSelectedItem().toString();
        double totalRemovedQty = 0;
        
        for (int i = 0; i < dt.getRowCount(); i++) {
            String productInCart = dt.getValueAt(i, 1).toString(); // Assuming product name is in column 1
            if (productInCart.equals(selectedProduct)) {
                totalRemovedQty += Double.valueOf(dt.getValueAt(i, 3).toString()); // Assuming quantity is in column 3
            }
        }
        
        // Update stock quantity in real-time
        double currentStockQty = Double.valueOf(stk_qty.getText());
        double updatedStockQty = currentStockQty + totalRemovedQty;
        stk_qty.setText(String.valueOf(updatedStockQty));
        
        // Clear all rows from the cart
        dt.setRowCount(0);

        // Reset cart total and discount values explicitly
        cart_tot.setText("0.00");
        added_dis.setText("0.00");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }

    // Recalculate totals after clearing cart
    cart_total();
    paidtot();
    
    
    }//GEN-LAST:event_removeAllActionPerformed

    private void p_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_qtyActionPerformed

    private void paid_amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paid_amtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amtActionPerformed

    private void combo_cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_cusActionPerformed
        // get cid
        String name = combo_cus.getSelectedItem().toString();
        
        try{
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT customer_name, cid FROM customers WHERE customer_name= '"+name+"' ");
        if (rs.next()){
            
            cus_id = (rs.getString("cid"));
            
        }

        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_combo_cusActionPerformed

    private void combo_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_proActionPerformed
        // product combo box action
        
        String name = combo_pro.getSelectedItem().toString();
        
        try{
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT product_price, product_qty, product_barcode FROM products WHERE product_name= '"+name+"' ");
        if (rs.next()){
            u_price.setText(rs.getString("product_price"));
            bar_code.setText(rs.getString("product_barcode"));
            stk_qty.setText(rs.getString("product_qty"));
        }
        
        pro_total_cal();
        
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_combo_proActionPerformed

    private void p_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_qtyKeyReleased
        // product qty
        pro_total_cal();
        
    }//GEN-LAST:event_p_qtyKeyReleased

    private void paid_amtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amtKeyReleased
         paidtot();
    }//GEN-LAST:event_paid_amtKeyReleased

    private void dis_selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_selectActionPerformed
        // select discount combo button 
     // Get the selected discount name
    String discountName = dis_select.getSelectedItem().toString();
    
    try {
        // Query the database for the selected discount
        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT discount_name, discount_type, discount_value FROM Discounts WHERE discount_name = '" + discountName + "'");
        
        if (rs.next()) {
            // Process the discount details
            String discountType = rs.getString("discount_type"); // Get discount type (Percentage or Fixed)
            double discountValue = rs.getDouble("discount_value"); // Get the discount value (percentage or fixed amount)
            
            // Log for debugging purposes
            System.out.println("Discount Type: " + discountType);
            System.out.println("Discount Value: " + discountValue);
            
            // Fetch current cart total (before applying the discount)
            double currentCartTotal = Double.parseDouble(cart_tot.getText());

            if (!isDiscountApplied) {
                // Store the original cart total before any discount is applied
                originalCartTotal = currentCartTotal;
            }

            double newCartTotal = originalCartTotal;  // Initialize with original total
            double appliedDiscount = 0;

            // Apply the discount based on the discount type
            if ("Percentage".equalsIgnoreCase(discountType)) {
                // Apply percentage-based discount
                appliedDiscount = (newCartTotal * discountValue) / 100; // Discount is a percentage
                newCartTotal = newCartTotal - appliedDiscount;
            } else if ("Fixed".equalsIgnoreCase(discountType)) {
                // Apply fixed amount discount
                appliedDiscount = discountValue; // Discount is a fixed amount (e.g., $5)
                newCartTotal = newCartTotal - appliedDiscount;
            }

            // Update the cart total label with the new total
            cart_tot.setText(String.format("%.2f", newCartTotal));

            // Display the applied discount (fixed or percentage)
            added_dis.setText(String.format("%.2f", appliedDiscount));

            // Optionally, you can show the discount details (for debugging purposes)
            System.out.println("Applied Discount: " + appliedDiscount);
            System.out.println("New Cart Total: " + newCartTotal);

            // Set the flag to indicate a discount has been applied
            isDiscountApplied = true;
        }
        
    } catch (SQLException e) {
        System.out.println("Error fetching discount details: " + e.getMessage());
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(Sales.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_dis_selectActionPerformed

    private void dis_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dis_removeActionPerformed
        // remove discount from cart
        resetDiscount(); 
        cart_total();
        paidtot();
    }//GEN-LAST:event_dis_removeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel added_dis;
    private javax.swing.JButton addtocart;
    private javax.swing.JLabel balance;
    private javax.swing.JLabel bar_code;
    private javax.swing.JTable cart_tbl;
    private javax.swing.JLabel cart_tot;
    private javax.swing.JComboBox<String> combo_cus;
    private javax.swing.JComboBox<String> combo_pro;
    private javax.swing.JButton dis_remove;
    private javax.swing.JComboBox<String> dis_select;
    private javax.swing.JLabel inid;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField p_qty;
    private javax.swing.JTextField paid_amt;
    private javax.swing.JButton payNprint;
    private javax.swing.JButton remove;
    private javax.swing.JButton removeAll;
    private javax.swing.JLabel stk_qty;
    private javax.swing.JLabel tot_qty;
    private javax.swing.JLabel totalp;
    private javax.swing.JLabel u_price;
    // End of variables declaration//GEN-END:variables
}
