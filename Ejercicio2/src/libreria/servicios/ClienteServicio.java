package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

public class ClienteServicio {

    private final ClienteDAO DAO = new ClienteDAO();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public Cliente crearCliente(Integer documento, String nombre, String apellido, String telefono) {
        Cliente cliente = new Cliente();
        try {
            cliente.setDocumento(documento);
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setTelefono(telefono);
            DAO.guardarCliente(cliente);
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Cliente ingresarCliente() {
        Cliente cliente = new Cliente();
        try {
            System.out.println("Ingrese el DNI");
            cliente.setDocumento(leer.nextInt());
            System.out.println("Ingrese el nombre");
            cliente.setNombre(leer.next());
            System.out.println("Ingrese el apellido");
            cliente.setApellido(leer.next());
            System.out.println("Ingrese el telefono");
            cliente.setTelefono(leer.next());
            DAO.guardarCliente(cliente);
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
