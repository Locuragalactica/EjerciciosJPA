package libreria;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Libro;
import libreria.persistencia.AutorDAO;
import libreria.persistencia.EditorialDAO;
import libreria.persistencia.LibroDAO;
import libreria.servicios.AutorServicio;
import libreria.servicios.EditorialServicio;

public class Menu {

    AutorServicio As = new AutorServicio();
    AutorDAO auDao = new AutorDAO();
    Autor au = new Autor();
    EditorialDAO edDao = new EditorialDAO();
    EditorialServicio Es = new EditorialServicio();
    LibroDAO ldao = new LibroDAO();

    public void menu() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        int rta;
        String rta2 = "";
        do {
            System.out.println("\u001B[41m ----------MENU----------");
            System.out.println("\u001B[41m");
            System.out.println("\u001B[41m1) Añadir Libro");
            System.out.println("\u001B[41m2) Buscar libros por ID");
            System.out.println("\u001B[41m3) Buscar Autor");
            System.out.println("\u001B[41m4) Buscar libros por Autor");
            System.out.println("\u001B[41m5) Buscar libros por Editorial");
            System.out.println("\u001B[41m6) Salir");
            rta = leer.nextInt();
            if (rta == 6) {
                System.out.println("¿Esta seguro que desea salir? S/N");
                rta2 = leer.next();
                if (rta2.equals("s")) {
                    break;
                }
            }
            switch (rta) {
                case 1:
                    Libro libro = new Libro();
                    System.out.println("-------------------------------------");
                    System.out.print("NUEVO LIBRO: \n"
                            + "Ingrese el título: ");
                    libro.setTitulo(leer.next());
                    System.out.println("Ingrese el ISBN del libro: ");
                    libro.setIsbn(leer.nextLong());
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
                    break;
                case 2:
                    System.out.println("-------------------------------------");
                    System.out.println("Ingrese el ISBN del libro:");
                    long isbn = leer.nextLong();
                    System.out.println("Su libro es: ");
                    System.out.println(ldao.buscarPorIsbn(isbn));
                    System.out.println("-------------------------------------");
                    break;
                case 3:
                    System.out.println("-------------------------------------");
                    System.out.println("Ingrese el nombre del autor que desea buscar: ");
                    String nom = leer.next();
                    System.out.println(auDao.buscarAutores(nom));
                    System.out.println("-------------------------------------");
                    break;
                case 4:
                    System.out.println("-------------------------------------");
                    System.out.print("Ingrese el nombre completo del Autor: ");
                    String nombreAu = leer.next();
                    System.out.println(ldao.buscarLibrosPorAutor(nombreAu));
                    System.out.println("-------------------------------------");
                    break;
                case 5:
                    System.out.println("-------------------------------------");
                    System.out.println("Ingrese el nombre de Editorial");
                    String nombreEd = leer.next();
                    System.out.println(ldao.buscarLibrosPorEditorial(nombreEd));
                    System.out.println("-------------------------------------");
                    break;
            }
        } while (!rta2.equals("s"));

    }

}
