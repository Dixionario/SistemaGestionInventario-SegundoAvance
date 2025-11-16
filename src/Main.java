import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tienda tienda = new Tienda(10); // Capacidad de cola: 10

        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE VENTAS ===");
            System.out.println("1. Añadir producto al inventario");
            System.out.println("2. Registrar cliente y armar carrito");
            System.out.println("3. Atender cliente (factura)");
            System.out.println("4. Ver inventario");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            try {
                opcion = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.print("ID: ");
                    int id = 0;
                    try { id = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { id = 1; }
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) { System.out.println("Nombre requerido."); break; }
                    System.out.print("Precio: ");
                    double precio = 0;
                    try { precio = Double.parseDouble(sc.nextLine().trim()); } catch (Exception e) { precio = 1.0; }
                    System.out.print("Categoría: ");
                    String cat = sc.nextLine().trim();
                    if (cat.isEmpty()) cat = "Sin categoría";
                    System.out.print("Fecha venc. (opcional, ej: 2025-12-31): ");
                    String fv = sc.nextLine().trim();
                    if (fv.isEmpty()) fv = null;
                    System.out.print("Cantidad: ");
                    int cant = 1;
                    try { cant = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { cant = 1; }
                    if (cant < 1) cant = 1;

                    Producto p = new Producto(id, nombre, precio, cat, fv, cant);
                    tienda.añadirProducto(p);
                    break;

                case 2:
                    tienda.registrarCliente(sc);
                    break;

                case 3:
                    tienda.atenderCliente();
                    break;

                case 4:
                    tienda.getInventario().mostrarInventario();
                    break;

                case 5:
                    System.out.println("👋 ¡Hasta luego!");
                    break;

                default:
                    if (opcion != -1) System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        sc.close();
    }
}