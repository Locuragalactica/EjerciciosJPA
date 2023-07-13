package libreria.persistencia;

import java.util.List;
import libreria.entidades.Cliente;

public class ClienteDAO extends DAO<Cliente> {
    
    @Override
    public void guardar(Cliente cliente) {
        super.guardar(cliente);
    }

    @Override
    public void eliminar(Cliente cliente) {
        super.eliminar(cliente);
    }

    @Override
    public void editar(Cliente cliente) {
        super.editar(cliente);
    }

    public List<Cliente> listarTodosClientes() {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c")
                .getResultList();
        desconectar();
        return clientes;
    }

    public Cliente buscarPorNombre(String nombre) {
        conectar();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getSingleResult();
        desconectar();
        return cliente;
    }
    
    public Cliente buscarPorId(Integer id) {
        conectar();
        //Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
        //        .setParameter("isbn", isbn).getSingleResult();
        Cliente cliente = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id = :id")
                .setParameter("id", id).getSingleResult();
        desconectar();
        return cliente;
    }

}
