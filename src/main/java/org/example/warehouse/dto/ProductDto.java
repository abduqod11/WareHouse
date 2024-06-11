package org.example.warehouse.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {

    private String name;
    private String code;
    private boolean active = true;
    private Integer categoryId;
}
