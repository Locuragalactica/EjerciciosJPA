package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

public class ClienteServicio {

    private final ClienteDAO DAO;
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public ClienteServicio() {
        this.DAO = new ClienteDAO();
    }

    public Cliente crearCliente(String nombre, String apellido, String telefono) {

        Cliente cliente = new Cliente();
        try {

            /*
            if (documento == null) {
                throw new Exception("Debe indicar el documento del cliente");
            }*/
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del cliente");
            }
            if (apellido == null || apellido.trim().isEmpty()) {
                throw new Exception("Debe indicar el apellido del cliente");
            }
            if (telefono == null || telefono.trim().isEmpty()) {
                throw new Exception("Debe indicar el telefono del cliente");
            }

            cliente.setDocumento((long) (int) (Math.random() * 50000000 + 1));
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);

            DAO.guardar(cliente);
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Cliente> listarClientes() {
        try {
            return DAO.listarTodosClientes();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cliente buscarPorNombreCliente(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    ////////////
    public Cliente crearClientePorDatoIngresado() {

        Cliente cliente = new Cliente();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {
            
            //Faltan todas las validaciones

            System.out.println("Ingrese el nombre del Cliente");
            System.out.print("Nombre: ");
            String nombreCliente = leer.next();
            cliente.setNombre(nombreCliente);
            
            if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el nombre del Cliente");
            }

            System.out.println("Ingrese el apellido del Cliente");
            System.out.print("Apellido: ");
            String apellidoCliente = leer.next();
            cliente.setApellido(apellidoCliente);
            
            if (cliente.getApellido()== null || cliente.getApellido().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el apellido del Cliente");
            }

            cliente.setDocumento((long) (int) (Math.random() * 50000000 + 1));

            System.out.println("Ingrese el telefono del Cliente");
            System.out.print("Telefono: ");
            String telefonoCliente = leer.next();
            cliente.setTelefono(telefonoCliente);
            
            if (cliente.getTelefono() == null || cliente.getTelefono().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el telefono del Cliente");
            }

            if (validarClienteNuevo(nombreCliente, apellidoCliente, telefonoCliente)) {
                System.out.println("");
                System.out.println("Cliente existente, ya esta registrado");
            } else {

                DAO.guardar(cliente);
                System.out.println("");
                System.out.println("Cliente cargado satisfactoriamente");
            }

            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /////////
    public Boolean validarClienteNuevo(String nombreCliente, String apellidoCliente, String telefonoCliente) {
        Boolean esta = false;
        for (Cliente clienteAux : listarClientes()) {
            if (clienteAux.getNombre().equals(nombreCliente) && clienteAux.getApellido().equals(apellidoCliente) && clienteAux.getTelefono().equals(telefonoCliente)) {
                esta = true;
                break;
            }
        }
        return esta;
    }
    
    
    public void modificarCliente() {
        try {
            
            System.out.println("Ingrese el Nombre del Cliente a modificar");
            System.out.print("Nombre: ");
            String nombreBuscar = leer.next();
            
            if (nombreBuscar == null || nombreBuscar.trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el nombre del Cliente");
            }
                      
            Cliente cliente = DAO.buscarPorNombre(nombreBuscar);
            
            System.out.println("Ingrese el Nuevo Nombre del Cliente");
            System.out.print("Nuevo Nombre: ");
            String nuevoNombre = leer.next();

            cliente.setNombre(nuevoNombre);

            DAO.editar(cliente);
            System.out.println("");
            System.out.println("La modicación del nombre del Cliente fue exitosa");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public Cliente buscarPorNombre(String nombre) {
        try {

            //Me da error cuando apreto enter, deberia ser null ver esto
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Cliente");
            }

            boolean AutorEsta = false;
            for (Cliente aux : listarClientes()) {
                if (aux.getNombre().equals(nombre)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El nombre del Cliente no se encuentra cargado");
            }

            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public boolean eliminarPorId() {
        try {
           
            System.out.println("Ingrese el ID del Cliente a eliminar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
            
            Cliente cliente = DAO.buscarPorId(idBuscar);
          
            DAO.eliminar(cliente);
            
            System.out.println("");
            System.out.println("La eliminación del Cliente fue exitosa");
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    

}
