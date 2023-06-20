package Entidades;

public class Usuario {
private String nombreUsuario;
private Boolean verificado;
private Integer cantidadTweets;
private Integer cantidadFavoritos;



    public Usuario(String nombreUsuario, Boolean verificado, Integer cantidadTweets) {
        this.nombreUsuario = nombreUsuario;
        this.verificado = verificado;
        this.cantidadTweets = cantidadTweets;
        this.cantidadFavoritos = 0;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Boolean getVerificado() {
        return verificado;
    }

    public void setVerificado(Boolean verificado) {
        this.verificado = verificado;
    }

    public Integer getCantidadTweets() {
        return cantidadTweets;
    }

    public void setCantidadTweets(Integer cantidadTweets) {
        this.cantidadTweets = cantidadTweets;
    }

    public Integer getCantidadFavoritos() {
        return cantidadFavoritos;
    }

    public void setCantidadFavoritos(String cantidadFavoritos) {

        double numeroDeLikes;

        if (cantidadFavoritos.contains(".")) {
            numeroDeLikes = Double.parseDouble(cantidadFavoritos);
        }
        else {
            numeroDeLikes = Integer.parseInt(cantidadFavoritos);
        }
        this.cantidadFavoritos = (int) numeroDeLikes;

    }
}

