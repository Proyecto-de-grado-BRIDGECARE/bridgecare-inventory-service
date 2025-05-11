package com.bridgecare.inventory.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> getAllInventarios() {
        return ResponseEntity.ok(inventarioService.getAllInventariosLight());
    }

    @Transactional(readOnly=true)
    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> getInventarioById(@PathVariable Long id){
        InventarioDTO dto = inventarioService.getInventarioById(id);
        return ResponseEntity.ok(dto);
    }

    @Transactional(readOnly=true)
    @GetMapping("/puente/{puenteId}")
    public ResponseEntity<InventarioDTO> getInventarioByPuenteId(@PathVariable Long puenteId) {
        InventarioDTO dto = inventarioService.getInventarioByPuenteId(puenteId);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventario(@PathVariable Long id, Authentication authentication) {
        inventarioService.deleteInventario(id, authentication);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateInventario(@PathVariable Long id, @RequestBody InventarioDTO request, Authentication authentication) {
        inventarioService.updateInventario(id, request, authentication);
        return ResponseEntity.noContent().build();
    }
}
