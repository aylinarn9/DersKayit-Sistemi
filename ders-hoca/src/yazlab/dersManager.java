/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yazlab;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aylin
 */
public class dersManager implements IDers{
  Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;

    @Override
    public void insert(String dersad, String dersharfnot, int ogrenciid) {
                      baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO ders(dersad,dersharfnot,ogrenciid) VALUES(?,?,?)";
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, dersad);
            pt.setString(2, dersharfnot);
            pt.setInt(3,ogrenciid);
         
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }

    @Override
    public void update(String dersad, String dersharfnot, int ogrenciid, int dersid) {
                        baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
       String sql=("UPDATE ders SET dersad=?,dersharfnot=?,ogrenciid=? WHERE dersid=?");
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, dersad);
            pt.setString(2, dersharfnot);
            pt.setInt(3,ogrenciid);
              pt.setInt(4, dersid);
      
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}
