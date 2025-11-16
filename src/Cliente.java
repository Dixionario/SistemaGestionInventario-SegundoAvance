public class Cliente {
    private String nombre; // nombre del cliente
    private int prioridad; // 1, 2 o 3
    private ListaProductos carrito;

    public Cliente(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.carrito = new ListaProductos();
    }

    public String getNombre() {
        return nombre;
    }
    public int getPrioridad() {
        return prioridad;
    }
    public ListaProductos getCarrito() {
        return carrito;
    }

    @Override
    public String toString() {
        return "Cliente{nombre='" + nombre + "', prioridad=" + prioridad + "}";
    }
}