package com.mtgsell.mtgsellapp.services;

import com.mtgsell.mtgsellapp.entities.Order;
import com.mtgsell.mtgsellapp.entities.UserEntity;
import com.mtgsell.mtgsellapp.repositories.OrderRepository;
import com.mtgsell.mtgsellapp.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    public Page<Order> getAllByUser(HttpServletRequest request, PageRequest pageRequest){
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        Page<Order> orders = orderRepository.findAllByUser(userEntity, pageRequest);

        return orders;
    }

    public Order save(HttpServletRequest request, Order order){
        String username = jwtService.extractTokenFromRequest(request);
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow();
        order.setUser(userEntity);
        order.setOrderDate(new Date());
        return orderRepository.save(order);
    }
}
