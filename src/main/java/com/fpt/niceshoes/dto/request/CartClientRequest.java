package com.fpt.niceshoes.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartClientRequest {
    private Long id;
    private Integer quantity;
    private Long shoeDetail;
}
