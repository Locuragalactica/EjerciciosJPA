package libreria.servicios;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    private final LibroDAO DAO;

    public LibroServicio() {
        this.DAO = new LibroDAO();
    }

    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    // este método persiste un registro de tipo Libro en la base de datos
    // a través del método guardar() de la clase DAO.
    public Libro crearLibro(String titulo, Integer anio, Autor autor, Editorial editorial) {

        Libro libro = new Libro();
        try {

            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo del libro");
            }
            if (anio == null || anio < 0) {
                throw new Exception("Debe indicar el año");
            }
            if (autor == null) {
                throw new Exception("Autor nulo");
            }
            if (editorial == null) {
                throw new Exception("Editorial nulo");
            }
            
            //Ver al final la explicacion UUID.randomUUID().toString()
            libro.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            libro.setIsbn((long) (int) (Math.random() * 100000 + 1));
            libro.setTitulo(titulo);
            libro.setAnio(anio);

            int ejemplares = (int) (Math.random() * 100 + 1);
            int ejemplaresPrestados = (int) (Math.random() * 10 + 1);

            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);

            libro.setAlta(Boolean.TRUE);
            libro.setAutor(autor);
            libro.setEditorial(editorial);

            DAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarPorIsbn(Long isbn) {
        try {

            //Me da error cuando apreto enter, deberia ser null ver esto
            if (isbn == null || isbn == 0) {
                throw new Exception("Debe indicar el ISBN del Libro");
            }

            boolean AutorEsta = false;
            for (Libro aux : listarLibros()) {
                if (aux.getIsbn().equals(isbn)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El ISBN del Libro no se encuentra cargado");
            }

            return DAO.buscarPorIsbn(isbn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Libro buscarPorTitulo(String titulo) {
        try {

            if (titulo == null || titulo.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo del Libro");
            }

            boolean TituloEsta = false;
            for (Libro aux : listarLibros()) {
                if (aux.getTitulo().equals(titulo)) {
                    TituloEsta = true;

                }
            }

            if (!TituloEsta) {
                throw new Exception("El titulo del Libro no se encuentra cargado");
            }

            return DAO.buscarPorTitulo(titulo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean eliminarPorIsbn(Long isbn) {
        try {
            Libro libro = DAO.buscarPorIsbn(isbn);
            DAO.eliminar(libro);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminarPorIsbn() {
        try {
           
            System.out.println("Ingrese el ISBN del libro a eliminar");
            System.out.print("ISBN: ");
            Long isbnBuscar = leer.nextLong();
            
            Libro libro = DAO.buscarPorIsbn(isbnBuscar);
                                
            DAO.eliminar(libro);
            
            System.out.println("");
            System.out.println("La eliminación del Libro fue exitosa");
            
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<Libro> listarLibros() {
        try {
            return DAO.listarTodosLibros();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void modificarLibro(Long isbn, String titulo) {
        try {
            Libro libro = DAO.buscarPorIsbn(isbn);

            libro.setTitulo(titulo);

            DAO.editar(libro);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public void setearLibroPrestado(String titulo, Integer cantPrestado) {
        try {
            Libro libro = DAO.buscarPorTitulo(titulo);

            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + cantPrestado);
            libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - cantPrestado);

            DAO.editar(libro);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }
    
    public void modificarLibro() {
        try {
            
            System.out.println("Ingrese el ISBN del libro a modificar");
            System.out.print("ISBN: ");
            Long isbnBuscar = leer.nextLong();
                      
            Libro libro = DAO.buscarPorIsbn(isbnBuscar);
            
            System.out.println("Ingrese el Nuevo Titulo del libro");
            System.out.print("Nuevo Titulo: ");
            String nuevoTitulo = leer.next();

            libro.setTitulo(nuevoTitulo);

            DAO.editar(libro);
            System.out.println("");
            System.out.println("La modicación del Libro fue exitosa");

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public List<Libro> buscarLibroPorNombreAutor(String nombre) {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Autor");
            }

            boolean AutorEsta = false;
            for (Autor aux : autorServicio.listarAutores()) {
                if (aux.getNombre().equals(nombre)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El nombre del Autor no se encuentra cargado");
            }

            return DAO.buscarLibroNombreAutor(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    public List<Libro> buscarLibroPorNombreEditorial(String nombre) {
        try {

            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre de la Editorial");
            }

            boolean AutorEsta = false;
            for (Editorial aux : editorialServicio.listarEditoriales()) {
                if (aux.getNombre().equals(nombre)) {
                    AutorEsta = true;

                }
            }

            if (!AutorEsta) {
                throw new Exception("El nombre de la Editorial no se encuentra cargado");
            }

            return DAO.buscarLibroNombreEditorial(nombre);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //////////////
    public Libro crearLibroPorDatoIngresado() {

        Libro libro = new Libro();
        Scanner leer = new Scanner(System.in).useDelimiter("\n");

        try {

            libro.setId(UUID.randomUUID().toString().replaceAll("-", ""));
            libro.setIsbn((long) (int) (Math.random() * 100000 + 1));

            System.out.println("Ingrese el titulo del libro");
            System.out.print("Titulo: ");
            String tituloLibro = leer.next();
            libro.setTitulo(tituloLibro);
            
            if (tituloLibro == null || tituloLibro.trim().isEmpty()) {
                throw new Exception("Debe indicar el titulo del libro");
            }

            System.out.println("Ingrese el año del libro");
            System.out.print("Año: ");
            Integer anioLibro = leer.nextInt();
            libro.setAnio(anioLibro);
            
            if (anioLibro == null || anioLibro == 0) {
                throw new Exception("Debe indicar el año del libro");
            }

            int ejemplares = (int) (Math.random() * 100 + 1);
            int ejemplaresPrestados = (int) (Math.random() * 10 + 1);

            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);

            libro.setAlta(Boolean.TRUE);

            System.out.println("Informacion del Autor del libro");

            System.out.println("Ingrese el nombre del Autor");
            System.out.print("Nombre: ");
            String nombreAutorIngresado = leer.next();
            boolean AutorEsta = false;
            
            if (nombreAutorIngresado == null || nombreAutorIngresado.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del Autor");
            }

            for (Autor aux : autorServicio.listarAutores()) {
                if (aux.getNombre().equals(nombreAutorIngresado)) {
                    System.out.println("El Autor ya existe");
                    //Creo que esta linea no sirve porque le estoy seteando a libro el autor aux
                    autorServicio.buscarPorNombre(aux.getNombre());
                    libro.setAutor(aux);
                    System.out.println("Autor ingresado correctamente");
                    AutorEsta = true;
                }

            }

            if (!AutorEsta) {
                System.out.println("Autor nuevo");
                libro.setAutor(autorServicio.crearAutorPorDatoIngresado());
            }

            System.out.println("Informacion de la Editorial del libro");

            System.out.println("Ingrese el nombre de la Editorial");
            System.out.print("Nombre: ");
            String nombreEditorialIngresado = leer.next();
            boolean EditorialEsta = false;
            
            if (nombreEditorialIngresado == null || nombreEditorialIngresado.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre de la Editorial");
            }

            for (Editorial aux : editorialServicio.listarEditoriales()) {
                if (aux.getNombre().equals(nombreEditorialIngresado)) {
                    System.out.println("La Editorial ya existe");
                    //Creo que esta linea no sirve porque le estoy seteando a libro la editorial aux
                    editorialServicio.buscarPorNombre(aux.getNombre());
                    libro.setEditorial(aux);
                    System.out.println("Editorial ingresada correctamente");
                    EditorialEsta = true;
                }

            }

            if (!EditorialEsta) {
                System.out.println("Editorial nueva");
                libro.setEditorial(editorialServicio.crearEditorialPorDatoIngresado());
            }

            if (validarLibroNuevo(tituloLibro, anioLibro, nombreAutorIngresado, nombreEditorialIngresado)) {
                System.out.println("");
                System.out.println("El libro ya se encuentra en la lista");
                System.out.println("No se puede registrar");
            } else {
                //ls.crearLibro(null, tituloLibro, anioLibro, ejemplaresLibro, 0, ejemplaresLibro, true, autor, editorial);
                DAO.guardar(libro);
                System.out.println("");
                System.out.println("Libro cargado satisfactoriamente");
            }

            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean validarLibroNuevo(String tituloL, Integer anioL, String autorL, String editorialL) {
        Boolean esta = false;
        for (Libro libroAux : listarLibros()) {
            int anioN = anioL;
            if (libroAux.getTitulo().equalsIgnoreCase(tituloL) && libroAux.getAnio() == anioN && libroAux.getAutor().getNombre().equalsIgnoreCase(autorL) && libroAux.getEditorial().getNombre().equalsIgnoreCase(editorialL)) {
                esta = true;
                break;
            }
        }
        return esta;
    }

}


/*
UUID.randomUUID().toString() es una expresión en Java que se utiliza para generar un
identificador único universal (UUID, por sus siglas en inglés) en formato de cadena de texto.

El objeto UUID en Java representa un identificador único universal según el estándar
definido por la especificación de la versión 4 de UUID. Un UUID es un valor de 128 bits
que se utiliza para identificar de manera única una entidad o recurso en un sistema.

La expresión UUID.randomUUID() genera un nuevo objeto UUID de forma aleatoria utilizando
algoritmos criptográficamente seguros. Cada vez que se llama a randomUUID(), se genera un
nuevo UUID único.

El método toString() en el objeto UUID convierte el UUID en una cadena de texto representando
su valor en formato de caracteres. La cadena generada tiene un formato estándar, generalmente
con grupos de caracteres separados por guiones, por ejemplo: "f47ac10b-58cc-4372-a567-0e02b2c3d479".

La utilización de UUID.randomUUID().toString() es común en casos donde se requiere generar
un identificador único para propósitos como asignar identificadores a objetos, generar claves
de acceso únicas, o cualquier otra situación donde se necesite una identificación única.

Es importante destacar que los UUID generados por randomUUID() son muy poco probables de
repetirse, lo que los hace adecuados para su uso en escenarios distribuidos y sistemas de
alta concurrencia.
*/