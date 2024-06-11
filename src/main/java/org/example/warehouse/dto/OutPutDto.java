package org.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.warehouse.model.WareHouse;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutPutDto {
    private String factureNumber;
    private String code;
    private Integer clientId;
    private Integer warehouseId;
}
