/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package yazlab;

/**
 *
 * @author Aylin
 */
public interface IOgrenci {
    void insert(String kullaniciad,String sifre,int ogrencino,String ogrenciad);
    void update(String kullaniciad,String sifre,int ogrencino,String ogrenciad,int id);
     void delete(int id);
     
    void insert(String kullaniciad,String sifre,Boolean statu);
    void update(int id);
   
    void update(int ogrencino,String ogrenciad,int id);
 
    
}
