package com.spaghettininjas.yaposs.repository.entity;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class InventoryDTO {

    private Long id;

    private Long productId;

    private int stockQuantity;

}
