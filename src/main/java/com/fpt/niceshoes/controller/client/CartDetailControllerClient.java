package com.fpt.niceshoes.controller.client;

import com.fpt.niceshoes.dto.request.CartClientRequest;
import com.fpt.niceshoes.dto.response.CartResponse;
import com.fpt.niceshoes.infrastructure.common.ResponseObject;
import com.fpt.niceshoes.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/client/api/cart")
public class CartDetailControllerClient {
    @Autowired
    private CartService cartService;

    @GetMapping("/{idAccount}")
    public List<CartResponse> get(@PathVariable Long idAccount) {
        return cartService.getListCart(idAccount);
    }

    @PostMapping
    public ResponseObject post(@RequestBody CartClientRequest request){
        return cartService.create(request);
    }

    @PutMapping
    public ResponseObject updateQuantity(@RequestBody CartClientRequest request){
        return cartService.updateQuantity(request);
    }

    @DeleteMapping("/{idCartDetail}")
    public ResponseObject delete(@PathVariable Long idCartDetail){
        return cartService.deleteById(idCartDetail);
    }

    @DeleteMapping("/delete-all/{idAccount}")
    public ResponseObject deleteAll(@PathVariable Long idAccount){
        return cartService.deleteAll(idAccount);
    }
}
