/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rekomendasi_kuliner;

import java.util.Random;
import java.sql.DriverManager;
import java.sql.ResultSet;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
/**
 *
 * @author user
 */
public class ALgen {
    public static double cr;
    

    //AHPTopsis ahp = new AHPTopsis();
    Random random = new Random();
    public static int Pop_Size = 5; // ukuran populasi
    public  int StringLenChromosome = 4;
    public double inis[][] = new double[Pop_Size][StringLenChromosome];
    public double cros[][] = new double [Pop_Size][StringLenChromosome];
    public double mut[][] = new double [Pop_Size][StringLenChromosome];
    //public double gabungan[][] = new double [Pop_Size][StringLenChromosome];
    
    public static int Pop_Size_induk_dan_anak=8;
    // setting parameter 
    public static double Cr = 0.2, Mr = 0.4;
    //Case : Max, y = f(x) = -x^2 + 14x – 13, 0 ≤ x ≤ 15
    public static int Batas_Min = 0, Batas_Max = 4;
    //Cek panjang string dari nilai biner Batas_Max
    public int baris = 46;
    public int kolom = 6;
    public double BatasBawahRandD = 0.0, BatasAtasRandD = 0.1;
    ALgen(){
        
    }
    ALgen(double g1){
        this.cr = g1;
       
        
    }
    public double[][] inisial(double[][] individu){
        String[][] Individu = new String[Pop_Size][StringLenChromosome];
        double[][] data = new double[Pop_Size][StringLenChromosome];
        
        for (int i = 0; i < Pop_Size; i++) {
            for (int j = 0; j < StringLenChromosome; j++) {
                // random code real
                inis[i][j] = BatasBawahRandD + (random.nextDouble() * (BatasAtasRandD - BatasBawahRandD));
                //Individu[i][j] = String.format("%.4f", inis[i][j]);
//                data[i][j] =BatasBawahRandD + (random.nextDouble() * (BatasAtasRandD - BatasBawahRandD));
//                System.out.print(" "+data[0][0]);
            }
        }
        return inis;
    }
//    public double[][] inisial_kromosom(double[][] individu){
//        double[][] Individu = new double[Pop_Size][StringLenChromosome];
//        ALgen a = new ALgen();
//        for (int i = 0; i < Pop_Size; i++) {
//            for (int j = 0; j < StringLenChromosome; j++) {
//                // random code real
//                double BatasBawahRandD = 0.0, BatasAtasRandD = 10.0;
//                Individu = a.inisial(individu);
//                //Individu[i][j] = Individu[i][j];
//            }
//        }
//        for (int i = 0; i < Pop_Size; i++) {
//            System.out.print("Individu ke-" + (i + 1) + " : ");
//            
//            for (int j = 0; j < StringLenChromosome; j++) {
//                System.out.print(Individu[i][j] + " ");
//                String StringTemp = "";
//                StringTemp = StringTemp + Individu[i][j];
//                
//            }
//            System.out.println("");
//        }
//        
//        return Individu;
//        
//    }
    public double[][] reproduksi_crosover(double [][] Individu){
       double Pc = Cr, Pm = Mr;
       ALgen a = new ALgen();

         // start proses crossover
        // hitung jumlah offspring crossover
        int byk_anak_crossover = (int) (Math.ceil(Cr * Pop_Size));
        //System.out.println("Banyaknya offspring crossover = (" + Cr + "*" + Pop_Size + ") = " + (Cr * Pop_Size) + " = " + byk_anak_crossover);

        // Proses crossover dilakukan sebanyak (n_crossover)
        int n_crossover = (int) Math.ceil((double) byk_anak_crossover / 2); // dibagi 2, krn 1 kali proses crossover akan menghasilkan 2 anak
        //System.out.println("Proses crossover dilakukan sebanyak = " + n_crossover);
               
        double[][] HasilCrossover = new double[byk_anak_crossover][StringLenChromosome];
        double[][] HasilCrossoverTemp = new double[2][StringLenChromosome];
        int loop_anak_crossover = 0; 
        // membuat offspring crossover sebanyak byk_anak_crossover
        for (int i = 0; i < n_crossover; i++) {
            //menampung hasil OneCutPointCrossover
            HasilCrossoverTemp = a.OneCutPoint(a.inisial(Individu));

            for (int j = 0; j < StringLenChromosome; j++) {
                HasilCrossover[loop_anak_crossover][j] = HasilCrossoverTemp[0][j];
            }

            loop_anak_crossover = loop_anak_crossover + 1;

            if ((loop_anak_crossover + 1) > byk_anak_crossover) {
                break;
            }

            for (int j = 0; j < StringLenChromosome; j++) {
                HasilCrossover[loop_anak_crossover][j] = HasilCrossoverTemp[1][j];
            }

            loop_anak_crossover = loop_anak_crossover + 1;

        }

        
        return HasilCrossover;
    }
    public double [][] reproduksi_mutasi(double[][] Individu){
        ALgen a = new ALgen();
//       String[][] Individu = new String[Pop_Size][StringLenChromosome];
       double[][] HasilCrossoverTemp = new double[2][StringLenChromosome];
        // start proses mutasi
        // hitung jumlah offspring mutasi
        int byk_anak_mutasi = (int) (Math.ceil(Mr * Pop_Size));
        //System.out.println("Banyaknya offspring mutasi = (" + Mr + "*" + Pop_Size + ") = " + (Mr * Pop_Size) + " = " + byk_anak_mutasi);

        // Proses mutasi dilakukan sebanyak (n_mutasi)
        int n_mutasi = byk_anak_mutasi;
        //System.out.println("Proses mutasi dilakukan sebanyak = " + n_mutasi);

        double[][] HasilMutasi = new double[byk_anak_mutasi][StringLenChromosome];
        double[][] HasilMutasuTemp = new double[1][StringLenChromosome];

        // membuat offspring mutasi sebanyak byk_anak_mutasi
        for (int i = 0; i < n_mutasi; i++) {
            //menampung hasil Random Mutasi
            HasilCrossoverTemp = a.RandomMutasi(a.inisial(Individu));

            for (int j = 0; j < StringLenChromosome; j++) {
                HasilMutasi[i][j] = HasilCrossoverTemp[0][j];
            }
        }

        
        return HasilMutasi;
    }
    public double[][]OneCutPoint(double[][] Individu){
     Random random = new Random();
        int StringLenChromosome = Individu[0].length;
        int Pop_Size = Individu.length;
        //System.out.println("StringLenChromosome = " + StringLenChromosome);
        double[][] Hasil = new double[2][StringLenChromosome]; // nilai 2 karena offspring satu kali crossover selalu = 2 

        // Menentukan PosisiOneCutPoint secara random
        int BatasBawahRand = 1, BatasAtasRand = StringLenChromosome - 1;
        int PosisiOneCutPoint = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
        System.out.println("\nPosisiOneCutPoint = " + PosisiOneCutPoint);

        // memilih 2 parent secara random        
        double [][] P1 = new double[1][StringLenChromosome];
        double [][] P2 = new double[1][StringLenChromosome];
        BatasBawahRand = 0;
        BatasAtasRand = Pop_Size - 1;

        boolean flag = true;
        int IndexP1 = 0, IndexP2 = 0;
        while (flag) {
            IndexP1 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
            IndexP2 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
            if (IndexP1 != IndexP2) {
                flag = false;
            }
        }

        System.out.println("Index P1 = " + (IndexP1 + 1) + ", Index P2 = " + (IndexP2 + 1));
        // menampilkan hasil induk terpilih

        System.out.print("P1 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(Individu[IndexP1][j] + " ");
        }
        System.out.println();

        System.out.print("P2 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(Individu[IndexP2][j] + " ");
        }
        System.out.println();

        //System.arraycopy(Individu[IndexP1], 0, P1[0], 0, StringLenChromosome);
        for (int i = 0; i < StringLenChromosome; i++) {
            P1[0][i] = Individu[IndexP1][i];
            Hasil[0][i] = Individu[IndexP1][i];
        }

        //System.arraycopy(Individu[IndexP2], 0, P2[0], 0, StringLenChromosome);
        for (int i = 0; i < StringLenChromosome; i++) {
            P2[0][i] = Individu[IndexP2][i];
            Hasil[1][i] = Individu[IndexP2][i];
        }

        // melakukan proses pindah silang P1 dan P2
        for (int i = PosisiOneCutPoint; i < StringLenChromosome; i++) {
            Hasil[0][i] = P2[0][i];
            Hasil[1][i] = P1[0][i];
        }

        // menampilkan hasil crossover
        for (int i = 0; i < 2; i++) {
            System.out.print("C" + (i + 1) + " = ");
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(Hasil[i][j] + " ");
            }
            System.out.println();
        }
        return Hasil;
    }

    
    public double[][] reciprocal_exchange_mutation (double [][] Individu){
        Random random = new Random();
        double [][] Hasil = new double[Individu.length][StringLenChromosome];
        
        // Menentukan PosisiPointRandom secara random
        int BatasBawahRand = 1, BatasAtasRand = StringLenChromosome;
        int PosisiPointRandom = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
        System.out.println("\nPosisiPointRandom = " + PosisiPointRandom);

        // memilih 1 parent secara random        
        double[][] P1 = new double[1][StringLenChromosome];
        double[][] P2 = new double[1][StringLenChromosome];
        BatasBawahRand = 0;
        BatasAtasRand = StringLenChromosome - 1;

        boolean flag = true;
        int IndexP1 = 0, IndexP2 = 0;
        while (flag) {
            IndexP1 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
            IndexP2 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
            if (IndexP1 != IndexP2) {
                flag = false;
            }
        }
        System.out.println("Index 1 = " + (IndexP1 + 1));
        System.out.println("Index 2 = " + (IndexP2 + 1));
        // menampilkan hasil induk terpilih

        System.out.print("P1 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f",Individu[0][j]) + " ");
        }
        System.out.println();

        //System.arraycopy(Individu[IndexP1], 0, P1[0], 0, StringLenChromosome);
        for (int i = 0; i < StringLenChromosome; i++) {
            P1[0][i] = Individu[IndexP1][i];
            Hasil[0][i] = Individu[IndexP1][i];
            
        }
        Hasil[0][IndexP1] =Hasil[0][IndexP2];
        //Hasil[0][IndexP2] =Hasil[0][IndexP1];
//        
//        for (int i = 0; i < StringLenChromosome; i++) {
//            P2[0][i] = Individu[IndexP2][i];
//            Hasil[0][i] = Individu[IndexP2][i];
//        }
//        Hasil[0][IndexP2] =1;

       
        // menampilkan hasil mutasi
        System.out.print("C1 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f",Hasil[0][j])+ " ");
        }
        System.out.println();
        return Hasil;
//        int Pop_Size = Individu.length;
//        
//        int BatasBawahRand = 1, BatasAtasRand = StringLenChromosome - 1;                                              
//       // memilih 2 parent secara random        
//        double [][] P1 = new double[1][StringLenChromosome];
//        double [][] P2 = new double[1][StringLenChromosome];
//        BatasBawahRand = 0;
//        BatasAtasRand = Pop_Size - 1;
//
//        boolean flag = true;
//        int IndexP1 = 0, IndexP2 = 0;
//        while (flag) {
//            IndexP1 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
//            IndexP2 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
//            if (IndexP1 != IndexP2) {
//                flag = false;
//            }
//        }
//
//        System.out.println("Index P1 = " + (IndexP1 + 1) + ", Index P2 = " + (IndexP2 + 1));
//        // menampilkan hasil induk terpilih
//
//        System.out.print("P1 = ");
//        for (int j = 0; j < StringLenChromosome; j++) {
//            System.out.print(Individu[IndexP1][j] + " ");
//        }
//        System.out.println();
//
//        System.out.print("P2 = ");
//        for (int j = 0; j < StringLenChromosome; j++) {
//            System.out.print(Individu[IndexP2][j] + " ");
//        }
//        System.out.println();
//
//
//        
//        return Individu;
    }
    public double[][] Crossover(double[][] Individu) {
        Random random = new Random();
        //int StringLenChromosome = Individu[0].length;
        int Pop_Size = Individu.length;
        
        //System.out.println("StringLenChromosome = " + StringLenChromosome);
        double [][] Hasil = new double[Individu.length][StringLenChromosome]; // nilai 2 karena offspring satu kali crossover selalu = 2 
        double [][] alfa = new double [5][StringLenChromosome];
        double [][] HasilCrossover = new double[Individu.length][StringLenChromosome];
        // Menentukan PosisiOneCutPoint secara random
        double BatasBawahRand = -0.25, BatasAtasRand = 0.25;
        //String.format("%.4f", BatasBawahRandD + (random.nextDouble() * (BatasAtasRandD - BatasBawahRandD)));
        System.out.print("\nNilai Alfa = ");
        for (int j = 0; j < StringLenChromosome; j++) {
        alfa[0][j] = (BatasBawahRand + (random.nextDouble()*(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand));
            System.out.print(String.format("%.4f", alfa[0][j] )+" ");
        }
        System.out.print(" ");

        // memilih 2 parent secara random        
//        String[][] P1 = new String[1][StringLenChromosome];
//        String[][] P2 = new String[1][StringLenChromosome];
        BatasBawahRand = 0;
        BatasAtasRand = Pop_Size - 1;

//        boolean flag = true;
//        int IndexP1 = 0, IndexP2 = 0;
//        while (flag) {
            for (int j=0; j<StringLenChromosome;j++){
            Hasil[0][j]= BatasBawahRandD + (random.nextDouble() * (BatasAtasRandD - BatasBawahRandD));
            Hasil[1][j]= BatasBawahRandD + (random.nextDouble() * (BatasAtasRandD - BatasBawahRandD));
//            IndexP2 = (int)(random.nextDouble()*(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand);
//            if (IndexP1 != IndexP2) {
//                flag = false;
//            }
        }

        //System.out.println("Index P1 = " + (IndexP1 + 1) + ", Index P2 = " + (IndexP2 + 1));
        // menampilkan hasil induk terpilih

        System.out.print("\nP1 = ");
        //for(int i = 0; i<Individu.length;i++){
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f", Hasil[0][j] ) + " ");
            //Hasil[IndexP1][j]= Double.valueOf(Individu[IndexP1][j]);
            //System.out.println(Hasil[0][j]);
       // }
        }
        System.out.println();
        

        System.out.print("P2 = ");
        //for(int i=0; i<Individu.length;i++){
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f", Hasil[1][j] )+ " ");
            //Hasil[1][j] = Double.parseDouble(Individu[IndexP2][j]);
        //}
        }
        System.out.println();
        
        //melakukan perkalian tiap individu dengan nilai alfa
        for(int i =0; i<2; i++){
            for (int j=0; j<StringLenChromosome; j++){
            HasilCrossover[1][j]= Hasil[1][j]+(alfa[0][j]*(Hasil[0][j]-Hasil[1][j]));
            HasilCrossover[0][j]=Hasil[0][j]+(alfa[0][j]*(Hasil[1][j]-Hasil[0][j]));
            
            //Hasil[i][j] =Hasil[1][j]+(alfa[0][j]*(Hasil[0][j]-Hasil[1][j]));
             //   System.out.print(""+Hasil[i][j]);              
                //HasilCrossover [i][j]= String.valueOf(Hasil[i][j]);
            }
        }
        
        //System.arraycopy(Individu[IndexP1], 0, P1[0], 0, StringLenChromosome);
        
        // menampilkan hasil crossover
        for (int i = 0; i <2; i++) {
            System.out.print("C" + (i + 1) + " = ");
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(String.format("%.4f",HasilCrossover[i][j])+ " ");
                
            }
            System.out.println();
        }
        return HasilCrossover;
    }
    public double[][] RandomMutasi(double[][] Individu) { // hanya 1 point yang diubah
        Random random = new Random();
        int StringLenChromosome = Individu[0].length;
        int Pop_Size = Individu.length;
        //System.out.println("StringLenChromosome = " + StringLenChromosome);
        double[][] Hasil = new double[1][StringLenChromosome];

        // Menentukan PosisiPointRandom secara random
        int BatasBawahRand = 1, BatasAtasRand = StringLenChromosome;
        int PosisiPointRandom = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;
        System.out.println("\nPosisiPointRandom = " + PosisiPointRandom);

        // memilih 1 parent secara random        
        double[][] P1 = new double[1][StringLenChromosome];
        BatasBawahRand = 0;
        BatasAtasRand = Pop_Size - 1;

        int IndexP1 = random.nextInt(BatasAtasRand - BatasBawahRand + 1) + BatasBawahRand;

        System.out.println("Index P1 = " + (IndexP1 + 1));
        // menampilkan hasil induk terpilih

        System.out.print("P1 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f",Individu[IndexP1][j]) + " ");
        }
        System.out.println();

        //System.arraycopy(Individu[IndexP1], 0, P1[0], 0, StringLenChromosome);
        for (int i = 0; i < StringLenChromosome; i++) {
            P1[0][i] = Individu[IndexP1][i];
            Hasil[0][i] = Individu[IndexP1][i];
        }
        Hasil[0][PosisiPointRandom-1] =1.4576;

        // melakukan proses random mutasi
//        if ("0".equals(Hasil[0][PosisiPointRandom - 1])) {
//            Hasil[0][PosisiPointRandom - 1] = "1";
//        } else {
//            Hasil[0][PosisiPointRandom - 1] = "0";
//        }

        // menampilkan hasil mutasi
        System.out.print("C1 = ");
        for (int j = 0; j < StringLenChromosome; j++) {
            System.out.print(String.format("%.4f",Hasil[0][j])+ " ");
        }
        System.out.println();
        return Hasil;
    }
        public double [][] gabungan(double[][] Individu){
        // membuat penampungan populasi induk dan anak (crossover + mutasi)
        ALgen a = new ALgen();
        double[][] matrik = new double[baris][kolom];
        
        int byk_anak_crossover = (int) (Math.ceil(Cr * Pop_Size));
        int n_crossover = (int) Math.ceil((double) byk_anak_crossover / 2);
        int byk_anak_mutasi = (int) (Math.ceil(Mr * Pop_Size));
//        Individu = a.inisial_kromosom(Individu);
        Pop_Size_induk_dan_anak = Pop_Size + byk_anak_crossover + byk_anak_mutasi;
        double[][] IndividuGabungan = new double[Pop_Size_induk_dan_anak][StringLenChromosome];
        double[][] HasilCrossover = new double[byk_anak_crossover][StringLenChromosome];
        System.out.println("\n===Proses Crossover===");
        //HasilCrossover = a.reproduksi_crosover(Individu);
        HasilCrossover = a.OneCutPoint(Individu);
        double[][] HasilMutasi = new double[byk_anak_mutasi][StringLenChromosome];
        System.out.println("\n===Proses Mutasi===");
        HasilMutasi = a.reciprocal_exchange_mutation(Individu);
       
        for (int i = 0; i < Pop_Size; i++) {
            for (int j = 0; j < StringLenChromosome; j++) {
                IndividuGabungan[i][j] = Individu[i][j];
            }
        }

        for (int i = Pop_Size; i < (Pop_Size + byk_anak_crossover); i++) {
            for (int j = 0; j < StringLenChromosome; j++) {
                IndividuGabungan[i][j] = HasilCrossover[i - Pop_Size][j];
            }
        }

        for (int i = (Pop_Size + byk_anak_crossover); i < Pop_Size_induk_dan_anak; i++) {
            for (int j = 0; j < StringLenChromosome; j++) {
                IndividuGabungan[i][j]= HasilMutasi[i - (Pop_Size + byk_anak_crossover)][j];
            }
        }

        // menampilkan hasil individu gabungan
        System.out.println("\nHasil Populasi induk dan anak (crossover + mutasi) :");
        for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
            System.out.print("P" + (i + 1) + " = ");
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(String.format("%.4f",IndividuGabungan[i][j]) + " ");
                //System.out.print(" "+IndividuGabungan [0][0]);
                
            }
            
            System.out.println("");
            }
//            System.out.println("\n3. Menghitung nilai fitness :");
//           for (int i =1; i<6;i++){
//               matrik[0][i] = IndividuGabungan[0][i-1];
//               System.out.println("Matrik[0]["+i+"] = "+matrik[0][i]);
//           }
        
        return IndividuGabungan;
    }
        public String[][] SeleksiElitismPrecisionFx(String[][] IndividuGabungan, int Pop_Size, int m1, int m2, int d, double Batas_MinX1, double Batas_MaxX1, double Batas_MinX2, double Batas_MaxX2) {
        ALgen gen = new ALgen();
        int StringLenChromosome = IndividuGabungan[0].length;
        int Pop_Size_induk_dan_anak = IndividuGabungan.length;
        String[][] Hasil = new String[Pop_Size][StringLenChromosome];

        int[] IndexAwalIndividuGabungan = new int[Pop_Size_induk_dan_anak];
        for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
            IndexAwalIndividuGabungan[i] = i;
        }

        //proses penghitungan f(x1,x2)
        //System.out.println("                                Chromosome           |DesimalX1|DesimalX2|   x1     |     x2      |   y=f(x1,x2)    ");
        //System.out.println("=============================================================================================================================================================================");
        double[] NilaiPrecisionFx = new double[Pop_Size_induk_dan_anak];
        int DecX1, DecX2;
        double TempX1, TempX2;
        for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
            System.out.print("Individu Gabungan ke-" + (i + 1) + " : ");
            String StringTemp = "";
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(IndividuGabungan[i][j] + "");
                StringTemp = StringTemp + IndividuGabungan[i][j];
            }


        }

        // sorting NilaiFx
        double[] SortNilaiFx = new double[Pop_Size_induk_dan_anak];
        for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
            double TempNilaiFx = NilaiPrecisionFx[i];
            int TempIndexAwalIndividuGabungan = IndexAwalIndividuGabungan[i];
            String[][] TempHasil = new String[1][StringLenChromosome];
            for (int j = (i + 1); j < Pop_Size_induk_dan_anak; j++) {
                if (NilaiPrecisionFx[j] > TempNilaiFx) {
                    TempNilaiFx = NilaiPrecisionFx[j];
                    NilaiPrecisionFx[j] = NilaiPrecisionFx[i];
                    NilaiPrecisionFx[i] = TempNilaiFx;
                    TempIndexAwalIndividuGabungan = IndexAwalIndividuGabungan[j];
                    IndexAwalIndividuGabungan[j] = IndexAwalIndividuGabungan[i];
                    IndexAwalIndividuGabungan[i] = TempIndexAwalIndividuGabungan;
                    for (int k = 0; k < StringLenChromosome; k++) {
                        TempHasil[0][k] = IndividuGabungan[j][k];
                    }

                    for (int k = 0; k < StringLenChromosome; k++) {
                        IndividuGabungan[j][k] = IndividuGabungan[i][k];
                    }

                    for (int k = 0; k < StringLenChromosome; k++) {
                        IndividuGabungan[i][k] = TempHasil[0][k];
                    }

                }
            }
        }

        // menampilkan NilaiFx hasil sorting
        /*System.out.println("Individu Gabungan yang sudah di-sorting");
         for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
         System.out.print("Individu Gabungan ke-" + (i + 1) + " : ");
         System.out.println(+NilaiFx[i]);
         }*/
        System.out.println("");
        System.out.println("Hasil Sorting untuk Individu Gabungan :");
        //proses penghitungan f(x)
        System.out.println(" Index Asal                          Chromosome           |DesimalX1|DesimalX2|   x1     |     x2      |   y=f(x1,x2)    ");
        System.out.println("=============================================================================================================================================================================");
        for (int i = 0; i < Pop_Size_induk_dan_anak; i++) {
            System.out.print((IndexAwalIndividuGabungan[i] + 1) + "   |   Individu Gabungan ke-" + (i + 1) + " : ");
            String StringTemp = "";
            for (int j = 0; j < StringLenChromosome; j++) {
                System.out.print(IndividuGabungan[i][j] + "");
                StringTemp = StringTemp + IndividuGabungan[i][j];
            }

//            DecX1 = myGAs.MyBin2Dec(StringTemp.substring(0, m1));
//            DecX2 = myGAs.MyBin2Dec(StringTemp.substring(m1, StringLenChromosome));
//            TempX1 = myGAs.MyDecodeToNormalizeDec(Batas_MinX1, Batas_MaxX1, d, DecX1, m1);
//            TempX2 = myGAs.MyDecodeToNormalizeDec(Batas_MinX2, Batas_MaxX2, d, DecX2, m2);
//
//            NilaiPrecisionFx[i] = myGAs.MyFxPrecision(TempX1, TempX2);
//
//            System.out.print("  |   " + DecX1);
//            System.out.print("  |   " + DecX2);
//            System.out.print("  |   " + String.format("%.4f", TempX1));
//            System.out.print("  |   " + String.format("%.4f", TempX2));
//            System.out.print("  |   " + String.format("%.4f", NilaiPrecisionFx[i]));
//            System.out.println("");
        }

        // Menampung Hasing IndividuGabungan ter-sorting sebanyak Pop_Size
        for (int i = 0; i < Pop_Size; i++) {
            for (int j = 0; j < StringLenChromosome; j++) {
                Hasil[i][j] = IndividuGabungan[i][j];
            }
        }

        return Hasil;
    }
    
    public void setCr(double cr){
        this.cr = cr;
    }
    public double getCr (double cr){
        this.cr = cr;
        return cr;
        
    }
    
    
  
    
    
    
    
}
