package decorators;

import producto.Producto;

public class Packaging extends Decorado {
    private final Double precio;

    public Packaging(Producto producto, Double precio) {
        this.producto = producto;
        this.precio = precio;
    }

    public Double precio() {
        return super.producto.precio() + precio;
    }
}
