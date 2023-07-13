package libreria;

import libreria.servicios.LibroServicio;

public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        LibroServicio ls = new LibroServicio();
        ls.crearBaseDatos();
        Menu menu = new Menu();
        menu.menu();
    }
}
