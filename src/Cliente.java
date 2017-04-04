/**
 * Clase Cliente que almacenará información sobre estos
 * @author José Manuel Moreno Córdoba
 */
public class Cliente {

    //Atributos de los objetos Cliente
    private String nif;
    private String nombre;
    private String telefono;
    private String direccion;
    private double deuda;

    /**
     * Constructor que recibe por parámetros los datos del cliente
     * @param nif NIF del cliente
     * @param nombre Nombre del cliente
     * @param telefono Teléfono del cliente
     * @param direccion Dirección del cliente
     * @param deuda Deuda del cliente
     * @throws IllegalArgumentException Error cuando algunos de los parámetros recibidos no son válidos
     */
    public Cliente(String nif, String nombre, String telefono, String direccion, double deuda) throws IllegalArgumentException{
        setNif(nif);
        setNombre(nombre);
        setTelefono(telefono);
        setDireccion(direccion);
        setDeuda(deuda);
    }
    
    /**
     * Devuelve el NIF del cliente
     * @return NIF del cliente
     */
    public String getNif() {
        return nif;
    }

    /**
     * Asigna el NIF a un cliente
     * @param nif NIF del cliente
     * @throws IllegalArgumentException Error cuando se introduce un NIF inválido
     */
    public void setNif(String nif) throws IllegalArgumentException {
        if (comprobarDNI(nif))
            this.nif = nif;
        else
            throw new IllegalArgumentException("El NIF introducido no es válido.");
    }

    /**
     * Devuelve el nombre del cliente
     * @return Nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre al cliente
     * @param nombre Nombre del cliente
     * @throws IllegalArgumentException Error cuando el nombre está vacío
     */
    public void setNombre(String nombre) throws IllegalArgumentException {
        if(!nombre.equals(""))
            this.nombre = nombre;
        else
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
    }

    /**
     * Devuelve el teléfono del cliente
     * @return Teléfono del cliente
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono del cliente
     * @param telefono Teléfono del cliente
     * @throws IllegalArgumentException Error cuando el número introducido no es válido.
     */
    public void setTelefono(String telefono) throws IllegalArgumentException {
        if(telefono.length() == 9 && Integer.parseInt(telefono.substring(0)) > 5)
            this.telefono = telefono;
        else
            throw new IllegalArgumentException("El número de telefono no es válido.");
    }

    /**
     * Devuelve la dirección del cliente
     * @return Dirección del cliente
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la dirección del cliente
     * @param direccion Dirección del cliente
     * @throws IllegalArgumentException Error cuando la dirección está vacía
     */
    public void setDireccion(String direccion) throws IllegalArgumentException {
        if(!direccion.equals(""))
            this.direccion = direccion;
        else
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
    }

    /**
     * Devuelve la deuda del cliente
     * @return Deuda del cliente
     */
    public double getDeuda() {
        return deuda;
    }

    /**
     * Asigna la deudda del cliente
     * @param deuda Deuda del cliente
     * @throws IllegalArgumentException Error cuando la deuda del cliente es negativa
     */
    public void setDeuda(double deuda) throws IllegalArgumentException {
        if(deuda >= 0)
            this.deuda = deuda;
        else
            throw new IllegalArgumentException("La deuda no puede ser negativa");
    }

    private boolean comprobarDNI(String dni) {
        dni = dni.replace("-", "").replace(" ", "");
        boolean valido = false;
        int numeroDNI = -1;
        if (dni.length() == 9) {
            numeroDNI = Integer.parseInt(dni.substring(0, 8));
            if(calcularLetraDNI(numeroDNI).toUpperCase().equals(dni.substring(8).toUpperCase()))
                valido = true;
        }
        return valido;
    }

    private String calcularLetraDNI(int numeroDNI) {
        int resto = numeroDNI % 23;
        String letrasDNI = "TRWAGMYFPDXBNJZSQVHLCKE", letra = null;
        try{
            letra = letrasDNI.substring(resto, resto + 1);
        } catch (IndexOutOfBoundsException ex){
            letra = null;
        }
        return letra;
    }

    /**
     * Devuelve una cadena de texto con los atributos del cliente
     * @return Cadena de texto con los atributos del cliente
     */
    @Override
    public String toString() {
        return "Cliente{" + "nif=" + nif + ", nombre=" + nombre + ", telefono=" + telefono + ", direccion=" + direccion + ", deuda=" + deuda + '}';
    }
}
