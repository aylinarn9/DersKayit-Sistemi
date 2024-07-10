/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yazlab;

/**
 *
 * @author Aylin
 */
public class user {
    private int id;
    private String kullaniciad;
    String sifre;

    public user(int id, String kullaniciad, String sifre) {
        this.id = id;
        this.kullaniciad = kullaniciad;
        this.sifre = sifre;
    }
public user(){
    
}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKullaniciad() {
        return kullaniciad;
    }

    public void setKullaniciad(String kullaniciad) {
        this.kullaniciad = kullaniciad;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
    
    
}
