import Entidades.Hashtag;
import Entidades.Piloto;
import Entidades.Tweet;
import uy.edu.um.prog2.adt.linkedlist.Exceptions.IlegalIndexException;
import uy.edu.um.prog2.adt.linkedlist.MyLinkedList;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    Tweet[] tweets ;
    Piloto[] pilotos;
   public Main(){
    try{
        this.pilotos=LeerCsv.leerPilotos();
        this.tweets=LeerCsv.leerTweets();
    }catch (IOException e) {
        throw new RuntimeException(e);
    }
   }
    public void topPilotosActivos(String mes, String año){
       for(int i =1; i< tweets.length;i++){
           if(tweets[i]==null) break;
           if(tweets[i].getFecha().split("-")[0].contains(año)&&(tweets[i].getFecha().split("-")[1].contains(mes))){
               for(int j=0;j< pilotos.length;j++){
                    String nombrePiloto= pilotos[j].getNombre().split(" ")[0];
                    String apellidoPiloto= pilotos[j].getNombre().split(" ")[1];
                    if(tweets[i].getContent().contains(pilotos[j].getNombre())|| tweets[i].getContent().contains(nombrePiloto)||tweets[i].getContent().contains(apellidoPiloto)){
                        pilotos[j].setContador(pilotos[j].getContador()+1);
                }}
           }
       }

        boolean cambiados;
        for(int i=0;i< pilotos.length-1;i++){
            cambiados=false;
            for (int j=0;j< pilotos.length-i-1;j++){
                int actual=pilotos[j].getContador();
                int siguiente=pilotos[j+1].getContador();
                if(actual<siguiente){
                    Piloto aux = pilotos[j];
                    pilotos[j] = pilotos[j+1];
                    pilotos[j+1]=aux;
                    cambiados=true;
                }
            }if(!cambiados)break;}
        for (int i=0;i<10;i++){
            System.out.println((i+1)+"Posicion----> "+pilotos[i].getNombre()+"  cantidad: "+pilotos[i].getContador());
        }}
    public void cantidadHashtagsParaUnDia(String dia) throws IlegalIndexException {
        MyList<Hashtag> listaDeHashtags= new MyLinkedList<>();
        for(int i=1;i< tweets.length;i++){
            if(tweets[i]==null)break;
            if(tweets[i].getFecha().contains(dia)){
                String[] hashes = tweets[i].getHashtags().split("'");
                for(int j=0; j< hashes.length;j++){
                    if((j%2)!=0){
                        Hashtag nuevoHashtag=new Hashtag(0,hashes[j]);
                        if (!(listaDeHashtags.contains(nuevoHashtag))){
                            listaDeHashtags.add(nuevoHashtag);
                        }
            }
        }
    }}
        System.out.println(listaDeHashtags.size());}
    public void cantidadDeTweetsConPalabra(String palabra){
       int contador=0;
       String fraseMinuscula =palabra.toLowerCase();
        for(int i=1;i< tweets.length;i++){
            if(tweets[i]==null)break;
            if(tweets[i].getContent().contains(palabra)&&tweets[i].getContent().contains(fraseMinuscula)) contador++;
        }
        System.out.println(contador);}
    public void hashTagMasUsadoEnUnDia(String dia) throws IlegalIndexException {
       MyList<Hashtag> listaDeHashtags = new MyLinkedList<>();
       Hashtag masUsado = new Hashtag(0,"ASdasd");
       for(int i=1;i< tweets.length;i++){
           if(tweets[i]==null)break;
           if(tweets[i].getFecha().contains(dia)){
               String hashes[] = tweets[i].getHashtags().split("'");
               for(int j=0;j<hashes.length;j++){
                   if((j%2)!=0){
                       if(hashes[j].contains("F1")||hashes[j].contains("f1")) continue;
                       Hashtag nuevoHashtag= new Hashtag(1,hashes[j]);
                       if(!(listaDeHashtags.contains(nuevoHashtag))) listaDeHashtags.add(nuevoHashtag);
                       else{
                            for (int y=0;y<listaDeHashtags.size();y++){
                                if (listaDeHashtags.get(y).equals(nuevoHashtag)) listaDeHashtags.get(y).setContador(listaDeHashtags.get(y).getContador()+1);
                            }
                       }
                   }
               }
           }
       }
    for(int i=0;i<listaDeHashtags.size();i++){
        if(listaDeHashtags.get(i).getContador()>masUsado.getContador()) masUsado=listaDeHashtags.get(i);
    }
        System.out.println("Hashtag mas usado: "+ masUsado.getText()+"  Cantidad de veces:  "+masUsado.getContador());
   }

public static void main(String [] args) throws IlegalIndexException {
    Main prueba =new Main();
    //prueba.topPilotosActivos("07","2021");
    //prueba.cantidadHashtagsParaUnDia("2021-11-07");
    prueba.cantidadDeTweetsConPalabra("race");
    //prueba.hashTagMasUsadoEnUnDia("2021-11-07");
}

}