package com.juanma1301.libros.service;

import com.juanma1301.libros.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Libro crearLibro(Libro libro);
    List<Libro> obtenerTodos();
    Optional<Libro> obtenerPorId(Long id);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
}
