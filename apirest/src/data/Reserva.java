package data;

public class Reserva {
    
    private String href;

    private int idReserva;
    private String fechaReserva;
    private String fechaRecogida;
    private String fechaEntrega;
    private float precioFinal;

    private String hrefFilialRecogida;

    private String hrefFilialEntrega;

    private String hrefCoche;

    private String hrefPago;

    public Reserva () {
    }

    public Reserva (String href, int idReserva, String fechaReserva, String fechaRecogida, String fechaEntrega, float precioFinal,
        String hrefFilialRecogida, String hrefFilialEntrega, String hrefCoche, String hrefPago) {

        this.href = href;
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.fechaRecogida = fechaRecogida;
        this.fechaEntrega = fechaEntrega;
        this.precioFinal = precioFinal;

        this.hrefFilialRecogida = hrefFilialRecogida;

        this.hrefFilialEntrega = hrefFilialEntrega;

        this.hrefCoche = hrefCoche;

        this.hrefPago = hrefPago;
    }

    public String getHref() {
        return this.href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getFechaRecogida() {
        return fechaRecogida;
    }

    public void setFechaRecogida(String fechaRecogida) {
        this.fechaRecogida = fechaRecogida;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(float precioFinal) {
        this.precioFinal = precioFinal;
    }

    public String getHrefFilialRecogida() {
        return this.hrefFilialRecogida;
    }

    public void setHrefFilialRecogida(String hrefFilialRecogida) {
        this.hrefFilialRecogida = hrefFilialRecogida;
    }

    public String getHrefFilialEntrega() {
        return this.hrefFilialEntrega;
    }

    public void setHrefFilialEntrega(String hrefFilialEntrega) {
        this.hrefFilialEntrega = hrefFilialEntrega;
    }

    public String getHrefCoche() {
        return this.hrefCoche;
    }

    public void setHrefCoche(String hrefCoche) {
        this.hrefCoche = hrefCoche;
    }

    public String getHrefPago () {
        return this.hrefPago;
    }

    public void setHrefPago (String hrefPago) {
        this.hrefPago = hrefPago;
    }
}
