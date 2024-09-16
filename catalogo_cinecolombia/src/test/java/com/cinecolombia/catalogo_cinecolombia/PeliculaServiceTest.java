package com.cinecolombia.catalogo_cinecolombia;

import com.cinecolombia.catalogo_cinecolombia.model.Pelicula;
import com.cinecolombia.catalogo_cinecolombia.repository.PeliculaRepository;
import com.cinecolombia.catalogo_cinecolombia.service.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula(1L, "Encanto", "Animación", 2021, "Byron Howard", 109, 7.2);
    }

    @Test
    void getAllPeliculas() {
        when(peliculaRepository.findAll()).thenReturn(Arrays.asList(pelicula));

        List<Pelicula> result = peliculaService.getAllPeliculas();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(pelicula, result.get(0));
    }

    @Test
    void getPeliculaById() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        Optional<Pelicula> result = peliculaService.getPeliculaById(1L);

        assertTrue(result.isPresent());
        assertEquals(pelicula, result.get());
    }

    @Test
    void savePelicula() {
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        Pelicula result = peliculaService.savePelicula(pelicula);

        assertNotNull(result);
        assertEquals(pelicula, result);
    }

    @Test
    void updatePelicula() {
        Pelicula updatedPelicula = new Pelicula(1L, "Encanto (Updated)", "Animación", 2021, "Byron Howard", 109, 7.5);

        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(updatedPelicula);

        Pelicula result = peliculaService.updatePelicula(1L, updatedPelicula);

        assertNotNull(result);
        assertEquals(updatedPelicula.getNombre(), result.getNombre());
        assertEquals(updatedPelicula.getCalificacion(), result.getCalificacion());
    }

    @Test
    void deletePelicula() {
        doNothing().when(peliculaRepository).deleteById(1L);

        peliculaService.deletePelicula(1L);

        verify(peliculaRepository, times(1)).deleteById(1L);
    }
}