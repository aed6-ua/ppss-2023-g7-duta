package ppss.ejercicio1;

public class GestorLlamadasTestable extends GestorLlamadas{
    private int horaActual = 1;
    @Override
    public int getHoraActual() {
        return horaActual;
    }
    public void setHoraActual(int hora) {
        horaActual = hora;
    }
}
