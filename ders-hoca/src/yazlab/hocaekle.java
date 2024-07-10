/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package yazlab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aylin
 */
public class hocaekle extends javax.swing.JInternalFrame {
hoca hoca=new hoca();
Object[] hocaVeri=null;
DefaultTableModel hocaModel=null;
    /**
     * Creates new form hocaekle
     */
    public hocaekle() {
        initComponents();
         baglantiAc();
         idhoca();
         comboxDers();
//         tablo();
         
          hocaVeri =new Object[8];
        hocaModel=(DefaultTableModel) jTablehoca.getModel();
         for(int i=0;i<hoca.gethocaList().size();i++){
             hocaVeri[0]=hoca.gethocaList().get(i).getId();
             hocaVeri[1]=hoca.gethocaList().get(i).getKullaniciad();
             hocaVeri[2]=hoca.gethocaList().get(i).getSifre();
             hocaVeri[3]=hoca.gethocaList().get(i).getSicilno();
             hocaVeri[4]=hoca.gethocaList().get(i).getHocaad();
             hocaVeri[5]=hoca.gethocaList().get(i).getDersad();
            hocaVeri[6]=hoca.gethocaList().get(i).getKota();
            hocaVeri[7]=hoca.gethocaList().get(i).getIlgialani();
            
            hocaModel.addRow(hocaVeri);
                  
         }
         
       hocaSelUpdate();  
    
    }
    
        public void hocaSelUpdate(){
       jTablehoca.getModel().addTableModelListener(new TableModelListener() {
           @Override
           public void tableChanged(TableModelEvent e) {
              if(e.getType()==TableModelEvent.UPDATE){
                  int id=Integer.parseInt(jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 0).toString());
                  String kullaniciad=jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 1).toString();
                  String sifre=jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 2).toString();
                  int sicilno=Integer.parseInt(jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 3).toString());
                  String hocaad=jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 4).toString();
                  String dersad=jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 5).toString();
                 int kota=Integer.parseInt(jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 6).toString());
                 String ilgialani=jTablehoca.getValueAt(jTablehoca.getSelectedRow(), 7).toString();
                hocaManager hoc=new hocaManager();
                hoc.update(kullaniciad, sifre, sicilno, hocaad, dersad, kota, ilgialani,id);
              }
           }
       });
    }
         public void updateHocaTable(){
    DefaultTableModel clearTable=(DefaultTableModel) jTablehoca.getModel();
    clearTable.setRowCount(0);
   for(int i=0;i<hoca.gethocaList().size();i++){
             hocaVeri[0]=hoca.gethocaList().get(i).getId();
             hocaVeri[1]=hoca.gethocaList().get(i).getKullaniciad();
             hocaVeri[2]=hoca.gethocaList().get(i).getSifre();
             hocaVeri[3]=hoca.gethocaList().get(i).getSicilno();
             hocaVeri[4]=hoca.gethocaList().get(i).getHocaad();
             hocaVeri[5]=hoca.gethocaList().get(i).getDersad();
            hocaVeri[6]=hoca.gethocaList().get(i).getKota();
             hocaVeri[7]=hoca.gethocaList().get(i).getIlgialani();
            
            hocaModel.addRow(hocaVeri);
                  
         }
 
}
  Connection baglan; 
  PreparedStatement pat;
  ResultSet rs;
  int q,i;
   
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
   public void comboxDers(){
          try {
        pat=baglan.prepareStatement("SELECT*FROM ders");
        rs=pat.executeQuery();
        while(rs.next()){
        jComboBoxDers.addItem(rs.getString("dersad"));
    }
         }catch (SQLException ex) {
        Logger.getLogger(hocaekle.class.getName()).log(Level.SEVERE, null, ex);
    }
           jComboBoxDers.setSelectedIndex(-1);
   }
   
   
   /*  public void tablo(){
       try {
            pat=baglan.prepareStatement("SELECT*FROM hoca");
             rs=pat.executeQuery();
             DefaultTableModel model=(DefaultTableModel) jTablehoca.getModel();
            ResultSetMetaData stveri=rs.getMetaData();
            
            q=stveri.getColumnCount();
            DefaultTableModel RecordTable=(DefaultTableModel)jTablehoca.getModel();
               RecordTable.setRowCount(0);
             while(rs.next()){
                 Vector sutunVeri=new Vector();
                 for(i =1;i<=q;i++){
                     sutunVeri.add(rs.getString("id"));
                     sutunVeri.add(rs.getString("kullaniciad"));
                     sutunVeri.add(rs.getString("sifre"));
                     sutunVeri.add(rs.getString("sicilno"));
                     sutunVeri.add(rs.getString("hocaad"));
                     sutunVeri.add(rs.getString("dersad"));
                      sutunVeri.add(rs.getString("kota"));
                 }
                 RecordTable.addRow(sutunVeri);
                 
             }       
            
           jTablehoca.setModel(model);
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextkullaniciad = new javax.swing.JTextField();
        jTextsifre = new javax.swing.JTextField();
        jTextsicilno = new javax.swing.JTextField();
        jTextadsoyad = new javax.swing.JTextField();
        EKLE = new javax.swing.JButton();
        GUNCELLE = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txthocaid = new javax.swing.JComboBox<>();
        ARAMA = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablehoca = new javax.swing.JTable();
        jDers = new javax.swing.JLabel();
        jComboBoxDers = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldkota = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldilgialani = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 204, 204));
        setPreferredSize(new java.awt.Dimension(1000, 1000));

        jLabel2.setText("KULLANİCİ AD:");

        jLabel3.setText("SİFRE:");

        jLabel4.setText("SİCİL NO:");

        jLabel5.setText("AD SOYAD:");

        jTextkullaniciad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextkullaniciadActionPerformed(evt);
            }
        });

        jTextadsoyad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextadsoyadActionPerformed(evt);
            }
        });

        EKLE.setText("EKLE");
        EKLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EKLEActionPerformed(evt);
            }
        });

        GUNCELLE.setText("GUNCELLE");
        GUNCELLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GUNCELLEActionPerformed(evt);
            }
        });

        DELETE.setText("DELETE");
        DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DELETEActionPerformed(evt);
            }
        });

        jLabel1.setText("hoca_id:");

        txthocaid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ARAMA.setText("ARAMA");
        ARAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARAMAActionPerformed(evt);
            }
        });

        jTablehoca.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "kullaniciad", "sifre", "sicilno", "adsoyad", "dersad", "kota", "ilgialani"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTablehoca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablehocaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTablehoca);

        jDers.setText("DERS:");

        jLabel6.setText("KOTA:");

        jTextFieldkota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldkotaActionPerformed(evt);
            }
        });

        jLabel7.setText("İLGİ ALANİ:");

        jTextFieldilgialani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldilgialaniActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextsicilno, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76)
                                .addComponent(EKLE)
                                .addGap(18, 18, 18)
                                .addComponent(GUNCELLE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DELETE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextkullaniciad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jTextsifre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
                                    .addComponent(jTextadsoyad, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                                .addGap(234, 234, 234)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ARAMA)
                                    .addComponent(txthocaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jTextFieldkota, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(411, 411, 411))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jDers, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jComboBoxDers, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jTextFieldilgialani, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextkullaniciad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txthocaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextsifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ARAMA))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextsicilno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(EKLE)
                            .addComponent(GUNCELLE)
                            .addComponent(DELETE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextadsoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jDers)
                    .addComponent(jComboBoxDers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldkota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldilgialani, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextkullaniciadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextkullaniciadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextkullaniciadActionPerformed

    private void jTextadsoyadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextadsoyadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextadsoyadActionPerformed

    private void EKLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EKLEActionPerformed
        // TODO add your handling code here:
        hocaManager hocaManager=new hocaManager();
          hocaManager.insert(jTextkullaniciad.getText(),  jTextsifre.getText(),  Integer.parseInt(jTextsicilno.getText()), jTextadsoyad.getText(), (String) jComboBoxDers.getSelectedItem(),Integer.parseInt(jTextFieldkota.getText()),jTextFieldilgialani.getText());
          comboxDers();
          updateHocaTable();
      //  tablo();
        //JOptionPane.showMessageDialog(this, "");
        jTextkullaniciad.setText("");
        jTextsifre.setText("");
        jTextsicilno.setText("");
        jTextadsoyad.setText("");
        jTextFieldkota.setText("");
        jTextFieldilgialani.setText("");
    }//GEN-LAST:event_EKLEActionPerformed

    private void GUNCELLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUNCELLEActionPerformed
        // TODO add your handling code here:
            /* DefaultTableModel model=(DefaultTableModel)jTablehoca.getModel();
         int satirSec=jTablehoca.getSelectedRow();
         int hocaid=Integer.parseInt(model.getValueAt(satirSec,0).toString());*/
        String oyuncuid=txthocaid.getSelectedItem().toString();
        int hocaid=Integer.parseInt(oyuncuid);
        hocaManager hocaManager=new hocaManager();
        hocaManager.update(jTextkullaniciad.getText(),  jTextsifre.getText(),  Integer.parseInt(jTextsicilno.getText()), jTextadsoyad.getText(), (String) jComboBoxDers.getSelectedItem(),Integer.parseInt(jTextFieldkota.getText()),jTextFieldilgialani.getText(),hocaid);
    comboxDers();
     updateHocaTable();
//      tablo();
      
           
          
            jTextkullaniciad.setText("");
            jTextsifre.setText("");
            jTextsicilno.setText("");
            jTextadsoyad.setText("");
           jComboBoxDers.setSelectedIndex(0);
           jTextFieldkota.setText("");
           jTextFieldilgialani.setText("");
            jTextkullaniciad.requestFocus();
            //idoyuncu();
    }//GEN-LAST:event_GUNCELLEActionPerformed

    private void ARAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARAMAActionPerformed
        // TODO add your handling code here:
           try {
            // TODO add your handling code here:

            String hocaid=txthocaid.getSelectedItem().toString();
          int hocaidd=  Integer.parseInt(hocaid);
            pat=baglan.prepareStatement("SELECT*FROM hoca WHERE id=?");
           pat.setInt(1, hocaidd);
            
            rs=pat.executeQuery();
            if(rs.next()==true){
               jTextkullaniciad.setText(rs.getString(2));
               jTextsifre.setText(rs.getString(3));
                jTextsicilno.setText(rs.getString(4));
                 jTextadsoyad.setText(rs.getString(5));
                 jTextFieldkota.setText(rs.getString(7));
                 jTextFieldilgialani.setText(rs.getString(8));
                 
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }//GEN-LAST:event_ARAMAActionPerformed

    private void jTablehocaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablehocaMouseClicked
        // TODO add your handling code here:
            DefaultTableModel model=(DefaultTableModel)jTablehoca.getModel();
        int satirSec=jTablehoca.getSelectedRow();
        int id=Integer.parseInt(model.getValueAt(satirSec,0).toString());
         jTextkullaniciad.setText(model.getValueAt(satirSec,1).toString());
            jTextsifre.setText(model.getValueAt(satirSec,2).toString());
             jTextsicilno.setText(model.getValueAt(satirSec,3).toString());
              jTextadsoyad.setText(model.getValueAt(satirSec,4).toString());
           
            jTextFieldkota.setText(model.getValueAt(satirSec,5).toString());
             jComboBoxDers.setSelectedItem(model.getValueAt(satirSec, 6).toString());
              jTextFieldilgialani.setText(model.getValueAt(satirSec,7).toString());
             updateHocaTable();
              
               
    }//GEN-LAST:event_jTablehocaMouseClicked

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
             DefaultTableModel model=(DefaultTableModel)jTablehoca.getModel();
         int satirSec=jTablehoca.getSelectedRow();
         int hocaid=Integer.parseInt(model.getValueAt(satirSec,0).toString());
         
        
       hocaManager hocaManager=new hocaManager();
        hocaManager.delete(hocaid);
        
       // tablo();
       updateHocaTable();
          jTextkullaniciad.setText("");
           jTextkullaniciad.requestFocus();
            jTextsifre.setText("");
            jTextsicilno.setText("");
            jTextadsoyad.setText("");
            jTextFieldkota.setText("");
            jTextFieldilgialani.setText("");
    }//GEN-LAST:event_DELETEActionPerformed

    private void jTextFieldkotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldkotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldkotaActionPerformed

    private void jTextFieldilgialaniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldilgialaniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldilgialaniActionPerformed

   private void idhoca() {
           try {
            pat=baglan.prepareStatement("SELECT id FROM hoca");
            rs=pat.executeQuery();
            txthocaid.removeAllItems();
            while(rs.next()){
                
                txthocaid.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        }
         txthocaid.setSelectedIndex(-1);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ARAMA;
    private javax.swing.JButton DELETE;
    private javax.swing.JButton EKLE;
    private javax.swing.JButton GUNCELLE;
    private javax.swing.JComboBox<String> jComboBoxDers;
    private javax.swing.JLabel jDers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablehoca;
    private javax.swing.JTextField jTextFieldilgialani;
    private javax.swing.JTextField jTextFieldkota;
    private javax.swing.JTextField jTextadsoyad;
    private javax.swing.JTextField jTextkullaniciad;
    private javax.swing.JTextField jTextsicilno;
    private javax.swing.JTextField jTextsifre;
    private javax.swing.JComboBox<String> txthocaid;
    // End of variables declaration//GEN-END:variables
}
