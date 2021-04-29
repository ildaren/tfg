package data; 

public class Oferta {

    //Atributos que se devolveran al pedir a la api un listado de los coches
    private int idCoche;
    private int idOferta;
    private String href;
    private String modelo;
    private String matricula;
    private String marca;
    private int numeroPuertas;
    private int capacidadMaletero;
    private String cambioMarchas;
    private int plazas;
    private boolean aireAcondicionado; 
    private String gama;
    private double precioDia;
    private String politicaCombustible;
    private int politicaCancelacion;
    
    //Contructor vacio
    public Oferta() {
    }

    //Constructor con todos los elementos 
    public Oferta(int idCoche, int idOferta, String href, String modelo, String matricula, String marca, int numeroPuertas, int capacidadMaletero,       
        String cambioMarchas, int plazas, boolean aireAcondicionado, String gama, double precioDia, String politicaCombustible, int politicaCancelacion) {

        this.idCoche = idCoche;
        this.idOferta = idOferta;
        this.href = href;
        this.modelo = modelo; 
        this.matricula = matricula;  
        this.marca = marca; 
        this.numeroPuertas = numeroPuertas;          
        this.capacidadMaletero = capacidadMaletero;  
        this.cambioMarchas = cambioMarchas;                                                                                     
        this.plazas = plazas;
        this.aireAcondicionado = aireAcondicionado;
        this.gama = gama;
        this.precioDia = precioDia;
        this.politicaCombustible = politicaCombustible; 
        this.politicaCancelacion = politicaCancelacion;
    }                                                                                                                                                                                                                                               //Get and set del id coche                                                                                              public int getIdCoche() {                                                                                                   return this.idCoche;                                                                                                }                                                                                                                                                                                                                                               
    
    public int getIdCoche() {
        return this.idCoche;
    }

    public void setIdCoche(int idCoche) {
        this.idCoche = idCoche;
    }
    
    //Get and set del id oferta
    public int getIdOferta() {
        return this.idOferta;
    }
    
    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }
    
    //Get and set del enlace del elemento
    public String getHref() {
        return this.href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    //Get and set del modelo de coche
    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    } 
    
    //Get and set de la matricula del coche (dato poco relevante la verdad)
    public String getMatricula() {
        return this.matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    //get and set de la marca del coche
    public String getMarca() {
        return this.marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    //Get and set del numero de puertas
    public int getNumeroPuertas() {
        return this.numeroPuertas;
    }

    public void setNumeroPuertas(int numeroPuertas) {
        this.numeroPuertas = numeroPuertas;
    }
    
    //Get and set de la capacidad del maletero en maletas
    public int getCapacidadMaletero() {
        return this.capacidadMaletero;
    } 
    
    public void setCapacidadMaletero(int capacidadMaletero) {
        this.capacidadMaletero = capacidadMaletero;
    }
    
    //Get and set del tipo de cambio de marchas, solo tendra dos valores, automatico o manual
    public String getCambioMarchas() {
        return this.cambioMarchas;
    }
    
    public void setCambioMarchas(String cambioMarchas) {
        this.cambioMarchas = cambioMarchas;
    }
    
    //Get and set del numero de plazas que tiene el coche
    public int getPlazas() {
        return this.plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }
     
    //Get and set que dira si el coche tiene o no aire acondicionado
    public boolean getAireAcondicionado() {
        return this.aireAcondicionado;
    }
    
    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }
    
    //Get and set de la gama del coche
    public String getGama() {
        return this.gama;
    }
    
    public void setGama(String gama) {
        this.gama = gama;
    }
    
    //Get and set del pedio del alquiler del coche por dia
    public double getPrecioDia() {
        return this.precioDia;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }
    
    //Get and set de la politica del combustible, en cuando a si el deposito debe estar lleno al devolverse o no
    public String getPoliticaCombustible() {
        return this.politicaCombustible;
    }
     
    public void setPoliticaCombustible(String politicaCombustible) {
        this.politicaCombustible = politicaCombustible;
    }
        
    //Get and set de la politica de cancelacion, sera un int, el numero representara el pago a dar en caso de que sea cancelada una semana antes de la fecha escogida
    public int getPoliticaCancelacion() {
        return this.politicaCancelacion;
    }
        
    public void setPoliticaCancelacion(int politicaCancelacion) {
        this.politicaCancelacion = politicaCancelacion;
    }
}