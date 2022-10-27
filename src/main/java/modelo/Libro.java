/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import excepciones.SinEjemplaresException;

/**
 *
 * @author Escoz
 */
public class Libro {

    // --------------- ATRIBUTOS --------------- 
    private String isbn;
    private String titulo;
    private String autor;
    private String anio_publicacion;
    private int n_ejemplares;

    // --------------- CONSTRUCTORES ---------------
    public Libro(String isbn, String titulo, String autor, String anio_publicacion, int n_ejemplares) {
        setIsbn(isbn);
        setTitulo(titulo);
        setAutor(autor);
        setAnio_publicacion(anio_publicacion);
        setN_ejemplares(n_ejemplares);

    }

    public Libro(String isbn) {
        setIsbn(isbn);
    }

    public Libro() {
        n_ejemplares = 0;
        anio_publicacion = "2022";
    }

    // --------------- METODOS ---------------
    @Override
    public String toString() {
        return String.format("%s - %s", isbn, titulo);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getIsbn().equalsIgnoreCase(((Libro) obj).getIsbn());
    }

    public void aniadeEjemplar(int n) {
        n_ejemplares += n;
    }

    public void eliminaEjemplar(int n) throws SinEjemplaresException {
        if ((n_ejemplares - n) <= -1) {
            throw new SinEjemplaresException(titulo);
        } else {
            n_ejemplares -= n;
        }
    }

    // --------------- GETTERS & SETTERS ---------------
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getAnio_publicacion() {
        return anio_publicacion;
    }

    public void setAnio_publicacion(String anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    public int getN_ejemplares() {
        return n_ejemplares;
    }

    public void setN_ejemplares(int n_ejemplares) {
        this.n_ejemplares = n_ejemplares;
    }

}
