package pro06ficheros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clase donde guardaremos un fichero y trabajaremos con él
 * @author José Manuel Moreno Córdoba
 */
public class Fichero {
    
    //Fichero del objeto
    private File fichero = new File("clientes.dat");
    
    /**
     * Constructor por defecto de la clase fichero que inicializa el fichero
     * @throws IOException Error cuando no se puede crear el fichero
     */
    public Fichero() throws IOException{
        if(!fichero.exists()){
            try{
                fichero.createNewFile();
            } catch(IOException ex){
                throw new IOException("Error al crear el archivo.");
            }
        }
    }
    
    /**
     * Añade un cliente a nuestro fichero
     * @param c Objeto de la clase Cliente
     * @throws FileNotFoundException Error cuando no se encuentra el fichero
     * @throws IOException Error cuando no se puede escribir en el fichero
     */
    public void anadirCliente(Cliente c) throws FileNotFoundException, IOException{
        if(!fichero.exists()) fichero.createNewFile();
        DataOutputStream dos = null;
        boolean salir = false;
        try{
            dos = new DataOutputStream(new FileOutputStream(fichero, true));
            dos.writeUTF(c.getNif());
            dos.writeUTF(c.getNombre());
            dos.writeUTF(c.getTelefono());
            dos.writeUTF(c.getDireccion());
            dos.writeDouble(c.getDeuda());                    
        } catch(FileNotFoundException ex){
            throw new FileNotFoundException("No se ha encontrado el archivo.");
        } catch(IOException ex){
            throw new IOException("Error al escribir en el archivo.");
        }
        dos.close();
    }
    
    /**
     * Devuelve un ArrayList con todos los clientes almacenados en el fichero
     * @return ArrayList con la lista de los clientes
     * @throws FileNotFoundException Error cuando no se encuentra el fichero
     * @throws IOException Error al leer el archivo
     * @throws IllegalArgumentException Error cuando los datos en el fichero no son datos válidos de un cliente
     */
    public ArrayList<Cliente> obtenerClientes() throws FileNotFoundException, IOException, IllegalArgumentException{
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        DataInputStream dis = null;
        boolean salir = false;
        try {
            dis = new DataInputStream(new FileInputStream(fichero));
            do {
                try {
                    Cliente c = new Cliente(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readDouble());
                    listaClientes.add(c);
                } catch (EOFException ex) {
                    salir = true;
                } catch (IOException ex) {
                    throw new IOException("Error al leer en el archivo.");
                }
            } while (!salir);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("No se ha encontrado el archivo, reinicie la aplicación.");
        }
        dis.close();
        return listaClientes;
    }
    
    /**
     * Devuelve una lista de los clientes con el NIF recibido por parámetro
     * @param nif NIF del cliente
     * @return ArrayList con los clientes que tienen ese NIF
     * @throws FileNotFoundException Error cuando no se encuentra el archivo
     * @throws IOException Error al leer el archivo
     * @throws IllegalArgumentException Error cuando los datos en el fichero no son datos válidos de un cliente 
     */
    public ArrayList<Cliente> buscarCliente(String nif) throws FileNotFoundException, IOException, IllegalArgumentException{
        ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
        DataInputStream dis = null;
        boolean salir = false;
        try{
            dis = new DataInputStream(new FileInputStream(fichero));
            do{
                try{
                    Cliente c = new Cliente(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readDouble());
                    if(c.getNif().toUpperCase().equals(nif.toUpperCase()))
                        listaClientes.add(c);
                } catch(EOFException ex){
                    salir = true;
                } catch(IOException ex){
                    throw new IOException("Error al leer el archivo.");
                }
            } while(!salir);
        } catch(FileNotFoundException ex){
            throw new FileNotFoundException("No se ha encontrado el archivo, reinicie la aplicación.");
        } 
        dis.close();
        return listaClientes;
    }
    
    /**
     * Borra el cliente que contiene el NIF introducido por parámetro
     * @param nif NIF del cliente
     * @throws IOException Error cuando no se puede leer el archivo, escribir o no se puede crear la copia de seguridad
     * @throws FileNotFoundException Error cuando no se encuentra el archivo
     * @throws IllegalArgumentException Error cuando los datos en el fichero no son datos válidos de un cliente
     */
    public void borrarCliente(String nif) throws IOException, FileNotFoundException, IllegalArgumentException{
        boolean salir = false, copiaCorrecta = true;
        File copiaSeguridad = new File("clientes.dat.bak");
        DataInputStream dis = null;
        DataOutputStream dos = null;
        if(fichero.exists()){
            if(fichero.renameTo(copiaSeguridad)){
                fichero.createNewFile();
                try{
                    dis = new DataInputStream(new FileInputStream(copiaSeguridad));
                    dos = new DataOutputStream(new FileOutputStream(fichero));
                    do{
                        try{
                            Cliente c = new Cliente(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readDouble());
                            if(!c.getNif().toUpperCase().equals(nif.toUpperCase())){
                                try{
                                    dos.writeUTF(c.getNif());
                                    dos.writeUTF(c.getNombre());
                                    dos.writeUTF(c.getTelefono());
                                    dos.writeUTF(c.getDireccion());
                                    dos.writeDouble(c.getDeuda());
                                } catch(IOException ex){
                                    copiaCorrecta = false;
                                    throw new IOException("Error al escribir en el archivo.");
                                }
                            }
                        } catch (EOFException ex){
                            salir = true;
                        }
                    } while(!salir);
                } catch(FileNotFoundException ex){
                    copiaCorrecta = false;
                    throw new FileNotFoundException("No se ha encontrado el archivo.");
                }
                dis.close();
                dos.close();
                if(!copiaCorrecta){
                    if(fichero.exists())
                        fichero.delete();
                    copiaSeguridad.renameTo(fichero);
                    throw new IOException("Error al borrar el cliente.");
                }
                copiaSeguridad.delete();
            } else{
                throw new IOException("No se ha podido crear la copia de seguridad.");
            }
        } else{
            throw new FileNotFoundException("No se ha encontrado el archivo, reinicie la aplicación.");
        }
    }
    
    /**
     * Elimina el fichero donde tenemos almacenados los clientes
     */
    public void borrarFichero(){
        fichero.delete();
    }
}