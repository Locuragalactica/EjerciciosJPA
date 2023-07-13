package libreria;

import java.util.InputMismatchException;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.AutorDAO;
import libreria.persistencia.ClienteDAO;
import libreria.persistencia.EditorialDAO;
import libreria.persistencia.LibroDAO;
import libreria.persistencia.PrestamoDAO;
import libreria.servicios.AutorServicio;
import libreria.servicios.ClienteServicio;
import libreria.servicios.EditorialServicio;
import libreria.servicios.PrestamoServicio;

public class Menu {

    AutorServicio As = new AutorServicio();
    AutorDAO auDao = new AutorDAO();
    Autor au = new Autor();
    EditorialDAO edDao = new EditorialDAO();
    EditorialServicio Es = new EditorialServicio();
    LibroDAO ldao = new LibroDAO();
    PrestamoServicio ps = new PrestamoServicio();
    PrestamoDAO pdao = new PrestamoDAO();
    ClienteDAO cdao = new ClienteDAO();
    Integer id;
    ClienteServicio cs = new ClienteServicio();
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public void menu() throws Exception {

        String rta2 = "";
        int rta;
        try {
            do {
                System.out.println("\u001B[41m ----------MENU----------");
                System.out.println("\u001B[41m");
                System.out.println("\u001B[41m1) Añadir Libro");
                System.out.println("\u001B[41m2) Buscar libros por ID");
                System.out.println("\u001B[41m3) Buscar Autor");
                System.out.println("\u001B[41m4) Buscar libros por Autor");
                System.out.println("\u001B[41m5) Buscar libros por Editorial");
                System.out.println("\u001B[41m6) Ingresar un Cliente");
                System.out.println("\u001B[41m7) Devolver un Libro");
                System.out.println("\u001B[41m8) Buscar prestamos de un Cliente");
                System.out.println("\u001B[41m9) Solicitar Prestamo");
                System.out.println("\u001B[41m10) Salir");
                rta = 0;
                rta = leer.nextInt();
                if (rta == 10) {
                    System.out.println("¿Esta seguro que desea salir? S/N");
                    rta2 = leer.next();
                    if (rta2.equals("s")) {
                        break;
                    }
                }

                switch (rta) {
                    case 1:
                    
                    try {
                        Libro libro = new Libro();
                        System.out.println("-------------------------------------");
                        System.out.print("NUEVO LIBRO: \n"
                                + "Ingrese el título: ");
                        libro.setTitulo(leer.next());
                        try {
                            System.out.println("Ingrese el ISBN del libro: ");
                            libro.setIsbn(leer.nextLong());
                        } catch (InputMismatchException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                        System.out.print("Ingrese el año de lanzamiento: ");
                        libro.setAnio(leer.nextInt());
                        System.out.print("Ejemplares: ");
                        libro.setEjemplares(leer.nextInt());
                        System.out.print("Ejemplares prestados: ");
                        libro.setEjemplaresPrestados(leer.nextInt());
                        System.out.print("Ejemplares restantes: ");
                        libro.setEjemplaresRestantes(leer.nextInt());
                        libro.setAlta(Boolean.TRUE);
                        System.out.println("LISTA DE AUTORES: ");
                        System.out.println(auDao.listarAutor());
                        try {
                            System.out.println("¿Desea agregar un nuevo autor? ");
                            String resp = leer.next();
                            if (resp.equalsIgnoreCase("s")) {
                                libro.setAutor(As.ingresarAutor());
                            } else if (resp.equalsIgnoreCase("n")) {
                                try {
                                    System.out.print("Ingrese el código del autor: ");
                                    int id = leer.nextInt();
                                    libro.setAutor(auDao.buscarPorId(id));
                                } catch (Exception e) {
                                    throw new Exception("Error tuki Autor.");
                                }
                            } else {
                                System.out.println("Error.");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("LISTA DE EDITORIALES: ");
                        System.out.println(edDao.listarEditoriales());
                        try {
                            System.out.println("¿Desea agregar una nueva editorial? ");
                            String resp = leer.next();
                            if (resp.equalsIgnoreCase("s")) {
                                libro.setEditorial(Es.ingresarEditorial());
                            } else if (resp.equalsIgnoreCase("n")) {
                                try {
                                    System.out.print("Ingrese el código del editorial: ");
                                    int id = leer.nextInt();
                                    libro.setEditorial(edDao.buscarPorId(id));
                                } catch (Exception e) {
                                    throw new Exception("Error tuki Editorial.");
                                }
                            } else {
                                System.out.println("Error.");
                            }

                            if (ldao.validarLibroNuevo(libro.getTitulo(), libro.getAnio(), libro.getAutor().getNombre(), libro.getEditorial().getNombre())) {
                                System.out.println("Este libro ya se encuentra en la lista");
                            } else {
                                ldao.guardarLibro(libro);
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("-------------------------------------");

                    } catch (Exception e) {

                        System.out.println("Error General en el ingreso de libro.");

                    }
                    break;
                    case 2:
                        System.out.println("-------------------------------------");
                        System.out.println("Ingrese el ISBN del libro:");
                        try {
                            long isbn = leer.nextLong();
                            System.out.println("Su libro es: ");
                            System.out.println(ldao.buscarPorIsbn(isbn));
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la consulta de libro");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 3:
                        System.out.println("-------------------------------------");
                        System.out.println("Ingrese el nombre del autor que desea buscar: ");
                        try {
                            String nom = leer.next();
                            System.out.println(auDao.buscarAutores(nom));
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la consulta de autores");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 4:
                        System.out.println("-------------------------------------");
                        System.out.print("Ingrese el nombre completo del Autor: ");
                        try {
                            String nombreAu = leer.next();
                            System.out.println(ldao.buscarLibrosPorAutor(nombreAu));
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la consulta de Libros por autor");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 5:
                        System.out.println("-------------------------------------");
                        System.out.println("Ingrese el nombre de Editorial");
                        try {
                            String nombreEd = leer.next();
                            System.out.println(ldao.buscarLibrosPorEditorial(nombreEd));
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la consulta de Editorial");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 6:
                        System.out.println("-------------------------------------");
                        System.out.println("Ingresar un Cliente");
                        try {
                            cs.ingresarCliente();
                        } catch (Exception e) {
                            System.out.println("No se pudo registrar el cliente");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 7:
                        System.out.println("-------------------------------------");
                        System.out.println("Devolver un Libro");
                        System.out.println("Ingrese el ID del prestamo");
                        try {
                            id = leer.nextInt();
                            ps.devolverLibro(id);
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la devolución");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 8:
                        System.out.println("-------------------------------------");
                        System.out.println("Buscar prestamos de un Cliente");
                        System.out.println("Ingrese DNI del cliente:");
                        try {
                            id = leer.nextInt();
                            System.out.println("Sus prestamos son: ");
                            System.out.println(pdao.buscarPrestamoPorCliente(cdao.buscarPorDNI(id).getId()));
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar la consulta de prestamos");
                        }
                        System.out.println("-------------------------------------");
                        break;
                    case 9:
                        System.out.println("-------------------------------------");
                        try {
                            Prestamo p1 = ps.solicitarPrestamo();
                            pdao.guardarPrestamo(p1);
                        } catch (Exception e) {
                            System.out.println("No se pudo realizar el prestamo.");
                        }

                        System.out.println("-------------------------------------");
                        break;
                    default:
                        System.out.println("Opcion no valida");
                }

            } while (!rta2.equals("s"));

        } catch (Exception e) {
            System.out.println("Error de Menu");
            leer.nextLine();

        }
    }
}
