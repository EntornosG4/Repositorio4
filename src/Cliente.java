/**
 * Clase Cliente que almacenará información sobre estos
 * @author José Manuel Moreno Córdoba
 */
public class Cliente {

    //Atributos de los objetos Cliente
    private String id;
    private String name;
    private String phone;
    private String address;
    private double debt;

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
        setId(nif);
        setName(nombre);
        setPhone(telefono);
        setAddress(direccion);
        setDebt(deuda);
    }
    
    /**
     * Devuelve el NIF del cliente
     * @return NIF del cliente
     */
    public String getId() {
        return id;
    }

    /**
     * Asigna el NIF a un cliente
     * @param id NIF del cliente
     * @throws IllegalArgumentException Error cuando se introduce un NIF inválido
     */
    public void setId(String id) throws IllegalArgumentException {
        if (comprobarDNI(id))
            this.id = id;
        else
            throw new IllegalArgumentException("El NIF introducido no es válido.");
    }

    /**
     * Devuelve el name del cliente
     * @return Nombre del cliente
     */
    public String getName() {
        return name;
    }

    /**
     * Asigna el name al cliente
     * @param name Nombre del cliente
     * @throws IllegalArgumentException Error cuando el name está vacío
     */
    public void setName(String name) throws IllegalArgumentException {
        if(!name.equals(""))
            this.name = name;
        else
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
    }

    /**
     * Devuelve el teléfono del cliente
     * @return Teléfono del cliente
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Asigna el teléfono del cliente
     * @param phone Teléfono del cliente
     * @throws IllegalArgumentException Error cuando el número introducido no es válido.
     */
    public void setPhone(String phone) throws IllegalArgumentException {
        if(phone.length() == 9 && Integer.parseInt(phone.substring(0)) > 5)
            this.phone = phone;
        else
            throw new IllegalArgumentException("El número de telefono no es válido.");
    }

    /**
     * Devuelve la dirección del cliente
     * @return Dirección del cliente
     */
    public String getAddress() {
        return address;
    }

    /**
     * Asigna la dirección del cliente
     * @param address Dirección del cliente
     * @throws IllegalArgumentException Error cuando la dirección está vacía
     */
    public void setAddress(String address) throws IllegalArgumentException {
        if(!address.equals(""))
            this.address = address;
        else
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
    }

    /**
     * Devuelve la debt del cliente
     * @return Deuda del cliente
     */
    public double getDebt() {
        return debt;
    }

    /**
     * Asigna la deudda del cliente
     * @param debt Deuda del cliente
     * @throws IllegalArgumentException Error cuando la debt del cliente es negativa
     */
    public void setDebt(double debt) throws IllegalArgumentException {
        if(debt >= 0)
            this.debt = debt;
        else
            throw new IllegalArgumentException("La deuda no puede ser negativa");
    }

    private boolean comprobarDNI(String dni) {
        dni = dni.replace("-", "").replace(" ", "");
        boolean valido = false;
        int numeroDNI = -1;
        if (dni.length() == 9) {
            numeroDNI = Integer.parseInt(dni.substring(0, 8));
            if(calcularLetraDNI(numeroDNI, "").toUpperCase().equals(dni.substring(8).toUpperCase()))
                valido = true;
        }
        return valido;
    }

    private String calcularLetraDNI(int numeroDNI, String nuevoParametro) {
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
        return "Cliente{" + "nif=" + id + ", nombre=" + name + ", telefono=" + phone + ", direccion=" + address + ", deuda=" + debt + '}';
    }
}
