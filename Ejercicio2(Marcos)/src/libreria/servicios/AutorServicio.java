package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {

    private final AutorDAO DAO;
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public AutorServicio() {
        this.DAO = new AutorDAO();
    }

    // este método persiste un registro de tipo Autor en la base de datos
    // a través del método guardar() de la clase DAO.
    public Autor crearAutor(String nombre) {

        Autor autor = new Autor();
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Autor");
            }

            autor.setNombre(nombre);
            autor.setAlta(Boolean.TRUE);

            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor buscarPorId(Integer id) {
        try {
            return DAO.buscarPorId(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor buscarPorNombre(String nombre) {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Autor");
            }

            boolean AutorEsta = false;
            for (Autor aux : listarAutores()) {
                if (aux.getNombre().equals(nombre)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El Autor no se encuentra cargado");
            }

            return DAO.buscarPorNombre(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorId(Integer id) {
        try {
            Autor autor = DAO.buscarPorId(id);
            DAO.eliminar(autor);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarPorId() {
        try {
            
            System.out.println("Ingrese el ID del Autor a eliminar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
            
            Autor autor = DAO.buscarPorId(idBuscar);
          
            DAO.eliminar(autor); 
            
            System.out.println("");
            System.out.println("La eliminación del Autor fue exitosa");
            
            return true;
        } catch (Exception e) {
            //System.out.println("Debe indicar el ID del Autor a eliminar");
            System.out.println(e.getMessage());          
            return false;
        }
    }

    public List<Autor> listarAutores() {
        try {
            return DAO.listarTodosAutores();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void modificarAutor(Integer id, String nombre) {
        try {
            Autor autor = DAO.buscarPorId(id);

            autor.setNombre(nombre);

            DAO.editar(autor);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public void modificarAutor() {
        try {
            
            System.out.println("Ingrese el ID del Autor a modificar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
            
            Autor autor = DAO.buscarPorId(idBuscar);
            
            if (idBuscar == null || idBuscar == 0) {
                throw new Exception("Debe indicar el ID del Autor");
            }
            
            System.out.println("Ingrese el Nuevo Nombre del Autor");
            System.out.print("Nuevo Nombre: ");
            String nuevoNombre = leer.next();
            
            if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nuevo nombre del Autor");
            }

            autor.setNombre(nuevoNombre);
 
            DAO.editar(autor);
            System.out.println("");
            System.out.println("La modicación del Autor fue exitosa");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    ////////////////
    public Autor crearAutorPorDatoIngresado() {

        Autor autor = new Autor();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el nombre del Autor");
            System.out.print("Nombre: ");
            String nombreAutorIngresado = leer.next();
            autor.setNombre(nombreAutorIngresado);

            if (autor.getNombre() == null || autor.getNombre().trim().isEmpty()) {
                throw new Exception("Error: Debe indicar el nombre del Autor");
            }

            //Cuando sale la Exception, no setea el Alta, ni guarda el Autor mediante DAO.                                  
            autor.setAlta(Boolean.TRUE);

            if (validarAutorNuevo(nombreAutorIngresado)) {
                System.out.println("");
                System.out.println("El Autor ya se encuentra en la lista");
                System.out.println("No se puede registrar");
            } else {

                DAO.guardar(autor);
                System.out.println("");
                System.out.println("Autor cargado satisfactoriamente");
            }

            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
            /*
            System.out.println("Ingrese NUEVAMENTE el nombre del Autor");
            System.out.print("Nombre: ");
            autor.setNombre(leer.next());

            autor.setAlta(Boolean.TRUE);

            DAO.guardar(autor);
            return autor;*/

            //Ver esto, el null creo que va
            //crearAutorPorDatoIngresado();
            //return null;
        }

    }

    public Boolean validarAutorNuevo(String nombreAutor) {
        Boolean esta = false;
        for (Autor autorAux : listarAutores()) {
            if (autorAux.getNombre().equalsIgnoreCase(nombreAutor)) {
                esta = true;
                break;
            }
        }
        return esta;
    }

}
