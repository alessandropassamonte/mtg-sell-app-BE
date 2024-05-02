package com.mtgsell.mtgsellapp.controllers;

import com.mtgsell.mtgsellapp.entities.Order;
import com.mtgsell.mtgsellapp.entities.UserCard;
import com.mtgsell.mtgsellapp.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RestController
public class GestioneOrdiniController {

    @Autowired
    OrderService orderService;

    @GetMapping("/all")
    public Page<Order> getUsername(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "12") int size,
                                   HttpServletRequest request) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return orderService.getAllByUser(request, pageRequest);
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order,
                                   HttpServletRequest request) {
        return orderService.save(request, order);
    }
}
