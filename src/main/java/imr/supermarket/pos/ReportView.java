
package imr.supermarket.pos;

import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.swing.*;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;



public class ReportView extends JFrame
{
    public ReportView(String fileName) throws ClassNotFoundException, SQLException
    {
        this(fileName, null);
    }
   
    
    
    public ReportView(String fileName, HashMap para) throws ClassNotFoundException, SQLException
    {
        super("QuickPick Market POS System (Report Viewer)");

        Connection conn = dbMSSQL.dbMSSQL();
        Statement s = conn.createStatement();


        try
        {
            JasperPrint print = JasperFillManager.fillReport(fileName, para, conn);
            JRViewer viewer = new JRViewer(print);
            Container c = getContentPane();
            c.add(viewer);            
        } 
        catch (JRException jRException)
        {
            System.out.println(jRException);
        }
        setBounds(2, 2, 900, 700);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    void print() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
