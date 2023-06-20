import Entidades.Tweet;
import Entidades.Usuario;
import uy.edu.um.prog2.adt.Hash.MyHash;
import uy.edu.um.prog2.adt.linkedlist.MyList;

public class CSVReaderReturn {
    Tweet[] tweets;
    MyHash<String, Usuario> usuarios;
    MyList<String> nombresUsuarios;
    MyList<Usuario> favoritosUsuarios;

    public CSVReaderReturn(Tweet[] tweets, MyHash<String, Usuario> usuarios, MyList<String> nombresUsuarios, MyList<Usuario> favoritosUsuarios) {
        this.tweets = tweets;
        this.usuarios = usuarios;
        this.nombresUsuarios = nombresUsuarios;
        this.favoritosUsuarios = favoritosUsuarios;
    }
    public void clear(){
        this.tweets=null;
        this.usuarios=null;
    }

    public Tweet[] getTweets() {
        return tweets;
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets = tweets;
    }

    public MyHash<String, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(MyHash<String, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public MyList<String> getNombresUsuarios() {
        return nombresUsuarios;
    }

    public void setNombresUsuarios(MyList<String> nombresUsuarios) {
        this.nombresUsuarios = nombresUsuarios;
    }
    public MyList<Usuario> getFavoritosUsuarios(){return favoritosUsuarios;}
}
