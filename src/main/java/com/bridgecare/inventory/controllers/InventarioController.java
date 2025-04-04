package com.bridgecare.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.bridgecare.inventory.models.dtos.InventarioDTO;
import com.bridgecare.inventory.services.InventarioService;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;
    
    @PostMapping("/add")
    public ResponseEntity<String> addInventario(@RequestBody InventarioDTO request, Authentication authentication) {
        inventarioService.saveInventario(request, authentication);
        return ResponseEntity.ok("Inventario created");
    }
}
