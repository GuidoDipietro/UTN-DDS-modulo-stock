package decorators;

import producto.Producto;

public class DescuentoFijo extends Decorado {
    private final Double valor;

    public DescuentoFijo(Producto producto, Double valor) {
        this.producto = producto;
        this.valor = valor;
    }

    public Double precio() {
        return this.producto.precio() - this.valor;
    }
}
