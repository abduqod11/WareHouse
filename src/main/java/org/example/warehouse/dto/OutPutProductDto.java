package org.example.warehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OutPutProductDto {
    private Double amount;
    private Double price;
    private Integer output;
}
