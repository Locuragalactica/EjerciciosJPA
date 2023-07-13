package libreria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.servicios.AutorServicio;
import libreria.servicios.ClienteServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.LibroServicio;
import libreria.servicios.PrestamoServicio;

public class Menu {

    private Scanner leer = new Scanner(System.in).useDelimiter("\n");

    private final LibroServicio libroServicio = new LibroServicio();
    private final AutorServicio autorServicio = new AutorServicio();
    private final EditorialServicio editorialServicio = new EditorialServicio();
    private final ClienteServicio clienteServicio = new ClienteServicio();
    private final PrestamoServicio prestamoServicio = new PrestamoServicio();

    public void ejecucion1() {

        //Creacion de autores
        Autor a1 = autorServicio.crearAutor("Autor 1");
        Autor a2 = autorServicio.crearAutor("Autor 2");

        //autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
        //Creacion de editoriales
        Editorial e1 = editorialServicio.crearEditorial("Editorial 1");
        Editorial e2 = editorialServicio.crearEditorial("Editorial 2");

        //editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));     
        //Creacion de libros
        Libro l1 = libroServicio.crearLibro("Libro 1", 1991, a1, e1);
        Libro l2 = libroServicio.crearLibro("Libro 2", 1992, a2, e2);
        Libro l3 = libroServicio.crearLibro("Libro 3", 1993, a1, e1);
        Libro l4 = libroServicio.crearLibro("Libro 4", 1994, a1, e1);
        Libro l5 = libroServicio.crearLibro("Libro 5", 1995, a1, e1);

        Cliente c1 = clienteServicio.crearCliente("NombreCliente1", "ApellidoCliente1", "12345678");
        Cliente c2 = clienteServicio.crearCliente("NombreCliente2", "ApellidoCliente2", "87456321");

        //Falta cargar los Prestamos, que estan en el metodo ejecucion2();
        System.out.println("");
        System.out.println("Los libros registrados son:");
        libroServicio.listarLibros().forEach((l) -> System.out.println(l.toString()));
        System.out.println("");

        System.out.println("Autor por nombre:");
        System.out.println(autorServicio.buscarPorNombre("Autor 1"));
        System.out.println(autorServicio.buscarPorNombre("Autor 1").getNombre());
        System.out.println("");

        System.out.println("Libro por Isbn:");
        System.out.println(libroServicio.buscarPorIsbn(8712L));
        System.out.println("");

        System.out.println("Libro por titulo:");
        System.out.println(libroServicio.buscarPorTitulo("Libro 2"));
        System.out.println(libroServicio.buscarPorTitulo("Libro 2").getTitulo());
        System.out.println("");

        System.out.println("Libros por nombre de autor:");
        //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
        libroServicio.buscarLibroPorNombreAutor("Autor 1").forEach((l) -> System.out.println(l.toString()));
        System.out.println("");

        System.out.println("Libros por nombre de editorial:");
        //System.out.println(libroServicio.buscarLibroPorNombreEditorial("Editorial 2")); 
        libroServicio.buscarLibroPorNombreEditorial("Editorial 2").forEach((l) -> System.out.println(l.toString()));
        System.out.println("");

    }

    public void ejecucion2() {
        System.out.println("");
        System.out.println("----- MENU LIBRERIA -----");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Crear Autores, Libros, Editoriales, Clientes y Prestamos Preestablecidos");
        System.out.println("2. Crear Libros");
        System.out.println("3. Crear Autor");
        System.out.println("4. Crear Editorial");
        System.out.println("5. Imprimir los Libros registrados");
        System.out.println("6. Imprimir Autores registrados");
        System.out.println("7. Imprimir Editoriales registradas");
        System.out.println("8. Buscar Autor por nombre");
        System.out.println("9. Buscar Libro por ISBN");
        System.out.println("10. Buscar Libro por Titulo");
        System.out.println("11. Buscar Libros por nombre de Autor");
        System.out.println("12. Buscar Libros por nombre de Editorial");
        System.out.println("13. Crear Prestamo");
        System.out.println("14. Crear Cliente");
        System.out.println("15. Imprimir Prestamos registrados");
        System.out.println("16. Imprimir Clientes registrados");
        System.out.println("17. Buscar Prestamo por nombre de Cliente");
        System.out.println("18. Salir del programa");
        System.out.print("Opcion: ");
        String opc = leer.next();
        System.out.println("");
        switch (opc) {
            case "1":
                //Creacion de autores
                Autor a1 = autorServicio.crearAutor("Autor 1");
                Autor a2 = autorServicio.crearAutor("Autor 2");

                //autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                //Creacion de editoriales
                Editorial e1 = editorialServicio.crearEditorial("Editorial 1");
                Editorial e2 = editorialServicio.crearEditorial("Editorial 2");

                //editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));     
                //Creacion de libros
                Libro l1 = libroServicio.crearLibro("Libro 1", 1991, a1, e1);
                Libro l2 = libroServicio.crearLibro("Libro 2", 1992, a2, e2);
                Libro l3 = libroServicio.crearLibro("Libro 3", 1993, a1, e1);
                Libro l4 = libroServicio.crearLibro("Libro 4", 1994, a1, e1);
                Libro l5 = libroServicio.crearLibro("Libro 5", 1995, a1, e1);

                Cliente c1 = clienteServicio.crearCliente("NombreCliente1", "ApellidoCliente1", "12345678");
                Cliente c2 = clienteServicio.crearCliente("NombreCliente2", "ApellidoCliente2", "87456321");

                Date fechaPrestamo1 = null;
                Date fechaDevolucion1 = null;
                Date fechaPrestamo2 = null;
                Date fechaDevolucion2 = null;
                Date fechaPrestamo3 = null;
                Date fechaDevolucion3 = null;

                try {
                    String inputDate1 = "06/05/2023";
                    String inputDate2 = "06/10/2023";
                    fechaPrestamo1 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate1);
                    fechaDevolucion1 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate2);
                    //////
                    String inputDate3 = "02/01/2023";
                    String inputDate4 = "02/18/2023";
                    fechaPrestamo2 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate3);
                    fechaDevolucion2 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate4);
                    //////
                    /*
                    String inputDate5 = "05/03/2023";
                    String inputDate6 = "05/25/2023";
                    fechaPrestamo3 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate5);
                    fechaDevolucion3 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate6);*/
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                Prestamo p1 = prestamoServicio.crearPrestamo(fechaPrestamo1, fechaDevolucion1, l1, c1);
                Prestamo p2 = prestamoServicio.crearPrestamo(fechaPrestamo2, fechaDevolucion2, l4, c2);
                Prestamo p3 = prestamoServicio.crearPrestamo(new Date(2023 - 1900, 05 - 1, 03), new Date(2023 - 1900, 05 - 1, 25), l5, c2);

                System.out.println("Libros, Autores, Editoriales, Clientes y Prestamos cargados satisfactoriamente");

                ejecucion2();
                break;
            case "2":

                System.out.println("");
                libroServicio.crearLibroPorDatoIngresado();
                System.out.println("");

                ejecucion2();
                break;
            case "3":

                System.out.println("");
                autorServicio.crearAutorPorDatoIngresado();
                System.out.println("");

                ejecucion2();
                break;
            case "4":

                System.out.println("");
                editorialServicio.crearEditorialPorDatoIngresado();
                System.out.println("");

                ejecucion2();
                break;
            case "5":

                System.out.println("LIBROS REGISTRADOS");
                libroServicio.listarLibros().forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "6":

                System.out.println("AUTORES REGISTRADOS");
                autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "7":

                System.out.println("EDITORIALES REGISTRADAS");
                editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "8":

                System.out.println("Buscar Autor por nombre");
                System.out.print("Nombre: ");
                String nombreAutor = leer.next();

                System.out.println("AUTOR POR NOMBRE");
                System.out.println(autorServicio.buscarPorNombre(nombreAutor));
                System.out.println(autorServicio.buscarPorNombre(nombreAutor).getNombre());
                System.out.println("");

                ejecucion2();
                break;
            case "9":

                System.out.println("Ingrese el ISBN del Libro a buscar");
                System.out.print("ISBN: ");
                Long isbnBuscar = leer.nextLong();

                System.out.println("LIBRO POR ISBN");
                System.out.println(libroServicio.buscarPorIsbn(isbnBuscar));
                System.out.println("");

                ejecucion2();
                break;
            case "10":

                System.out.println("Buscar Libro por titulo");
                System.out.print("Titulo: ");
                String tituloLibro = leer.next();

                System.out.println("LIBRO POR TITULO");
                System.out.println(libroServicio.buscarPorTitulo(tituloLibro));
                System.out.println(libroServicio.buscarPorTitulo(tituloLibro).getTitulo());
                System.out.println("");

                ejecucion2();
                break;
            case "11":

                System.out.println("Buscar Libros por nombre de Autor");
                System.out.print("Nombre Autor: ");
                String autorNombre = leer.next();

                System.out.println("LIBROS POR NOMBRE DE AUTOR");
                //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
                libroServicio.buscarLibroPorNombreAutor(autorNombre).forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "12":

                System.out.println("Buscar Libros por nombre de Editorial");
                System.out.print("Nombre Editorial: ");
                String editorialNombre = leer.next();

                System.out.println("LIBROS POR NOMBRE DE EDITORIAL");
                //System.out.println(libroServicio.buscarLibroPorNombreEditorial("Editorial 2")); 
                libroServicio.buscarLibroPorNombreEditorial(editorialNombre).forEach((l) -> System.out.println(l.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "13":

                System.out.println("");
                prestamoServicio.crearPrestamoPorDatoIngresado();
                System.out.println("");
                ejecucion2();
                break;
            case "14":

                System.out.println("");
                clienteServicio.crearClientePorDatoIngresado();
                System.out.println("");
                ejecucion2();
                break;
            case "15":

                System.out.println("PRESTAMOS REGISTRADOS");
                prestamoServicio.listarPrestamos().forEach((p) -> System.out.println(p.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "16":

                System.out.println("CLIENTES REGISTRADOS");
                clienteServicio.listarClientes().forEach((c) -> System.out.println(c.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "17":

                System.out.println("Buscar Prestamos por nombre de Cliente");
                System.out.print("Nombre de Cliente: ");
                String clienteNombre = leer.next();

                System.out.println("PRESTAMOS POR NOMBRE DE CLIENTE");
                //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
                prestamoServicio.buscarPrestamoPorNombreCliente(clienteNombre).forEach((p) -> System.out.println(p.toString()));
                System.out.println("");

                ejecucion2();
                break;
            case "18":
                System.out.println("Ejecución finalizada");
                break;
            default:
                System.out.println("Opcion no válida");
                ejecucion2();
        }

    }

    //////
    public void ejecucion3() {
        System.out.println("");
        System.out.println("----- MENU LIBRERIA -----");
        System.out.println("Seleccione una opcion: ");
        System.out.println("1. Crear Autores, Libros, Editoriales, Clientes y Prestamos Preestablecidos");
        System.out.println("2. Menu libros");
        System.out.println("3. Menu Autores");
        System.out.println("4. Menu Editoriales");
        System.out.println("5. Menu Prestamos");
        System.out.println("6. Menu Clientes");
        System.out.println("7. Salir del programa");
        System.out.print("Opcion: ");
        String opc = leer.next();
        System.out.println("");
        switch (opc) {
            case "1":
                //Creacion de autores
                Autor a1 = autorServicio.crearAutor("Autor 1");
                Autor a2 = autorServicio.crearAutor("Autor 2");

                //autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                //Creacion de editoriales
                Editorial e1 = editorialServicio.crearEditorial("Editorial 1");
                Editorial e2 = editorialServicio.crearEditorial("Editorial 2");

                //editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));     
                //Creacion de libros
                Libro l1 = libroServicio.crearLibro("Libro 1", 1991, a1, e1);
                Libro l2 = libroServicio.crearLibro("Libro 2", 1992, a2, e2);
                Libro l3 = libroServicio.crearLibro("Libro 3", 1993, a1, e1);
                Libro l4 = libroServicio.crearLibro("Libro 4", 1994, a1, e1);
                Libro l5 = libroServicio.crearLibro("Libro 5", 1995, a1, e1);

                Cliente c1 = clienteServicio.crearCliente("NombreCliente1", "ApellidoCliente1", "12345678");
                Cliente c2 = clienteServicio.crearCliente("NombreCliente2", "ApellidoCliente2", "87456321");

                Date fechaPrestamo1 = null;
                Date fechaDevolucion1 = null;
                Date fechaPrestamo2 = null;
                Date fechaDevolucion2 = null;
                Date fechaPrestamo3 = null;
                Date fechaDevolucion3 = null;

                try {
                    String inputDate1 = "06/05/2023";
                    String inputDate2 = "06/10/2023";
                    fechaPrestamo1 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate1);
                    fechaDevolucion1 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate2);
                    //////
                    String inputDate3 = "02/01/2023";
                    String inputDate4 = "02/18/2023";
                    fechaPrestamo2 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate3);
                    fechaDevolucion2 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate4);
                    //////
                    /*
                    String inputDate5 = "05/03/2023";
                    String inputDate6 = "05/25/2023";
                    fechaPrestamo3 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate5);
                    fechaDevolucion3 = new SimpleDateFormat("MM/dd/yyyy").parse(inputDate6);*/
                } catch (ParseException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }

                Prestamo p1 = prestamoServicio.crearPrestamo(fechaPrestamo1, fechaDevolucion1, l1, c1);
                Prestamo p2 = prestamoServicio.crearPrestamo(fechaPrestamo2, fechaDevolucion2, l4, c2);
                Prestamo p3 = prestamoServicio.crearPrestamo(new Date(2023 - 1900, 05 - 1, 03), new Date(2023 - 1900, 05 - 1, 25), l5, c2);

                System.out.println("Libros, Autores, Editoriales, Clientes y Prestamos cargados satisfactoriamente");

                ejecucion3();
                break;

            case "2":

                System.out.println("");
                System.out.println("----- MENU LIBROS -----");
                System.out.println("Seleccione una opcion: ");
                System.out.println("1. Crear libro");
                System.out.println("2. Editar libro");
                System.out.println("3. Eliminar libro");
                System.out.println("4. Imprimir los Libros registrados");
                System.out.println("5. Buscar Libro por ISBN");
                System.out.println("6. Buscar Libro por Titulo");
                System.out.println("7. Buscar Libros por nombre de Autor");
                System.out.println("8. Buscar Libros por nombre de Editorial");

                System.out.println("9. Menu Libreria");
                System.out.print("Opcion: ");
                opc = leer.next();
                System.out.println("");

                switch (opc) {
                    case "1":
                        System.out.println("");
                        libroServicio.crearLibroPorDatoIngresado();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "2":
                        System.out.println("");
                        libroServicio.modificarLibro();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "3":
                        System.out.println("");                        
                        libroServicio.eliminarPorIsbn();
                        System.out.println("");
                        ejecucion3();
                        break;

                    case "4":

                        System.out.println("LIBROS REGISTRADOS");
                        libroServicio.listarLibros().forEach((l) -> System.out.println(l.toString()));
                        System.out.println("");

                        ejecucion3();
                        break;

                    case "5":
                        
                        try {

                        System.out.println("Ingrese el ISBN del Libro a buscar");
                        System.out.print("ISBN: ");
                        Long isbnBuscar = leer.nextLong();

                        if (isbnBuscar == null || isbnBuscar == 0) {
                            throw new Exception("Debe indicar el ISBN del Libro");
                        }

                        System.out.println("LIBRO POR ISBN");
                        System.out.println(libroServicio.buscarPorIsbn(isbnBuscar));
                        System.out.println("");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    ejecucion3();
                    break;
                    case "6":

                        System.out.println("Buscar Libro por titulo");
                        System.out.print("Titulo: ");
                        String tituloLibro = leer.next();

                        System.out.println("LIBRO POR TITULO");
                        System.out.println(libroServicio.buscarPorTitulo(tituloLibro));
                        //System.out.println(libroServicio.buscarPorTitulo(tituloLibro).getTitulo());
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "7":
                        
                        try {

                        System.out.println("Buscar Libros por nombre de Autor");
                        System.out.print("Nombre Autor: ");
                        String autorNombre = leer.next();

                        if (autorNombre == null || autorNombre.trim().isEmpty()) {
                            throw new Exception("Debe indicar el nombre del Autor");
                        }

                        System.out.println("LIBROS POR NOMBRE DE AUTOR");
                        //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
                        libroServicio.buscarLibroPorNombreAutor(autorNombre).forEach((l) -> System.out.println(l.toString()));
                        System.out.println("");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    ejecucion3();
                    break;
                    case "8":
                        
                        try {

                        System.out.println("Buscar Libros por nombre de Editorial");
                        System.out.print("Nombre Editorial: ");
                        String editorialNombre = leer.next();

                        if (editorialNombre == null || editorialNombre.trim().isEmpty()) {
                            throw new Exception("Debe indicar el nombre de la Editorial");
                        }

                        System.out.println("LIBROS POR NOMBRE DE EDITORIAL");
                        //System.out.println(libroServicio.buscarLibroPorNombreEditorial("Editorial 2")); 
                        libroServicio.buscarLibroPorNombreEditorial(editorialNombre).forEach((l) -> System.out.println(l.toString()));
                        System.out.println("");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    ejecucion3();
                    break;
                    case "9":

                        ejecucion3();
                        break;
                    default:
                        System.out.println("Opcion no válida");
                        ejecucion3();
                }
                break;

            case "3":

                System.out.println("");
                System.out.println("----- MENU AUTORES -----");
                System.out.println("Seleccione una opcion: ");
                System.out.println("1. Crear Autor");
                System.out.println("2. Editar Autor");
                System.out.println("3. Eliminar Autor");
                System.out.println("4. Imprimir Autores registrados");
                System.out.println("5. Buscar Autor por Nombre");

                System.out.println("6. Menu Libreria");
                System.out.print("Opcion: ");
                opc = leer.next();
                System.out.println("");

                switch (opc) {
                    case "1":
                        System.out.println("");
                        autorServicio.crearAutorPorDatoIngresado();
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "2":
                        System.out.println("");
                        autorServicio.modificarAutor();
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "3":
                        System.out.println("");
                        autorServicio.eliminarPorId();
                        System.out.println("");

                        ejecucion3();
                        break;

                    case "4":
                        System.out.println("AUTORES REGISTRADOS");
                        autorServicio.listarAutores().forEach((a) -> System.out.println(a.toString()));
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "5":

                        System.out.println("Buscar Autor por nombre");
                        System.out.print("Nombre: ");
                        String nombreAutor = leer.next();

                        System.out.println("AUTOR POR NOMBRE");
                        System.out.println(autorServicio.buscarPorNombre(nombreAutor));
                        //System.out.println(autorServicio.buscarPorNombre(nombreAutor).getNombre());
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "6":

                        ejecucion3();
                        break;
                }
                break;

            case "4":

                System.out.println("");
                System.out.println("----- MENU EDITORIALES -----");
                System.out.println("Seleccione una opcion: ");
                System.out.println("1. Crear Editorial");
                System.out.println("2. Editar Editorial");
                System.out.println("3. Eliminar Editorial");
                System.out.println("4. Imprimir Editoriales registradas");

                System.out.println("5. Menu Libreria");
                System.out.print("Opcion: ");
                opc = leer.next();
                System.out.println("");

                switch (opc) {
                    case "1":
                        System.out.println("");
                        editorialServicio.crearEditorialPorDatoIngresado();
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "2":
                        System.out.println("");
                        editorialServicio.modificarEditorial();
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "3":
                        System.out.println("");
                        editorialServicio.eliminarPorId();
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "4":
                        System.out.println("EDITORIALES REGISTRADAS");
                        editorialServicio.listarEditoriales().forEach((e) -> System.out.println(e.toString()));
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "5":

                        ejecucion3();
                        break;
                }
                break;

            case "5":

                System.out.println(""); 
                System.out.println("----- MENU PRESTAMOS -----");
                System.out.println("Seleccione una opcion: ");
                System.out.println("1. Crear Prestamo");
                System.out.println("2. Devolver Prestamo");
                System.out.println("3. Editar Prestamo");
                System.out.println("4. Eliminar Prestamo");
                System.out.println("5. Imprimir Prestamos registrados");
                System.out.println("6. Buscar Prestamo por nombre de Cliente");

                System.out.println("7. Menu Libreria");
                System.out.print("Opcion: ");
                opc = leer.next();
                System.out.println("");

                switch (opc) {
                    case "1":
                        System.out.println("");
                        prestamoServicio.crearPrestamoPorDatoIngresado();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "2":
                        System.out.println("");
                        prestamoServicio.crearDevolucionPorDatoIngresado();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "3":
                        System.out.println("");
                        prestamoServicio.modificarPrestamo();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "4":
                        System.out.println("");
                        prestamoServicio.eliminarPorId();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "5":
                        System.out.println("PRESTAMOS REGISTRADOS");
                        prestamoServicio.listarPrestamos().forEach((p) -> System.out.println(p.toString()));
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "6":
                        
                        try {

                        System.out.println("Buscar Prestamos por nombre de Cliente");
                        System.out.print("Nombre de Cliente: ");
                        String clienteNombre = leer.next();

                        if (clienteNombre == null || clienteNombre.trim().isEmpty()) {
                            throw new Exception("Debe indicar el nombre del Autor");
                        }

                        System.out.println("PRESTAMOS POR NOMBRE DE CLIENTE");
                        //System.out.println(libroServicio.buscarLibroPorNombreAutor("Autor 1"));
                        prestamoServicio.buscarPrestamoPorNombreCliente(clienteNombre).forEach((p) -> System.out.println(p.toString()));
                        System.out.println("");

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    ejecucion3();
                    break;
                    case "7":

                        ejecucion3();
                        break;
                }
                break;

            case "6":

                System.out.println("");
                System.out.println("----- MENU CLIENTES -----");
                System.out.println("Seleccione una opcion: ");
                System.out.println("1. Crear Cliente");
                System.out.println("2. Editar Cliente");
                System.out.println("3. Eliminar Cliente");
                System.out.println("4. Imprimir Clientes registrados");

                System.out.println("5. Menu Libreria");
                System.out.print("Opcion: ");
                opc = leer.next();
                System.out.println("");

                switch (opc) {
                    case "1":
                        System.out.println("");
                        clienteServicio.crearClientePorDatoIngresado();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "2":
                        System.out.println("");
                        clienteServicio.modificarCliente();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "3":
                        System.out.println("");
                        clienteServicio.eliminarPorId();
                        System.out.println("");
                        ejecucion3();
                        break;
                    case "4":
                        System.out.println("CLIENTES REGISTRADOS");
                        clienteServicio.listarClientes().forEach((c) -> System.out.println(c.toString()));
                        System.out.println("");

                        ejecucion3();
                        break;
                    case "5":

                        ejecucion3();
                        break;
                }
                break;

            case "7":
                System.out.println("Ejecución finalizada");
                break;
            default:
                System.out.println("Opcion no válida");
                ejecucion3();
                break;
        }

    }

}
