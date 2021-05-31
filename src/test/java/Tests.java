import decorators.DescuentoFijo;
import decorators.DescuentoPorcentual;
import decorators.Packaging;
import org.junit.Test;
import producto.Combo;
import producto.Producto;
import producto.ProductoSimple;

public class Tests {

    // Java no comparaba bien dos Double si diferian por 0.0000000000000000000000000000000000001 ¯\_(ツ)_/¯
    private Boolean DoubleEq(Double a, Double b) {
        return Math.abs(a-b) < 0.00001;
    }

    @Test
    public void testCombo() {
        Producto zapatillas = new ProductoSimple(
                "Zapas",
                "Zapatillas muy bonitas para correr muy rápido.",
                10000.0,
                2
        );
        Producto medias = new ProductoSimple(
                "Calcetines Java",
                "Medias muy cómodas para ponerse mientras se codea en Java.",
                1000.1,
                3
        );

        Combo piesContentos = new Combo(zapatillas, medias);

        assert piesContentos.precio().equals(
                zapatillas.precio() + medias.precio()
        );
        assert piesContentos.stock().equals(
                medias.stock() < zapatillas.stock()? medias.stock() : zapatillas.stock()
        );
    }

    @Test
    public void testDescuentoFijo() {
        double PRECIO_INICIAL = 142749.73;
        Producto compu = new ProductoSimple(
                "Dell Inspiron 14.27-49",
                "Una increíble computadora personal para todos los usos que busques.",
                142749.73,
                15
        );
        Producto compu_menos_1000 = new DescuentoFijo(compu, 1000.0);

        assert compu_menos_1000.precio().equals(PRECIO_INICIAL - 1000);
    }

    @Test
    public void testDescuentosYPackagingAcumulados() {
        Producto guantes = new ProductoSimple(
            "Guantes de LaTeX",
                "Guantes de LaTeX para escribir informes más estéticos.",
                100.0,
                2
        );
        Producto guantes_menos_40_pesos = new DescuentoFijo(guantes, 40.0);

        Producto casco = new ProductoSimple(
                "Casquito",
                "Casco para que no te rompas la cabeza codeando en Java.",
                500.5,
                5
        );
        Producto casco_menos_10_porciento = new DescuentoPorcentual(casco, 0.1);

        Producto chaleco = new ProductoSimple(
                "Chaleco Antibalas Marca Lahora",
                "Un chaleco antibalas que resiste a todos los tiros, pero se desarma con Maven.",
                123.123,
                10
        );
        Producto chaleco_tuneado = new Packaging(chaleco, 500.0);

        Producto super_combo_del_enunciado = new Combo(
                guantes_menos_40_pesos, casco_menos_10_porciento, chaleco_tuneado
        );

        Producto super_combo_con_caja_bonita = new Packaging(
                super_combo_del_enunciado, 1000.0
        );

        assert this.DoubleEq(
                super_combo_con_caja_bonita.precio(),
                guantes.precio()-40.0 + casco.precio()*0.9 + chaleco.precio()+500.0 + 1000.0
        );
        assert super_combo_con_caja_bonita.stock() == 2;
    }

    @Test
    public void testComboDeCombos() {
        Producto comboBigMac = new Combo(
                new ProductoSimple("BigMac","Hamburguesa rica.",300.0,5),
                new ProductoSimple("Coca-Cola","Bebida carbonatada negra.",100.0,4)
        );
        Producto comboPostre = new Combo(
                new ProductoSimple("Sundae","Crema helada con salsa.",150.0, 10),
                new ProductoSimple("McFlurry","Crema helada con salsa y Oreos.",200.0, 3)
        );

        Producto almuerzoFelizUsandoLaApp = new Combo(
                new DescuentoFijo(comboBigMac, 300.0),
                new DescuentoPorcentual(comboPostre, 0.1)
        );

        Producto almuerzoFelizSiConozcoAAlguienEnMcDonalds = new DescuentoPorcentual(
                almuerzoFelizUsandoLaApp,
                0.25 // apa
        );

        assert this.DoubleEq(
                almuerzoFelizSiConozcoAAlguienEnMcDonalds.precio(),
                ((300+100 - 300) + (150+200)*0.9) * 0.75
        );

        assert almuerzoFelizSiConozcoAAlguienEnMcDonalds.stock() == 3;
    }
}
