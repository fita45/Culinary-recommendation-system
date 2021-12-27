/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rekomendasi_kuliner;

/**
 *
 * @author user
 */
public class DataKuliner implements Comparable<DataKuliner>{
    private String nama;
    private int harga, fasilitas, jarak, suasana;
    private double v_tot;
    
    public DataKuliner(String nama, int harga, int fasilitas, int jarak, int suasana, double v_tot){
        this.nama = nama;
        this.harga = harga;
        this.fasilitas = fasilitas;
        this.jarak = jarak;
        this.suasana = suasana;
        this.v_tot = v_tot;
    }

    @Override
    public int compareTo(DataKuliner dk){
        if (this.v_tot < dk.v_tot) {
            return 1;
        } else if (this.v_tot == dk.v_tot) {
            return 0;
        } else {
            return -1;
        }
    }

    public String getNama() {
        return nama;
    }

    public double getV_tot() {
        return v_tot;
    }
    
    
}
