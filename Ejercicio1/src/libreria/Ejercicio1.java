package libreria;

import libreria.servicios.LibroServicio;

public class Ejercicio1 {

    public static void main(String[] args) throws Exception {
        LibroServicio ls = new LibroServicio();
        Menu menu = new Menu();
        ls.crearBaseDatos();
        menu.menu();
    }

}
