/*
 */
package modelo;

/**
 *
 * @author Escoz
 */
class Ejemplar {

    // --------------- ATRIBUTOS --------------- 
    private boolean prestado;
    private String codigo;

    // --------------- CONSTRUCTORES --------------- 
    public Ejemplar() {
        setPrestado(false);
    }

    public Ejemplar(boolean prestado) {
        this.prestado = prestado;
    }

    // --------------- GETTER & SETTERS --------------- 
    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

}
