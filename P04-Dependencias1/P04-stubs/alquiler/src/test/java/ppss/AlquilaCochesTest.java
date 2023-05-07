package ppss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class AlquilaCochesTest {
    AlquilaCochesTestable acTestable;
    CalendarioStub calstub;
    ServicioStub servicioStub;
    Ticket ticketEsperado;

    @BeforeEach
    void setUp() {

        calstub    = new CalendarioStub();
        ticketEsperado = new Ticket();
        servicioStub = new ServicioStub();
        servicioStub.setPrecio(10);
        acTestable = new AlquilaCochesTestable();
        acTestable.setService(servicioStub);
    }

    @Test
    void C1_calculaPrecio() {
        TipoCoche tcoche = TipoCoche.TURISMO;
        LocalDate fechaInicio = LocalDate.of(2021, Month.MAY, 18);
        int ndias = 10;
        int diasFestivos[] = {};
        float precioEsperado = 75;

        calstub.setDiasFestivos(diasFestivos);
        acTestable.setCalendario(calstub);
        ticketEsperado.setPrecio_final(precioEsperado);

        Ticket ticketReal = assertDoesNotThrow(() -> acTestable.calculaPrecio(tcoche, fechaInicio, ndias));
        assertEquals(ticketEsperado.getPrecio_final(), ticketReal.getPrecio_final());
    }

    @Test
    void C2_calculaPrecio() {
        TipoCoche tcoche = TipoCoche.CARAVANA;
        LocalDate fechaInicio = LocalDate.of(2021, Month.JUNE, 19);
        int ndias = 7;
        int diasFestivos[] = {20, 24};
        float precioEsperado = 62.5f;

        calstub.setDiasFestivos(diasFestivos);
        acTestable.setCalendario(calstub);
        ticketEsperado.setPrecio_final(precioEsperado);

        Ticket ticketReal = assertDoesNotThrow(() -> acTestable.calculaPrecio(tcoche, fechaInicio, ndias));
        assertEquals(ticketEsperado.getPrecio_final(), ticketReal.getPrecio_final());
    }

    @Test
    void C3_calculaPrecio() {
        TipoCoche tcoche = TipoCoche.TURISMO;
        LocalDate fechaInicio = LocalDate.of(2021, Month.APRIL, 17);
        int ndias = 8;
        int diasExcepcion[] = {18, 21, 22};
        String mensajeDeErrorEsperado = "Error en dia: 2021-04-18; Error en dia: 2021-04-21; Error en dia: 2021-04-22; ";

        calstub.setDiasExcepcion(diasExcepcion);
        acTestable.setCalendario(calstub);

        MensajeException errorReal = assertThrows(MensajeException.class, () -> acTestable.calculaPrecio(tcoche, fechaInicio, ndias));
        assertEquals(mensajeDeErrorEsperado, errorReal.getMessage());
    }
}