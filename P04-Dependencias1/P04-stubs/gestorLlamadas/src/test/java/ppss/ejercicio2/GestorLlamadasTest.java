package ppss.ejercicio2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
public class GestorLlamadasTest {
    GestorLlamadasTestable glt;
    StubCalendario sc;

    @BeforeEach
    void setUp() {
        glt = new GestorLlamadasTestable();
        sc = new StubCalendario();
    }

    @Test
    void C1_calculaConsumo() {
        int minutos = 10;
        int hora = 15;
        double resultadoEsperado = 208;

        sc.setHoraActual(hora);
        glt.setCalendario(sc);
        double resultadoReal = glt.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void C2_calculaConsumo() {
        int minutos = 10;
        int hora = 22;
        double resultadoEsperado = 105;

        sc.setHoraActual(hora);
        glt.setCalendario(sc);
        double resultadoReal = glt.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }
}
