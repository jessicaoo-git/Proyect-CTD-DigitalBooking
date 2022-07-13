package com.digitalbooking.backend.Models;

import com.digitalbooking.backend.Models.Enums.EnumTituloPolitica;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "titulos_politicas")
public class TituloPolitica {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Enumerated(EnumType.STRING)
        private EnumTituloPolitica tituloPolitica;
        @OneToMany(mappedBy = "titulo")
        @JsonIgnore
        private Set<Politica> politicas;


}
