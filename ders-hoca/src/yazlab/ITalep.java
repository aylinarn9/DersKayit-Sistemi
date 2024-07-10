/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package yazlab;

/**
 *
 * @author Aylin
 */
public interface ITalep {
    void insert (int ogrenciid,String ogrenciad,String hocaad,String anlasmadurumu,String iletilenmesaj,String dersad);
    void update(int ogrenciid,String ogrenciad,String hocaad,String anlasmadurumu,String iletilenmesaj,String dersad,int id);
    void updatedersalim(String anlasmadurumu,int id);
    void delete(int id);
    
}
