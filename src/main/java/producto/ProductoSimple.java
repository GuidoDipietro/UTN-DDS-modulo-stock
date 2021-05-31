package producto;

public class ProductoSimple extends Producto {
    private final Double precio;
    private Integer stock;

    public ProductoSimple(String nombre, String descripcion, Double precio, Integer stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
    }

    public Double precio() {
        return this.precio;
    }

    public Integer stock() {
        return this.stock;
    }
}
