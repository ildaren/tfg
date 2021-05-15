package data;

public class Pago {

    private int idPago;
    private String href;
    private String titular;
    private String numTarjeta;
    private String hrefUsuarioPago;
    
    public Pago () {}

    public Pago (int idPago, String href, String titular, String numTarjeta, String hrefUsuarioPago) {

        this.idPago = idPago;
        this.href = href;
        this.titular = titular;
        this.numTarjeta = numTarjeta;
        this.hrefUsuarioPago = hrefUsuarioPago;
    }

    public int getIdPago() {
        return idPago;
    }
    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }

    public String getTitular() {
        return titular;
    }
    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getNumTarjeta() {
        return numTarjeta;
    }
    public void setNumTarjeta(String numTarjeta) {
        this.numTarjeta = numTarjeta;
    }

    public String getHrefUsuarioPago() {
        return hrefUsuarioPago;
    }
    public void setHrefUsuarioPago(String hrefUsuarioPago) {
        this.hrefUsuarioPago = hrefUsuarioPago;
    }




}