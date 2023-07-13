package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.persistencia.AutorDAO;

public class AutorServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    private final AutorDAO ad = new AutorDAO();

    public Autor crearAutor(String nombre, Boolean alta) {
        Autor autor = new Autor();
        try {
            autor.setAlta(alta);
            autor.setNombre(nombre);
            ad.guardarAutor(autor);
            return autor;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Autor ingresarAutor() {
        Autor a1 = new Autor();
        boolean esta = false;
        try {
            System.out.println("Ingrese el nombre:");
            a1.setNombre(leer.next());
            if (a1.getNombre() == null || a1.getNombre().trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del autor");
            }
            a1.setAlta(Boolean.TRUE);
            for (Autor aux : ad.listarAutor()) {
                if (aux.getNombre().equalsIgnoreCase(a1.getNombre())) {
                    ad.guardarAutor(aux);
                    return aux;
                }
            }
            if (!esta) {
                ad.guardarAutor(a1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ingresarAutor();
        }
        a1.setAlta(Boolean.TRUE);
        return a1;
    }

}
