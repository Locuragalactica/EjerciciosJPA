package libreria.persistencia;

import java.util.Collection;
import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial> {

    public Collection<Editorial> listarEditoriales() {
        conectar();
        Collection<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e").getResultList();
        desconectar();
        return editoriales;
    }

    public void guardarEditorial(Editorial editorial) {
        super.guardar(editorial);
    }

    public void eliminarEditorial(String id) throws Exception {
        Editorial editorial = buscarPorId(id);
        super.eliminar(editorial);
    }

    public void editarEditorial(String id) throws Exception {
        Editorial editorial = buscarPorId(id);
        super.editar(editorial);
    }

    public Editorial buscarPorId(String id) throws Exception {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }

    public Editorial buscarPorNombre(String nombre) throws Exception {
        conectar();
        Editorial editorial = em.find(Editorial.class, nombre);
        desconectar();
        return editorial;
    }

    public Editorial buscarPorId(int id) throws Exception {
        conectar();
        Editorial editorial = em.find(Editorial.class, id);
        desconectar();
        return editorial;
    }
}
