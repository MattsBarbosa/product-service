package com.ms.product.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

public record ProductRecordDto(@NotBlank String name,
                               @NotNull BigDecimal value,
                               Integer quantity) {
    //DATA TRANSFER OBJECT (TEMPORARY)
    //O INPUT DO USER FICA SALVO NESTE OBJETO
    //NO CONTROLLER AS INFOS SALVAS AQUI SÃO TRANSFERIDAS PARA O MODEL
    //E O MODEL É SALVO NO DB
}
