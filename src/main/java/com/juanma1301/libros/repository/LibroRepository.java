package com.juanma1301.libros.repository;

import com.juanma1301.libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
