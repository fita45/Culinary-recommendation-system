/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekomendasi_kuliner;

import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class koneksi {
    private Connection con;
    private Component rootPane;
     public void koneksiDatabase() {
        String driver = "com.mysql.jdbc.Driver";//nama driver
        String url = "jdbc:mysql://localhost/data_kuliner";//nama database
        String usr = "root";//nama user database
        String pwd = "";//password database
        try {
            Class.forName(driver);
            con = (Connection) DriverManager.getConnection(url, usr, pwd);
            //JOptionPane.showMessageDialog(rootPane, "Koneksi Berhasil...");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Gagal Koneksi..");
        }
    }
}
