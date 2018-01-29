public class Producto {
    String nombre;
    int cantidad;
    double precio;
    int ID;
    String categoria;

    public Producto(String nombre, int cantidad, double precio, int ID, String categoria) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.ID = ID;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Producto(" + "Nombre = " + nombre + ", Cantidad = " + cantidad + ", Precio = " + precio + ", ID = " + ID + ", Categoria = " + categoria + ')';
    }    
}
