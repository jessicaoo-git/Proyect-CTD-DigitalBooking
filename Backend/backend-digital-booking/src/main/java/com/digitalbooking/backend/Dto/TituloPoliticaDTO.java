package com.digitalbooking.backend.Dto;

import com.digitalbooking.backend.Models.Enums.EnumTituloPolitica;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TituloPoliticaDTO {
    private Integer id;
    private EnumTituloPolitica tituloPolitica;
}
