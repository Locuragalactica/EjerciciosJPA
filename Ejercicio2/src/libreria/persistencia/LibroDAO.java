package libreria.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro> {

    public Collection<Libro> listarLibros() {
        conectar();
        Collection<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        desconectar();
        return libros;
    }

    public void guardarLibro(Libro libro) {
        super.guardar(libro);
    }

    public void eliminarLibro(Libro libro) throws Exception {
        super.eliminar(libro);
    }

    public void editarLibro(Libro libro) throws Exception {
        super.editar(libro);
    }

    public Libro buscarPorIsbn(long isbn) throws Exception {
        conectar();
        Libro libro = new Libro();
        try {
            libro = em.find(Libro.class, isbn);
            if (libro == null) {
                throw new Exception("Libro Inexistente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        desconectar();

        return libro;
    }

    public Libro buscarPorTitulo(String nombre) throws Exception {
        conectar();
        Libro libro = new Libro();
        try {
            libro = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class).setParameter("titulo", nombre).getSingleResult();
            if (libro == null) {
                throw new Exception("Libro Inexistente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
            return null;
        }
        desconectar();
        return libro;

    }

    public Collection<Libro> buscarLibrosPorAutor(String nombre) throws Exception {
        try {
            conectar();
            Collection<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre").setParameter("nombre", nombre).getResultList();
            if (libros == null) {
                throw new Exception("No se encontraron libros relacionados al Autor.");
            }
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
            return null;
        }
    }

    public Collection<Libro> buscarLibrosPorEditorial(String nombre) throws Exception {

        try {
            conectar();
            Collection<Libro> libros = em.createQuery("SELECT e FROM Libro e WHERE e.editorial.nombre LIKE CONCAT('%', :nombre, '%')").setParameter("nombre", nombre).getResultList();
            if (libros == null) {
                throw new Exception("No se encontraron libros relacionados a la Editorial.");
            }
            desconectar();
            return libros;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            desconectar();
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
