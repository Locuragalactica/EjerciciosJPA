package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    /*
    public void guardar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }*/
    
    @Override
    public void editar(Libro libro) {
        super.editar(libro);
    }

    @Override
    public void eliminar(Libro libro) {
        super.eliminar(libro);
    }

    public Libro buscarPorIsbn(Long isbn) {
        conectar();
        //Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
        //        .setParameter("isbn", isbn).getSingleResult();
        Libro libro = (Libro) em.createNamedQuery("Libro.finByIsbn", Libro.class)
                .setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }

    public Libro buscarPorTitulo(String titulo) {
        conectar();
        //Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo")
        //        .setParameter("titulo", titulo).getSingleResult();
        Libro libro = (Libro) em.createNamedQuery("Libro.findByTitulo", Libro.class)
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
    }

    public List<Libro> listarTodosLibros() {
        conectar();
        //List<Libro> libros = em.createQuery("SELECT l FROM Libro l").getResultList();
        List<Libro> libros = em.createNamedQuery("Libro.findAll").getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibroNombreAutor(String nombre) {
        conectar();
        //List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre")
        //        .setParameter("nombre", nombre).getResultList();
        List<Libro> libros = em.createNamedQuery("Libro.findByNombreAutor", Libro.class)
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }

    public List<Libro> buscarLibroNombreEditorial(String nombre) {
        conectar();
        //List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre")
        //        .setParameter("nombre", nombre).getResultList();
        List<Libro> libros = em.createNamedQuery("Libro.findByNombreEditorial", Libro.class)
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return libros;
    }

}
