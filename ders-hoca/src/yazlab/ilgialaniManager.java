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
public class ilgialaniManager implements IİlgiAlani{
  Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
    @Override
    public void insert(String ilgialani, int ogrenciid) {
         baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO ilgialani(ilgialani,ogrenciid) VALUES(?,?)";
        pt=  baglan.prepareStatement(sql);
         pt.setString(1, ilgialani);
         pt.setInt(2,ogrenciid);
         
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public void update(String ilgialani, int ogrenciid, int id) {
     baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
       String sql=("UPDATE ilgialani SET ilgialani=?,ogrenciid=? WHERE id=?");
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, ilgialani);
            pt.setInt(2,ogrenciid);
              pt.setInt(3, id);
      
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
}
