/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package yazlab;

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
import javax.swing.table.DefaultTableModel;
import static yazlab.login.studentData;

/**
 *
 * @author Aylin
 */
public class ogrenciarayuz extends javax.swing.JFrame {
ogrenci ogrenci=new ogrenci();
ders ds=new ders();
ilgialani ilgi=new ilgialani();

  Connection baglan; 
  PreparedStatement pat;
  ResultSet rs;
  int q,i;
  DefaultListModel listmodel=new DefaultListModel();
  DefaultListModel listmodel2=new DefaultListModel();
   
    DefaultListModel listmodell2=new DefaultListModel();
   DefaultListModel listmodel4=new DefaultListModel();
  
  
    /**
     * Creates new form OgrenciArayuz
     */
    public ogrenciarayuz() {
        initComponents();
        baglantiAc();
        comboxDers();
        comboxİlgi();
  
      // tablo();
        taleptablo();
        
        jtable2();
        jList();
        jlist2();
        
        jList2();
        jlist4();
        /*
         try {
         int ogrenciid=(int) studentData[0];   
      // String sorgu="SELECT *FROM ders WHERE ogrenciid='"+ogrenciid+"'";
           // baglanti baglanti=new baglanti();
          //  baglan=baglanti.getConnection();
             pat=baglan.prepareStatement("SELECT *FROM ders WHERE ogrenciid='"+ogrenciid+"'");
             rs=pat.executeQuery();
             while(rs.next()){
                 tablo();
             }
           
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
          */
        
    }
  public void jlist2(){
      listmodel2.removeAllElements();
       for(int i=0;i<ds.getderslist((int) studentData[0]).size();i++){
           listmodel2.addElement(ds.getderslist((int) studentData[0]).get(i).getDersad());
       }
       jList2.setModel(listmodel2);
  }
  
 public void jlist4(){
     listmodel4.removeAllElements();
     for(int i=0;i<ilgi.getilgilist((int) studentData[0]).size();i++){
         listmodel4.addElement(ilgi.getilgilist((int) studentData[0]).get(i).getIlgialani());
     }
     jList4.setModel(listmodel4);
     
 }
  
   public void comboxDers(){
       ArrayList<String> yeni=new ArrayList<>();
        for(int i=0;i<ds.getderslist((int) studentData[0]).size();i++){
         yeni.add(ds.getderslist((int) studentData[0]).get(i).getDersad());
       }
        for(int j=0;j<yeni.size();j++){
          
               //  try {
               // pat=baglan.prepareStatement("SELECT *FROM ders");
               //rs=pat.executeQuery();
               // while(rs.next()){
               jComboBoxdersad.addItem(yeni.get(j));
        }
           
    jComboBoxdersad.setSelectedIndex(-1);
         }
        
        
 public void comboxİlgi(){
    
     ArrayList<String> yeniilgi=new ArrayList<>();     
     for(int i=0;i<ilgi.getilgilist((int) studentData[0]).size();i++){
        yeniilgi.add(ilgi.getilgilist((int) studentData[0]).get(i).getIlgialani());
     }
     for(int j=0;j<yeniilgi.size();j++){
         jComboBoxilgialani.addItem(yeniilgi.get(j));
     }
     
      jComboBoxilgialani.setSelectedIndex(-1);
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
   
public void jList(){
    listmodel.removeAllElements();
     String dersad= (String) jComboBoxdersad.getSelectedItem();
        try {
            
            pat=baglan.prepareStatement("select hocaad from hoca where dersad='"+dersad+"'");
             rs=pat.executeQuery();
             while(rs.next()){
                 
                 listmodel.addElement(rs.getString("hocaad"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciarayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
         jList3.setModel(listmodel);
    
}

public void jList2(){
        listmodell2.removeAllElements();
     String ilgialani= (String) jComboBoxilgialani.getSelectedItem();
        try {
            
            pat=baglan.prepareStatement("select hocaad from hoca where ilgialani='"+ilgialani+"'");
             rs=pat.executeQuery();
             while(rs.next()){
                 
                 listmodell2.addElement(rs.getString("hocaad"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciarayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
         jList5.setModel(listmodell2);
    
    
    
}
public void taleptablo(){
    try {
       
               pat=baglan.prepareStatement("SELECT*FROM derstalepanlasma WHERE ogrenciid='"+studentData[0]+"'");
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
             
           } catch (SQLException ex) {
               Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
           }
     
    
    
    
}

public void jtable2(){
    try {
       String kabul="kabuledildi";
     pat=baglan.prepareStatement("SELECT hocaad,dersad FROM derstalepanlasma WHERE ogrenciid='"+studentData[0]+"' AND anlasmadurumu='"+kabul+"'");
               rs=pat.executeQuery();
               DefaultTableModel model=(DefaultTableModel) jTable2.getModel();
               ResultSetMetaData stveri=rs.getMetaData();
               
               q=stveri.getColumnCount();
               DefaultTableModel RecordTable=(DefaultTableModel)jTable2.getModel();
               RecordTable.setRowCount(0);
               while(rs.next()){
                   Vector sutunVeri=new Vector();
                  // Object[] studentData=new Object[q];
                   for(i =1;i<=q;i++){
                       
                        sutunVeri.add(rs.getString("hocaad"));
                        sutunVeri.add(rs.getString("dersad"));
                       
                   }
                   RecordTable.addRow(sutunVeri);
                   
               }
               
               jTable2.setModel(model);
             
           } catch (SQLException ex) {
               Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
           } 
    
    
}
    /* public void tablo(){   
         
     try {
      // String sorgu="SELECT *FROM ders WHERE ogrenciid='"+ogrenciid+"'";
            
            
              pat=baglan.prepareStatement("select dersid,dersad,dersharfnot,ogrenciid from ders inner join ogrenci on ders.ogrenciid=ogrenci.id");
             rs=pat.executeQuery();
             DefaultTableModel model=(DefaultTableModel) jTable1.getModel();
            ResultSetMetaData stveri=rs.getMetaData();
            
            q=stveri.getColumnCount();
            DefaultTableModel RecordTable=(DefaultTableModel)jTable1.getModel();
               RecordTable.setRowCount(0);
             while(rs.next()){
                 Vector sutunVeri=new Vector();
                 for(i =1;i<=q;i++){
                     
                     sutunVeri.add(rs.getString("dersid"));
                      sutunVeri.add(rs.getString("dersad"));
                     sutunVeri.add(rs.getString("dersharfnot"));
                     sutunVeri.add(rs.getString("ogrenciid"));
                   //  sutunVeri.add(rs.getString("ogrenciad"));
                     
                   
                 }
                 RecordTable.addRow(sutunVeri);
                 
             }       
            
           jTable1.setModel(model);      
                        
                      
         
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        }
   }*/

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
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxdersad = new javax.swing.JComboBox<>();
        HocaGirisButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jComboBoxdurum = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldmesaj = new javax.swing.JTextField();
        TALEPÇEKMEButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTalep = new javax.swing.JTable();
        TALEPButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButtoncıkıs = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxilgialani = new javax.swing.JComboBox<>();
        ilgialanibuton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList5 = new javax.swing.JList<>();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jScrollPane4.setViewportView(jList2);

        jLabel4.setText("ÖĞRENCİNİN ALDIĞI DERSLER TABLOSU:");

        jLabel2.setText("DERSİ VEREN HOCA TABLOSU:");

        jScrollPane1.setViewportView(jList3);

        jLabel1.setText("ders ad:");

        HocaGirisButton.setText("arama");
        HocaGirisButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HocaGirisButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxdersad, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(HocaGirisButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBoxdersad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(HocaGirisButton)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jComboBoxdurum.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bekleme", "kabuledildi", "red", "talep yok" }));

        jLabel3.setText("iletilen mesaj:");

        TALEPÇEKMEButton2.setText("TALEP ÇEKME");
        TALEPÇEKMEButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TALEPÇEKMEButton2ActionPerformed(evt);
            }
        });

        jTableTalep.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "ogrenciid", "ogrenciad", "hocaad", "anlasma durumu", "iletilen mesaj", "dersad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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

        TALEPButton1.setText("TALEP");
        TALEPButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TALEPButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jComboBoxdurum, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TALEPButton1)
                .addGap(139, 139, 139))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldmesaj, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(TALEPÇEKMEButton2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxdurum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TALEPButton1))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldmesaj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(TALEPÇEKMEButton2)))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 204));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "hocaad", "dersad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable2);

        jLabel5.setText("ÖĞRENCİNİN HOCA TARAFINDAN ONAYLANAN DERSLERİ:");

        jButtoncıkıs.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        jButtoncıkıs.setForeground(new java.awt.Color(204, 51, 0));
        jButtoncıkıs.setText("çıkıs");
        jButtoncıkıs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtoncıkısActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtoncıkıs, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jButtoncıkıs, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));

        jScrollPane3.setViewportView(jList4);

        jLabel6.setText("ÖĞRENCİNİN İLGİ ALANLARI:");

        jLabel7.setText("ilgi alani:");

        ilgialanibuton.setText("arama");
        ilgialanibuton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ilgialanibutonActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(jList5);

        jLabel8.setText("İLGİ ALANINA GÖRE HOCALAR:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(ilgialanibuton))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxilgialani, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxilgialani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(ilgialanibuton)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HocaGirisButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HocaGirisButtonActionPerformed
        // TODO add your handling code here:
     jList();
       
    }//GEN-LAST:event_HocaGirisButtonActionPerformed

    private void TALEPButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TALEPButton1ActionPerformed
        // TODO add your handling code here:
       talepManager tpm=new talepManager();
   
      String dersadd= (String) jComboBoxdersad.getSelectedItem();
     int ogrenciid= (int) studentData[0];
     String ogrenciad=(String) studentData[4];
      String hoca= jList3.getSelectedValue();
     String durum= (String) jComboBoxdurum.getSelectedItem();
     
  
     String mesaj=jTextFieldmesaj.getText();
          try {
            
            pat=baglan.prepareStatement("select id from ogrenci where id='"+ogrenciid+"'");
             rs=pat.executeQuery();
             while(rs.next()){
                tpm.insert(Integer.parseInt(rs.getString("id")),ogrenciad,hoca,durum,mesaj,dersadd);
                taleptablo();
             
              
             }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciarayuz.class.getName()).log(Level.SEVERE, null, ex);
        }
   
      
        
        
        
        
    }//GEN-LAST:event_TALEPButton1ActionPerformed

    private void TALEPÇEKMEButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TALEPÇEKMEButton2ActionPerformed
        // TODO add your handling code here:
         DefaultTableModel model=(DefaultTableModel)jTableTalep.getModel();
         int satirSec=jTableTalep.getSelectedRow();
         int id=Integer.parseInt(model.getValueAt(satirSec,0).toString());
           try {
       String durum="bekleme";
     pat=baglan.prepareStatement("SELECT id FROM derstalepanlasma WHERE anlasmadurumu='"+durum+"'");
               rs=pat.executeQuery();
               while(rs.next()){
                   talepManager tlp=new talepManager();
                   tlp.delete(Integer.parseInt(rs.getString("id")));
                   taleptablo();
               }
              
             
           } catch (SQLException ex) {
               Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
           } 
    
     
       
        
           //jTextkullaniciad.requestFocus();
           jComboBoxdurum.setSelectedIndex(-1);
           jTextFieldmesaj.setText("");
        
        
    }//GEN-LAST:event_TALEPÇEKMEButton2ActionPerformed

    private void jTableTalepMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableTalepMouseClicked
        // TODO add your handling code here:
        
          DefaultTableModel model=(DefaultTableModel)jTableTalep.getModel();
        int satirSec=jTableTalep.getSelectedRow();
        int id=Integer.parseInt(model.getValueAt(satirSec,0).toString());
        
    }//GEN-LAST:event_jTableTalepMouseClicked

    private void ilgialanibutonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ilgialanibutonActionPerformed
        // TODO add your handling code here:
        jList2();
    }//GEN-LAST:event_ilgialanibutonActionPerformed

    private void jButtoncıkısActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtoncıkısActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
       login lg=new login();
       lg.setVisible(true);
    }//GEN-LAST:event_jButtoncıkısActionPerformed

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
            java.util.logging.Logger.getLogger(ogrenciarayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ogrenciarayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ogrenciarayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ogrenciarayuz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ogrenciarayuz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton HocaGirisButton;
    private javax.swing.JButton TALEPButton1;
    private javax.swing.JButton TALEPÇEKMEButton2;
    private javax.swing.JButton ilgialanibuton;
    private javax.swing.JButton jButtoncıkıs;
    private javax.swing.JComboBox<String> jComboBoxdersad;
    private javax.swing.JComboBox<String> jComboBoxdurum;
    private javax.swing.JComboBox<String> jComboBoxilgialani;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JList<String> jList4;
    private javax.swing.JList<String> jList5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTableTalep;
    private javax.swing.JTextField jTextFieldmesaj;
    // End of variables declaration//GEN-END:variables
}
