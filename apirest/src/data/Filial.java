package data;

public class Filial {
    
    private int idFilial;
    private String href;
    private String comunidad;
    private String ciudad;
    private String calle;
    private int numero;

    public Filial () {

    }

    public Filial (int idFilial, String href, String comunidad, String ciudad, String calle, int numero) {

        this.idFilial = idFilial;
        this.href = href;
        this.comunidad = comunidad;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numero = numero;
    }

    
    public int getIdFilial() {
        return idFilial;
    }
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public String getHref () {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }

    public String getComunidad() {
        return comunidad;
    }
    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCalle() {
        return calle;
    }
    public void setCalle(String calle) {
        this.calle = calle;
    }
}
