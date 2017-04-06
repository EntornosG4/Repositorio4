import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Clase principal de la práctica 6
 * @author José Manuel Moreno Córdoba
 */
public class Principal {

    /**
     *  Método principal que se encarga de mostrar la interfaz al usuario
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            Cliente c = null;
            Repositorio miFichero = new Repositorio();
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String menu = null, nif = null, nombre = null, telefono = null, direccion = null;
            double deuda = -1;
            boolean salir = false;
            do{
                
                menu = mostrarMenu();
                switch(menu){
                    case "1":
                        System.out.print("Introduce el NIF del cliente: ");
                        nif = br.readLine();
                        System.out.print("Introduce el nombre del cliente: ");
                        nombre = br.readLine();
                        System.out.print("Introduce el teléfono del cliente: ");
                        telefono = br.readLine();
                        System.out.print("Introduce la dirección del cliente: ");
                        direccion = br.readLine();
                        System.out.print("Introduce la deuda del cliente (Si no tiene introduce 0): ");
                        deuda = Double.parseDouble(br.readLine());
                        try{
                            c = new Cliente(nif, nombre, telefono, direccion, deuda);
                            miFichero.agregarCliente(c);
                        }
                        catch(FileNotFoundException ex){ System.err.println(ex.getMessage()); }
                        catch(IOException ex){ System.err.println(ex.getMessage()); }
                        catch(IllegalArgumentException ex) { System.err.println(ex.getMessage()); }
                        break;
                    case "2":
                        try{
                            mostrarArrayList(miFichero.obtenerClientes());
                        } 
                        catch(FileNotFoundException ex){ System.err.println(ex.getMessage()); }
                        catch(IOException ex){ System.err.println(ex.getMessage()); }
                        catch(IllegalArgumentException ex) { System.err.println(ex.getMessage()); }
                        break;
                    case "3":
                        System.out.print("Introduce el NIF del cliente que quieres buscar: ");
                        String dni = br.readLine();
                        try{
                            mostrarArrayList(miFichero.buscarCliente(dni));
                        }
                        catch(FileNotFoundException ex){ System.err.println(ex.getMessage()); }
                        catch(IOException ex){ System.err.println(ex.getMessage()); }
                        catch(IllegalArgumentException ex) { System.err.println(ex.getMessage()); }
                        break;
                    case "4":
                        System.out.print("Introduce el NIF del cliente a borrar: ");
                        String dni1 = br.readLine();
                        try{
                            miFichero.borrarCliente(dni1);
                        }
                        catch(FileNotFoundException ex){ System.err.println(ex.getMessage()); }
                        catch(IOException ex){ System.err.println(ex.getMessage()); }
                        catch(IllegalArgumentException ex) { System.err.println(ex.getMessage()); }
                        break;
                    case "5":
                        miFichero.borrarFichero();
                        break;
                    case "6":
                        salir = true;
                        break;
                    default:
                        System.out.println("No has elegido una opción válida.");
                        break;
                }
                System.out.println("Presione una tecla para continuar...");
                br.readLine();
            } while(!salir);
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
    
    /**
     * Muestra el contenido de un ArrayList de clientes por pantalla
     * @param array ArrayList de clientes
     */
    public static void mostrarArrayList(ArrayList<Cliente> array){
        for(Cliente c: array){
            System.out.println(c.toString());
        }
    }
    
    public static String mostrarMenu() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("1. Añadir cliente.");
                System.out.println("2. Listar clientes.");
                System.out.println("3. Buscar clientes.");
                System.out.println("4. Borrar cliente.");
                System.out.println("5. Borrar fichero de clientes.");
                System.out.println("6. Salir de la aplicación.");
                System.out.print("Elige una opción: ");
                String menu = br.readLine();
                return menu;
    }
    
}
