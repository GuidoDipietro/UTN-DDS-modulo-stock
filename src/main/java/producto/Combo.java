package producto;

import java.util.*;

public class Combo extends Producto {
    private final List<Producto> productos;

    // Inits
    public Combo() {
        this.productos = new ArrayList<>();
    }
    public Combo(Producto ...ps) {
        this.productos = Arrays.asList(ps);
    }

    // Metodos
    public Double precio() {
        return this.productos.stream().mapToDouble(Producto::precio).sum();
    }

    public Integer stock() {
        OptionalInt stock = this.productos.stream().mapToInt(Producto::stock).min();
        if (stock.isPresent()) return stock.getAsInt();
        return -1;
    }

    public void agregarProducto(Producto p) {
        this.productos.add(p);
    }
    public void agregarProductos(Producto ...ps) {
        Collections.addAll(this.productos, ps);
    }
}
