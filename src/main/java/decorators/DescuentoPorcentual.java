package decorators;

import producto.Producto;

public class DescuentoPorcentual extends Decorado {
    private final Double porcentaje; // de 0.0 a 1.0

    public DescuentoPorcentual(Producto producto, Double porcentaje) {
        this.producto = producto;
        this.porcentaje = porcentaje;
    }

    public Double precio() {
        return super.producto.precio() * (1-porcentaje);
    }
}
