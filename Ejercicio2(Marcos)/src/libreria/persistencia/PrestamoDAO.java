package libreria.persistencia;

import java.util.List;
import libreria.entidades.Prestamo;

public class PrestamoDAO extends DAO<Prestamo> {

    @Override
    public void guardar(Prestamo prestamo) {
        super.guardar(prestamo);
    }

    @Override
    public void eliminar(Prestamo prestamo) {
        super.eliminar(prestamo);
    }

    @Override
    public void editar(Prestamo prestamo) {
        super.editar(prestamo);
    }

    public List<Prestamo> listarTodosPrestamos() {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p")
                .getResultList();
        desconectar();
        return prestamos;
    }

    ///////////
    public List<Prestamo> buscarPrestamoNombreCliente(String nombre) {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p WHERE p.cliente.nombre LIKE :nombre")
                .setParameter("nombre", nombre).getResultList();
        desconectar();
        return prestamos;
    }
    
    
    public Prestamo buscarPorId(Integer id) {
        conectar();        
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id = :id")
                .setParameter("id", id).getSingleResult();
        desconectar();
        return prestamo;
    }
    
    public Prestamo buscarPrestamoPorTituloLibro(String titulo) {
        conectar();        
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.libro.titulo LIKE :titulo")
                .setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return prestamo;
    }

}
