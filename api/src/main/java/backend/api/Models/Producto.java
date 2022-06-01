package backend.api.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "categoria" , nullable = false)
    private String categoria;

    @Column(name = "precio" , nullable = false)
    private Double precio ;

    @Column(name = "cantidad" , nullable = false)
    private Long cantidad;

    @Column(name = "inventario" , nullable = false)
    private String inventario;

    @Column(name = "descripcion" , nullable = false)
    private String descripcion;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getInventario() {
        return this.inventario;
    }

    public void setInventario(String inventario) {
        this.inventario = inventario;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }



    public Producto(Long id, String imagen, String nombre, String categoria, Double precio, Long cantidad, String inventario, String descripcion) {
        this.id = id;
        this.imagen = imagen;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.cantidad = cantidad;
        this.inventario = inventario;
        this.descripcion = descripcion;
    }


    public Producto() {
    }
    
}
