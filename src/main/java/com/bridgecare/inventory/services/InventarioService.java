package com.bridgecare.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bridgecare.common.models.entities.Puente;
import com.bridgecare.common.models.entities.Usuario;
import com.bridgecare.inventory.models.dtos.InventarioDTO;
import com.bridgecare.inventory.models.entities.DatosAdministrativos;
import com.bridgecare.inventory.models.entities.DatosTecnicos;
import com.bridgecare.inventory.models.entities.Estribo;
import com.bridgecare.inventory.models.entities.Inventario;
import com.bridgecare.inventory.models.entities.Pila;
import com.bridgecare.inventory.models.entities.Subestructura;
import com.bridgecare.inventory.repositories.ApoyoRepository;
import com.bridgecare.inventory.repositories.CargaRepository;
import com.bridgecare.inventory.repositories.DatosAdministrativosRepository;
import com.bridgecare.inventory.repositories.DatosTecnicosRepository;
import com.bridgecare.inventory.repositories.DetalleRepository;
import com.bridgecare.inventory.repositories.EstriboRepository;
import com.bridgecare.inventory.repositories.InventarioRepository;
import com.bridgecare.inventory.repositories.MiembrosInteresadosRepository;
import com.bridgecare.inventory.repositories.PasoRepository;
import com.bridgecare.inventory.repositories.PilaRepository;
import com.bridgecare.inventory.repositories.PosicionGeograficaRepository;
import com.bridgecare.inventory.repositories.SenialRepository;
import com.bridgecare.inventory.repositories.SubestructuraRepository;
import com.bridgecare.inventory.repositories.SuperestructuraRepository;

import jakarta.transaction.Transactional;

@SuppressWarnings("unused")
@Service
public class InventarioService {
    
    @Autowired
    private RestTemplate restTemplate; // HTTP calls

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private DatosTecnicosRepository datosTecnicosRepository;

    @Autowired
    private DatosAdministrativosRepository datosAdministrativosRepository;

    @Autowired
    private SubestructuraRepository subestructuraRepository;

    @Autowired
    private PilaRepository pilaRepository;

    @Autowired
    private EstriboRepository estriboRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private SenialRepository senialRepository;

    @Autowired
    private ApoyoRepository apoyoRepository;

    @Autowired
    private CargaRepository cargaRepository;

    @Autowired
    private MiembrosInteresadosRepository miembrosInteresadosRepository;

    @Autowired
    private PasoRepository pasoRepository;

    @Autowired
    private PosicionGeograficaRepository posicionGeograficaRepository;

    @Autowired
    private SuperestructuraRepository superestructuraRepository;

    @Transactional
    public Long saveInventario(InventarioDTO request) {
        // Save puente via puente-service (TBD)
        String puenteUrl = "http://puente-service/api/puentes";
        ResponseEntity<Long> response = restTemplate.postForEntity(puenteUrl, request.getPuente(), Long.class);
        Long puenteId = response.getBody();

        Puente puente = new Puente();
        puente.setId(puenteId);
        puente.setNombre(request.getPuente().getNombre());

        Inventario inventario = new Inventario();
        inventario.setPuente(puente);
        inventario.setObservaciones(request.getObservaciones());

        Usuario usuario = new Usuario();
        usuario.setId(request.getUsuario().getId());
        inventario.setUsuario(usuario);


        // Save subtables
        if (request.getDatosTecnicos() != null) {
            DatosTecnicos dt = new DatosTecnicos();
            dt.setInventario(inventario);
            dt.setNumeroLuces(request.getDatosTecnicos().getNumeroLuces());
            // TBD
            datosTecnicosRepository.save(dt);
            inventario.setDatosTecnicos(dt);
        }

        if (request.getDatosAdministrativos() != null) {
            DatosAdministrativos da = new DatosAdministrativos();
            da.setInventario(inventario);
            da.setAnioConstruccion(request.getDatosAdministrativos().getAnioConstruccion());
            // TBD
            datosAdministrativosRepository.save(da);
            inventario.setDatosAdministrativos(da);
        }

        // Save subestructura and its subtables
        if (request.getSubestructura() != null) {
            Subestructura sub = new Subestructura();
            sub.setInventario(inventario);
            sub = subestructuraRepository.save(sub);

            if (request.getSubestructura().getPila() != null) {
                Pila pila = new Pila();
                pila.setSubestructura(sub);
                pila.setTipo(request.getSubestructura().getPila().getTipo());
                // TBD
                pilaRepository.save(pila);
                sub.setPila(pila);
            }

            if (request.getSubestructura().getEstribo() != null) {
                Estribo estribo = new Estribo();
                estribo.setSubestructura(sub);
                estribo.setTipo(request.getSubestructura().getEstribo().getTipo());
                // TBD
                estriboRepository.save(estribo);
                sub.setEstribo(estribo);
            }

            // TBD
            inventario.setSubestructura(sub);
        }

        // TBD

        inventarioRepository.save(inventario);
        return inventario.getId();
    }
}