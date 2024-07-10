/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package yazlab;

/**
 *
 * @author Aylin
 */
public interface IHoca {
    void insert(String kullaniciad,String sifre,int sicilno,String hocaad,String dersad,int kota,String ilgialani);
    void update(String kullaniciad,String sifre,int sicilno,String hocaad,String dersad,int kota,String ilgialani,int id);
  
    void update(int kota,String hocaad);
    void delete(int id);
}
