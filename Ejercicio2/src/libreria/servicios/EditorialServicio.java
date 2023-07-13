package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private final EditorialDAO ed = new EditorialDAO();

    public Editorial crearEditorial(String nombre, Boolean alta) {
        Editorial editorial = new Editorial();
        try {
            editorial.setNombre(nombre);
            editorial.setAlta(alta);
            ed.guardarEditorial(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Editorial ingresarEditorial() {
        Editorial e1 = new Editorial();
        boolean esta = false;
        try {
            System.out.println("Ingrese el nombre:");
            e1.setNombre(leer.next());
            if (e1.getNombre() == null || e1.getNombre().trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autor");
            }
            e1.setAlta(Boolean.TRUE);

            for (Editorial aux : ed.listarEditoriales()) {
                if (aux.getNombre().equalsIgnoreCase(e1.getNombre())) {
                    ed.guardarEditorial(aux);
                    return aux;
                }
            }
            if (!esta) {
                ed.guardarEditorial(e1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            ingresarEditorial();
        }
        return e1;
    }
}
