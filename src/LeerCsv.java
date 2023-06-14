import Entidades.Piloto;
import Entidades.Tweet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeerCsv {
    public static Piloto[] leerPilotos() throws IOException {
        Piloto[] listaPilotos = new Piloto[20];
        String pathDrivers="C:\\Users\\Matias\\Desktop\\Facultad\\Programacion2\\Hash\\grupo11-p2-obligatorio\\src\\DATASET\\drivers.txt";
        BufferedReader br2 = new BufferedReader(new FileReader(pathDrivers));
        String linea2="";
        int contador=0;
        while((linea2=br2.readLine())!=null){
            Piloto nuevoPiloto= new Piloto(linea2,0);
            listaPilotos[contador]=nuevoPiloto;
            contador++;
        }
        return listaPilotos;
    }
    public static Tweet[] leerTweets() throws IOException {
        Tweet[] listaDeTweets = new Tweet[700000];
        String pathDataset ="C:\\Users\\Matias\\Desktop\\Facultad\\Programacion2\\Hash\\grupo11-p2-tads\\src\\DATASET\\f1_dataset_test.csv";
        String linea="";
        int tweets_contador=0;
        BufferedReader br = new BufferedReader(new FileReader(pathDataset));
        while((linea=br.readLine())!=null){
            if (tweets_contador == 0) {
                tweets_contador++;
                continue;
        }
            for (int posibilidad = 0; posibilidad < 280; posibilidad++) { //porque el tama;o maximo de un tweet es 280
                if ((linea.split(",")[linea.split(",").length - 1].equals("True") || linea.split(",")[linea.split(",").length - 1].equals("False"))) {
                    break;
            }   else {
                    linea += br.readLine();
                if ((linea.split(",")[linea.split(",").length - 1].equals("True") || linea.split(",")[linea.split(",").length - 1].equals("False"))) {
                    break;
                }
            }

        }
            String[] tweet = linea.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            if(tweet.length!=14)continue;
            Tweet nuevoTweet = new Tweet(Long.parseLong(tweet[0]), tweet[10], tweet[12], Boolean.parseBoolean(tweet[13]),tweet[11],tweet[9]);
            listaDeTweets[tweets_contador]=nuevoTweet;
            tweets_contador++;

    }
        return listaDeTweets;}
}
