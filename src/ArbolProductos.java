public class ArbolProductos {
    private Producto raiz;

    public ArbolProductos() {
        this.raiz = null;
    }

    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private Producto insertarRec(Producto nodo, Producto producto) {
        if (nodo == null) {
            return producto;
        }
        int cmp = nodo.compararPorNombre(producto.getNombre());
        if (cmp > 0) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), producto));
        } else if (cmp < 0) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), producto));
        } else {
            // si tiene el mismo nombre se suma la cantidad
            nodo.setCantidad(nodo.getCantidad() + producto.getCantidad());
            System.out.println("ℹProducto '" + producto.getNombre() + "' actualizado (cantidad = " + nodo.getCantidad() + ")");
        }
        return nodo;
    }

    public Producto buscar(String nombre) {
        Producto encontrado = buscarRec(raiz, nombre);
        if (encontrado != null) {
            // Devolver copia para el carrito
            return new Producto(
                    encontrado.getId(),
                    encontrado.getNombre(),
                    encontrado.getPrecio(),
                    encontrado.getCategoria(),
                    encontrado.getFechaVencimiento(),
                    encontrado.getCantidad()
            );
        }
        return null;
    }

    private Producto buscarRec(Producto nodo, String nombre) {
        if (nodo == null) return null;
        int cmp = nodo.compararPorNombre(nombre);
        if (cmp == 0) return nodo;
        if (cmp > 0) return buscarRec(nodo.getIzquierdo(), nombre);
        return buscarRec(nodo.getDerecho(), nombre);
    }

    public void mostrarInventario() {
        if (raiz == null) {
            System.out.println("Inventario vacío.");
        } else {
            System.out.println("\nINVENTARIO (orden alfabético):");
            inorden(raiz);
            System.out.println();
        }
    }

    private void inorden(Producto nodo) {
        if (nodo != null) {
            inorden(nodo.getIzquierdo());
            System.out.println("- " + nodo);
            inorden(nodo.getDerecho());
        }
    }
}