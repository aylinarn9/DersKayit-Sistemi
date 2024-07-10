/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package yazlab;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import static yazlab.login.hocaVeri;
import static yazlab.login.studentData;

/**
 *
 * @author Aylin
 */
public class hocaArayuz extends javax.swing.JFrame {
  Connection baglan; 
  PreparedStatement pat;
  ResultSet rs;
  int q,i;
 DefaultListModel listmodel1=new DefaultListModel();
   DefaultListModel listmodel2=new DefaultListModel();
    DefaultListModel listmodel3=new DefaultListModel();
  ders ds=new ders();
 hoca hc=new hoca();
 ArrayList<String>puanlanacakdersler;
 ArrayList<String>puanlar;
java.util.List<String> verii;
   double toplam ;
    /**
     * Creates new form HocaArayuz
     */
    public hocaArayuz() {
        initComponents();
        baglantiAc();
        talepanlasmatablo();
        kotatablo();
        jlist1();
        comboxders();
   
   jComboBoxders.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    puanlanacakdersler=new ArrayList<>();
   String puanlanacakders=(String) jComboBoxders.getSelectedItem();
   puanlanacakdersler.add(puanlanacakders); 
        
  for(String pdd:puanlanacakdersler){
      System.out.println(pdd);
      
  }
 puanlar=new ArrayList<>();
  for(int j=0;j<puanlanacakdersler.size();j++){
        try {
            String puanders= puanlanacakdersler.get(j);
            pat=baglan.prepareStatement("select derspuan from ders where dersad='"+puanders+"'");
            rs=pat.executeQuery();
            while(rs.next()){
                puanlar.add(rs.getString("derspuan"));
     
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(hocaArayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
    for(String p:puanlar){
       listmodel3.addElement(p);
    
    // System.out.println(p); 
      }  
  
jList3.setModel(listmodel3);

JList<String> jList3 = new JList<>(listmodel3);
jList3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        
  }
   }
   
   
   );
   
  
}
    
  public void ogrencininderslerinerisim(){
      String ogrenciaddd=jList1.getSelectedValue();
      try {
          pat=baglan.prepareStatement("select id from ogrenci where ogrenciad='"+ogrenciaddd+"'");
          rs=pat.executeQuery();
          
          while(rs.next()){
         // pat=baglan.prepareStatement("select dersad from ders where ogrenciid='"+rs.getInt("id")+"'");
         // rs=pat.executeQuery();
         studentData[0]= rs.getInt("id");
         listmodel2.removeAllElements();
       for(int i=0;i<ds.getderslist((int) studentData[0]).size();i++){
           listmodel2.addElement(ds.getderslist((int) studentData[0]).get(i).getDersad());
       }
       jList2.setModel(listmodel2);
        
          }
      } catch (SQLException ex) {
          Logger.getLogger(hocaArayuz.class.getName()).log(Level.SEVERE, null, ex);
      }
    
      
  }
  public void comboxders(){
      try {
          pat=baglan.prepareStatement("select dersad from ders");
          rs=pat.executeQuery();
          while(rs.next()){
              jComboBoxders.addItem(rs.getString("dersad"));
             
          }
           jComboBoxders.setSelectedIndex(-1);
      } 
       
      catch (SQLException ex) {
          Logger.getLogger(hocaArayuz.class.getName()).log(Level.SEVERE, null, ex);
      }
          
  }

 
  public void puanlamaformulolustur(){
 
    
   
  }
  public double formulhesap3(){   
 double sayi3= Double.parseDouble(jList3.getSelectedValue());

  double c=(sayi3)*(1.0);
   System.out.println(""+c);

   return c; 
 } 
  
public double formulhesap2(){    
  double sayi2= Double.parseDouble(jList3.getSelectedValue());
 

 double b=(sayi2)*(2.0);
   System.out.println(""+b);
    
   return b;
 } 
 
public double formulhesap1(){
   
    
  double sayi1= Double.parseDouble(jList3.getSelectedValue());
 

 double a=(sayi1)*(3.0);
    System.out.println(""+a);
    return a;
 }  
 public double hesp(){
     


   double m=formulhesap1();
   double l= formulhesap2();
   double k= formulhesap3();
 toplam = (m)+(l)+(k);
     System.out.println(""+toplam);
    return toplam;

/* verii = jList3.getSelectedValuesList();
 double puanlamatoplam=0;
for(int i=0;i<verii.size();i++){
    
     puanlamatoplam+=(Double.parseDouble(verii.get(i)))*(3.0-i);
 }

 return puanlamatoplam;*/
 }
 

  public void jlist1(){
      listmodel1.removeAllElements();
       for(int i=0;i<hc.getogrencilist((String) hocaVeri[4]).size();i++){
           listmodel1.addElement(hc.getogrencilist((String) hocaVeri[4]).get(i).getOgrenciad());
       }
       jList1.setModel(listmodel1);
  }
    
  public void talepanlasmatablo(){
        try {
      String durum="bekleme";
      String hocaad=(String) hocaVeri[4];
      // String derssad="select dersad from hoca where hocaad='"+hocaad+"'";
       pat=baglan.prepareStatement("select dersad from hoca where hocaad='"+hocaad+"'");
             rs=pat.executeQuery();
             while(rs.next()){
               
               pat=baglan.prepareStatement("SELECT*FROM derstalepanlasma WHERE anlasmadurumu='"+durum+"' AND dersad='"+rs.getString("dersad")+"'");
               rs=pat.executeQuery();
               DefaultTableModel model=(DefaultTableModel) jTableTalep.getModel();
               ResultSetMetaData stveri=rs.getMetaData();
               
               q=stveri.getColumnCount();
               DefaultTableModel RecordTable=(DefaultTableModel)jTableTalep.getModel();
               RecordTable.setRowCount(0);
               while(rs.next()){
                   Vector sutunVeri=new Vector();
                  // Object[] studentData=new Object[q];
                   for(i =1;i<=q;i++){
                       sutunVeri.add(rs.getString("id"));
                       sutunVeri.add(rs.getString("ogrenciid"));
                       sutunVeri.add(rs.getString("ogrenciad"));
                       sutunVeri.add(rs.getString("hocaad"));
                       sutunVeri.add(rs.getString("anlasmadurumu"));
                       sutunVeri.add(rs.getString("iletilenmesaj"));
                       sutunVeri.add(rs.getString("dersad"));
                       
                   }
                   RecordTable.addRow(sutunVeri);
                   
               }
               
               jTableTalep.setModel(model);
              
             }
          
             
           } catch (SQLException ex) {
               Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
           }
     
      
      
      
  }
  public void kotatablo(){
      
      try {
          pat=baglan.prepareStatement("SELECT hocaad,dersad,kota FROM hoca WHERE hocaad='"+hocaVeri[4]+"'");
          rs=pat.executeQuery();
          DefaultTableModel model=(DefaultTableModel) jTablekota.getModel();
          ResultSetMetaData stveri=rs.getMetaData();
          
          q=stveri.getColumnCount();
          DefaultTableModel RecordTable=(DefaultTableModel)jTablekota.getModel();
          RecordTable.setRowCount(0);
          while(rs.next()){
              Vector sutunVeri=new Vector();
              // Object[] studentData=new Object[q];
              for(i =1;i<=q;i++){
                  sutunVeri.add(rs.getString("hocaad"));
                  sutunVeri.add(rs.getString("dersad"));
                  sutunVeri.add(rs.getString("kota"));
              }
              RecordTable.addRow(sutunVeri);
              
          }
          
          jTablekota.setModel(model);
      } catch (SQLException ex) {
          Logger.getLogger(hocaArayuz.class.getName()).log(Level.SEVERE, null, ex);
      }
      
  }
   public void baglantiAc(){
       try{
    Class.forName("org.postgresql.Driver");
     String bag="jdbc:postgresql://localhost:5432/YAZLAB";
     String username="postgres";
     String sifre="6842";
     baglan= DriverManager.getConnection(bag,username,sifre);
  
   // JOptionPane.showMessageDialog(null, "veri tabani baglantisi basarili");
   
       }
       catch (ClassNotFoundException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        ogrenciderslisteleButton2 = new javax.swing.JButton();
        jComboBoxders = new javax.swing.JComboBox<>();
        formul1Button = new javax.swing.JButton();
        formul2Button = new javax.swing.JButton();
        formul3Button = new javax.swing.JButton();
        formulButton8 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jList3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jButtoncıkıs1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxdurum = new javax.swing.JComboBox<>();
        OGRENCİONAYLAMAButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTalep = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablekota = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 255, 153));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jScrollPane4.setViewportView(jList2);

        ogrenciderslisteleButton2.setText("ogrenci ders listele");
        ogrenciderslisteleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ogrenciderslisteleButton2ActionPerformed(evt);
            }
        });

        jComboBoxders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxdersActionPerformed(evt);
            }
        });

        formul1Button.setText("formül1");
        formul1Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formul1ButtonMouseClicked(evt);
            }
        });
        formul1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formul1ButtonActionPerformed(evt);
            }
        });

        formul2Button.setText("formül2");
        formul2Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formul2ButtonMouseClicked(evt);
            }
        });
        formul2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formul2ButtonActionPerformed(evt);
            }
        });

        formul3Button.setText("formül3");
        formul3Button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formul3ButtonMouseClicked(evt);
            }
        });
        formul3Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formul3ButtonActionPerformed(evt);
            }
        });

        formulButton8.setText("formül");
        formulButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formulButton8MouseClicked(evt);
            }
        });
        formulButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formulButton8ActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(jList3);

        jButtoncıkıs1.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        jButtoncıkıs1.setForeground(new java.awt.Color(204, 51, 0));
        jButtoncıkıs1.setText("çıkıs");
        jButtoncıkıs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncıkıs1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Seçilen Öğrencinin Dersleri:");

        jLabel3.setText("tum dersler:");

        jLabel4.setText("Ders Puanları:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButtoncıkıs1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(formul2Button)
                                    .addComponent(formul1Button)
                                    .addComponent(formul3Button))
                                .addGap(64, 64, 64))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(ogrenciderslisteleButton2)
                                .addGap(142, 142, 142))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxders, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(325, 325, 325)
                        .addComponent(formulButton8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtoncıkıs1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ogrenciderslisteleButton2)
                                .addGap(26, 26, 26)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addComponent(formul1Button)
                                .addGap(18, 18, 18)
                                .addComponent(formul2Button)
                                .addGap(27, 27, 27)
                                .addComponent(formul3Button))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addComponent(formulButton8)
                        .addGap(45, 45, 45))))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 204));

        jScrollPane1.setViewportView(jList1);

        jLabel1.setText("Hocanın derslerine talep oluşturan öğrenciler:");

        jComboBoxdurum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bekleme", "kabuledildi", "red", "talep yok" }));

        OGRENCİONAYLAMAButton1.setText("OGRENCİ ONAYLAMA");
        OGRENCİONAYLAMAButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OGRENCİONAYLAMAButton1ActionPerformed(evt);
            }
        });

        jTableTalep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "ogrenciid", "ogrenciad", "hocaad", "anlasma durumu", "iletilen mesaj"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableTalep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableTalepMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableTalep);

        jTablekota.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "hocaad", "dersad", "kota"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTablekota);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxdurum, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(OGRENCİONAYLAMAButton1)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(162, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jComboBoxdurum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(OGRENCİONAYLAMAButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OGRENCİONAYLAMAButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OGRENCİONAYLAMAButton1ActionPerformed
        // TODO add your handling code here:
        String ogrenciad=jList1.getSelectedValue();
       
         String durum= (String) jComboBoxdurum.getSelectedItem();
         
         talepManager tpm=new talepManager();
         hocaManager hMng=new hocaManager();
         
         String hocaad=(String) hocaVeri[4];
         int kota=(int) hocaVeri[6];
         int yenikota=(int) hocaVeri[6];
           try {
               
            pat=baglan.prepareStatement("SELECT id FROM derstalepanlasma WHERE ogrenciad='"+ogrenciad+"' AND hocaad='"+hocaVeri[4]+"'");
             rs=pat.executeQuery();
             while(rs.next()){
                 tpm.updatedersalim(durum,Integer.parseInt(rs.getString("id")));
                 yenikota=yenikota-1;
                 hMng.update(yenikota, hocaad);
                 if(yenikota==0){
                    JOptionPane.showMessageDialog(null, "bu hocanın kotası dolmuştur");
                 }
              kotatablo();
              
             
              
             }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciarayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }//GEN-LAST:event_OGRENCİONAYLAMAButton1ActionPerformed

    private void jTableTalepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTalepMouseClicked
        // TODO add your handling code here:
       

    }//GEN-LAST:event_jTableTalepMouseClicked

    private void ogrenciderslisteleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ogrenciderslisteleButton2ActionPerformed
        // TODO add your handling code here:
        ogrencininderslerinerisim();
    }//GEN-LAST:event_ogrenciderslisteleButton2ActionPerformed

    private void formul1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formul1ButtonActionPerformed
        // TODO add your handling code here:
        formulhesap1();

    }//GEN-LAST:event_formul1ButtonActionPerformed

    private void formul1ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formul1ButtonMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formul1ButtonMouseClicked

    private void jComboBoxdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxdersActionPerformed
        // TODO add your handling code here:
      
    
     
    }//GEN-LAST:event_jComboBoxdersActionPerformed

    private void formul2ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formul2ButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formul2ButtonMouseClicked

    private void formul2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formul2ButtonActionPerformed
        // TODO add your handling code here:
         formulhesap2();
    }//GEN-LAST:event_formul2ButtonActionPerformed

    private void formul3ButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formul3ButtonMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formul3ButtonMouseClicked

    private void formul3ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formul3ButtonActionPerformed
        // TODO add your handling code here:
 
        formulhesap3();
    }//GEN-LAST:event_formul3ButtonActionPerformed

    private void formulButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formulButton8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_formulButton8MouseClicked

    private void formulButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formulButton8ActionPerformed
  
        hesp();
     
    }//GEN-LAST:event_formulButton8ActionPerformed

    private void jButtoncıkıs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncıkıs1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        login lg=new login();
       lg.setVisible(true);
    }//GEN-LAST:event_jButtoncıkıs1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(hocaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hocaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hocaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hocaArayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
   
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
              new hocaArayuz().setVisible(true);
      
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton OGRENCİONAYLAMAButton1;
    private javax.swing.JButton formul1Button;
    private javax.swing.JButton formul2Button;
    private javax.swing.JButton formul3Button;
    private javax.swing.JButton formulButton8;
    private javax.swing.JButton jButtoncıkıs1;
    private javax.swing.JComboBox<String> jComboBoxders;
    private javax.swing.JComboBox<String> jComboBoxdurum;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableTalep;
    private javax.swing.JTable jTablekota;
    private javax.swing.JButton ogrenciderslisteleButton2;
    // End of variables declaration//GEN-END:variables

  
}
