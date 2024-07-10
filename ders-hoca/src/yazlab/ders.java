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
public class ders {
 
 
   String dersad;
 


    public String getDersad() {
        return dersad;
    }

    public void setDersad(String dersad) {
        this.dersad = dersad;
    }

    public ders( String dersad) {
        this.dersad = dersad;
  
    }
     int q,i;
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
  public ders()
  {
      
  }
    public  ArrayList<ders> getderslist(int ogrenciid)
    {
            baglanti baglanti=new baglanti();
            ArrayList<ders> lesList = new ArrayList<>();
            ders obj;
        try {
            baglan=baglanti.getConnection();
           pt=baglan.prepareStatement("select dersad from ders where ogrenciid='"+ogrenciid+"'");
        rs=pt.executeQuery();
        
        
             while(rs.next()){
           obj=new ders(rs.getString("dersad"));
               lesList.add(obj);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
       }
       try {
           baglan.close();
       } catch (SQLException ex) {
           Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
       }
;
            return lesList;
            
            
        }
      
            
    
    }

    
    

 

