package com.digitalbooking.backend.Services.impl;


import com.digitalbooking.backend.Dto.PaginaDTO;
import com.digitalbooking.backend.Dto.ReservaDTO;
import com.digitalbooking.backend.Dto.ReservaDTOList;
import com.digitalbooking.backend.Exceptions.EntityNotFoundException;
import com.digitalbooking.backend.Exceptions.InvalidIdException;
import com.digitalbooking.backend.Models.Reserva;
import com.digitalbooking.backend.Repository.IReservasRepository;
import com.digitalbooking.backend.Security.entity.Usuario;
import com.digitalbooking.backend.Services.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService implements IReservaService {

    @Autowired
    private IReservasRepository reservaRepository;
    @Autowired
    private ObjectMapper mapper;

    public static final String ENTITY_NOT_FOUND_MESSAGE  = "No se encontro la reserva con el id indicado";

    @Override
    public ReservaDTO create(ReservaDTO reservaDTO) {
        Reserva reserva = mapEntity(reservaDTO);
        reserva.setUsuario(new Usuario(reservaDTO.getUsuario().getId()));
        Reserva newReserva = reservaRepository.save(reserva);
        return mapDTO(newReserva);
    }

    @Override
    public ReservaDTO update(ReservaDTO obj) {
        int id = obj.getId();
        if(id <= 0) throw new InvalidIdException();
        reservaRepository.findById(obj.getId())
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        Reserva newReserva = reservaRepository.save(mapEntity(obj));
        return mapDTO(newReserva);
    }

    @Override
    public ReservaDTO findById(Integer id) {
         Reserva newreserva = reservaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        return mapDTO(newreserva);
    }

    @Override
    public void deleteById(Integer id) {
        reservaRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException(ENTITY_NOT_FOUND_MESSAGE));
        reservaRepository.deleteById(id);
    }

    @Override
    public PaginaDTO<ReservaDTO> findAll(Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageable = PageRequest.of(page, size);
        Page<Reserva> pagina = reservaRepository.findAll(pageable);
        List<ReservaDTO> reservasDTO = pagina.getContent().stream()
                .map(this::mapDTO)
                .collect(Collectors.toList());
        long numeroReservas = pagina.getTotalElements();
        return new PaginaDTO<>(page, size, numeroReservas, reservasDTO);
    }


    @Override
    public PaginaDTO<ReservaDTOList> findByProductoId(Integer productoId, Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageable = PageRequest.of(page, size);

        Page<Reserva> pagina = reservaRepository.findByProductoId(productoId,pageable);
        List<ReservaDTOList> reservasDTO = pagina.getContent().stream()
                .map(this::mapDTOList)
                .collect(Collectors.toList());
        long numeroReservas = pagina.getTotalElements();
        return new PaginaDTO<>(page, size, numeroReservas, reservasDTO);
    }

    @Override
    public PaginaDTO<ReservaDTO> findByUsuarioId(Integer usuarioId, Integer page, Integer size) {
        page=page==null?0:page;
        size=size==null?8:size;
        Pageable pageable = PageRequest.of(page, size);

        Page<Reserva> pagina = reservaRepository.findByUsuarioId(usuarioId,pageable);
        List<ReservaDTO> reservasDTO = pagina.getContent().stream()
                .map(this::mapDTO)
                .collect(Collectors.toList());
        long numeroReservas = pagina.getTotalElements();
        return new PaginaDTO<>(page, size, numeroReservas, reservasDTO);
    }


    private ReservaDTO mapDTO(Reserva reserva){
            return mapper.convertValue(reserva, ReservaDTO.class);
        }
    public Reserva mapEntity(ReservaDTO reservaDTO){
            return mapper.convertValue(reservaDTO, Reserva.class);
    }

    private ReservaDTOList mapDTOList(Reserva reserva){
            return mapper.convertValue(reserva, ReservaDTOList.class);
        }
    public Reserva mapEntity(ReservaDTOList reservaDTOList){
            return mapper.convertValue(reservaDTOList, Reserva.class);
    }

}
