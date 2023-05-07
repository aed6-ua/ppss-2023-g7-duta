package ppss;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GestorLlamadasTest {
    GestorLlamadas glmock;
    Calendario cmock;
    IMocksControl ctl;

    @BeforeEach
    void setUp() {
        ctl = EasyMock.createStrictControl();
        glmock = EasyMock.partialMockBuilder(GestorLlamadas.class).addMockedMethod("getCalendario").createMock(ctl);
        cmock = ctl.createMock(Calendario.class);
        EasyMock.expect(glmock.getCalendario()).andReturn(cmock);

    }

    @Test
    void calculaConsumo_C1() {
        int minutos = 22;
        int hora = 10;
        double esperado = 457.6f;
        EasyMock.expect(cmock.getHoraActual()).andReturn(10);

        ctl.replay();
        double actual = glmock.calculaConsumo(minutos);
        Assertions.assertEquals(esperado, actual, 0.01f);
        ctl.verify();

    }

    @Test
    void calculaConsumo_C2() {
        int minutos = 13;
        int hora = 21;
        double esperado = 136.5f;
        EasyMock.expect(cmock.getHoraActual()).andReturn(21);

        ctl.replay();
        double actual = glmock.calculaConsumo(minutos);
        Assertions.assertEquals(esperado, actual, 0.01f);
        ctl.verify();
    }

}
