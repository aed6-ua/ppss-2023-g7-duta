package ppss;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CineTest {

    @Test
    void reservaButacasC1() {
        boolean[] asientos = {};
        int solicitados = 3;
        ppss.Cine cine = new ppss.Cine();

        ppss.ButacasException exception = assertThrows(ppss.ButacasException.class, () -> cine.reservaButacasV1(asientos, solicitados));
    }
}