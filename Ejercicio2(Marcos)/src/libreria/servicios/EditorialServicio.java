package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {

    private final EditorialDAO DAO;
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public EditorialServicio() {
        this.DAO = new EditorialDAO(); 
    }

    // este método persiste un registro de tipo Editorial en la base de datos
    // a través del método guardar() de la clase DAO.
    public Editorial crearEditorial(String nombre) {

        Editorial editorial = new Editorial();
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre de la Editorial");
            }

            editorial.setNombre(nombre);
            editorial.setAlta(Boolean.TRUE);

            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Editorial buscarPorId(Integer id) {
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Editorial buscarPorNombre(String nombre) {
        try {
            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorId(Integer id) {
        try {
            Editorial editorial = DAO.buscarPorId(id);
            DAO.eliminar(editorial);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarPorId() {
        try {
            
            System.out.println("Ingrese el ID de la Editorial a eliminar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
            
            Editorial editorial = DAO.buscarPorId(idBuscar);
            
            DAO.eliminar(editorial);
            
            System.out.println("");
            System.out.println("La eliminación de la Editorial fue exitosa");
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public List<Editorial> listarEditoriales() {
        try {
            return DAO.listarTodasEditoriales();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void modificarEditorial(Integer id, String nombre) {
        try {
            Editorial editorial = DAO.buscarPorId(id);

            editorial.setNombre(nombre);

            DAO.editar(editorial);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public void modificarEditorial() {
        try {
            
            System.out.println("Ingrese el ID de la Editorial a modificar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();

            Editorial editorial = DAO.buscarPorId(idBuscar);
            
            if (idBuscar == null || idBuscar == 0) {
                throw new Exception("Debe indicar el ID de la Editorial");
            }
            
            System.out.println("Ingrese el Nuevo Nombre de la Editorial");
            System.out.print("Nuevo Nombre: ");
            String nuevoNombre = leer.next();
            
            if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nuevo nombre de la Editorial");
            }

            editorial.setNombre(nuevoNombre);

            DAO.editar(editorial);
            System.out.println("");
            System.out.println("La modicación de la Editorial fue exitosa");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    ////////////////////
    public Editorial crearEditorialPorDatoIngresado() {

        Editorial editorial = new Editorial();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre de la Editorial");
            System.out.print("Nombre: ");
            String nombreEditorialIngresado = leer.next();
            editorial.setNombre(nombreEditorialIngresado);

            if (editorial.getNombre() == null || editorial.getNombre().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el nombre de la Editorial");
            }

            editorial.setAlta(Boolean.TRUE);

            if (validarEditorialNuevo(nombreEditorialIngresado)) {
                System.out.println("");
                System.out.println("La Editorial ya se encuentra en la lista");
                System.out.println("No se puede registrar");
            } else {

                DAO.guardar(editorial);
                System.out.println("");
                System.out.println("Editorial cargada satisfactoriamente");
            }

            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            /*
            System.out.println("Ingrese NUEVAMENTE el nombre de la Editorial");
            System.out.print("Nombre: ");
            editorial.setNombre(leer.next());

            editorial.setAlta(Boolean.TRUE);
            DAO.guardar(editorial);
            return editorial;*/

            //Ver esto, el null creo que va
            //crearEditorialPorDatoIngresado();
            //return null;
        }
    }

    public Boolean validarEditorialNuevo(String nombreEditorial) {
        Boolean esta = false;
        for (Editorial editorialAux : listarEditoriales()) {
            if (editorialAux.getNombre().equalsIgnoreCase(nombreEditorial)) {
                esta = true;
                break;
            }
        }
        return esta;
    }

}
