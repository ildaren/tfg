package data;

public class Usuario {
    
    private int idUsuario;
    private String href;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private String correoElectronico;
    private String dni;
    private String direccion;
    private String ciudad;
    private String codigoPostal;
    private String fechaCarne;

    public Usuario () {}

    public Usuario (int idUsuario, String href, String nombre, String apellidos, String correoElectronico, 
        String dni, String direccion, String ciudad, String codigoPostal, String fechaCarne) {

        this.idUsuario = idUsuario;
        this.href = href;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.dni = dni;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.fechaCarne = fechaCarne;
    }

    public Usuario (int idUsuario, String href, String nombre, String apellidos, String contrasena, String correoElectronico, 
        String dni, String direccion, String ciudad, String codigoPostal, String fechaCarne) {

        this.idUsuario = idUsuario;
        this.href = href;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contrasena = contrasena;
        this.correoElectronico = correoElectronico;
        this.dni = dni;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
        this.fechaCarne = fechaCarne;
    }
    
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getFechaCarne() {
        return fechaCarne;
    }
    public void setFechaCarne(String fechaCarne) {
        this.fechaCarne = fechaCarne;
    }

    
}
