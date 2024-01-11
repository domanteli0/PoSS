package com.spaghettininjas.yaposs.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class InventoryDTO {

    private Long id;

    @JsonProperty("product_id")
    private Long productId;

    private int stockQuantity;

}
