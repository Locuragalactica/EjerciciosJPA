package libreria.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import libreria.entidades.Autor;

public class AutorDAO extends DAO<Autor> {

    public Collection<Autor> listarAutor() {
        conectar();
        Collection<Autor> autores = em.createQuery("SELECT a FROM Autor a").getResultList();
        desconectar();
        return autores;
    }

    public void guardarAutor(Autor autor) {
        super.guardar(autor);
    }

    public void eliminarAutor(Autor autor) throws Exception {
        super.eliminar(autor);
    }

    public void editarAutor(Autor autor) throws Exception {
        super.editar(autor);
    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        conectar();
        Autor autor = em.find(Autor.class, nombre);
        desconectar();
        return autor;
    }

    public Collection<Autor> buscarAutores(String nom) {
        conectar();
        Collection<Autor> autores = new ArrayList();
        try {
            autores = em.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE CONCAT('%', :nombre, '%')").setParameter("nombre", nom).getResultList();
            if (autores.isEmpty()) {
                System.out.println("No se encontraron Autores.");
                throw new Exception("Entro al catch");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Desconecto ");
        desconectar();

        return autores;
    }

    public Autor buscarPorId(int id) throws Exception {
        conectar();
        Autor autor = em.find(Autor.class, id);
        desconectar();
        return autor;
    }
}
