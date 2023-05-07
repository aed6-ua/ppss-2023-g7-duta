package ppss;

import java.time.LocalDate;

public class CalendarioStub extends Calendario {
    private int diasFestivos[]  = {};
    private int diasExcepcion[] = {};

    public void setDiasFestivos(int diasFestivos[]) {
        this.diasFestivos = diasFestivos;
    }

    public void setDiasExcepcion(int diasExcepcion[]) {
        this.diasExcepcion = diasExcepcion;
    }

    @Override
    public boolean es_festivo(LocalDate otroDia) throws CalendarioException {
        for (int i = 0; i < diasExcepcion.length; i++) {
            if(diasExcepcion[i] == otroDia.getDayOfMonth()) {
                throw new CalendarioException(otroDia.toString());
            }
        }
        for (int i = 0; i < diasFestivos.length; i++) {
            if(diasFestivos[i] == otroDia.getDayOfMonth()) {
                return true;
            }
        }
        return false;
    }
}
