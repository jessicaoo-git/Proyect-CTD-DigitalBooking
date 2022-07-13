package com.digitalbooking.backend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="politicas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Politica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(mappedBy = "politicas")
    @JsonIgnore
    private Set<Producto> productos;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "titulo_id", nullable = false)
    private TituloPolitica titulo;
    @Column(nullable = false, columnDefinition="Text")
    private String descripcion;

}
