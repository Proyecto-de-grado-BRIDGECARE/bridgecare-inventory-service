package com.bridgecare.inventory.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgecare.common.models.dtos.PuenteDTO;
import com.bridgecare.common.models.dtos.UsuarioDTO;
import com.bridgecare.common.models.entities.Puente;
import com.bridgecare.common.models.entities.Usuario;
import com.bridgecare.inventory.models.dtos.InventarioDTO;
import com.bridgecare.inventory.models.entities.Inventario;
import com.bridgecare.inventory.repositories.InventarioRepository;
import jakarta.transaction.Transactional;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public Long saveInventario(InventarioDTO request, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Unauthorized: No valid token provided");
        }

        // Extract user ID from JWT
        String userEmail = extractUserEmailFromAuthentication(authentication);
        System.out.println("userEmail: " + userEmail);

        // Save Puente via BridgeService
        String puenteUrl = "http://localhost:8081/api/puentes/add";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + getTokenFromAuthentication(authentication));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> entity = new HttpEntity<>(request.getPuente(), headers);
        ResponseEntity<Puente> response = restTemplate.postForEntity(puenteUrl, entity, Puente.class);

        if (response.getStatusCode() != HttpStatus.CREATED || response.getBody() == null) {
            throw new IllegalStateException("Failed to create Puente: " + response.getStatusCode());
        }

        Puente puente = response.getBody();

        // Build Inventario
        Inventario inventario = new Inventario();
        inventario.setPuente(puente);
        inventario.setObservaciones(request.getObservaciones());

        Usuario usuario = mapUsuarioDTOToUsuario(request.getUsuario());
        inventario.setUsuario(usuario);

        return inventarioRepository.save(inventario).getId();
    }

    private Usuario mapUsuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDTO.getId());
        usuario.setNombres(usuarioDTO.getNombres());
        usuario.setApellidos(usuarioDTO.getApellidos());
        usuario.setIdentificacion(usuarioDTO.getIdentificacion());
        usuario.setTipoUsuario(usuarioDTO.getTipoUsuario());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setMunicipio(usuarioDTO.getMunicipio());
        usuario.setContrasenia(usuarioDTO.getContrasenia());

        return usuario;
    }

    private String extractUserEmailFromAuthentication(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof String) {
            String email = (String) authentication.getPrincipal();
            if (email.contains("@")) { // Optionally validate it's a proper email format
                return email;
            } else {
                throw new IllegalStateException("User email in token is not valid: " + email);
            }
        }
        throw new IllegalStateException("Unable to extract user email from token");
    }    

    private String getTokenFromAuthentication(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getCredentials() instanceof String) {
            return (String) authentication.getCredentials();
        }
        throw new IllegalStateException("No JWT token found in authentication");
    }

    public List<InventarioDTO> getAllInventariosDTO() {
        List<Inventario> inventarios = inventarioRepository.findAll();

        return inventarios.stream()
            .map(this::mapToDTO)
            .toList();
    }

    private InventarioDTO mapToDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();
        dto.setObservaciones(inventario.getObservaciones());

        dto.setUsuario(mapUsuarioToDTO(inventario.getUsuario()));
        dto.setPuente(mapPuenteToDTO(inventario.getPuente()));
        
        return dto;
    }

    private UsuarioDTO mapUsuarioToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombres(usuario.getNombres());
        dto.setApellidos(usuario.getApellidos());
        dto.setCorreo(usuario.getCorreo());
        dto.setTipoUsuario(usuario.getTipoUsuario());
        dto.setIdentificacion(usuario.getIdentificacion());
        dto.setMunicipio(usuario.getMunicipio());
        return dto;
    }

    private PuenteDTO mapPuenteToDTO(Puente puente) {
        PuenteDTO dto = new PuenteDTO();
        dto.setId(puente.getId());
        dto.setNombre(puente.getNombre());
        dto.setIdentif(puente.getIdentif());
        dto.setCarretera(puente.getCarretera());
        dto.setPr(puente.getPr());
        dto.setRegional(puente.getRegional());
        return dto;
    }

    
}