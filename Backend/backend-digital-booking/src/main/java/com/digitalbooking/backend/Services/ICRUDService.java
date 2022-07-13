package com.digitalbooking.backend.Services;

import com.digitalbooking.backend.Dto.PaginaDTO;

import java.util.List;

public interface ICRUDService<T> {
        T create(T obj);
        T update(T obj);
        T findById(Integer id);
        void deleteById(Integer id);
        PaginaDTO<T> findAll(Integer page, Integer size);


}
