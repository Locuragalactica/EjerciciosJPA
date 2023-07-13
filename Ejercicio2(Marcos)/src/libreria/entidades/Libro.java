package libreria.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l"),
    @NamedQuery(name = "Libro.finByIsbn", query = "SELECT l FROM Libro l WHERE l.isbn = :isbn"),
    @NamedQuery(name = "Libro.findByTitulo", query = "SELECT l FROM Libro l WHERE l.titulo LIKE :titulo"),
    @NamedQuery(name = "Libro.findByNombreAutor", query = "SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre"),
    @NamedQuery(name = "Libro.findByNombreEditorial", query = "SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre")
})

@Entity
public class Libro implements Serializable {

    //private static final long serialVersionUID = 1L;
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    
    @Column(unique = true)
    private Long isbn;
    private String titulo;

    //@Temporal(TemporalType.DATE) no hace falta porque solo necesitamos el año, el Date maneja Fechas
    private Integer anio;

    private Integer ejemplares;
    private Integer ejemplaresPrestados;
    private Integer ejemplaresRestantes;
    private Boolean alta;
    
    //Ver la eliminacion en cascada
    //@OneToOne(cascade = CascadeType.REMOVE)
    @OneToOne
    private Autor autor;

    @OneToOne
    private Editorial editorial;

    public Libro() {
    }

    public Libro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Integer getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Integer ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Integer getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Integer ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Integer getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Integer ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (isbn != null ? isbn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.isbn == null && other.isbn != null) || (this.isbn != null && !this.isbn.equals(other.isbn))) {
            return false;
        }
        return true;
    }

    /*
    @Override
    public String toString() {
        return "Libro [" + "Id: " + id + ", ISBN: " + isbn + ", Titulo: " + titulo + ", Año: " + anio + ", Ejemplares totales: " + ejemplares + ", Ejemplares prestados: " + ejemplaresPrestados + ", Ejemplares Restantes: " + ejemplaresRestantes + ", Alta: " + alta + ", \n"
                + " Autor: " + autor + ", \n"
                + " Editorial: " + editorial + ']';
    }*/

    @Override
    public String toString() {
        return " [Libro] "
                + "\n  - Id: " + id
                + "\n  - ISBN: " + isbn
                + "\n  - Titulo: " + titulo
                + "\n  - Año: " + anio
                + "\n  - Ejemplares totales: " + ejemplares
                + "\n  - Ejemplares prestados: " + ejemplaresPrestados
                + "\n  - Ejemplares Restantes: " + ejemplaresRestantes
                + "\n  - Alta: " + alta
                + "\n  - Autor: " + autor
                + "\n  - Editorial: " + editorial;
    }

}
