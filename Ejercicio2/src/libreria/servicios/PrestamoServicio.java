package libreria.servicios;

import java.awt.BorderLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.ClienteDAO;
import libreria.persistencia.LibroDAO;
import libreria.persistencia.PrestamoDAO;

public class PrestamoServicio {

    Scanner leer = new Scanner(System.in).useDelimiter("\n");
    ClienteDAO cdao = new ClienteDAO();
    ClienteServicio sdao = new ClienteServicio();
    LibroDAO ldao = new LibroDAO();
    PrestamoDAO pdao = new PrestamoDAO();

    public Prestamo crearPrestamo(Date fechaPrestamo, Date fechaDevolucion, Libro libro, Cliente cliente) {
        Prestamo prestamo = new Prestamo();
        PrestamoDAO pdao = new PrestamoDAO();
        try {
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaDevolucion(fechaDevolucion);
            prestamo.setCliente(cliente);
            prestamo.setLibro(libro);
            pdao.guardarPrestamo(prestamo);
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Prestamo solicitarPrestamo() throws ParseException, Exception {
        Prestamo prestamo = new Prestamo();
        System.out.println("¿El cliente esta registrado? s/n");
        String opc = leer.next().toLowerCase();
        do {
            if (opc.equals("s")) {
                System.out.println("Ingrese el DNI del cliente");
                Integer dni = leer.nextInt();
                prestamo.setCliente(cdao.buscarPorDNI(dni));
            } else if (opc.equals("n")) {
                prestamo.setCliente(sdao.ingresarCliente());
            } else {
                System.out.println("La letra ingresada es incorrecta");

            }
        } while (!opc.equals("s") && !opc.equals("n"));

        System.out.println("Ingrese el titulo del libro");
        String title = leer.next();
        Libro l1 = ldao.buscarPorTitulo(title);
        if (l1.getEjemplaresRestantes() > 0) {
            if (l1.getTitulo().equalsIgnoreCase(title)) {
                prestamo.setLibro(l1);
                l1.setEjemplaresRestantes(l1.getEjemplaresRestantes() - 1);
                l1.setEjemplaresPrestados(l1.getEjemplaresPrestados() + 1);
                ldao.editarLibro(l1);
                prestamo.setFechaPrestamo(new Date());
                prestamo.setFechaDevolucion(null);
                return prestamo;
            } else {
                return null;
            }
        } else {
            System.out.println("No hay ejemplares disponibles");
            return null;
        }
    }

    public void devolverLibro(Integer id) throws Exception {

        Prestamo p1 = pdao.buscarPrestamoPorId(id);
        Libro l1 = p1.getLibro();
        p1.setFechaDevolucion(new Date());
        l1.setEjemplaresPrestados(l1.getEjemplaresPrestados() - 1);
        l1.setEjemplaresRestantes(l1.getEjemplaresRestantes() + 1);
        ldao.editarLibro(l1);
        pdao.editarPrestamo(p1);
        System.out.println("Devolución Exitosa");
    }

}
