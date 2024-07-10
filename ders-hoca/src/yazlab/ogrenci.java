/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yazlab;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Aylin
 */
public class ogrenci extends user{

    private int ogrencino;
    private String ogrenciad;
 // Connection baglan; 
  //PreparedStatement pat;
 // ResultSet rs;
  int q,i;
 Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
  

   public ogrenci(){}
     public ArrayList<ogrenci> getogrenciList() {
         baglanti baglanti=new baglanti();
        
         ArrayList<ogrenci> list=new ArrayList<>();
        
         try{
            baglan=baglanti.getConnection();
           pt=baglan.prepareStatement("SELECT*FROM ogrenci");
        rs=pt.executeQuery();
          while(rs.next()){
          ogrenci ogr=new ogrenci(rs.getInt("id"),rs.getString("kullaniciad"),rs.getString("sifre"),rs.getInt("ogrencino"),rs.getString("ogrenciad"));
 
              list.add(ogr); 
              
          } 
        
         }
         catch (SQLException ex) {
            Logger.getLogger(ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
     }
     
  
    public ogrenci(int id, String kullaniciad, String sifre,int ogrencino,String ogrenciad) {
        super(id, kullaniciad, sifre);
        this.ogrencino=ogrencino;
        this.ogrenciad=ogrenciad;
    }

    public int getOgrencino() {
        return ogrencino;
    }

    public void setOgrencino(int ogrencino) {
        this.ogrencino = ogrencino;
    }

    public String getOgrenciad() {
        return ogrenciad;
    }

    public void setOgrenciad(String ogrenciad) {
        this.ogrenciad = ogrenciad;
    }
   
     public static void main(String[] args) {
   
            
        }
        
    
     }
   

