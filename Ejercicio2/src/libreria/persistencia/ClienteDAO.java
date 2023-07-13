package libreria.persistencia;

import java.util.Collection;
import libreria.entidades.Cliente;

public class ClienteDAO extends DAO<Cliente> {

    public Collection<Cliente> listarCliente() {
        conectar();
        Collection<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c").getResultList();
        desconectar();
        return clientes;
    }

    public void guardarCliente(Cliente cliente) {
        super.guardar(cliente);
    }

    public void eliminarCliente(Cliente cliente) throws Exception {
        super.eliminar(cliente);
    }

    public void editarCliente(Cliente cliente) throws Exception {
        super.editar(cliente);
    }

    public Cliente buscarPorId(Integer id) throws Exception {
        conectar();
        Cliente cliente = new Cliente();
        try {
            cliente = em.find(Cliente.class, id);
            if (cliente == null) {
                throw new Exception("Libro Inexistente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        desconectar();

        return cliente;
    }

    public Cliente buscarPorDNI(Integer documento) throws Exception {
        conectar();

        Cliente cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.documento = :documento", Cliente.class).setParameter("documento", documento).getSingleResult();
        try {
            if (cliente == null) {
                throw new Exception("Cliente Inexistente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        desconectar();

        return cliente;
    }

}
