package Entidades;

import java.util.Objects;

public class Hashtag {
    private int contador;
    private String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hashtag hashtag = (Hashtag) o;
        return Objects.equals(text, hashtag.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    public Hashtag(int contador, String text) {
        this.contador = contador;
        this.text = text;
    }

    public int getContador() {
        return contador;
    }

    public String getText() {
        return text;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void setText(String text) {
        this.text = text;
    }

}
