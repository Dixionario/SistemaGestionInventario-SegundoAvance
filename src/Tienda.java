import java.util.Scanner;

public class Tienda {
    private ArbolProductos inventario;
    private ColaClientes cola;

    public Tienda(int capacidadCola) {
        this.inventario = new ArbolProductos();
        this.cola = new ColaClientes(capacidadCola);
    }

    public void añadirProducto(Producto p) {
        inventario.insertar(p);
    }

    public void registrarCliente(Scanner sc) {
        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) {
            System.out.println("Nombre inválido.");
            return;
        }

        System.out.print("Prioridad (1.básico, 2.afiliado, 3.premium): ");
        int prioridad;
        try {
            prioridad = Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            System.out.println("Entrada inválida. Se asignará prioridad 1.");
            prioridad = 1;
        }
        if (prioridad < 1) prioridad = 1;
        if (prioridad > 3) prioridad = 3;

        Cliente cliente = new Cliente(nombre, prioridad);
        System.out.println("Armando carrito para " + nombre + "...");
        inventario.mostrarInventario();

        while (true) {
            System.out.print("Buscar producto (o 'fin' para terminar): ");
            String busqueda = sc.nextLine().trim();
            if (busqueda.equalsIgnoreCase("fin")) break;

            Producto prod = inventario.buscar(busqueda);
            if (prod == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad deseada (máx. " + prod.getCantidad() + "): ");
            int cant;
            try {
                cant = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Cantidad inválida.");
                continue;
            }

            if (cant <= 0) {
                System.out.println("La cantidad debe ser mayor 0.");
                continue;
            }
            if (cant > prod.getCantidad()) {
                System.out.print("Solo hay " + prod.getCantidad() + ". ¿Usar esa cantidad? (s/n): ");
                if (!sc.nextLine().trim().equalsIgnoreCase("s")) {
                    continue;
                }
                cant = prod.getCantidad();
            }

            // Crear copia para el carrito
            Producto item = new Producto(
                    prod.getId(), prod.getNombre(), prod.getPrecio(),
                    prod.getCategoria(), prod.getFechaVencimiento(), cant
            );
            cliente.getCarrito().insertarAlInicio(item);
            System.out.println("Añadido: " + cant + "x " + prod.getNombre());
        }

        cola.encolar(cliente);
        System.out.println("Cliente '" + nombre + "' registrado y encolado.\n");
    }

    public void atenderCliente() {
        Cliente c = cola.atender();
        if (c == null) {
            System.out.println("No hay clientes en la cola.");
            return;
        }

        System.out.println("\nATENDIENDO A: " + c.getNombre() + " (P" + c.getPrioridad() + ")");
        c.getCarrito().generarReporte(true);
        System.out.println("Atención completada.\n");
    }

    public ArbolProductos getInventario() { return inventario; }
    public ColaClientes getCola() { return cola; }
}