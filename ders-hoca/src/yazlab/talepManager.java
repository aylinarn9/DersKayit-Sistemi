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
public class talepManager implements ITalep{
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
    @Override
    public void insert(int ogrenciid,String ogrenciad, String hocaad, String anlasmadurumu, String iletilenmesaj,String dersad) {
        baglanti baglanti=new baglanti();
     
         try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO derstalepanlasma(ogrenciid,ogrenciad,hocaad,anlasmadurumu,iletilenmesaj,dersad) VALUES(?,?,?,?,?,?)";
        pt=  baglan.prepareStatement(sql);
           pt.setInt(1, ogrenciid);
             pt.setString(2,ogrenciad);
            pt.setString(3, hocaad);
            pt.setString(4,anlasmadurumu);
            pt.setString(5,iletilenmesaj);
             pt.setString(6,dersad);
          
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    
    
}
 
    @Override
    public void update(int ogrenciid, String ogrenciad,String hocaad, String anlasmadurumu, String iletilenmesaj,String dersad,int id) {
           baglanti baglanti=new baglanti();
     
         try {
          baglan=baglanti.getConnection();
          String sql=("UPDATE derstalepanlasma SET ogrenciid=?,ogrenciad=?,hocaad=?,anlasmadurumu=?,iletilenmesaj=?,dersad=? WHERE id=?");
        pt=  baglan.prepareStatement(sql);
           pt.setInt(1,ogrenciid);
            pt.setString(2, ogrenciad);
            pt.setString(3, hocaad);
            pt.setString(4,anlasmadurumu);
            pt.setString(5,iletilenmesaj);
           pt.setString(6,dersad);
            pt.setInt(7, id);
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    public void updatedersalim(String anlasmadurumu,int id){
           baglanti baglanti=new baglanti();
     
         try {
          baglan=baglanti.getConnection();
          String sql=("UPDATE derstalepanlasma SET anlasmadurumu=? WHERE id=?");
        pt=  baglan.prepareStatement(sql);
        
            pt.setString(1,anlasmadurumu);
            pt.setInt(2,id);
            
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
        
        
    }
    @Override
    public void delete(int id) {
         try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("DELETE FROM derstalepanlasma WHERE id=?");
          pt=baglan.prepareStatement(sql);
           pt.setInt(1, id);
           pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
   
    
}