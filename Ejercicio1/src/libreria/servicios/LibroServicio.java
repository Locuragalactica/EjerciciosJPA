package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

public class LibroServicio {

    private final LibroDAO DAO = new LibroDAO();
    AutorServicio as = new AutorServicio();
    EditorialServicio es = new EditorialServicio();

    public Libro crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        Libro libro = new Libro();
        try {
            libro.setIsbn(isbn);
            libro.setAlta(alta);
            libro.setAnio(anio);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setTitulo(titulo);
            DAO.guardarLibro(libro);
            return libro;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void modificarTitulo(Long isbn, String titulo) {
        try {
            Libro libro = DAO.buscarPorIsbn(isbn);

            libro.setTitulo(titulo);

            DAO.editarLibro(libro);

        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void crearBaseDatos() {

        Autor a1 = as.crearAutor("Gabriel Garcia Marquez", Boolean.TRUE);
        Autor a2 = as.crearAutor("Harper Lee", Boolean.TRUE);
        Autor a3 = as.crearAutor("Paulo Cohelo", Boolean.TRUE);
        Autor a4 = as.crearAutor("George Orwell", Boolean.TRUE);
        Autor a5 = as.crearAutor("J.K Rowling", Boolean.TRUE);
        Autor a6 = as.crearAutor("Dan Brown", Boolean.TRUE);
        Autor a7 = as.crearAutor("Carlos Ruiz Zafón", Boolean.TRUE);

        Editorial e1 = es.crearEditorial("Literatura Random House", Boolean.TRUE);
        Editorial e2 = es.crearEditorial("Harper Perennial Modern Classics", Boolean.TRUE);
        Editorial e3 = es.crearEditorial("Planeta", Boolean.TRUE);
        Editorial e4 = es.crearEditorial("HarperOne", Boolean.TRUE);
        Editorial e5 = es.crearEditorial("Signet Classics", Boolean.TRUE);
        Editorial e6 = es.crearEditorial("Salamandra", Boolean.TRUE);
        Editorial e7 = es.crearEditorial("Umbriel Editores", Boolean.TRUE);

        Libro l1 = crearLibro(9788498387085l, "Cien años de soledad", 1967, 200, 50, 150, Boolean.TRUE, a1, e1);
        Libro l2 = crearLibro(9780061122415l, "Matar a un ruiseñor", 1960, 70, 30, 40, Boolean.TRUE, a2, e2);
        Libro l3 = crearLibro(9788420483306l, "El amor en los tiempos del cólera", 1985, 100, 20, 80, Boolean.TRUE, a1, e3);
        Libro l4 = crearLibro(9780060935467l, "El alquimista", 1988, 120, 60, 60, Boolean.TRUE, a3, e4);
        Libro l5 = crearLibro(9780451524935l, "1984", 1949, 50, 40, 10, Boolean.TRUE, a4, e5);
        Libro l6 = crearLibro(9788408098105l, "Harry Potter y la piedra filosofal", 1997, 500, 400, 100, Boolean.TRUE, a5, e6);
        Libro l7 = crearLibro(9788497930398l, "El código Da Vinci", 2003, 10, 5, 5, Boolean.TRUE, a7, e7);

    }

    public void borrarLibro() throws Exception {
        Scanner leer = new Scanner(System.in).useDelimiter("\n");
        DAO.eliminarLibro(DAO.buscarPorIsbn(leer.nextLong()));

    }
}
