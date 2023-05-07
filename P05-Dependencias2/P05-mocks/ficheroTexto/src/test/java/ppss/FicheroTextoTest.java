package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class FicheroTextoTest {
    FicheroTexto ftmock;
    FileReader frmock;
    IMocksControl ctl;

    @BeforeEach
    void setUp() {
        ctl = EasyMock.createStrictControl();
        ftmock = EasyMock.partialMockBuilder(FicheroTexto.class).addMockedMethod("createFichero").createMock(ctl);
        frmock = ctl.createMock(FileReader.class);
    }

    @Test
    void contarCaracteres_C1() {
        String nombreFichero = "src/test/resources/ficheroC1.txt";
        String resultadoEsperado = "src/test/resources/ficheroC1.txt (Error al leer el archivo)";

        Assertions.assertDoesNotThrow( () -> {
            EasyMock.expect(ftmock.createFichero(nombreFichero)).andReturn(frmock);
            EasyMock.expect(frmock.read()).andReturn((int) 'a').andReturn((int) 'b').andThrow(new IOException());
        });

        ctl.replay();
        FicheroException resultadoReal = Assertions.assertThrows(FicheroException.class, () -> ftmock.contarCaracteres(nombreFichero));
        Assertions.assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }

    @Test
    void contarCaractetres_C2() {
        String nombreFichero = "src/test/resources/ficheroC2.txt";
        String resultadoEsperado = "src/test/resources/ficheroC2.txt (Error al cerrar el archivo)";

        Assertions.assertDoesNotThrow( () -> {
            EasyMock.expect(ftmock.createFichero(nombreFichero)).andReturn(frmock);
            EasyMock.expect(frmock.read()).andReturn((int) 'a').andReturn((int) 'b').andReturn((int) 'c').andReturn(-1);
            frmock.close();
            EasyMock.expectLastCall().andThrow(new IOException());
        });

        ctl.replay();
        FicheroException resultadoReal = Assertions.assertThrows(FicheroException.class, () -> ftmock.contarCaracteres(nombreFichero));
        Assertions.assertEquals(resultadoEsperado, resultadoReal.getMessage());
    }
}
