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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aylin
 */
public class hoca extends user {
      int q,i;
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
     private int sicilno;
     private String hocaad;
     private String dersad;
     private int kota;
     private String ilgialani;

    public String getIlgialani() {
        return ilgialani;
    }

    public void setIlgialani(String ilgialani) {
        this.ilgialani = ilgialani;
    }

    String ogrenciad;
    public hoca(String ogrenciad){
        this.ogrenciad=ogrenciad;
    }
  public  ArrayList<hoca> getogrencilist(String hocaad)
    {
            baglanti baglanti=new baglanti();
            ArrayList<hoca> ogrenciList = new ArrayList<>();
            hoca obj;
        try {
            baglan=baglanti.getConnection();
           pt=baglan.prepareStatement("select ogrenciad from derstalepanlasma where hocaad='"+hocaad+"'");
        rs=pt.executeQuery();
        
        
             while(rs.next()){
           obj=new hoca(rs.getString("ogrenciad"));
               ogrenciList.add(obj);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
       }
          try {
              baglan.close();
          } catch (SQLException ex) {
              Logger.getLogger(hoca.class.getName()).log(Level.SEVERE, null, ex);
          }
            return ogrenciList;
            
            
        }
    public String getOgrenciad() {
        return ogrenciad;
    }

    public void setOgrenciad(String ogrenciad) {
        this.ogrenciad = ogrenciad;
    }
     
    public int getSicilno() {
        return sicilno;
    }

    public void setSicilno(int sicilno) {
        this.sicilno = sicilno;
    }

    public String getHocaad() {
        return hocaad;
    }

    public void setHocaad(String hocaad) {
        this.hocaad = hocaad;
    }

    public String getDersad() {
        return dersad;
    }

    public void setDersad(String dersad) {
        this.dersad = dersad;
    }

    public int getKota() {
        return kota;
    }

    public void setKota(int kota) {
        this.kota = kota;
    }

 public hoca(int id, String kullaniciad, String sifre, int sicilno, String hocaad, String dersad,int kota,String ilgialani){
      super(id, kullaniciad, sifre);
      this.sicilno=sicilno;
      this.hocaad=hocaad;
      this.dersad=dersad;
      this.kota=kota;
      this.ilgialani=ilgialani;
 }
 public hoca(){
     
 }
     public ArrayList<hoca> gethocaList() {
         baglanti baglanti=new baglanti();
        
         ArrayList<hoca> list=new ArrayList<>();
        
         try{
              baglan=baglanti.getConnection();
           pt=baglan.prepareStatement("SELECT*FROM hoca");
        rs=pt.executeQuery();
          while(rs.next()){
    hoca hoca=new hoca(rs.getInt("id"),rs.getString("kullaniciad"),rs.getString("sifre"),rs.getInt("sicilno"),rs.getString("hocaad"),rs.getString("dersad"),rs.getInt("kota"),rs.getString("ilgialani"));
 
              list.add(hoca); 
              
          } 
        
         }
         catch (SQLException ex) {
            Logger.getLogger(ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
     }
     
}
