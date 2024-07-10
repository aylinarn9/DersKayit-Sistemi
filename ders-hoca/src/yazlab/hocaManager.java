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
public class hocaManager implements IHoca{
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
    @Override
    public void insert(String kullaniciad, String sifre, int sicilno, String hocaad,String dersad,int kota,String ilgialani) {
              baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO hoca(kullaniciad,sifre,sicilno,hocaad,dersad,kota,ilgialani) VALUES(?,?,?,?,?,?,?)";
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, kullaniciad);
            pt.setString(2, sifre);
            pt.setInt(3,sicilno);
            pt.setString(4,hocaad);
             pt.setString(5,dersad);
             pt.setInt(6,kota);
              pt.setString(7,ilgialani);
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }

    @Override
    public void update(String kullaniciad, String sifre, int sicilno, String hocaad,String dersad, int kota,String ilgialani,int id) {
          try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("UPDATE hoca SET kullaniciad=?,sifre=?,sicilno=?,hocaad=?,dersad=?,kota=?,ilgialani=? WHERE id=?");
          pt= baglan.prepareStatement(sql);
          pt.setString(1,kullaniciad);
        pt.setString(2, sifre);
        pt.setInt(3,sicilno);
        pt.setString(4,hocaad);
         pt.setString(5,dersad);
         pt.setInt(6,kota);
          pt.setString(7,ilgialani);
        pt.setInt(8, id);
         pt.executeUpdate();
        
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  @Override
    public void update(int kota, String hocaad) {
        try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("UPDATE hoca SET kota=? WHERE hocaad=?");
          pt= baglan.prepareStatement(sql);
         
        pt.setInt(1,kota);
        pt.setString(2, hocaad);
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
          String sql=("DELETE FROM hoca WHERE id=?");
          pt=baglan.prepareStatement(sql);
           pt.setInt(1, id);
           pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
          
    }

  
}
