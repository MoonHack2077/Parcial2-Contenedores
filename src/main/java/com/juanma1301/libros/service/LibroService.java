package com.tuusuario.libros.service;

import com.tuusuario.libros.model.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    Libro crearLibro(Libro libro);
    List<Libro> obtenerTodos();
    Optional<Libro> obtenerPorId(Long id);
    Libro actualizarLibro(Long id, Libro libro);
    void eliminarLibro(Long id);
}
