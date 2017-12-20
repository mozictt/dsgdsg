/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package konfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fatma Wati
 */
public class koneksi {
    private static Connection koneksi;
    public static Connection GetconConnection()throws SQLException{
        if(koneksi==null){
            koneksi=DriverManager.getConnection("jdbc:mysql://localhost:3306/owncamping","root","");
        }
        return koneksi;
    }
}
