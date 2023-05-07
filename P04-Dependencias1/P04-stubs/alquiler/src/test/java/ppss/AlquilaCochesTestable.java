package ppss;

public class AlquilaCochesTestable extends AlquilaCoches {
    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }
    public void setService(IService s) {
        this.service = s;
    }
    @Override
    public IService getServicio() {
        return service;
    }
    private IService service;
}
