package rekomendasi_kuliner;

/**
 *
 * @author user
 */
public class SkorParameter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
    public int getSkorHarga(int harga, int hargaP){
        int skor=0;
        if(hargaP == harga){
            skor = 20;
        }
        else{
            skor = 20+Math.abs(harga-hargaP);
        }
        return skor;
    }
        
    public int getSkorFasilitas(int fasilitas, int fasilitasP){
        int skor=0;
        if(fasilitasP == fasilitas){
            skor = 25;
        }
        else if(fasilitasP>fasilitas){
            skor = 20-Math.abs(fasilitas-fasilitasP);
        }
        else{
            skor = 20;
        }
        return skor;
    }
    
    public int getSkorJarak(int jarak, int jarakP){
        int skor=0;
        if(jarakP == jarak){
            skor = 20;
        }
        else{
            skor = 20+Math.abs(jarak-jarakP);
        }
        return skor;
    }
    
    public int getSkorSuasana(int suasana, int suasanaP){
        int skor=0;
        if(suasanaP == suasana){
            skor = 25;
        }
        else if(suasanaP>suasana){
            skor = 20-Math.abs(suasana-suasanaP);
        }
        else{
            skor = 20;
        }
        return skor;
    }
}
