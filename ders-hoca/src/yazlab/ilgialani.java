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
public class ilgialani {
    
    String ilgialani;
    
    
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
  
    public String getIlgialani() {
        return ilgialani;
    }

    public void setIlgialani(String ilgialani) {
        this.ilgialani = ilgialani;
    }
//constructor
    public ilgialani(String ilgialani) {
        this.ilgialani = ilgialani;
    }
    public ilgialani(){
        
    }
    public  ArrayList<ilgialani> getilgilist(int ogrenciid)
    {
            baglanti baglanti=new baglanti();
            ArrayList<ilgialani> ilgialaniList = new ArrayList<>();
            ilgialani obj;
        try {
            baglan=baglanti.getConnection();
           pt=baglan.prepareStatement("select ilgialani from ilgialani where ogrenciid='"+ogrenciid+"'");
        rs=pt.executeQuery();
        
        
             while(rs.next()){
           obj=new ilgialani(rs.getString("ilgialani"));
              ilgialaniList.add(obj);
           }
       } catch (SQLException ex) {
           Logger.getLogger(ders.class.getName()).log(Level.SEVERE, null, ex);
       }
            return ilgialaniList;
            
            
        }
    
    
}
