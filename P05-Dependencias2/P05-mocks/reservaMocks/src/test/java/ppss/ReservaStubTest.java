package ppss;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ppss.excepciones.IsbnInvalidoException;
import ppss.excepciones.JDBCException;
import ppss.excepciones.ReservaException;
import ppss.excepciones.SocioInvalidoException;

public class ReservaStubTest {
    Reserva rmock;
    FactoriaBOs fbomock;
    IOperacionBO iobomock;

    @BeforeEach
    void setUp() {
        rmock = EasyMock.partialMockBuilder(Reserva.class).addMockedMethods("setFd", "compruebaPermisos").createMock();
        fbomock = EasyMock.createNiceMock(FactoriaBOs.class);
        iobomock = EasyMock.createNiceMock(IOperacionBO.class);

        Assertions.assertDoesNotThrow(() -> rmock.setFd());
        EasyMock.expectLastCall().andAnswer(() -> {
            rmock.setFd2(fbomock);
            return null;
        });
       EasyMock.expect(fbomock.getOperacionBO()).andStubReturn(iobomock);
    }

    @Test
    void realizaReserva_C1() {
        String login = "xxxx";
        String password = "xxxx";
        String socio = "Pepe";
        String []isbns = {"33333"};
        ReservaException esperado = new ReservaException("ERROR de permisos; ");


        EasyMock.expect(rmock.compruebaPermisos(EasyMock.anyString(),EasyMock.anyString(),EasyMock.anyObject(Usuario.class))).andReturn(false);
        EasyMock.replay(rmock);
        ReservaException resultado = Assertions.assertThrows(ReservaException.class, () -> rmock.realizaReserva(login,password,socio,isbns));
        Assertions.assertEquals(esperado.getMessage(), resultado.getMessage());
    }

    @Test
    void realizaReserva_C2() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String []isbns = {"22222","33333"};


        EasyMock.expect(rmock.compruebaPermisos(EasyMock.anyString(),EasyMock.anyString(),EasyMock.anyObject(Usuario.class))).andStubReturn(true);
        EasyMock.replay(rmock, fbomock, iobomock);
        Assertions.assertDoesNotThrow(() -> rmock.realizaReserva(login,password,socio,isbns));
    }

    @Test
    void realizaReserva_C3() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String []isbns = {"11111","22222","55555"};
        ReservaException esperado = new ReservaException("ISBN invalido:11111; ISBN invalido:55555; ");


        EasyMock.expect(rmock.compruebaPermisos(EasyMock.anyString(),EasyMock.anyString(),EasyMock.anyObject(Usuario.class))).andStubReturn(true);
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[1]));
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        EasyMock.replay(rmock, fbomock, iobomock);
        ReservaException resultado = Assertions.assertThrows(ReservaException.class, () -> rmock.realizaReserva(login,password,socio,isbns));
        Assertions.assertEquals(esperado.getMessage(), resultado.getMessage());
    }

    @Test
    void realizaReserva_C4() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Luis";
        String []isbns = {"22222"};
        ReservaException esperado = new ReservaException("SOCIO invalido; ");


        EasyMock.expect(rmock.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(true);
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new SocioInvalidoException());
        EasyMock.replay(rmock, fbomock, iobomock);
        ReservaException resultado = Assertions.assertThrows(ReservaException.class, () -> rmock.realizaReserva(login,password,socio,isbns));
        Assertions.assertEquals(esperado.getMessage(), resultado.getMessage());
    }

    @Test
    void realizaReserva_C5() {
        String login = "ppss";
        String password = "ppss";
        String socio = "Pepe";
        String []isbns = {"11111","22222","33333"};
        ReservaException esperado = new ReservaException("ISBN invalido:11111; CONEXION invalida; ");


        EasyMock.expect(rmock.compruebaPermisos(login,password,Usuario.BIBLIOTECARIO)).andReturn(true);
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[0]));
        EasyMock.expectLastCall().andThrow(new IsbnInvalidoException());
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[1]));
        Assertions.assertDoesNotThrow(() -> iobomock.operacionReserva(socio,isbns[2]));
        EasyMock.expectLastCall().andThrow(new JDBCException());
        EasyMock.replay(rmock, fbomock, iobomock);
        ReservaException resultado = Assertions.assertThrows(ReservaException.class, () -> rmock.realizaReserva(login,password,socio,isbns));
        Assertions.assertEquals(esperado.getMessage(), resultado.getMessage());
    }

}
