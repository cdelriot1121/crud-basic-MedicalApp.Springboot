package com.example.CrudBasicCitas.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "citas")
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "asunto", nullable = false, length = 100)
    private String asunto;

    @Column(name = "descripcion", nullable = false, length = 275)
    private String descripcion;

    @Column(name = "tipo_cita", nullable = false, length = 50)
    private String tipoCita;

    @Column(name = "fecha_cita", nullable = false)
    private String fecha_cita;

    @Column(name = "doctor", nullable = false, length = 100)
    private String doctor;

    //Constructor default y con parametros


    public Citas() {
    }

    public Citas(Long id, Cliente cliente, String asunto, String descripcion, String tipoCita, String fecha_cita, String doctor) {
        this.id = id;
        this.cliente = cliente;
        this.asunto = asunto;
        this.descripcion = descripcion;
        this.tipoCita = tipoCita;
        this.fecha_cita = fecha_cita;
        this.doctor = doctor;
    }


    //los getters y setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
