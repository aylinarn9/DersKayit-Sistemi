/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package yazlab;




import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 *
 * @author Aylin
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
  Connection baglan;
  Statement st;
  ResultSet rs; 
  PreparedStatement pt;
  
int noo;
 String sql;
 String dersler ;
 String harfnotlar;
String derspuan;
String[] bolunmus;
String yeniderspuan;
 //String kullaniciad;
    public login() {
        initComponents();
        baglantiAc();
        
    }
    
 ArrayList yeni=new ArrayList<>();
    public void transkript(int transkriptid){
  
         try{
            File file=  new File("C://Users//Aylin//Downloads//page-2.pdf");
           
            String pageText = null;
            
            PDDocument dosya=PDDocument.load(file);
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pageText = pdfStripper.getText(dosya);
       
      String str;
      //String[] stringdizi;
     // StringBuilder metin = new StringBuilder();
       Scanner sc=new Scanner(pageText);
       
       while(sc.hasNextLine()){
           str=sc.nextLine();
       
       //System.out.println(str);
       String pattern = "^[1-9][0-9]{8}$"; 
     Pattern ogrencino=Pattern.compile(pattern);
    Matcher matcherno= ogrencino.matcher(str);
          while(matcherno.find()){
        // System.out.println(matcherno.group());
        String no= matcherno.group(); 
        noo=Integer.parseInt(no);
          }
       boolean bul =false;
     String[] isim=  str.split("\n");
     String eleman="AYLİN";
     for(String sn:isim){
        if(sn.equals(eleman)){
            bul=true;
            break;
        } 
     }
    
  
     if(bul){
      //   System.out.println(eleman);      
     String yenieleman=eleman.toLowerCase();
     ogrenciManager ogrenciManager=new ogrenciManager();
     ogrenciManager.update(noo,yenieleman,transkriptid);
            
        }
     
       Pattern ders=Pattern.compile("[A-Z]{3}\\d{3}");
       Matcher matcherders = ders.matcher(str);
      while (matcherders.find()) {
          //System.out.println("" + matcher.group()); 
          int bitindex=matcherders.end();
          dersler = str.substring(bitindex);
          
     /*String[] ayrilmisders = dersler.split("\n");
            for(String s:ayrilmisders){
                yeni.add(s);
            }*/
          //System.out.println(derslist);
  
      }
      
     StringBuilder stringBuilder = new StringBuilder();
     String pattern1= ".*Z Tr.*";
     Pattern harfnot=Pattern.compile(pattern1);
    Matcher matcherharfnot= harfnot.matcher(str);
    
    while(matcherharfnot.find()){
        
     // String harfnotlari=  matcherharfnot.group();
    // System.out.println(matcherharfnot.group();
      
  /*  String []yeni7= matcherharfnot.group().split("\n");
    for(String sg:yeni7){
        bolunmus= sg.split(" ");
    }
    
    for(String bl:bolunmus){
        stringBuilder.append(bl);
    }
    
    String sonuccc=stringBuilder.toString();
     String pattern2= "[A-Z]{2}";
     Pattern puan=Pattern.compile(pattern2);
    Matcher matcherpuan= puan.matcher(sonuccc);
    while(matcherpuan.find()){
       int bass= matcherpuan.start();
      
       //derspuan=sonuccc.substring(0,bass);
       
       yeniderspuan=sonuccc.substring(7,10);
    }*/
  
       int bitisin=matcherharfnot.end();
       harfnotlar=str.substring(bitisin-5,bitisin-3);
      derspuan= str.substring(12,bitisin-5);
      // derspuan=str.substring(bitisin-9, bitisin-5);
       
       // harfnotlar=str.substring(bitisin);
        //System.out.println(harfnotlar);
                  try {
            baglanti baglanti=new baglanti();
              baglan = baglanti.getConnection();
              
              String sorgu="INSERT INTO ders(dersad,dersharfnot,ogrenciid,derspuan) VALUES(?,?,?,?)";
           pt= baglan.prepareStatement(sorgu);
        
            pt.setString(1,dersler);
             pt.setString(2,harfnotlar);
            pt.setInt(3,transkriptid);
            pt.setString(4,derspuan);
            pt.executeUpdate();
           
            
         
        } catch (SQLException ex) {
            Logger.getLogger(ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }
               finally {
            // Bağlantıyı kapatma
            try {
                if (pt != null) {
                    pt.close();
                }
                if (baglan != null) {
                   baglan.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
     
        
 }
 
  dosya.close();
 
            }
       catch(IOException e){
            e.printStackTrace();
            }
      
        
        
 
    }

    public int getNoo() {
        return noo;
    }

    public void setNoo(int noo) {
        this.noo = noo;
    }

    public String getDersler() {
        return dersler;
    }

    public void setDersler(String dersler) {
        this.dersler = dersler;
    }

    public String getHarfnotlar() {
        return harfnotlar;
    }

    public void setHarfnotlar(String harfnotlar) {
        this.harfnotlar = harfnotlar;
    }
static Object[] studentData=new Object[6];
public boolean ogrencigiriserisim(String kullaniciad,String sifre){
        boolean key=false;
        try {
            String query="SELECT*FROM ogrenci WHERE kullaniciad='"+kullaniciad+"'AND sifre='"+sifre+"'";
            st=baglan.createStatement();
            rs=st.executeQuery(query);
            while(rs.next()){
                studentData[0]=rs.getInt("id");
                studentData[1]=rs.getString("kullaniciad");
                studentData[2]=rs.getString("sifre");
                studentData[3]=rs.getString("ogrencino");
                studentData[4]=rs.getString("ogrenciad");
                studentData[5]=rs.getString("loadstatu");
               
                
                if(kullaniciad.equals(studentData[1])&&sifre.equals(studentData[2])){
                     key=true;
                }
                else{
                    key=false;
                }
                }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    if(key)
        return true;
    else
        return false;  
}

 static Object[] hocaVeri=new Object[8];
public boolean hocagiriserisim(String kullaniciad,String sifre){
        boolean key=false;
        try {
            String query="SELECT*FROM hoca WHERE kullaniciad='"+kullaniciad+"'AND sifre='"+sifre+"'";
            st=baglan.createStatement();
            rs=st.executeQuery(query);
            while(rs.next()){
                hocaVeri[0]=rs.getInt("id");
                hocaVeri[1]=rs.getString("kullaniciad");
                hocaVeri[2]=rs.getString("sifre");
                hocaVeri[3]=rs.getInt("sicilno");
                hocaVeri[4]=rs.getString("hocaad");
                hocaVeri[5]=rs.getString("dersad");
                hocaVeri[6]=rs.getInt("kota");
                hocaVeri[7]=rs.getString("ilgialani");
               
                
                if(kullaniciad.equals(hocaVeri[1])&&sifre.equals(hocaVeri[2])){
                     key=true;
                }
                else{
                    key=false;
                }
                }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
    if(key)
        return true;
    else
        return false;  
}
public void baglantiAc(){
     
        try{
       Class.forName("org.postgresql.Driver");
     String bag="jdbc:postgresql://localhost:5432/YAZLAB";
     String username="postgres";
     String sifre="6842";
     baglan= DriverManager.getConnection(bag,username,sifre);
    //JOptionPane.showMessageDialog(null, "veri tabani baglantisi basarili");
   
       }
       
        catch (SQLException ex) {
           
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jYoneticiPanel = new javax.swing.JPanel();
        YoneticiKullaniciAdLabel = new javax.swing.JLabel();
        YoneticiKullaniciAdTextField = new javax.swing.JTextField();
        YoneticiSifreLabel = new javax.swing.JLabel();
        YoneticiPasswordField = new javax.swing.JPasswordField();
        YoneticiGirisButton = new javax.swing.JButton();
        jHocaPanel = new javax.swing.JPanel();
        jYoneticiPanel1 = new javax.swing.JPanel();
        HocaKullaniciAdLabel = new javax.swing.JLabel();
        HocaKullaniciAdTextField = new javax.swing.JTextField();
        HocaSifreLabel = new javax.swing.JLabel();
        HocaPasswordField = new javax.swing.JPasswordField();
        HocaGirisButton = new javax.swing.JButton();
        jOgrenciPanel = new javax.swing.JPanel();
        jYoneticiPanel2 = new javax.swing.JPanel();
        OgrenciKullaniciAdLabel = new javax.swing.JLabel();
        OgrenciKullaniciAdTextField = new javax.swing.JTextField();
        OgrenciSifreLabel = new javax.swing.JLabel();
        OgrenciPasswordField = new javax.swing.JPasswordField();
        TKOgreniGirisButton = new javax.swing.JButton();
        OgreniGirisButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createMatteBorder(5, 5, 5, 5, new java.awt.Color(100, 153, 0)));
        jTabbedPane1.setForeground(new java.awt.Color(51, 102, 0));

        jYoneticiPanel.setBackground(new java.awt.Color(204, 204, 255));

        YoneticiKullaniciAdLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        YoneticiKullaniciAdLabel.setText("Kullanici Adı:");

        YoneticiSifreLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        YoneticiSifreLabel.setText("Sifre:");

        YoneticiPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YoneticiPasswordFieldActionPerformed(evt);
            }
        });

        YoneticiGirisButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        YoneticiGirisButton.setForeground(new java.awt.Color(51, 102, 0));
        YoneticiGirisButton.setText("GİRİŞ");
        YoneticiGirisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YoneticiGirisButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jYoneticiPanelLayout = new javax.swing.GroupLayout(jYoneticiPanel);
        jYoneticiPanel.setLayout(jYoneticiPanelLayout);
        jYoneticiPanelLayout.setHorizontalGroup(
            jYoneticiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jYoneticiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(YoneticiGirisButton)
                    .addComponent(YoneticiKullaniciAdLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                    .addComponent(YoneticiSifreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(YoneticiKullaniciAdTextField)
                    .addComponent(YoneticiPasswordField))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        jYoneticiPanelLayout.setVerticalGroup(
            jYoneticiPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanelLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(YoneticiKullaniciAdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(YoneticiKullaniciAdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(YoneticiSifreLabel)
                .addGap(18, 18, 18)
                .addComponent(YoneticiPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(YoneticiGirisButton)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Yönetici Panel", jYoneticiPanel);

        jHocaPanel.setBackground(new java.awt.Color(255, 204, 204));

        jYoneticiPanel1.setBackground(new java.awt.Color(255, 204, 204));

        HocaKullaniciAdLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        HocaKullaniciAdLabel.setText("Kullanici Adı:");

        HocaSifreLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        HocaSifreLabel.setText("Sifre:");

        HocaPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HocaPasswordFieldActionPerformed(evt);
            }
        });

        HocaGirisButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        HocaGirisButton.setForeground(new java.awt.Color(51, 102, 0));
        HocaGirisButton.setText("GİRİŞ");
        HocaGirisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HocaGirisButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jYoneticiPanel1Layout = new javax.swing.GroupLayout(jYoneticiPanel1);
        jYoneticiPanel1.setLayout(jYoneticiPanel1Layout);
        jYoneticiPanel1Layout.setHorizontalGroup(
            jYoneticiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jYoneticiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jYoneticiPanel1Layout.createSequentialGroup()
                        .addGroup(jYoneticiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HocaGirisButton)
                            .addComponent(HocaKullaniciAdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(HocaKullaniciAdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(274, Short.MAX_VALUE))
                    .addGroup(jYoneticiPanel1Layout.createSequentialGroup()
                        .addGroup(jYoneticiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(HocaPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(HocaSifreLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jYoneticiPanel1Layout.setVerticalGroup(
            jYoneticiPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(HocaKullaniciAdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(HocaKullaniciAdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(HocaSifreLabel)
                .addGap(18, 18, 18)
                .addComponent(HocaPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(HocaGirisButton)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jHocaPanelLayout = new javax.swing.GroupLayout(jHocaPanel);
        jHocaPanel.setLayout(jHocaPanelLayout);
        jHocaPanelLayout.setHorizontalGroup(
            jHocaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHocaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYoneticiPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jHocaPanelLayout.setVerticalGroup(
            jHocaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jHocaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYoneticiPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Hoca Panel", jHocaPanel);

        jOgrenciPanel.setBackground(new java.awt.Color(153, 204, 255));

        jYoneticiPanel2.setBackground(new java.awt.Color(153, 204, 255));

        OgrenciKullaniciAdLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        OgrenciKullaniciAdLabel.setText("Kullanici Adı:");

        OgrenciSifreLabel.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        OgrenciSifreLabel.setText("Sifre:");

        OgrenciPasswordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OgrenciPasswordFieldActionPerformed(evt);
            }
        });

        TKOgreniGirisButton.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        TKOgreniGirisButton.setForeground(new java.awt.Color(51, 102, 0));
        TKOgreniGirisButton.setText("TKGİRİŞ");
        TKOgreniGirisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKOgreniGirisButtonActionPerformed(evt);
            }
        });

        OgreniGirisButton1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        OgreniGirisButton1.setForeground(new java.awt.Color(51, 102, 0));
        OgreniGirisButton1.setText("GİRİŞ");
        OgreniGirisButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OgreniGirisButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jYoneticiPanel2Layout = new javax.swing.GroupLayout(jYoneticiPanel2);
        jYoneticiPanel2.setLayout(jYoneticiPanel2Layout);
        jYoneticiPanel2Layout.setHorizontalGroup(
            jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                .addGroup(jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(OgrenciKullaniciAdTextField))
                        .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(OgrenciKullaniciAdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)))
                    .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(OgrenciSifreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                .addComponent(OgrenciPasswordField))
                            .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                                .addComponent(OgreniGirisButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TKOgreniGirisButton)))))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jYoneticiPanel2Layout.setVerticalGroup(
            jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jYoneticiPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(OgrenciKullaniciAdLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OgrenciKullaniciAdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(OgrenciSifreLabel)
                .addGap(18, 18, 18)
                .addComponent(OgrenciPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jYoneticiPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OgreniGirisButton1)
                    .addComponent(TKOgreniGirisButton))
                .addGap(60, 60, 60))
        );

        javax.swing.GroupLayout jOgrenciPanelLayout = new javax.swing.GroupLayout(jOgrenciPanel);
        jOgrenciPanel.setLayout(jOgrenciPanelLayout);
        jOgrenciPanelLayout.setHorizontalGroup(
            jOgrenciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jOgrenciPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYoneticiPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jOgrenciPanelLayout.setVerticalGroup(
            jOgrenciPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jOgrenciPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jYoneticiPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ogrenci Panel", jOgrenciPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void YoneticiPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YoneticiPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_YoneticiPasswordFieldActionPerformed

    private void HocaPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HocaPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HocaPasswordFieldActionPerformed

    private void OgrenciPasswordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OgrenciPasswordFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OgrenciPasswordFieldActionPerformed

    private void YoneticiGirisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YoneticiGirisButtonActionPerformed
        // TODO add your handling code here:
 String kullaniciad= YoneticiKullaniciAdTextField.getText();
 String sifre=  String.valueOf(YoneticiPasswordField.getPassword());
       try{
        
   String sorgu="SELECT*FROM yonetici WHERE kullaniciad='"+kullaniciad+"' AND sifre='"+sifre+"'";
       // st=baglan.createStatement();
        pt=baglan.prepareStatement(sorgu);
       rs= pt.executeQuery();
       if(rs.next()){
           //JOptionPane.showMessageDialog(null, "yonetici sisteme giris yapti");
           new yoneticiarayuz().setVisible(true);
            //dispose();
       }
       else{
            JOptionPane.showMessageDialog(this, "kullanici adi yada sifreyi yanlis");
       }
     //baglan.close();
     
     }
    catch (SQLException ex) {
          Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
      }
YoneticiKullaniciAdTextField.setText(null);
YoneticiPasswordField.setText(null);
     
    }//GEN-LAST:event_YoneticiGirisButtonActionPerformed

    private void HocaGirisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HocaGirisButtonActionPerformed
        // TODO add your handling code here:
        /*String kullaniciad= HocaKullaniciAdTextField.getText();
 String sifre=  String.valueOf(HocaPasswordField.getPassword());
       try{
        
   String sorgu="SELECT*FROM hoca WHERE kullaniciad='"+kullaniciad+"' AND sifre='"+sifre+"'";
       // st=baglan.createStatement();
        pt=baglan.prepareStatement(sorgu);
       rs= pt.executeQuery();
       if(rs.next()){
          // JOptionPane.showMessageDialog(null, "hoca sisteme giris yapti");
           new hocaArayuz().setVisible(true);
            //dispose();
       }
       else{
            JOptionPane.showMessageDialog(this, "kullanici adi yada sifreyi yanlis");
       }
    
     
     }
    catch (SQLException ex) {
          Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
      }*/
         boolean control=hocagiriserisim(HocaKullaniciAdTextField.getText(),String.valueOf(HocaPasswordField.getPassword()));
  if(control==true){
                 
                    try {
                    st=baglan.createStatement(); 
                    rs=st.executeQuery("SELECT*FROM hoca");
               while(rs.next()){
              
                  JOptionPane.showMessageDialog(null,"Hoşgeldiniz SAYIN "+ hocaVeri[1] );
                    break;
               }
               
                } 
                    catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
                HocaKullaniciAdTextField.setText(null);
               HocaPasswordField.setText(null);
                hocaArayuz ogrenciarayuz = new hocaArayuz();
             
                new hocaArayuz().setVisible(true);
                dispose();
             }
            
            else{
                JOptionPane.showMessageDialog(null,"yanlış şifre yada kullanıcı adı");
            }
 
        
    }//GEN-LAST:event_HocaGirisButtonActionPerformed

    private void TKOgreniGirisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKOgreniGirisButtonActionPerformed
        // TODO add your handling code here:
 /* kullaniciad= OgrenciKullaniciAdTextField.getText();
 String sifre=  String.valueOf(OgrenciPasswordField.getPassword());
  
       try{
        
   String sorgu="SELECT*FROM ogrenci WHERE kullaniciad='"+kullaniciad+"' AND sifre='"+sifre+"'";
       pt=baglan.prepareStatement(sorgu);
       rs= pt.executeQuery();
       
         pt=baglan.prepareStatement("SELECT id FROM ogrenci WHERE kullaniciad='"+kullaniciad+"'");
             rs=pt.executeQuery();
           
                 if(rs.next()){
                     while(rs.next()){
                         transkript(Integer.parseInt(rs.getString("id")));
                        new ogrenciarayuz(Integer.parseInt(rs.getString("id")));
                     }
                     
                     
                 }
                    else{
            JOptionPane.showMessageDialog(this, "kullanici adi yada sifreyi yanlis");
              }
              
                   //JOptionPane.showMessageDialog(null, "ogrenci sisteme giris yapti");
                    
                    new ogrenciarayuz().setVisible(true);
                    
                   //dispose();
                
    
        
         
     
     }
    catch (SQLException ex) {
          Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
      }*/
 

 boolean control=ogrencigiriserisim(OgrenciKullaniciAdTextField.getText(),  String.valueOf(OgrenciPasswordField.getPassword()));

  if(control==true){
      
                   try {
                       st=baglan.createStatement();
                       rs=st.executeQuery("SELECT*FROM ogrenci");
                       while(rs.next()){ 
                boolean statu=false;
               try {
                   
                   String sorgu="SELECT*FROM ogrenci WHERE loadstatu='"+statu+"'";
                   pt=baglan.prepareStatement(sorgu);
                   rs= pt.executeQuery();
                   if(rs.next()){
                        transkript((int) studentData[0]);
                        ogrenciManager mg=new ogrenciManager();
                        mg.update((int) studentData[0]);
                        
                   
                   }
                  
               } 
                 catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                   JOptionPane.showMessageDialog(null,"Hoşgeldiniz SAYIN "+ studentData[4]);
                          break;
               
                          
                       }
                       
                   }
                   catch (SQLException ex) {
                       Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   OgrenciKullaniciAdTextField.setText(null);
                   OgrenciPasswordField.setText(null);
                 //  ogrenciarayuz ogrenciarayuz = new ogrenciarayuz();
                   new ogrenciarayuz().setVisible(true);
                   dispose();
             }
            
            else{
                JOptionPane.showMessageDialog(null,"wrongPassword");
            }
   
        
    }//GEN-LAST:event_TKOgreniGirisButtonActionPerformed

    private void OgreniGirisButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OgreniGirisButton1ActionPerformed
        // TODO add your handling code here:
       boolean control=ogrencigiriserisim(OgrenciKullaniciAdTextField.getText(),  String.valueOf(OgrenciPasswordField.getPassword()));
  if(control==true){
         
                    try {
                    st=baglan.createStatement(); 
                    rs=st.executeQuery("SELECT*FROM ogrenci");
                   
               while(rs.next()){
                    
                  JOptionPane.showMessageDialog(null,"Hoşgeldiniz SAYIN "+ studentData[1] );
                    break;
               }
              
                } catch (SQLException ex) {
                    Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                }
                OgrenciKullaniciAdTextField.setText(null);
                OgrenciPasswordField.setText(null);
                ogrenciarayuz ogrenciarayuz = new ogrenciarayuz();
               // new ogrenciarayuz(Integer.parseInt(rs.getString("id")));
                new ogrenciarayuz().setVisible(true);
                dispose();
             }
            
            else{
                JOptionPane.showMessageDialog(null,"wrongPassword");
            }
      
    }//GEN-LAST:event_OgreniGirisButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public  static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
              
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HocaGirisButton;
    private javax.swing.JLabel HocaKullaniciAdLabel;
    private javax.swing.JTextField HocaKullaniciAdTextField;
    private javax.swing.JPasswordField HocaPasswordField;
    private javax.swing.JLabel HocaSifreLabel;
    private javax.swing.JLabel OgrenciKullaniciAdLabel;
    private javax.swing.JTextField OgrenciKullaniciAdTextField;
    private javax.swing.JPasswordField OgrenciPasswordField;
    private javax.swing.JLabel OgrenciSifreLabel;
    private javax.swing.JButton OgreniGirisButton1;
    private javax.swing.JButton TKOgreniGirisButton;
    private javax.swing.JButton YoneticiGirisButton;
    private javax.swing.JLabel YoneticiKullaniciAdLabel;
    private javax.swing.JTextField YoneticiKullaniciAdTextField;
    private javax.swing.JPasswordField YoneticiPasswordField;
    private javax.swing.JLabel YoneticiSifreLabel;
    private javax.swing.JPanel jHocaPanel;
    private javax.swing.JPanel jOgrenciPanel;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jYoneticiPanel;
    private javax.swing.JPanel jYoneticiPanel1;
    private javax.swing.JPanel jYoneticiPanel2;
    // End of variables declaration//GEN-END:variables

 
}
