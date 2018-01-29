import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestion {

    LT entr = new LT();

    ArrayList<Producto> productos = new ArrayList();

    public void inicio() {
        System.out.println("\tGESTOR DE PRODUCTOS");
        menu();
    }

    public void printmenu() {
        System.out.println("0. Salir");
        System.out.println("1. Añadir producto");
        System.out.println("2. Buscar producto");
        System.out.println("3. Eliminar producto");
        System.out.println("4. Inventario");
        System.out.print("Opción: ");
    }

    public void menu() {
        printmenu();
        int numero = 7;
        while (numero != 0) {
            numero = entr.llegirSencer();
            switch (numero) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    añadir();
                    System.out.println(productos);
                    printmenu();
                    break;
                case 2:
                    printmenu();
                    break;
                case 3:
                    eliminar();
                    System.out.println(productos);
                    printmenu();
                    break;
                case 4:
                    inventario();
                    System.out.println(productos);
                    printmenu();
                    break;
            }
        }
    }

    private void añadir() {
        System.out.print("Nombre del producto: ");
        String nombre = entr.llegirLinia();
        System.out.print("Cantidad de producto (unidades): ");
        int cantidad = entr.llegirSencer();
        System.out.print("Precio del producto: ");
        double precio = entr.llegirReal();
        System.out.print("Categoria: ");
        String categoria = entr.llegirLinia();

        Random r = new Random();
        int ID = r.nextInt(1000);

        Producto p = new Producto(nombre, cantidad, precio, ID, categoria);
        productos.add(p);

        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_productos", "root", "portopetro98");
            Statement s = c.createStatement();

            String cadena = "INSERT INTO Productos (Nombre, Cantidad, Precio, ID, Categoria) VALUES "
                    + "(" + "'" + nombre + "'" + ", " + "'" + cantidad + "'" + ", " + "'" + precio + "'" + ", " + "'" + ID + "'" + ", " + "'" + categoria + "'" + ")";

            s.executeUpdate(cadena);

            System.out.println("Producto añadido correctamente\n");
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar() {
        System.out.print("Escribe la ID: ");
        int ID = entr.llegirSencer();

        if (getObject(ID) != null) {
            productos.remove(getObject(ID));
            try {
                Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_productos", "root", "portopetro98");
                Statement s = c.createStatement();
                s.executeUpdate("DELETE FROM Productos WHERE ID=" + ID);
                System.out.println("Producto eliminado correctamente\n");
            } catch (SQLException ex) {
                Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("El producto no existe en esta base de datos");
        }

    }

    public Producto getObject(int ID) {
        Producto aux = new Producto("", 0, 0, 0, "");

        boolean b = false;
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getID() == ID) {
                aux = productos.get(i);
                b = true;
            }
        }

        if (b) {
            return aux;
        } else {
            return null;
        }
    }

    private void inventario() {
        try {
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestor_productos", "root", "portopetro98");
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Productos");
            System.out.println("Nombre \t Cantidad \t Precio");
            while (rs.next()) {
                System.out.println(rs.getString("Nombre") + "    " + "\t" + rs.getString("Cantidad") + "\t" + rs.getString("Precio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gestion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        new Gestion().inicio();
    }
