package producto;

public abstract class Producto {
    protected String descripcion;
    protected String nombre;

    public abstract Double precio();
    public abstract Integer stock();
}
