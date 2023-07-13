package libreria.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO {

    public Collection<Prestamo> listarPrestamo() {
        conectar();
        Collection<Prestamo> prestamos = em.createQuery("SELECT c FROM Prestamo c").getResultList();
        desconectar();
        return prestamos;
    }

    public void guardarPrestamo(Prestamo prestamo) {
        super.guardar(prestamo);
    }

    public void eliminarPrestamo(Prestamo prestamo) throws Exception {
        super.eliminar(prestamo);
    }

    public void editarPrestamo(Prestamo prestamo) throws Exception {
        super.editar(prestamo);
    }

    public Prestamo buscarPrestamoPorId(Integer id) throws Exception {
        conectar();
        Prestamo prestamo = new Prestamo();
        try {
            prestamo = em.find(Prestamo.class, id);
            if (prestamo == null) {
                throw new Exception("Libro Inexistente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        desconectar();

        return prestamo;
    }

    public Collection<Prestamo> buscarPrestamoPorCliente(Integer id) throws Exception {
        conectar();
        Collection<Prestamo> prestamos = new ArrayList<Prestamo>();
        try {
            prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.id = :id", Prestamo.class).setParameter("id", id).getResultList();
            if (prestamos == null) {
                throw new Exception("No existen prestamos de este cliente");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        desconectar();

        return prestamos;
    }

}
