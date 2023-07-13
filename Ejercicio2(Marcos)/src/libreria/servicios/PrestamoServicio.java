package libreria.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoDAO;

public class PrestamoServicio {

    private final PrestamoDAO DAO;

    private final LibroServicio libroServicio = new LibroServicio();
    private final ClienteServicio clienteServicio = new ClienteServicio();
    
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public PrestamoServicio() {
        this.DAO = new PrestamoDAO();
    }

    public Prestamo crearPrestamo(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) {

        Prestamo prestamo = new Prestamo();
        try {

            if (fechaPrestamo == null) {
                throw new Exception("Debe indicar la fecha de prestamo del libro");
            }
            if (fechaDevolucion == null) {
                throw new Exception("Debe indicar la fecha de devolucion del libro");
            }
            if (libro == null) {
                throw new Exception("No se encontro el libro para el prestamo solicitado");
            }
            if (cliente == null) {
                throw new Exception("No se encontro el cliente para el prestamo solicitado");
            }

            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            prestamo.setLibro(libro);
            prestamo.setCliente(cliente);

            DAO.guardar(prestamo);
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Prestamo> listarPrestamos() {
        try {
            return DAO.listarTodosPrestamos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    ////////////////
    public Prestamo crearPrestamoPorDatoIngresado() {

        Prestamo prestamo = new Prestamo();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese la fecha de prestamo del libro (MM/dd/yyyy)");
            System.out.print("Fecha de prestamo: ");

            String fechaPrestamoIngresada = leer.next();

            Date fechaPrestamo = new SimpleDateFormat("MM/dd/yyyy").parse(fechaPrestamoIngresada);

            prestamo.setFechaPrestamo(fechaPrestamo);

            
            System.out.println("Ingrese la fecha de devolucion del libro (MM/dd/yyyy)");
            System.out.print("Fecha de devolucion: ");

            String fechaDevolucionIngresada = leer.next();

            Date fechaDevolucion = new SimpleDateFormat("MM/dd/yyyy").parse(fechaDevolucionIngresada);

            prestamo.setFechaDevolucion(fechaDevolucion);
            
            //prestamo.setFechaDevolucion(null);

            System.out.println("Ingrese el titulo del libro a prestar");
            System.out.print("Titulo del libro: ");

            String TituloLibroPrestar = leer.next();

            Libro libroPrestar = libroServicio.buscarPorTitulo(TituloLibroPrestar);
            
            System.out.print("Cantidad: ");
           
            int cantLibroPrestado = leer.nextInt();
            
            libroServicio.setearLibroPrestado(TituloLibroPrestar, cantLibroPrestado);
            
            prestamo.setLibro(libroPrestar);

            System.out.println("Ingrese el nombre del Cliente");
            System.out.print("Nombre del cliente: ");

            String NombreCliente = leer.next();

            Cliente clientePrestar = clienteServicio.buscarPorNombreCliente(NombreCliente);

            prestamo.setCliente(clientePrestar);

            if (validarPrestamoNuevo(fechaPrestamo, fechaDevolucion, TituloLibroPrestar, NombreCliente)) {
                System.out.println("");
                System.out.println("Prestamo existente, ya esta registrado");
            } else {

                DAO.guardar(prestamo);
                System.out.println("");
                System.out.println("Prestamo cargado satisfactoriamente");
            }

            return prestamo;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /////////
    public Boolean validarPrestamoNuevo(Date fechaPrestamo, Date fechaDevolucion, String tituloLibro, String nombreCliente) {
        Boolean esta = false;
        for (Prestamo prestamoAux : listarPrestamos()) {
            if (prestamoAux.getFechaPrestamo().equals(fechaPrestamo) && prestamoAux.getFechaDevolucion().equals(fechaDevolucion) && prestamoAux.getLibro().getTitulo().equalsIgnoreCase(tituloLibro) && prestamoAux.getCliente().getNombre().equalsIgnoreCase(nombreCliente)) {
                esta = true;
                break;
            }
        }
        return esta;
    }

    public List<Prestamo> buscarPrestamoPorNombreCliente(String nombre) {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Cliente");
            }

            boolean AutorEsta = false;
            for (Cliente aux : clienteServicio.listarClientes()) {
                if (aux.getNombre().equals(nombre)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El Cliente no se encuentra cargado");
            }

            return DAO.buscarPrestamoNombreCliente(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public void modificarPrestamo() {
        try {
            
            System.out.println("Ingrese el ID del Prestamo a modificar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
                      
            Prestamo prestamo = DAO.buscarPorId(idBuscar);
            
            System.out.println("Ingrese el Nueva Fecha de Devolucion del Prestamo (MM/dd/yyyy)");
            System.out.print("Nuevo Fecha de Devolucion: ");
                        
            String fechaDevolucionIngresada = leer.next();

            Date fechaDevolucionNueva = new SimpleDateFormat("MM/dd/yyyy").parse(fechaDevolucionIngresada);

            prestamo.setFechaDevolucion(fechaDevolucionNueva);
            

            DAO.editar(prestamo);
            System.out.println("");
            System.out.println("La modicación de la Fecha de Devolucion del Prestamo fue exitosa");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public boolean eliminarPorId() {
        try {
           
            System.out.println("Ingrese el ID del Prestamo a eliminar");
            System.out.print("ID: ");
            Integer idBuscar = leer.nextInt();
            
            Prestamo prestamo = DAO.buscarPorId(idBuscar);
          
            DAO.eliminar(prestamo);
            
            System.out.println("");
            System.out.println("La eliminación del Prestamo fue exitosa");
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    
    /////////////
    
    public Prestamo crearDevolucionPorDatoIngresado() {

        Prestamo prestamo = new Prestamo();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            System.out.println("Ingrese el titulo del libro a devolver");
            System.out.print("Titulo del libro: ");

            String TituloLibroDevolver = leer.next();

            Prestamo prestamoDevolver = buscarPrestamoPorTituloLibro(TituloLibroDevolver);
            
            System.out.println("Ingrese la fecha de devolucion del libro (MM/dd/yyyy)");
            System.out.print("Fecha de devolucion: ");

            String fechaDevolucionIngresada = leer.next();

            Date fechaDevolucion = new SimpleDateFormat("MM/dd/yyyy").parse(fechaDevolucionIngresada);
                      
            
            prestamoDevolver.setFechaDevolucion(fechaDevolucion);
            
   
            
            if (validarDevolucion(fechaDevolucion, TituloLibroDevolver)) {
                System.out.println("");
                System.out.println("Prestamo existente, ya esta registrado");
            } else {

                DAO.editar(prestamoDevolver);
                System.out.println("");
                System.out.println("Prestamo devuelto satisfactoriamente");
            }

            return prestamoDevolver;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Boolean validarDevolucion(Date fechaDevolucion, String tituloLibro) {
        Boolean esta = false;
        for (Prestamo prestamoAux : listarPrestamos()) {
            if (prestamoAux.getFechaDevolucion().equals(fechaDevolucion) && prestamoAux.getLibro().getTitulo().equalsIgnoreCase(tituloLibro)) {
                esta = true;
                break;
            }
        }
        return esta;
    }
    
    
    public Prestamo buscarPrestamoPorTituloLibro(String titulo) {
        try {

            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo del Libro");
            }

            boolean AutorEsta = false;
            for (Libro aux : libroServicio.listarLibros()) {
                if (aux.getTitulo().equals(titulo)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El Libro no se encuentra cargado");
            }

            return DAO.buscarPrestamoPorTituloLibro(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    

}
