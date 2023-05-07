package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PremioTest {
    IMocksControl ctl;
    Premio pmock;
    ClienteWebService cwmock;

    @BeforeEach
    void setUp() {
        ctl = EasyMock.createStrictControl();
        pmock = EasyMock.partialMockBuilder(Premio.class).addMockedMethod("generaNumero").createMock(ctl);
        cwmock = ctl.createMock(ClienteWebService.class);
        pmock.cliente = cwmock;
    }

    @Test
    void compruebaPremio_C1() {
        float aleatorio = 0.07f;
        String premio = "entrada final Champions";
        String resultadoEsperado = "Premiado con entrada final Champions";

        EasyMock.expect(pmock.generaNumero()).andReturn(aleatorio);
        Assertions.assertDoesNotThrow( () ->
                        EasyMock.expect(cwmock.obtenerPremio()).andReturn(premio)
                );
        ctl.replay();
        Assertions.assertEquals(resultadoEsperado, pmock.compruebaPremio());
        ctl.verify();
    }

    @Test
    void compruebaPremio_C2() {
        float aleatorio = 0.03f;
        String resultadoEsperado = "No se ha podido obtener el premio";

        EasyMock.expect(pmock.generaNumero()).andReturn(aleatorio);
        Assertions.assertDoesNotThrow(() ->
                EasyMock.expect(cwmock.obtenerPremio()).andThrow(new ClienteWebServiceException())
        );
        ctl.replay();
        Assertions.assertEquals(resultadoEsperado, pmock.compruebaPremio());
        ctl.verify();
    }

    @Test
    void compruebaPremio_C3() {
        float aleatorio = 0.3f;
        String premio = "entrada final Champions";
        String resultadoEsperado = "Sin premio";

        EasyMock.expect(pmock.generaNumero()).andReturn(aleatorio);
        ctl.replay();
        Assertions.assertEquals(resultadoEsperado, pmock.compruebaPremio());
        ctl.verify();
    }
}
