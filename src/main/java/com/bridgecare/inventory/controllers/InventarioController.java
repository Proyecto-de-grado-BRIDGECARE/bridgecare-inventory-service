package com.bridgecare.inventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<String> createInventario(@RequestBody InventarioDTO request) {
        Long inventarioId = inventarioService.saveInventario(request);
        return ResponseEntity.ok("Inventario created with ID: " + inventarioId);
    }
}