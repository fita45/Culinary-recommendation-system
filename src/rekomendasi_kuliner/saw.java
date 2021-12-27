/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekomendasi_kuliner;

import static rekomendasi_kuliner.Aplikasi.fasilitas;
import static rekomendasi_kuliner.Aplikasi.harga;
import static rekomendasi_kuliner.Aplikasi.jarak;
import static rekomendasi_kuliner.Aplikasi.nData;
import static rekomendasi_kuliner.Aplikasi.nama;
import static rekomendasi_kuliner.Aplikasi.suasana;
import static rekomendasi_kuliner.ALgen.Pop_Size;
import java.util.Arrays;
import java.util.Comparator;
import static rekomendasi_kuliner.ALgen.Cr;
import static rekomendasi_kuliner.ALgen.Mr;
import static rekomendasi_kuliner.ALgen.Pop_Size;
import static rekomendasi_kuliner.ALgen.Pop_Size_induk_dan_anak;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
import java.util.Scanner;
import static rekomendasi_kuliner.ALgen.Cr;
import static rekomendasi_kuliner.ALgen.Mr;

/**
 *
 * @author user
 */
public class saw {
    
    //public static int Pop_Size = 5; // ukuran populasi
    public  static int StringLenChromosome = 4;
    public static int nData = 50;
    static double v[][] = new double[50][4];
    static double w[] = {0.4, 0.2, 0.25, 0.15};
    static public double r[][] = new double[50][4];
    
    static public double v_tot[] = new double[50];
    static DataKuliner dk[] = new DataKuliner[50];
    static int hargaP, fasilitasP, jarakP, suasanaP;
    static Connection con;
    
     public double sigma(double k[])    {
       double data = 0;
        for(int i=0; i<8;i++){
         data = data + k[i];
        }
       return(data); }
    
    public static void main (String []arg){
        Scanner sc = new Scanner(System.in);
        System.out.print(" Pop Size = ");
        Pop_Size = sc.nextInt();
        System.out.println("Cr = ");
        Cr = sc.nextDouble();
        System.out.println("Mr = ");
        Mr = sc.nextDouble();
        System.out.print(" Jumlah Generasi = ");
        int iterasi=sc.nextInt();
        for(int z=0;z<iterasi;z++){
        ALgen gen = new ALgen();
        koneksi a = new koneksi();
        Aplikasi ap = new Aplikasi();
        SkorParameter skor = new SkorParameter();
        saw s= new saw();
        a.koneksiDatabase();
       // double[][] individu = new double[Pop_Size][StringLenChromosome];
        
        
        System.out.println("StringLen Chromosome = " + StringLenChromosome);
        System.out.println("\n1. inisialisasi individu :");
        double[][] Individu = new double[Pop_Size][StringLenChromosome];
        double r[][] = new double[50][4];
        double v_tot[] = new double[50];
        double [] hasil = new double[nData];
        Individu = gen.inisial(Individu);
        
        for (int i = 0; i < Pop_Size; i++) {
            System.out.print("Individu ke-" + (i + 1) + " : ");
            
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(Individu[i][j] + " ");
                String StringTemp = "";
                StringTemp = StringTemp + Individu[i][j];
                
            }
            System.out.println("");
        }
        //gen.gabungan(Individu);
        double [][] bobot = new double[nData][StringLenChromosome];
        double [] kriteria = new double[50];
        bobot = gen.gabungan(Individu);
        //ap.ambilDataKuliner();
//        ap.saw(r);
       ap.ambilDataKuliner();
        //memberikan skor tiap alternatif
        System.out.println("====Memberi skor nilai alternative====");
        for (int i = 0; i < nData; i++) {
            //skor harga
            v[i][0] = skor.getSkorHarga(harga[i], hargaP);
            System.out.print(v[i][0]+"\t");
            //skor fasilitas
            v[i][1] = skor.getSkorFasilitas(fasilitas[i], fasilitasP);
            System.out.print(v[i][1]+"\t");
            //skor jarak
            v[i][2] = skor.getSkorJarak(jarak[i], jarakP);
            System.out.print(v[i][2]+"\t");
            //skor suasana
            v[i][3] = skor.getSkorSuasana(suasana[i], suasanaP);
            System.out.println(v[i][3]);
        }
        
        double max[] = new double[4];
        double min[] = new double[4];
        
        //mencari nilai maximum kolom fasilitas dan suasana, minimum kolom harga dan jarak
        for (int j = 0; j < 4; j++) {
            if(j == 0 || j == 2){
                min[j] = v[0][j];
                for (int i = 0; i < nData; i++) {
                    if(min[j] >= v[i][j]){
                        min[j] = v[i][j];
                    }
                }
                System.out.println(j+":"+min[j]);
            }
            else if(j == 1 || j == 3){
                max[j]=v[0][j];
                for (int i = 0; i < nData; i++) {
                    if(max[j] <= v[i][j]){
                        max[j] = v[i][j];
                    }
                }
                System.out.println(j+":"+max[j]);
            }
        }
        
        //mencari nilai ternormalisasi
        System.out.println("====Hasil Normalisasi Data Kuliner====");
        for (int i = 0; i < nData; i++) {
            for (int j = 0; j < 4; j++) {
                if(j == 1 || j == 3){
                    r[i][j] = v[i][j]/max[j];
                    System.out.print(String.format("%.4f", r[i][j])+"\t");
                    
                }
                else if (j == 0 || j == 2){
                    r[i][j] = min[j]/v[i][j];
                    System.out.print(String.format("%.4f", r[i][j])+"\t");
                    
                }
            }
            System.out.println("");
        }
        
        //inisialisasi nilai bobot yang digunakan
        for(int p=0;p<Pop_Size_induk_dan_anak;p++){
        System.out.println("=====Inisialisasi bobot dari individu ke-"+(p+1)+"=====");
        for(int i=0;i<4;i++){
            //System.out.println(" Kriteria C"+(i+1)+"= ");
           for(int j=0;j<i+1;j++){
                kriteria[i]=bobot[p][j];
                
           }
       }
       
       for(int i=0;i<4;i++){
           //for(int j=0;j<4;j++){
           System.out.print(" "+String.format("%.4f",kriteria[i]));
       //}
           System.out.println("");
           
       }
       //System.out.println(" "+r[0][0]);
          //perhitungan v total
        double []data = new double[nData];
        for(int i=1;i<nData;i++){
            data[i]=i;
        }
        System.out.println("====Perhitungan bobot individu ke"+(p+1)+"====");
        for (int i = 0; i < nData; i++) {
            for (int j = 0; j < 4; j++) {
                v_tot[i] += kriteria[j]*r[i][j];
            }
            System.out.println(" "+(i+1)+" = "+String.format("%.4f",v_tot[i]));
            dk[i] = new DataKuliner(nama[i], harga[i], fasilitas[i], jarak[i], suasana[i], v_tot[i]);
        }
//        
            
        for(int i=0; i<nData;i++){
            data[i]=v_tot[i];
        } //System.out.println(" "+data[0]);
        double []akurasi = new double[10];
        //double max1=0,max2=0,max3=0,max4=0,max5=0,max6 =0, max7=0,max8=0;
        // data[0] = v_tot[0];
//         for(int i=0;i<46;i++){
//           aray[i]=data[i][0];
//        } 
//        min1 = aray[0];
//        for(int j=0;j<46;j++){
//            if(aray[j]<min1){
//                min1 = aray[j];
//            }
//        }
        //mengurutakan data yang direkomendasikan
        System.out.println("======Urutan rekomendasi=======");
        double temp []=null;
        double descending1=0;
        int descending2=0;
        double descending3=0;
         for(int x=0; x<nData; x++ )
            {
            descending1 = data[x];
            for(int y=x; y<nData; y++)
                {
            if(data[y]>=descending1)
            {
                descending1 = data[y];
                descending2=y;
            }
                   }
            descending3 = data[x];
            data[x] = data[descending2] ;
            data[descending2] = descending3;
  }  
         for(int i= 0; i<8;i++){
            System.out.println((i+1)+" = "+(data[i]));
        }
            
            //menghitung data yang sesuai data user
         //data[0] = v_tot[0];
//         for(int i=0; i<nData;i++){
//            
//        }
         for(int i=0;i<8;i++){
             //data[i]=v_tot[i];
            if(data[i]==v_tot[2]){
                    akurasi[0]=1;
                //System.out.println(" "+akurasi[0]);
                break;}
            else{
                    akurasi[0]=0;
                }
           
         } 
         //System.out.println(" "+akurasi[0]);
            
//            System.out.println(" "+akurasi[1]);
//            System.out.println(" "+akurasi[2])
//            System.out.println(" "+data[7]);;
//            System.out.println(" "+v_tot[2]);
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[9]){
                    akurasi[1]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[1]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[20]){
                    akurasi[2]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[2]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[0]){
                    akurasi[3]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[3]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[16]){
                    akurasi[4]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[4]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[10]){
                    akurasi[5]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[5]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[46]){
                    akurasi[6]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[6]=0;
                }
         }
         for(int i=0;i<8;i++){
            if(data[i]==v_tot[47]){
                    akurasi[7]=1;
                //System.out.println(" "+akurasi[0]);
            break;    
            }else{
                    akurasi[7]=0;
                }
         }
//           
        hasil[p]=((s.sigma(akurasi))/8)*100;
        System.out.println(" Nilai Akurasi yang di dapat adalah "+hasil[p]+"%");
        
        
        }
        System.out.println("=====Nilai Fitness=====");
        for(int i=0;i<Pop_Size_induk_dan_anak;i++){
            System.out.println("Nilai fitness individu ke-"+(i+1)+": "+(hasil[i]/100));
        }
                System.out.println("====Seleksi Elitism====");
        double temp []=null;
        double descending1=0;
        int descending2=0;
        double descending3=0;
         for(int x=0; x<Pop_Size_induk_dan_anak; x++ )
   {
            descending1 = hasil[x];
            for(int y=x; y<Pop_Size_induk_dan_anak; y++)
   {
        if(hasil[y]>=descending1)
   {
    descending1 = hasil[y];
    descending2=y;
   }
        }
            descending3 = hasil[x];
            hasil[x] = hasil[descending2] ;
            hasil[descending2] = descending3;
  }       
        System.out.println("====Individu Generasi ke- "+(z+1)+" Selanjutnya====");
        for(int i= 0; i<Pop_Size;i++){
            System.out.println("Individu ke- "+(i+1)+" = "+(hasil[i]/100));
        }
            System.out.println("");
        //iterasi++;
        }
        
    }
}
