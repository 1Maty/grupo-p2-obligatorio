package Entidades;

public class Tweet {
    public Usuario getUsuarioTweet() {
        return usuarioTweet;
    }

    public void setUsuarioTweet(Usuario usuarioTweet) {
        this.usuarioTweet = usuarioTweet;
    }

    private Usuario usuarioTweet;
    private long id;
    private String content;
    private String source;
    private boolean isRetweet;

    public Tweet(long id, String content, String source, boolean isRetweet, String hashtags, String fecha,Usuario usuarioTweet) {
        this.id = id;
        this.content = content;
        this.source = source;
        this.isRetweet = isRetweet;
        this.hashtags = hashtags;
        this.fecha = fecha;
        this.usuarioTweet = usuarioTweet;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    private String hashtags;


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    private String fecha;



    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSource() {
        return source;
    }

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }
}
