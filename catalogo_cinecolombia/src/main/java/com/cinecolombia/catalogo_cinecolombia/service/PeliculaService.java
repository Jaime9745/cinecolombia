package com.cinecolombia.catalogo_cinecolombia.service;

import com.cinecolombia.catalogo_cinecolombia.model.Pelicula;
import com.cinecolombia.catalogo_cinecolombia.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class PeliculaService {
    private final PeliculaRepository peliculaRepository;

    public List<Pelicula> getAllPeliculas() {
        log.info("Obteniendo todas las películas");
        return peliculaRepository.findAll();
    }

    public Optional<Pelicula> getPeliculaById(Long id) {
        log.info("Buscando película con id: {}", id);
        return peliculaRepository.findById(id);
    }

    public Pelicula savePelicula(Pelicula pelicula) {
        log.info("Guardando nueva película: {}", pelicula.getNombre());
        return peliculaRepository.save(pelicula);
    }

    public Pelicula updatePelicula(Long id, Pelicula peliculaDetails) {
        log.info("Actualizando película con id: {}", id);
        return peliculaRepository.findById(id)
                .map(pelicula -> {
                    pelicula.setNombre(peliculaDetails.getNombre());
                    pelicula.setCategoria(peliculaDetails.getCategoria());
                    pelicula.setAnio(peliculaDetails.getAnio());
                    pelicula.setDirector(peliculaDetails.getDirector());
                    pelicula.setDuracion(peliculaDetails.getDuracion());
                    pelicula.setCalificacion(peliculaDetails.getCalificacion());
                    return peliculaRepository.save(pelicula);
                })
                .orElseThrow(() -> new RuntimeException("Película no encontrada con id: " + id));
    }

    public void deletePelicula(Long id) {
        log.info("Eliminando película con id: {}", id);
        peliculaRepository.deleteById(id);
    }
}