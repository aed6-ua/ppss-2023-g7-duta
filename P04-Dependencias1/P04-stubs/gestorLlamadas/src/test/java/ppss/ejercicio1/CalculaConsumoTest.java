package ppss.ejercicio1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculaConsumoTest {
    GestorLlamadasTestable glt;

    @BeforeEach
    void setUp() {
        glt = new GestorLlamadasTestable();
    }

    @Test
    void C1_calculaConsumo() {
        int minutos = 10;
        int hora = 15;
        double resultadoEsperado = 208;

        glt.setHoraActual(hora);
        double resultadoReal = glt.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }

    @Test
    void C2_calculaConsumo() {
        int minutos = 10;
        int hora = 22;
        double resultadoEsperado = 105;

        glt.setHoraActual(hora);
        double resultadoReal = glt.calculaConsumo(minutos);
        assertEquals(resultadoEsperado, resultadoReal);
    }
}
