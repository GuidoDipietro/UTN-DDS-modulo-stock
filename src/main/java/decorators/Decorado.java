package decorators;

import producto.Producto;

public abstract class Decorado extends Producto {
    protected Producto producto;

    public abstract Double precio();

    public Integer stock() {
        return this.producto.stock();
    }
}
