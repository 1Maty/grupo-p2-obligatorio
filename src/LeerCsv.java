import Entidades.Piloto;
import Entidades.Tweet;
import Entidades.Usuario;
import uy.edu.um.prog2.adt.ArrayList.ArrayList;
import uy.edu.um.prog2.adt.Hash.Exceptions.HashLleno;
import uy.edu.um.prog2.adt.Hash.MyHash;
import uy.edu.um.prog2.adt.Hash.MyHashImpl;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class LeerCsv {
    public static Piloto[] leerPilotos() throws IOException {
        Piloto[] listaPilotos = new Piloto[20];
        String pathDrivers="DATASET/drivers.txt";
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
    public static CSVReaderReturn leerTweets() throws IOException, HashLleno {
        Tweet[] listaDeTweets = new Tweet[633000];
        MyList<String> nombresUsuarios = new MyLinkedList<>();
        MyList<Usuario> favoritosUsuarios = new MyLinkedList<>();
        MyHash<String,Usuario> usuarios = new MyHashImpl<>(140000);
        String pathDataset ="DATASET/f1_dataset.csv";
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
            Usuario usuarioTweet = new Usuario(tweet[1],Boolean.parseBoolean(tweet[8]),1);
            if(tweet[7].contains("-") || tweet[7].contains(":") || tweet[7]==null){usuarioTweet.setCantidadFavoritos("0");}
            else {usuarioTweet.setCantidadFavoritos(tweet[7]);}
            Tweet nuevoTweet = new Tweet(Long.parseLong(tweet[0]), tweet[10], tweet[12], Boolean.parseBoolean(tweet[13]),tweet[11],tweet[9],usuarioTweet);
            listaDeTweets[tweets_contador]=nuevoTweet;
            tweets_contador++;
            if(usuarios.get(nuevoTweet.getUsuarioTweet().getNombreUsuario())==null){
                    usuarios.add(nuevoTweet.getUsuarioTweet().getNombreUsuario(),nuevoTweet.getUsuarioTweet());
                    nombresUsuarios.add(nuevoTweet.getUsuarioTweet().getNombreUsuario());
                    favoritosUsuarios.add(nuevoTweet.getUsuarioTweet());

            }
            else{
                usuarios.get(nuevoTweet.getUsuarioTweet().getNombreUsuario()).getValue().setCantidadTweets(usuarios.get(nuevoTweet.getUsuarioTweet().getNombreUsuario()).getValue().getCantidadTweets()+1);
            }
            }
        return new CSVReaderReturn(listaDeTweets,usuarios,nombresUsuarios,favoritosUsuarios);
    }

    }





