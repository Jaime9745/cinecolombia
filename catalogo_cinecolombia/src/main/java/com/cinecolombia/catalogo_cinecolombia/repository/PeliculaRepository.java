package com.cinecolombia.catalogo_cinecolombia.repository;

import com.cinecolombia.catalogo_cinecolombia.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

}