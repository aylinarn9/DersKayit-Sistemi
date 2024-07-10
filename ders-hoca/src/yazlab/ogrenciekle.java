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
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Aylin
 */
public class ogrenciekle extends javax.swing.JInternalFrame {
 ogrenci ogrenci=new ogrenci();
  Connection baglan; 
  PreparedStatement pat;
  ResultSet rs;
  int q,i;
   Object[] studentData=null;
   DefaultTableModel ogrenciModel=null;
    /**
     * Creates new form ogrenciekle
     */
    public ogrenciekle()  {
   
 
        initComponents();
        baglantiAc();
        idogrenci();
      //  tablo();
            studentData=new Object[7];
            ogrenciModel=(DefaultTableModel) jTableogrenci.getModel();
            for(int i=0;i<ogrenci.getogrenciList().size();i++){
            studentData[0]=ogrenci.getogrenciList().get(i).getId();
            studentData[1]=ogrenci.getogrenciList().get(i).getKullaniciad();
            studentData[2]=ogrenci.getogrenciList().get(i).getSifre();
            studentData[3]=ogrenci.getogrenciList().get(i).getOgrencino();
            studentData[4]=ogrenci.getogrenciList().get(i).getOgrenciad();
         
            ogrenciModel.addRow(studentData);
           // stuSel();
            }
            studentSecimUpdate();
  
   }
    
    

  

      public void studentSecimUpdate(){
       jTableogrenci.getModel().addTableModelListener(new TableModelListener() {
           @Override
           public void tableChanged(TableModelEvent e) {
              if(e.getType()==TableModelEvent.UPDATE){
                  int id=Integer.parseInt(jTableogrenci.getValueAt(jTableogrenci.getSelectedRow(), 0).toString());
                  String kullaniciad=jTableogrenci.getValueAt(jTableogrenci.getSelectedRow(), 1).toString();
                  String sifre=jTableogrenci.getValueAt(jTableogrenci.getSelectedRow(), 2).toString();
                  int ogrencino=Integer.parseInt(jTableogrenci.getValueAt(jTableogrenci.getSelectedRow(), 3).toString());
                  String ogrenciad=jTableogrenci.getValueAt(jTableogrenci.getSelectedRow(), 4).toString();
                  
                ogrenciManager om=new ogrenciManager();
                om.update(kullaniciad, sifre, ogrencino, ogrenciad, id);
              }
           }
       });
    }
      public void updateSecimtablo(){
    DefaultTableModel clearTable=(DefaultTableModel) jTableogrenci.getModel();
    clearTable.setRowCount(0);
    for(int i=0;i<ogrenci.getogrenciList().size();i++){
            studentData[0]=ogrenci.getogrenciList().get(i).getId();
            studentData[1]=ogrenci.getogrenciList().get(i).getKullaniciad();
            studentData[2]=ogrenci.getogrenciList().get(i).getSifre();
            studentData[3]=ogrenci.getogrenciList().get(i).getOgrencino();
            studentData[4]=ogrenci.getogrenciList().get(i).getOgrenciad();
         
            ogrenciModel.addRow(studentData);
           // stuSel();
            }
 
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
       catch (ClassNotFoundException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   }

    
    private void idogrenci() {
           try {
            pat=baglan.prepareStatement("SELECT id FROM ogrenci");
            rs=pat.executeQuery();
            txtoid.removeAllItems();
            while(rs.next()){
                
                txtoid.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ogrenciekle.class.getName()).log(Level.SEVERE, null, ex);
        }
          txtoid.setSelectedIndex(-1);
    }
    
    
     /*public void comboxDersad(){
          try {
        pat=baglan.prepareStatement("SELECT*FROM ders");
        rs=pat.executeQuery();
        while(rs.next()){
       jComboBoxdersad.addItem(rs.getString("dersad"));
    }
         }catch (SQLException ex) {
        Logger.getLogger(hocaekle.class.getName()).log(Level.SEVERE, null, ex);
    }
   }*/
     
  /* public void tablo(){
    
          
           try {
               pat=baglan.prepareStatement("SELECT*FROM ogrenci");
               rs=pat.executeQuery();
               DefaultTableModel model=(DefaultTableModel) jTableogrenci.getModel();
               ResultSetMetaData stveri=rs.getMetaData();
               
               q=stveri.getColumnCount();
               DefaultTableModel RecordTable=(DefaultTableModel)jTableogrenci.getModel();
               RecordTable.setRowCount(0);
               while(rs.next()){
                   Vector sutunVeri=new Vector();
                  // Object[] studentData=new Object[q];
                   for(i =1;i<=q;i++){
                       sutunVeri.add(rs.getString("id"));
                       sutunVeri.add(rs.getString("kullaniciad"));
                       sutunVeri.add(rs.getString("sifre"));
                       sutunVeri.add(rs.getString("ogrencino"));
                       sutunVeri.add(rs.getString("ogrenciad"));
                       
                   }
                   RecordTable.addRow(sutunVeri);
                   
               }
               
               jTableogrenci.setModel(model);
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
        jTextkullaniciad = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextsifre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextogrencino = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextadsoyad = new javax.swing.JTextField();
        EKLE = new javax.swing.JButton();
        GUNCELLE = new javax.swing.JButton();
        DELETE = new javax.swing.JButton();
        jogrencid = new javax.swing.JLabel();
        txtoid = new javax.swing.JComboBox<>();
        ARAMA = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableogrenci = new javax.swing.JTable();
        TKEKLE = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldstatu = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 204));
        setPreferredSize(new java.awt.Dimension(1000, 1000));

        jLabel2.setText("KULLANİCİ AD:");

        jTextkullaniciad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextkullaniciadActionPerformed(evt);
            }
        });

        jLabel3.setText("SİFRE:");

        jLabel4.setText("OGRENCİ NO:");

        jTextogrencino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextogrencinoActionPerformed(evt);
            }
        });

        jLabel5.setText("AD SOYAD:");

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

        jogrencid.setText("ogrenci_id:");

        txtoid.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ARAMA.setText("ARAMA");
        ARAMA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ARAMAActionPerformed(evt);
            }
        });

        jTableogrenci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "kullaniciad", "sifre", "ogrencino", "adsoyad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableogrenci.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableogrenciMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableogrenci);

        TKEKLE.setText("TKEKLE");
        TKEKLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TKEKLEActionPerformed(evt);
            }
        });

        jLabel1.setText("Statu:");

        jTextFieldstatu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldstatuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextogrencino, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                            .addComponent(jTextsifre, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextkullaniciad, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextadsoyad)
                            .addComponent(jTextFieldstatu))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jogrencid, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(GUNCELLE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(TKEKLE)
                                        .addGap(18, 18, 18)
                                        .addComponent(EKLE)
                                        .addGap(18, 18, 18)
                                        .addComponent(DELETE))
                                    .addComponent(ARAMA))))))
                .addGap(0, 281, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextkullaniciad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jogrencid)
                    .addComponent(txtoid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextsifre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(ARAMA)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GUNCELLE)
                            .addComponent(EKLE)
                            .addComponent(DELETE)
                            .addComponent(TKEKLE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jTextogrencino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextadsoyad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldstatu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(477, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextogrencinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextogrencinoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextogrencinoActionPerformed

    private void jTextkullaniciadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextkullaniciadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextkullaniciadActionPerformed

    private void jTextadsoyadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextadsoyadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextadsoyadActionPerformed

    private void EKLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EKLEActionPerformed
        // TODO add your handling code here:
        ogrenciManager ogrenciManager=new ogrenciManager();
       ogrenciManager.insert(jTextkullaniciad.getText(),  jTextsifre.getText(),  Integer.parseInt(jTextogrencino.getText()), jTextadsoyad.getText());
    
     
       
      updateSecimtablo();
        JOptionPane.showMessageDialog(this, "");
        jTextkullaniciad.setText("");
        jTextsifre.setText("");
        jTextogrencino.setText("");
        jTextadsoyad.setText("");
    
    }//GEN-LAST:event_EKLEActionPerformed

    private void GUNCELLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GUNCELLEActionPerformed

    
         /*DefaultTableModel model=(DefaultTableModel)jTableogrenci.getModel();
         int satirSec=jTableogrenci.getSelectedRow();
         int oyuncuid=Integer.parseInt(model.getValueAt(satirSec,0).toString());*/
         
         String oyuncuid=txtoid.getSelectedItem().toString();
        int ogrenciid=Integer.parseInt(oyuncuid);
        
        ogrenciManager ogrenciManager=new ogrenciManager();
        ogrenciManager.update(jTextkullaniciad.getText(),  jTextsifre.getText(),  Integer.parseInt(jTextogrencino.getText()), jTextadsoyad.getText(),ogrenciid);
    
     // tablo();

           // JOptionPane.showMessageDialog(this, "");
          
            jTextkullaniciad.setText("");
            jTextsifre.setText("");
            jTextogrencino.setText("");
            jTextadsoyad.setText("");
       
            //jComboBoxdersad.setSelectedIndex(0);
            jTextkullaniciad.requestFocus();
            //idoyuncu();
           
           
        
            
        
    }//GEN-LAST:event_GUNCELLEActionPerformed

    private void ARAMAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ARAMAActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:

            String oyuncuid=txtoid.getSelectedItem().toString();
          int oyuncuidd=  Integer.parseInt(oyuncuid);
            pat=baglan.prepareStatement("SELECT*FROM ogrenci WHERE id=?");
           pat.setInt(1, oyuncuidd);
            
            rs=pat.executeQuery();
            if(rs.next()==true){
                jTextkullaniciad.setText(rs.getString(2));
               jTextsifre.setText(rs.getString(3));
                jTextogrencino.setText(rs.getString(4));
                 jTextadsoyad.setText(rs.getString(5));
                 
                 
              
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ogrenci.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
    }//GEN-LAST:event_ARAMAActionPerformed

    private void jTableogrenciMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableogrenciMouseClicked
        // TODO add your handling code here:
        
          DefaultTableModel model=(DefaultTableModel)jTableogrenci.getModel();
        int satirSec=jTableogrenci.getSelectedRow();
        int id=Integer.parseInt(model.getValueAt(satirSec,0).toString());
         jTextkullaniciad.setText(model.getValueAt(satirSec,1).toString());
            jTextsifre.setText(model.getValueAt(satirSec,2).toString());
             jTextogrencino.setText(model.getValueAt(satirSec,3).toString());
              jTextadsoyad.setText(model.getValueAt(satirSec,4).toString());
               
         
        
    }//GEN-LAST:event_jTableogrenciMouseClicked

    private void DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DELETEActionPerformed
        // TODO add your handling code here:
          DefaultTableModel model=(DefaultTableModel)jTableogrenci.getModel();
         int satirSec=jTableogrenci.getSelectedRow();
         int ogrenciid=Integer.parseInt(model.getValueAt(satirSec,0).toString());
     
        ogrenciManager ogrenciManager=new ogrenciManager();
        ogrenciManager.delete(ogrenciid);
       // tablo();
          jTextkullaniciad.setText("");
           jTextkullaniciad.requestFocus();
            jTextsifre.setText("");
            jTextogrencino.setText("");
            jTextadsoyad.setText("");
    }//GEN-LAST:event_DELETEActionPerformed

    private void TKEKLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TKEKLEActionPerformed
        // TODO add your handling code here:
        ogrenciManager ogrenciManager=new ogrenciManager();
        ogrenciManager.insert(jTextkullaniciad.getText(), jTextsifre.getText(),Boolean.parseBoolean(jTextFieldstatu.getText()));
   
       // tablo();
      updateSecimtablo();
        //JOptionPane.showMessageDialog(this, "");
        jTextkullaniciad.setText("");
        jTextsifre.setText("");
        
    }//GEN-LAST:event_TKEKLEActionPerformed

    private void jTextFieldstatuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldstatuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldstatuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ARAMA;
    private javax.swing.JButton DELETE;
    private javax.swing.JButton EKLE;
    private javax.swing.JButton GUNCELLE;
    private javax.swing.JButton TKEKLE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableogrenci;
    private javax.swing.JTextField jTextFieldstatu;
    private javax.swing.JTextField jTextadsoyad;
    private javax.swing.JTextField jTextkullaniciad;
    private javax.swing.JTextField jTextogrencino;
    private javax.swing.JTextField jTextsifre;
    private javax.swing.JLabel jogrencid;
    private javax.swing.JComboBox<String> txtoid;
    // End of variables declaration//GEN-END:variables

  
    
   

}
