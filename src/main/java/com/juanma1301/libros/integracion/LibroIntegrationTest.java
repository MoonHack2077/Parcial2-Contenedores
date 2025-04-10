// package com.juanma1301.libros.integracion;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.juanma1301.libros.model.Libro;
// import com.juanma1301.libros.repository.LibroRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.hamcrest.Matchers.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @SpringBootTest
// @AutoConfigureMockMvc
// @AutoConfigureTestDatabase
// public class LibroIntegrationTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @Autowired
//     private LibroRepository libroRepository;

//     private final ObjectMapper objectMapper = new ObjectMapper();

//     @BeforeEach
//     void setup() {
//         libroRepository.deleteAll(); // Limpiar base antes de cada test
//     }

//     @Test
//     void testCrearYObtenerLibro() throws Exception {
//         Libro libro = new Libro(null, "Cien años de soledad", "Gabriel García Márquez", 1967, "Sudamericana");

//         // Crear
//         mockMvc.perform(post("/api/libros")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(libro)))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.id").exists());

//         // Verificar que esté en la base
//         mockMvc.perform(get("/api/libros"))
//                 .andExpect(status().isOk())
//                 .andExpect(jsonPath("$.size()").value(1))
//                 .andExpect(jsonPath("$[0].titulo").value("Cien años de soledad"));
//     }

//     @Test
//     void testEliminarLibro() throws Exception {
//         Libro libro = new Libro(null, "Rayuela", "Julio Cortázar", 1963, "Sudamericana");
//         Libro saved = libroRepository.save(libro);

//         mockMvc.perform(delete("/api/libros/" + saved.getId()))
//                 .andExpect(status().isNoContent());

//         mockMvc.perform(get("/api/libros/" + saved.getId()))
//                 .andExpect(status().isNotFound());
//     }
// }
