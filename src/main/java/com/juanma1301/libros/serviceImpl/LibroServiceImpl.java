package com.juanma1301.libros.serviceImpl;

import com.juanma1301.libros.model.Libro;
import com.juanma1301.libros.repository.LibroRepository;
import com.juanma1301.libros.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository libroRepository;

    @Autowired
    public LibroServiceImpl(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> obtenerTodos() {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    @Override
    public Libro actualizarLibro(Long id, Libro libro) {
        return libroRepository.findById(id).map(l -> {
            l.setTitulo(libro.getTitulo());
            l.setAutor(libro.getAutor());
            l.setAnio(libro.getAnio());
            l.setEditorial(libro.getEditorial());
            return libroRepository.save(l);
        }).orElse(null);
    }

    @Override
    public void eliminarLibro(Long id) {
        libroRepository.deleteById(id);
    }
}
