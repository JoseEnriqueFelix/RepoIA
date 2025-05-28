public class ArbolBinBusq {
    private Nodo root;

    public boolean vacio(){
        return root == null;
    }

    public Nodo buscarNodo(int dato){
        if (root == null || root.getValor() == dato)
            return root;
        if (dato < root.getValor())
            return buscarNodo(dato, root.getIzquierda());
        else
            return buscarNodo(dato, root.getDerecha());
    }

    public Nodo buscarNodo(int dato, Nodo root){
        if (root == null || root.getValor() == dato)
            return root;
        if (dato < root.getValor())
            return buscarNodo(dato, root.getIzquierda());
        else
            return buscarNodo(dato, root.getDerecha());
    }
}