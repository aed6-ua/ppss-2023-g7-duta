package ppss.ejercicio2;

public class StubCalendario extends Calendario {
    private int horaActual = 0;

    @Override
    public int getHoraActual() {
        return horaActual;
    }

    public void setHoraActual(int horaActual) {
        this.horaActual = horaActual;
    }
}
