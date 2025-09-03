package com.example.CrudBasicCitas.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {


    // atributo principal ID de la tabla, tenemos que especificar que es una llave primaria y que es autoincremental
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // creacion de las otras columnas asi como los atributos de tu clase Cliente :D

    @Column(name = "nombre", unique = true, nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "identificacion", unique = true, nullable = false, length = 20)
    private String identificacion;

    @Column(name = "fecha_nacimiento", nullable = false, length = 15)
    private String fecha_nacimiento;

    @Column(name = "genero", nullable = false, length = 10)
    private String genero;

    @Column(name = "celular", unique = true, nullable = false, length = 15)
    private String celular;

    @Column(name = "direccion", nullable = false, length = 180)
    private String direccion;

    //Atributte with ENUM Estado
    public enum Estado {
        ACTIVO,
        INACTIVO
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false, length = 10)
    private Estado estado = Estado.ACTIVO; // esto lo coloco para que por default el estado sea ACTIVO


    // La relacion con de uno a muchos con Citas
    @OneToMany(mappedBy = "cliente")
    private java.util.List<Citas> citas;



    //Constructor default y con parametros
    public Cliente() {
    }

    public Cliente(Long id, String nombre, String email, String password, String identificacion, String fecha_nacimiento, String genero, String celular, String direccion, Estado estado, List<Citas> citas) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.identificacion = identificacion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
        this.celular = celular;
        this.direccion = direccion;
        this.estado = estado;
        this.citas = citas;
    }

    // que no se te olviden los getter y setter :D

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCitas(List<Citas> citas) {
        this.citas = citas;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
