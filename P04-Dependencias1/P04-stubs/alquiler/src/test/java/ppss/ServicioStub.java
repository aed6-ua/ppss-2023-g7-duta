package ppss;

public class ServicioStub implements IService {
    private float precio = 0.0f;

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    @Override
    public float consultaPrecio(TipoCoche tipo) {
        return precio;
    }
}
