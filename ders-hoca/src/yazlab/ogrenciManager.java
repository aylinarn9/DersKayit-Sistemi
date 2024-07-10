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
import javax.swing.JOptionPane;

/**
 *
 * @author Aylin
 */
public class ogrenciManager implements IOgrenci{
  Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
  ogrenci ogrenci=new ogrenci();
  login lg=new login();
 

    @Override
    public void insert(String kullaniciad, String sifre, int ogrencino, String ogrenciad) {
       baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO ogrenci(kullaniciad,sifre,ogrencino,ogrenciad) VALUES(?,?,?,?)";
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, kullaniciad);
            pt.setString(2, sifre);
            pt.setInt(3,ogrencino);
            pt.setString(4,ogrenciad);
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
        
        
    }

    @Override
    public void update(String kullaniciad, String sifre, int ogrencino, String ogrenciad,int id) {
      try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("UPDATE ogrenci SET kullaniciad=?,sifre=?,ogrencino=?,ogrenciad=? WHERE id=?");
          pt= baglan.prepareStatement(sql);
          pt.setString(1,kullaniciad);
        pt.setString(2, sifre);
        pt.setInt(3,ogrencino);
        pt.setString(4,ogrenciad);
        pt.setInt(5, id);
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
          String sql=("DELETE FROM ogrenci WHERE id=?");
          pt=baglan.prepareStatement(sql);
           pt.setInt(1, id);
           pt.execute();
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
          
    }
/*
    @Override
    public void arama(int id) {
      try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("SELECT*FROM ogrenci WHERE id=?");
          pt=baglan.prepareStatement(sql);
             pt.setInt(1, id);
              rs=pt.executeQuery();
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
    }*/

    @Override
    public void insert(String kullaniciad, String sifre,Boolean statu) {
          baglanti baglanti=new baglanti();
      try {
          baglan=baglanti.getConnection();
          String sql="INSERT INTO ogrenci(kullaniciad,sifre,loadstatu) VALUES(?,?,?)";
        pt=  baglan.prepareStatement(sql);
           pt.setString(1, kullaniciad);
            pt.setString(2, sifre);
             pt.setBoolean(3, statu);
   
          
         pt.executeUpdate();
             
            
           
          
  
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }

    @Override
    public void update(int ogrencino,String ogrenciad, int id) {
      
           try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("UPDATE ogrenci SET ogrencino=?,ogrenciad=? WHERE id=?");
          pt= baglan.prepareStatement(sql);
        pt.setInt(1,ogrencino);
        pt.setString(2,ogrenciad);
        pt.setInt(3,id);
         pt.executeUpdate();
        
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }

    @Override
    public void update( int id) {
                 try {
          baglanti baglanti=new baglanti();
          baglan=baglanti.getConnection();
          String sql=("UPDATE ogrenci SET loadstatu=? WHERE id=?");
          pt= baglan.prepareStatement(sql);
        pt.setBoolean(1, true);
        pt.setInt(2,id);
         pt.executeUpdate();
        
      } catch (SQLException ex) {
          Logger.getLogger(ogrenciManager.class.getName()).log(Level.SEVERE, null, ex);
      }
        
    }

   

}



