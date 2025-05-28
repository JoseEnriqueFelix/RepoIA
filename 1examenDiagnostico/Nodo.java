public class Nodo {
    private Nodo izquierda;
    private Nodo derecha;
    private int valor;

    public Nodo(int valor){
        this(null, null, valor); 
    }

    public Nodo(Nodo izquierda, Nodo derecha, int valor){
        this.izquierda = izquierda;
        this.derecha = derecha;
        this.valor = valor;
    }

    public Nodo getIzquierda() {
        return izquierda;
    }

    public Nodo getDerecha() {
        return derecha;
    }

    public int getValor() {
        return valor;
    }
    
}
