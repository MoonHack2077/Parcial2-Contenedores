package com.juanma1301.libros;

import com.juanma1301.libros.controller.LibroController;
import com.juanma1301.libros.model.Libro;
import com.juanma1301.libros.service.LibroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCrearLibro() throws Exception {
        Libro libro = new Libro(1L, "1984", "George Orwell", 1949, "Secker & Warburg");

        Mockito.when(libroService.crearLibro(Mockito.any())).thenReturn(libro);

        mockMvc.perform(post("/api/libros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("1984"));
    }

    @Test
    public void testObtenerTodos() throws Exception {
        Libro l1 = new Libro(1L, "Libro A", "Autor A", 2000, "Editorial A");
        Libro l2 = new Libro(2L, "Libro B", "Autor B", 2020, "Editorial B");

        Mockito.when(libroService.obtenerTodos()).thenReturn(Arrays.asList(l1, l2));

        mockMvc.perform(get("/api/libros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    public void testObtenerPorId() throws Exception {
        Libro libro = new Libro(1L, "Dune", "Frank Herbert", 1965, "Chilton Books");

        Mockito.when(libroService.obtenerPorId(1L)).thenReturn(Optional.of(libro));

        mockMvc.perform(get("/api/libros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Dune"));
    }

    @Test
    public void testActualizarLibro() throws Exception {
        Libro libroActualizado = new Libro(1L, "Dune Messiah", "Frank Herbert", 1969, "Putnam");

        Mockito.when(libroService.actualizarLibro(Mockito.eq(1L), Mockito.any())).thenReturn(libroActualizado);

        mockMvc.perform(put("/api/libros/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(libroActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Dune Messiah"));
    }

    @Test
    public void testEliminarLibro() throws Exception {
        mockMvc.perform(delete("/api/libros/1"))
                .andExpect(status().isNoContent());
    }
}
 