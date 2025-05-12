package com.bridgecare.inventory.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.bridgecare.common.models.dtos.PuenteDTO;
import com.bridgecare.common.models.dtos.UsuarioDTO;
import com.bridgecare.common.models.entities.Puente;
import com.bridgecare.common.models.entities.Usuario;
import com.bridgecare.inventory.models.dtos.InventarioDTO;
import com.bridgecare.inventory.models.dtos.MiembrosInteresadosDTO;
import com.bridgecare.inventory.models.dtos.PasoDTO;
import com.bridgecare.inventory.models.dtos.PilaDTO;
import com.bridgecare.inventory.models.dtos.PosicionGeograficaDTO;
import com.bridgecare.inventory.models.dtos.SenialDTO;
import com.bridgecare.inventory.models.dtos.SubestructuraDTO;
import com.bridgecare.inventory.models.dtos.SuperestructuraDTO;
import com.bridgecare.inventory.models.dtos.ApoyoDTO;
import com.bridgecare.inventory.models.dtos.CargaDTO;
import com.bridgecare.inventory.models.dtos.DatosAdministrativosDTO;
import com.bridgecare.inventory.models.dtos.DatosTecnicosDTO;
import com.bridgecare.inventory.models.dtos.DetalleDTO;
import com.bridgecare.inventory.models.dtos.EstriboDTO;
import com.bridgecare.inventory.models.entities.Apoyo;
import com.bridgecare.inventory.models.entities.Carga;
import com.bridgecare.inventory.models.entities.DatosAdministrativos;
import com.bridgecare.inventory.models.entities.DatosTecnicos;
import com.bridgecare.inventory.models.entities.Detalle;
import com.bridgecare.inventory.models.entities.Estribo;
import com.bridgecare.inventory.models.entities.Inventario;
import com.bridgecare.inventory.models.entities.MiembrosInteresados;
import com.bridgecare.inventory.models.entities.Paso;
import com.bridgecare.inventory.models.entities.Pila;
import com.bridgecare.inventory.models.entities.PosicionGeografica;
import com.bridgecare.inventory.models.entities.Senial;
import com.bridgecare.inventory.models.entities.Subestructura;
import com.bridgecare.inventory.models.entities.Superestructura;
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

@Service
public class InventarioService {

    @Autowired
    private ApoyoRepository apoyoRepository;

    @Autowired
    private CargaRepository cargaRepository;

    @Autowired
    private DatosAdministrativosRepository datosAdministrativosRepository;

    @Autowired
    private DatosTecnicosRepository datosTecnicosRepository;

    @Autowired
    private DetalleRepository detalleRepository;

    @Autowired
    private EstriboRepository estriboRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private MiembrosInteresadosRepository miembrosInteresadosRepository;

    @Autowired
    private PasoRepository pasoRepository;

    @Autowired
    private PilaRepository pilaRepository;

    @Autowired
    private PosicionGeograficaRepository posicionGeograficaRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SenialRepository senialRepository;

    @Autowired
    private SubestructuraRepository subestructuraRepository;

    @Autowired
    private SuperestructuraRepository superestructuraRepository;

    @Transactional
    public void saveInventario(InventarioDTO request, Authentication authentication) {
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

        Inventario savedInventario = new Inventario();
        savedInventario.setPuente(puente);
        savedInventario.setObservaciones(request.getObservaciones());

        Usuario usuario = mapDTOToEntity(request.getUsuario(), Usuario.class);
        savedInventario.setUsuario(usuario);

        final Inventario inventario = inventarioRepository.save(savedInventario);

        if (request.getPasos() != null) {
            List<Paso> pasos = request.getPasos()
                    .stream()
                    .map(pasoDTO -> {
                        Paso paso = mapDTOToEntity(pasoDTO, Paso.class);
                        paso.setInventario(inventario);
                        return pasoRepository.save(paso);
                    })
                    .collect(Collectors.toList());
            inventario.setPasos(pasos);
        }

        if (request.getDatosAdministrativos() != null) {
            DatosAdministrativos datosAdministrativos = mapDTOToEntity(request.getDatosAdministrativos(),
                    DatosAdministrativos.class);
            datosAdministrativos.setInventario(inventario);
            inventario.setDatosAdministrativos(datosAdministrativosRepository.save(datosAdministrativos));
        }

        if (request.getDatosTecnicos() != null) {
            DatosTecnicos datosTecnicos = mapDTOToEntity(request.getDatosTecnicos(), DatosTecnicos.class);
            datosTecnicos.setInventario(inventario);
            inventario.setDatosTecnicos(datosTecnicosRepository.save(datosTecnicos));
        }

        if (request.getSuperestructuras() != null) {
            List<Superestructura> superestructuras = request.getSuperestructuras()
                    .stream()
                    .map(superestructuraDTO -> {
                        Superestructura superestructura = mapDTOToEntity(superestructuraDTO, Superestructura.class);
                        superestructura.setInventario(inventario);
                        return superestructuraRepository.save(superestructura);
                    })
                    .collect(Collectors.toList());
            inventario.setSuperestructuras(superestructuras);
        }

        if (request.getSubestructura() != null) {
            Subestructura savedSubestructura = new Subestructura();
            savedSubestructura.setInventario(inventario);

            final Subestructura subestructura = subestructuraRepository.save(savedSubestructura);
            inventario.setSubestructura(subestructura);
        
            if (request.getSubestructura().getEstribo() != null) {
                Estribo estribo = mapDTOToEntity(request.getSubestructura().getEstribo(), Estribo.class);
                estribo.setSubestructura(subestructura);
                estriboRepository.save(estribo);
            }
        
            if (request.getSubestructura().getPila() != null) {
                Pila pila = mapDTOToEntity(request.getSubestructura().getPila(), Pila.class);
                pila.setSubestructura(subestructura);
                pilaRepository.save(pila);
            }
        
            if (request.getSubestructura().getDetalle() != null) {
                Detalle detalle = mapDTOToEntity(request.getSubestructura().getDetalle(), Detalle.class);
                detalle.setSubestructura(subestructura);
                detalleRepository.save(detalle);
            }
        
            if (request.getSubestructura().getSenial() != null) {
                Senial senial = mapDTOToEntity(request.getSubestructura().getSenial(), Senial.class);
                senial.setSubestructura(subestructura);
                senialRepository.save(senial);
            }
        }

        if (request.getApoyo() != null) {
            Apoyo apoyo = mapDTOToEntity(request.getApoyo(), Apoyo.class);
            apoyo.setInventario(inventario);
            inventario.setApoyo(apoyoRepository.save(apoyo));
        }

        if (request.getMiembrosInteresados() != null) {
            MiembrosInteresados miembrosInteresados = mapDTOToEntity(request.getMiembrosInteresados(),
                    MiembrosInteresados.class);
            miembrosInteresados.setInventario(inventario);
            inventario.setMiembrosInteresados(miembrosInteresadosRepository.save(miembrosInteresados));
        }
        
        if (request.getPosicionGeografica() != null) {
            PosicionGeografica posicionGeografica = mapDTOToEntity(request.getPosicionGeografica(),
                    PosicionGeografica.class);
            posicionGeografica.setInventario(inventario);
            inventario.setPosicionGeografica(posicionGeograficaRepository.save(posicionGeografica));
        }

        if (request.getCarga() != null) {
            Carga carga = mapDTOToEntity(request.getCarga(), Carga.class);
            carga.setInventario(inventario);
            inventario.setCarga(cargaRepository.save(carga));
        }
    }

    private <D, E> E mapDTOToEntity(D dto, Class<E> entityClass) {
        if (dto == null)
            return null;
        try {
            E entity = entityClass.getDeclaredConstructor().newInstance();

            Field[] dtoFields = dto.getClass().getDeclaredFields();

            for (Field dtoField : dtoFields) {
                dtoField.setAccessible(true);
                try {
                    Field entityField = entityClass.getDeclaredField(dtoField.getName());
                    entityField.setAccessible(true);
                    Object dtoValue = dtoField.get(dto);
                    if (dtoValue == null) {
                        entityField.set(entity, null);
                    } else if (dtoField.getType().isPrimitive() ||
                            dtoField.getType().getName().startsWith("java.lang") ||
                            dtoField.getType().getName().startsWith("java.math") ||
                            dtoField.getType().getName().startsWith("java.time")) {
                        // Primitive, java.lang (String, Boolean), java.math (BigDecimal), java.time
                        // (LocalDate)
                        entityField.set(entity, dtoValue);
                    } else {
                        // Nested object: recursively map it
                        Object entityValue = mapDTOToEntity(dtoValue, entityField.getType());
                        entityField.set(entity, entityValue);
                    }
                } catch (NoSuchFieldException e) {
                    continue;
                }
            }

            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping DTO to Entity", e);
        }
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


    public InventarioDTO getInventarioById(Long id) {
        Inventario inventario = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado con id: " + id));
        
        return mapToDTO(inventario);
    }

    public InventarioDTO getInventarioByPuenteId(Long puenteId) {
        Puente puente = new Puente();
        puente.setId(puenteId);
    
        Optional<Inventario> inventarioOpt = inventarioRepository.findByPuente(puente);
        if (inventarioOpt.isEmpty()) {
            throw new RuntimeException("No se encontró un inventario para el puente con ID " + puenteId);
        }
    
        return mapToDTO(inventarioOpt.get());
    }

    public List<InventarioDTO> getAllInventariosLight() {
        List<Inventario> inventarios = inventarioRepository.findAll();
    
        return inventarios.stream().map(inv -> {
            InventarioDTO dto = new InventarioDTO();
            dto.setId(inv.getId());
            dto.setObservaciones(inv.getObservaciones());
    
            // Puente
            PuenteDTO puenteDTO = new PuenteDTO();
            puenteDTO.setId(inv.getPuente().getId());
            puenteDTO.setNombre(inv.getPuente().getNombre());
            puenteDTO.setIdentif(inv.getPuente().getIdentif());
            puenteDTO.setCarretera(inv.getPuente().getCarretera());
            puenteDTO.setPr(inv.getPuente().getPr());
            puenteDTO.setRegional(inv.getPuente().getRegional());
            dto.setPuente(puenteDTO);
    
            // Usuario
             if (inv.getUsuario() != null) {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(inv.getUsuario().getId());
                usuarioDTO.setNombres(inv.getUsuario().getNombres());
                usuarioDTO.setApellidos(inv.getUsuario().getApellidos());
                usuarioDTO.setCorreo(inv.getUsuario().getCorreo());
                usuarioDTO.setIdentificacion(inv.getUsuario().getIdentificacion());
                usuarioDTO.setMunicipio(inv.getUsuario().getMunicipio());
                usuarioDTO.setTipoUsuario(inv.getUsuario().getTipoUsuario());
                dto.setUsuario(usuarioDTO);
            } else {
                dto.setUsuario(null); // o un DTO vacío si prefieres
            }
            
    
            return dto;
        }).collect(Collectors.toList());
    }
    
    
    private InventarioDTO mapToDTO(Inventario inventario) {
        InventarioDTO dto = new InventarioDTO();

        dto.setId(inventario.getId());
        dto.setObservaciones(inventario.getObservaciones());

        // Puente
        PuenteDTO puenteDTO = new PuenteDTO();
        puenteDTO.setId(inventario.getPuente().getId());
        puenteDTO.setNombre(inventario.getPuente().getNombre());
        puenteDTO.setIdentif(inventario.getPuente().getIdentif());
        puenteDTO.setCarretera(inventario.getPuente().getCarretera());
        puenteDTO.setPr(inventario.getPuente().getPr());
        puenteDTO.setRegional(inventario.getPuente().getRegional());
        dto.setPuente(puenteDTO);

        // Usuario
        if (inventario.getUsuario() != null) {
                UsuarioDTO usuarioDTO = new UsuarioDTO();
                usuarioDTO.setId(inventario.getUsuario().getId());
                usuarioDTO.setNombres(inventario.getUsuario().getNombres());
                usuarioDTO.setApellidos(inventario.getUsuario().getApellidos());
                usuarioDTO.setCorreo(inventario.getUsuario().getCorreo());
                usuarioDTO.setIdentificacion(inventario.getUsuario().getIdentificacion());
                usuarioDTO.setMunicipio(inventario.getUsuario().getMunicipio());
                usuarioDTO.setTipoUsuario(inventario.getUsuario().getTipoUsuario());
                dto.setUsuario(usuarioDTO);
            } else {
                dto.setUsuario(null); // o un DTO vacío si prefieres
            }

        // Apoyo
        if (inventario.getApoyo() != null) {
            ApoyoDTO apoyoDTO = new ApoyoDTO();
            apoyoDTO.setFijoSobreEstribo(inventario.getApoyo().getFijoSobreEstribo());
            apoyoDTO.setMovilSobreEstribo(inventario.getApoyo().getMovilSobreEstribo());
            apoyoDTO.setFijoEnPila(inventario.getApoyo().getFijoEnPila());
            apoyoDTO.setMovilEnPila(inventario.getApoyo().getMovilEnPila());
            apoyoDTO.setFijoEnViga(inventario.getApoyo().getFijoEnViga());
            apoyoDTO.setMovilEnViga(inventario.getApoyo().getMovilEnViga());
            apoyoDTO.setVehiculoDisenio(inventario.getApoyo().getVehiculoDisenio());
            apoyoDTO.setClaseDistribucionCarga(inventario.getApoyo().getClaseDistribucionCarga());
            dto.setApoyo(apoyoDTO);
        }

        // Carga
        if (inventario.getCarga() != null) {
            CargaDTO cargaDTO = new CargaDTO();
            cargaDTO.setLongitudLuzCritica(inventario.getCarga().getLongitudLuzCritica());
            cargaDTO.setFactorClasificacion(inventario.getCarga().getFactorClasificacion());
            cargaDTO.setFuerzaCortante(inventario.getCarga().getFuerzaCortante());
            cargaDTO.setMomento(inventario.getCarga().getMomento());
            cargaDTO.setLineaCargaPorRueda(inventario.getCarga().getLineaCargaPorRueda());
            dto.setCarga(cargaDTO);
        }

        // Datos Administrativos
        if (inventario.getDatosAdministrativos() != null) {
            DatosAdministrativosDTO da = new DatosAdministrativosDTO();
            da.setAnioConstruccion(inventario.getDatosAdministrativos().getAnioConstruccion());
            da.setAnioReconstruccion(inventario.getDatosAdministrativos().getAnioReconstruccion());
            da.setDireccionAbscCarretera(inventario.getDatosAdministrativos().getDireccionAbscCarretera());
            da.setRequisitosInspeccion(inventario.getDatosAdministrativos().getRequisitosInspeccion());
            da.setNumeroSeccionesInspeccion(inventario.getDatosAdministrativos().getNumeroSeccionesInspeccion());
            da.setEstacionConteo(inventario.getDatosAdministrativos().getEstacionConteo());
            da.setFechaRecoleccionDatos(inventario.getDatosAdministrativos().getFechaRecoleccionDatos());
            dto.setDatosAdministrativos(da);
        }

        // Datos Técnicos
        if (inventario.getDatosTecnicos() != null) {
            DatosTecnicosDTO dt = new DatosTecnicosDTO();
            dt.setNumeroLuces(inventario.getDatosTecnicos().getNumeroLuces());
            dt.setLongitudLuzMenor(inventario.getDatosTecnicos().getLongitudLuzMenor());
            dt.setLongitudLuzMayor(inventario.getDatosTecnicos().getLongitudLuzMayor());
            dt.setLongitudTotal(inventario.getDatosTecnicos().getLongitudTotal());
            dt.setAnchoTablero(inventario.getDatosTecnicos().getAnchoTablero());
            dt.setAnchoSeparador(inventario.getDatosTecnicos().getAnchoSeparador());
            dt.setAnchoAndenIzq(inventario.getDatosTecnicos().getAnchoAndenIzq());
            dt.setAnchoAndenDer(inventario.getDatosTecnicos().getAnchoAndenDer());
            dt.setAnchoCalzada(inventario.getDatosTecnicos().getAnchoCalzada());
            dt.setAnchoEntreBordillos(inventario.getDatosTecnicos().getAnchoEntreBordillos());
            dt.setAnchoAcceso(inventario.getDatosTecnicos().getAnchoAcceso());
            dt.setAlturaPilas(inventario.getDatosTecnicos().getAlturaPilas());
            dt.setAlturaEstribos(inventario.getDatosTecnicos().getAlturaEstribos());
            dt.setLongitudApoyoPilas(inventario.getDatosTecnicos().getLongitudApoyoPilas());
            dt.setLongitudApoyoEstribos(inventario.getDatosTecnicos().getLongitudApoyoEstribos());
            dt.setPuenteTerraplen(inventario.getDatosTecnicos().getPuenteTerraplen());
            dt.setPuenteCurvaTangente(inventario.getDatosTecnicos().getPuenteCurvaTangente());
            dt.setEsviajamiento(inventario.getDatosTecnicos().getEsviajamiento());
            dto.setDatosTecnicos(dt);
        }

        // Miembros Interesados
        if (inventario.getMiembrosInteresados() != null) {
            MiembrosInteresadosDTO mi = new MiembrosInteresadosDTO();
            mi.setPropietario(inventario.getMiembrosInteresados().getPropietario());
            mi.setDepartamento(inventario.getMiembrosInteresados().getDepartamento());
            mi.setAdministradorVial(inventario.getMiembrosInteresados().getAdministradorVial());
            mi.setProyectista(inventario.getMiembrosInteresados().getProyectista());
            mi.setMunicipio(inventario.getMiembrosInteresados().getMunicipio());
            dto.setMiembrosInteresados(mi);
        }

        // Posición Geográfica
        if (inventario.getPosicionGeografica() != null) {
            PosicionGeograficaDTO pg = new PosicionGeograficaDTO();
            pg.setLatitud(inventario.getPosicionGeografica().getLatitud());
            pg.setLongitud(inventario.getPosicionGeografica().getLongitud());
            pg.setAltitud(inventario.getPosicionGeografica().getAltitud());
            pg.setCoeficienteAceleracionSismica(inventario.getPosicionGeografica().getCoeficienteAceleracionSismica());
            pg.setPasoCauce(inventario.getPosicionGeografica().getPasoCauce());
            pg.setExisteVariante(inventario.getPosicionGeografica().getExisteVariante());
            pg.setLongitudVariante(inventario.getPosicionGeografica().getLongitudVariante());
            pg.setEstado(inventario.getPosicionGeografica().getEstado());
            dto.setPosicionGeografica(pg);
        }

        // Pasos
        if (inventario.getPasos() != null && !inventario.getPasos().isEmpty()) {
            List<PasoDTO> pasosDTO = inventario.getPasos().stream().map(p -> {
                PasoDTO pasoDTO = new PasoDTO();
                pasoDTO.setNumero(p.getNumero());
                pasoDTO.setTipoPaso(p.getTipoPaso());
                pasoDTO.setPrimero(p.getPrimero());
                pasoDTO.setSupInf(p.getSupInf());
                pasoDTO.setGaliboI(p.getGaliboI());
                pasoDTO.setGaliboIm(p.getGaliboIm());
                pasoDTO.setGaliboDm(p.getGaliboDm());
                pasoDTO.setGaliboD(p.getGaliboD());
                return pasoDTO;
            }).collect(Collectors.toList());
        
            dto.setPasos(pasosDTO);
        }
        
        

         // Subestructura
         if (inventario.getSubestructura() != null) {
            SubestructuraDTO sub = new SubestructuraDTO();

            if (inventario.getSubestructura().getEstribo() != null) {
                EstriboDTO e = new EstriboDTO();
                e.setTipo(inventario.getSubestructura().getEstribo().getTipo());
                e.setMaterial(inventario.getSubestructura().getEstribo().getMaterial());
                e.setTipoCimentacion(inventario.getSubestructura().getEstribo().getTipoCimentacion());
                sub.setEstribo(e);
            }

            if (inventario.getSubestructura().getDetalle() != null) {
                DetalleDTO d = new DetalleDTO();
                d.setTipoBaranda(inventario.getSubestructura().getDetalle().getTipoBaranda());
                d.setSuperficieRodadura(inventario.getSubestructura().getDetalle().getSuperficieRodadura());
                d.setJuntaExpansion(inventario.getSubestructura().getDetalle().getJuntaExpansion());
                sub.setDetalle(d);
            }

            if (inventario.getSubestructura().getSenial() != null) {
                SenialDTO s = new SenialDTO();
                s.setCargaMaxima(inventario.getSubestructura().getSenial().getCargaMaxima());
                s.setVelocidadMaxima(inventario.getSubestructura().getSenial().getVelocidadMaxima());
                s.setOtra(inventario.getSubestructura().getSenial().getOtra());
                sub.setSenial(s);
            }

            if (inventario.getSubestructura().getPila() != null) {
                PilaDTO p = new PilaDTO();
                p.setTipo(inventario.getSubestructura().getPila().getTipo());
                p.setMaterial(inventario.getSubestructura().getPila().getMaterial());
                p.setTipoCimentacion(inventario.getSubestructura().getPila().getTipoCimentacion());
                sub.setPila(p);
            }

            dto.setSubestructura(sub);
        }

        // Superestructuras
        if (inventario.getSuperestructuras() != null) {
            List<SuperestructuraDTO> superestructurasDTO = inventario.getSuperestructuras().stream().map(se -> {
                SuperestructuraDTO s = new SuperestructuraDTO();
                s.setTipo(se.getTipo());
                s.setDisenioTipo(se.getDisenioTipo());
                s.setTipoEstructuracionTransversal(se.getTipoEstructuracionTransversal());
                s.setTipoEstructuracionLongitudinal(se.getTipoEstructuracionLongitudinal());
                s.setMaterial(se.getMaterial());
                return s;
            }).collect(Collectors.toList());
            dto.setSuperestructuras(superestructurasDTO);
        }

        return dto;
    }

    @Transactional
    public void deleteInventario(Long id) {
        Optional<Inventario> inventarioOpt = inventarioRepository.findById(id);
        if (inventarioOpt.isPresent()) {
            inventarioRepository.delete(inventarioOpt.get());
        } else {
            throw new RuntimeException("Inventario no encontrado con id: " + id);
        }
    }

    @Transactional
    public void updateInventario(Long id, InventarioDTO request) {
        Inventario inventario = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Inventario no encontrado con ID: " + id));

        inventario.setObservaciones(request.getObservaciones());

        // Puente (si se permite actualizar)
        if (request.getPuente() != null) {
            Puente puente = inventario.getPuente();
            puente.setNombre(request.getPuente().getNombre());
            puente.setIdentif(request.getPuente().getIdentif());
            puente.setCarretera(request.getPuente().getCarretera());
            puente.setPr(request.getPuente().getPr());
            puente.setRegional(request.getPuente().getRegional());
        }

        // Apoyo
        if (request.getApoyo() != null) {
            Apoyo apoyo = inventario.getApoyo();
            if (apoyo == null) {
                apoyo = new Apoyo();
                apoyo.setInventario(inventario);
            }
            apoyo.setFijoSobreEstribo(request.getApoyo().getFijoSobreEstribo());
            apoyo.setMovilSobreEstribo(request.getApoyo().getMovilSobreEstribo());
            apoyo.setFijoEnPila(request.getApoyo().getFijoEnPila());
            apoyo.setMovilEnPila(request.getApoyo().getMovilEnPila());
            apoyo.setFijoEnViga(request.getApoyo().getFijoEnViga());
            apoyo.setMovilEnViga(request.getApoyo().getMovilEnViga());
            apoyo.setVehiculoDisenio(request.getApoyo().getVehiculoDisenio());
            apoyo.setClaseDistribucionCarga(request.getApoyo().getClaseDistribucionCarga());
            inventario.setApoyo(apoyo);
        }

        // Carga
        if (request.getCarga() != null) {
            Carga carga = inventario.getCarga();
            if (carga == null) {
                carga = new Carga();
                carga.setInventario(inventario);
            }
            carga.setLongitudLuzCritica(request.getCarga().getLongitudLuzCritica());
            carga.setFactorClasificacion(request.getCarga().getFactorClasificacion());
            carga.setFuerzaCortante(request.getCarga().getFuerzaCortante());
            carga.setMomento(request.getCarga().getMomento());
            carga.setLineaCargaPorRueda(request.getCarga().getLineaCargaPorRueda());
            inventario.setCarga(carga);
        }

        if (request.getDatosAdministrativos() != null) {
            DatosAdministrativos da = inventario.getDatosAdministrativos();
            if (da == null) {
                da = new DatosAdministrativos();
                da.setInventario(inventario);
            }
            da.setAnioConstruccion(request.getDatosAdministrativos().getAnioConstruccion());
            da.setAnioReconstruccion(request.getDatosAdministrativos().getAnioReconstruccion());
            da.setDireccionAbscCarretera(request.getDatosAdministrativos().getDireccionAbscCarretera());
            da.setRequisitosInspeccion(request.getDatosAdministrativos().getRequisitosInspeccion());
            da.setNumeroSeccionesInspeccion(request.getDatosAdministrativos().getNumeroSeccionesInspeccion());
            da.setEstacionConteo(request.getDatosAdministrativos().getEstacionConteo());
            da.setFechaRecoleccionDatos(request.getDatosAdministrativos().getFechaRecoleccionDatos());
            inventario.setDatosAdministrativos(da);
        }

        if (request.getDatosTecnicos() != null) {
            DatosTecnicos dt = inventario.getDatosTecnicos();
            if (dt == null) {
                dt = new DatosTecnicos();
                dt.setInventario(inventario);
            }
            dt.setNumeroLuces(request.getDatosTecnicos().getNumeroLuces());
            dt.setLongitudLuzMenor(request.getDatosTecnicos().getLongitudLuzMenor());
            dt.setLongitudLuzMayor(request.getDatosTecnicos().getLongitudLuzMayor());
            dt.setLongitudTotal(request.getDatosTecnicos().getLongitudTotal());
            dt.setAnchoTablero(request.getDatosTecnicos().getAnchoTablero());
            dt.setAnchoSeparador(request.getDatosTecnicos().getAnchoSeparador());
            dt.setAnchoAndenIzq(request.getDatosTecnicos().getAnchoAndenIzq());
            dt.setAnchoAndenDer(request.getDatosTecnicos().getAnchoAndenDer());
            dt.setAnchoCalzada(request.getDatosTecnicos().getAnchoCalzada());
            dt.setAnchoEntreBordillos(request.getDatosTecnicos().getAnchoEntreBordillos());
            dt.setAnchoAcceso(request.getDatosTecnicos().getAnchoAcceso());
            dt.setAlturaPilas(request.getDatosTecnicos().getAlturaPilas());
            dt.setAlturaEstribos(request.getDatosTecnicos().getAlturaEstribos());
            dt.setLongitudApoyoPilas(request.getDatosTecnicos().getLongitudApoyoPilas());
            dt.setLongitudApoyoEstribos(request.getDatosTecnicos().getLongitudApoyoEstribos());
            dt.setPuenteTerraplen(request.getDatosTecnicos().getPuenteTerraplen());
            dt.setPuenteCurvaTangente(request.getDatosTecnicos().getPuenteCurvaTangente());
            dt.setEsviajamiento(request.getDatosTecnicos().getEsviajamiento());
            inventario.setDatosTecnicos(dt);
        }

        if (request.getMiembrosInteresados() != null) {
            MiembrosInteresados mi = inventario.getMiembrosInteresados();
            if (mi == null) {
                mi = new MiembrosInteresados();
                mi.setInventario(inventario);
            }
            mi.setPropietario(request.getMiembrosInteresados().getPropietario());
            mi.setDepartamento(request.getMiembrosInteresados().getDepartamento());
            mi.setAdministradorVial(request.getMiembrosInteresados().getAdministradorVial());
            mi.setProyectista(request.getMiembrosInteresados().getProyectista());
            mi.setMunicipio(request.getMiembrosInteresados().getMunicipio());
            inventario.setMiembrosInteresados(mi);
        }

        if (request.getPosicionGeografica() != null) {
            PosicionGeografica pg = inventario.getPosicionGeografica();
            if (pg == null) {
                pg = new PosicionGeografica();
                pg.setInventario(inventario);
            }
            pg.setLatitud(request.getPosicionGeografica().getLatitud());
            pg.setLongitud(request.getPosicionGeografica().getLongitud());
            pg.setAltitud(request.getPosicionGeografica().getAltitud());
            pg.setCoeficienteAceleracionSismica(request.getPosicionGeografica().getCoeficienteAceleracionSismica());
            pg.setPasoCauce(request.getPosicionGeografica().getPasoCauce());
            pg.setExisteVariante(request.getPosicionGeografica().getExisteVariante());
            pg.setLongitudVariante(request.getPosicionGeografica().getLongitudVariante());
            pg.setEstado(request.getPosicionGeografica().getEstado());
            inventario.setPosicionGeografica(pg);
        }

        if (request.getSubestructura() != null) {
            Subestructura sub = inventario.getSubestructura();
            if (sub == null) {
                sub = new Subestructura();
                sub.setInventario(inventario);
            }
        
            if (request.getSubestructura().getEstribo() != null) {
                Estribo e = sub.getEstribo();
                if (e == null) {
                    e = new Estribo();
                    e.setSubestructura(sub);
                }
                e.setTipo(request.getSubestructura().getEstribo().getTipo());
                e.setMaterial(request.getSubestructura().getEstribo().getMaterial());
                e.setTipoCimentacion(request.getSubestructura().getEstribo().getTipoCimentacion());
                sub.setEstribo(e);
            }
        
            if (request.getSubestructura().getDetalle() != null) {
                Detalle d = sub.getDetalle();
                if (d == null) {
                    d = new Detalle();
                    d.setSubestructura(sub);
                }
                d.setTipoBaranda(request.getSubestructura().getDetalle().getTipoBaranda());
                d.setSuperficieRodadura(request.getSubestructura().getDetalle().getSuperficieRodadura());
                d.setJuntaExpansion(request.getSubestructura().getDetalle().getJuntaExpansion());
                sub.setDetalle(d);
            }
        
            if (request.getSubestructura().getSenial() != null) {
                Senial s = sub.getSenial();
                if (s == null) {
                    s = new Senial();
                    s.setSubestructura(sub);
                }
                s.setCargaMaxima(request.getSubestructura().getSenial().getCargaMaxima());
                s.setVelocidadMaxima(request.getSubestructura().getSenial().getVelocidadMaxima());
                s.setOtra(request.getSubestructura().getSenial().getOtra());
                sub.setSenial(s);
            }
        
            if (request.getSubestructura().getPila() != null) {
                Pila p = sub.getPila();
                if (p == null) {
                    p = new Pila();
                    p.setSubestructura(sub);
                }
                p.setTipo(request.getSubestructura().getPila().getTipo());
                p.setMaterial(request.getSubestructura().getPila().getMaterial());
                p.setTipoCimentacion(request.getSubestructura().getPila().getTipoCimentacion());
                sub.setPila(p);
            }
        
            inventario.setSubestructura(sub);
        }

        superestructuraRepository.deleteAll(inventario.getSuperestructuras());

        if (request.getSuperestructuras() != null) {
            List<Superestructura> nuevas = request.getSuperestructuras().stream().map(dto -> {
                Superestructura s = new Superestructura();
                s.setTipo(dto.getTipo());
                s.setDisenioTipo(dto.getDisenioTipo());
                s.setTipoEstructuracionTransversal(dto.getTipoEstructuracionTransversal());
                s.setTipoEstructuracionLongitudinal(dto.getTipoEstructuracionLongitudinal());
                s.setMaterial(dto.getMaterial());
                s.setInventario(inventario);
                return s;
            }).collect(Collectors.toList());
            inventario.setSuperestructuras(nuevas);
        }

        // Eliminar pasos existentes y guardar los nuevos
        pasoRepository.deleteAll(inventario.getPasos());
        if (request.getPasos() != null) {
            List<Paso> nuevosPasos = request.getPasos().stream().map(dto -> {
                Paso paso = new Paso();
                paso.setNumero(dto.getNumero());
                paso.setTipoPaso(dto.getTipoPaso());
                paso.setPrimero(dto.getPrimero());
                paso.setSupInf(dto.getSupInf());
                paso.setGaliboI(dto.getGaliboI());
                paso.setGaliboIm(dto.getGaliboIm());
                paso.setGaliboDm(dto.getGaliboDm());
                paso.setGaliboD(dto.getGaliboD());
                paso.setInventario(inventario);
                return paso;
            }).collect(Collectors.toList());
            inventario.setPasos(nuevosPasos);
        }

        // Guarda cambios
        inventarioRepository.save(inventario);
    }


}
