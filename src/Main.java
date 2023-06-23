import Entidades.Hashtag;
import Entidades.Piloto;
import Entidades.Tweet;
import Entidades.Usuario;
import uy.edu.um.prog2.adt.Hash.Exceptions.HashLleno;
import uy.edu.um.prog2.adt.Hash.MyHash;
import uy.edu.um.prog2.adt.Hash.MyHashImpl;
import uy.edu.um.prog2.adt.linkedlist.Exceptions.IlegalIndexException;
import uy.edu.um.prog2.adt.linkedlist.MyList;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    MyHash<String,Usuario> usuarios;
    MyList<Usuario> favoritosUsuarios;
    MyList<String> nombresUsuarios;
    Tweet[] tweets ;
    Piloto[] pilotos;

   public Main(){
    try {
        CSVReaderReturn cositas = LeerCsv.leerTweets();
        this.pilotos = LeerCsv.leerPilotos();
        this.tweets = cositas.getTweets();
        this.usuarios= cositas.getUsuarios();
        this.nombresUsuarios= cositas.getNombresUsuarios();
        this.favoritosUsuarios= cositas.getFavoritosUsuarios();

    } catch (IOException e) {
        throw new RuntimeException(e);
    } catch (HashLleno e) {
        throw new RuntimeException(e);
    }
       }
    public void topPilotosActivos() throws HashLleno {
        System.out.print("Ingresa el anio:");
        String anio= input.nextLine();
        System.out.print("Ingresa el mes:");
        String mes = input.nextLine();
        long startTime = System.nanoTime();
        Piloto[] arrayPilotos = pilotos;
       for(Tweet tweetTemp : tweets){
           if(tweetTemp!=null) {
               if(tweetTemp.getFecha().split("-")[0].contains(anio) && (tweetTemp.getFecha().split("-")[1].contains(mes))) {
                   String enMinuscula = tweetTemp.getContent().toLowerCase();
                   for (Piloto pilotos : arrayPilotos) {
                       String nombrePiloto = pilotos.getNombre().split(" ")[0];
                       String apellidoPiloto = pilotos.getNombre().split(" ")[1];
                       if (pilotos.getNombre().split(" ").length > 2)
                           apellidoPiloto = apellidoPiloto + " " + pilotos.getNombre().split(" ")[2];//por el caso de "Nyck de vryes"
                       String apellidoPilotoMinuscula = apellidoPiloto.toLowerCase();
                       String nombrePilotoMinuscula = nombrePiloto.toLowerCase();
                       if (enMinuscula.contains(apellidoPilotoMinuscula) || enMinuscula.contains(nombrePilotoMinuscula)) {
                           pilotos.setContador(1);
                       }
                   }
               }
           }
       }
       int low = 0;
       int high = arrayPilotos.length -1;
       quicksort(arrayPilotos, low, high);
       for (int i=0;i<10;i++){
            System.out.println((i+1)+"Posicion----> "+arrayPilotos[i].getNombre()+"  cantidad: "+arrayPilotos[i].getContador());
       }
       long endTime = System.nanoTime();
       double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
       System.out.println();
       double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
       System.out.println("La función tardó " + roundedDuration + " segundos.");
       System.out.println();
   }
    public void cantidadHashtagsParaUnDia() throws IlegalIndexException, HashLleno {
        System.out.print("Ingresa el dia:");
        String dia=input.nextLine();
        long startTime = System.nanoTime();
        MyHash<String,Integer> listaHashtags = new MyHashImpl<>(10000);
        int cantidadHashtags=0;
        for(int i=1;i< tweets.length;i++){
            if(tweets[i]==null)break;
            if(tweets[i].getFecha().contains(dia)){
                String[] hashes = tweets[i].getHashtags().split("'");
                for(int j=0; j< hashes.length;j++){
                    if((j%2)!=0){
                        if(listaHashtags.get(hashes[j])==null){
                            listaHashtags.add(hashes[j],1);
                            cantidadHashtags++;
                        }
                    }
                }
            }
        }
        System.out.println("La cantidad de hashtags en el dia: "+cantidadHashtags);
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println();
        double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
        System.out.println("La función tardó " + roundedDuration + " segundos.");
        System.out.println();}
    public void cantidadDeTweetsConPalabra(){
        System.out.print("Ingresa la palabra:");
        String palabra = input.nextLine();
        long startTime = System.nanoTime();
        int contador=0;
        String fraseMinuscula =palabra.toLowerCase();
        for(int i=1;i< tweets.length;i++){
            if(tweets[i]==null)break;
            if(tweets[i].getContent().contains(palabra)&&tweets[i].getContent().contains(fraseMinuscula)) contador++;
        }
        System.out.println("Cantidad de tweets con la palabra:" +contador);
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println();
        double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
        System.out.println("La función tardó " + roundedDuration + " segundos.");
        System.out.println();}
    public void hashTagMasUsadoEnUnDia() throws IlegalIndexException, HashLleno {
       System.out.print("Ingresa el dia:");
       String dia=input.nextLine();
       long startTime = System.nanoTime();
       MyHash<String,Hashtag> listaDeHashtags = new MyHashImpl<>(10000);
       Hashtag masUsado = new Hashtag(0,"aux");
       for(int i=1;i< tweets.length;i++){
           if(tweets[i]==null)break;
           if(tweets[i].getFecha().contains(dia)){
               String hashes[] = tweets[i].getHashtags().split("'");
               for(int j=0;j<hashes.length;j++){
                   if((j%2)!=0){
                       if(hashes[j].contains("F1")||hashes[j].contains("f1")) continue;
                       Hashtag nuevoHashtag= new Hashtag(1,hashes[j]);
                       if(listaDeHashtags.get(hashes[j])==null){
                           listaDeHashtags.add(hashes[j],nuevoHashtag);
                           continue;}
                       listaDeHashtags.get(hashes[j]).getValue().setContador(listaDeHashtags.get(hashes[j]).getValue().getContador()+1);
                       if(listaDeHashtags.get(hashes[j]).getValue().getContador()>masUsado.getContador()) masUsado=listaDeHashtags.get(hashes[j]).getValue();
                   }
               }
           }
       }


        System.out.println("Hashtag mas usado:"+ masUsado.getText()+"  Cantidad de veces:"+masUsado.getContador());
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println();
        double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
        System.out.println("La función tardó " + roundedDuration + " segundos.");
        System.out.println();
   }
   public void usuariosConMasTweets() throws HashLleno, IlegalIndexException {
       long startTime = System.nanoTime();

       int tamanioUsuarios = nombresUsuarios.size();
       Usuario[] usuariosAOrdenar = new Usuario[tamanioUsuarios];
       for(int i=0;i< nombresUsuarios.size();i++){
           usuariosAOrdenar[i]=usuarios.get(nombresUsuarios.get(i)).getValue();
       }
       MergeSort(usuariosAOrdenar,"CantidadDeTweets");
       for(int i=0;i<15;i++){
           System.out.println(i+1+"--> "+usuariosAOrdenar[tamanioUsuarios-1-i].getNombreUsuario()+" Cantidad de Tweets: "+usuariosAOrdenar[tamanioUsuarios-1-i].getCantidadTweets()+" Verficado: "+usuariosAOrdenar[tamanioUsuarios-1-i].getVerificado());
       }
       long endTime = System.nanoTime();
       double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
       System.out.println();
       double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
       System.out.println("La función tardó " + roundedDuration + " segundos.");
       System.out.println();
   }

    public void top7CuentasConMasFavoritos() throws IlegalIndexException {
        long startTime = System.nanoTime();

        int tamanioUsuarios = favoritosUsuarios.size();
        Usuario[] favoritosAOrdenar = new Usuario[tamanioUsuarios];
        for(int i=0;i< favoritosUsuarios.size() -1;i++){
            favoritosAOrdenar[i]=favoritosUsuarios.get(i);
        }
        MergeSort(favoritosAOrdenar,"Favoritos");
        for(int a = 0 ; a<7 ; a++){
            System.out.println(favoritosAOrdenar[a].getNombreUsuario()+"- cantidad de favoritos - "+favoritosAOrdenar[a].getCantidadFavoritos());
        }
        long endTime = System.nanoTime();
        double durationInSeconds = (endTime - startTime) / 1_000_000_000.0;
        System.out.println();
        double roundedDuration = Math.round(durationInSeconds * 10.0) / 10.0;
        System.out.println("La función tardó " + roundedDuration + " segundos.");
        System.out.println();
    }


    public  static void  MergeSort(Usuario[] listaUsuarios,String parametro){
       int largo=listaUsuarios.length;
       if(largo<=1) return;
       int medio=largo/2;
       Usuario[] arrayDeLaIzquierda = new Usuario[medio];
       Usuario[] arrayDeLaDerecha = new Usuario[largo-medio];

       int i=0;//array de la izquierda
             int j = 0; // array de la derecha
            for(;i<largo;i++){
                if(i<medio){
                    arrayDeLaIzquierda[i]=listaUsuarios[i];
                }
                else{arrayDeLaDerecha[j]=listaUsuarios[i];
                j++;}
            }
            MergeSort(arrayDeLaIzquierda,parametro);
            MergeSort(arrayDeLaDerecha,parametro);
            if (parametro.contains("CantidadDeTweets")) {
                mergeCantidadDeTweets(arrayDeLaIzquierda, arrayDeLaDerecha, listaUsuarios);
            } else if (parametro.contains("Favoritos")) {
                mergeFavoritos(arrayDeLaIzquierda,arrayDeLaDerecha,listaUsuarios);
            }
    }
        private static void mergeCantidadDeTweets(Usuario[] arrayDeLaIzquierda,Usuario[] arrayDeLaDerecha,Usuario[] listaDeUsuarios){
            int tamanioIzquierda=listaDeUsuarios.length/2;
            int tamanioDerecha= listaDeUsuarios.length-tamanioIzquierda;
            int i=0;
            int l=0;
            int r=0;

            while(l<tamanioIzquierda && r<tamanioDerecha){
                if(arrayDeLaIzquierda[l].getCantidadTweets()<arrayDeLaDerecha[r].getCantidadTweets()){
                    listaDeUsuarios[i]=arrayDeLaIzquierda[l];
                    i++;
                    l++;
                }
                else{
                    listaDeUsuarios[i]=arrayDeLaDerecha[r];
                    i++;
                    r++;
                }
            }
            while(l<tamanioIzquierda){
                listaDeUsuarios[i] = arrayDeLaIzquierda[l];
                i++;
                l++;
            }
            while(r<tamanioDerecha){
                listaDeUsuarios[i] = arrayDeLaDerecha[r];
                i++;
                r++;
            }
        }

    private static void mergeFavoritos(Usuario[] arrayDeLaIzquierda, Usuario[] arrayDeLaDerecha, Usuario[] listaDeUsuarios) {
        int tamanioIzquierda = arrayDeLaIzquierda.length;
        int tamanioDerecha = arrayDeLaDerecha.length;
        int i = 0;
        int l = 0;
        int r = 0;

        while (l < tamanioIzquierda && r < tamanioDerecha) {
            if (arrayDeLaIzquierda[l] != null && arrayDeLaDerecha[r] != null) {
                if (arrayDeLaIzquierda[l].getCantidadFavoritos() > arrayDeLaDerecha[r].getCantidadFavoritos()) {
                    listaDeUsuarios[i] = arrayDeLaIzquierda[l];
                    i++;
                    l++;
                } else {
                    listaDeUsuarios[i] = arrayDeLaDerecha[r];
                    i++;
                    r++;
                }
            } else if (arrayDeLaIzquierda[l] != null) {
                listaDeUsuarios[i] = arrayDeLaIzquierda[l];
                i++;
                l++;
            } else if (arrayDeLaDerecha[r] != null) {
                listaDeUsuarios[i] = arrayDeLaDerecha[r];
                i++;
                r++;
            }
        }

        while (l < tamanioIzquierda) {
            listaDeUsuarios[i] = arrayDeLaIzquierda[l];
            i++;
            l++;
        }

        while (r < tamanioDerecha) {
            listaDeUsuarios[i] = arrayDeLaDerecha[r];
            i++;
            r++;
        }
    }
    public static void quicksort(Piloto[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quicksort(array, low, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(Piloto[] array, int low, int high) {
        int pivot = array[high].getContador();
        int i = low;

        for (int j = low; j < high; j++) {
            if (array[j].getContador() > pivot) {
                swap(array, i, j);
                i++;
            }
        }

        swap(array, i, high);
        return i;
    }

    private static void swap(Piloto[] array, int i, int j) {
        Piloto temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String [] args) throws IlegalIndexException, HashLleno {
    Scanner input = new Scanner(System.in);
    Main prueba =new Main();
    while (true){
    System.out.println("Ingrese una opción:");
    System.out.println("1. Listar los 10 pilotos más mencionados");
    System.out.println("2. Top 15 usuarios con más tweets");
    System.out.println("3. Cantidad de hashtags distintos para un día dado");
    System.out.println("4. Hashtag más usado para un día dado");
    System.out.println("5. Top 7 cuentas con más favoritos");
    System.out.println("6. Cantidad de tweets con una palabra o frase específica");
    System.out.println("0. Salir");
    System.out.print("Opcion:");
    int opcion = input.nextInt();
    if(opcion==0) break;
    if(opcion==1) {prueba.topPilotosActivos();}
    if(opcion==2) {prueba.usuariosConMasTweets();}
    if(opcion==5) {prueba.top7CuentasConMasFavoritos();}
    if(opcion==6) {prueba.cantidadDeTweetsConPalabra();}
    if(opcion==3) {prueba.cantidadHashtagsParaUnDia();}
    if(opcion==4) {prueba.hashTagMasUsadoEnUnDia();}
    }
   }
}