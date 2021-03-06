package org.dalvarez.videoclub.persistence_h2.service;

import org.dalvarez.videoclub.persistence_h2.entities.MovieEntity;
import org.dalvarez.videoclub.persistence_h2.repositories.MovieRepository;
import org.dalvarez.videoclub.persistence_h2.utils.Utils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class SeederService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeederService.class);

    private MovieRepository movieRepository;

    public SeederService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void seedDatabase() {
        LOGGER.warn("Seeding the database");
        movieRepository.saveAll(
                Arrays.stream(Utils.getDefaultMovies())
                        .map(MovieEntity::fromMovie)
                        .collect(Collectors.toSet())
        );
        Assertions.assertEquals(Utils.getDefaultMovies().length, movieRepository.findAll().size());
    }

    public void deleteAll() {
        LOGGER.warn("Deleting the records of the database");
        movieRepository.deleteAll();
    }
}
