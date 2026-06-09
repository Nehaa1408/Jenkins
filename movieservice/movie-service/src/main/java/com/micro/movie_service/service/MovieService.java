package com.micro.movie_service.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.micro.movie_service.entity.Movie;
import com.micro.movie_service.feign.UserFeignClient;
import com.micro.movie_service.kafka.MovieProducer;
import com.micro.movie_service.repository.MovieRepository;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private MovieProducer movieProducer;

    public Movie saveMovie(@NonNull Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        movieProducer.sendMessage("Movie Created: " + savedMovie.getTitle());
        return savedMovie;
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(@NonNull Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public Object getMovieWithUser(@NonNull Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        Map<String, Object> user = userFeignClient.getUserById(movie.getUserId());
        return Map.of("movie", movie,
                "user", user);
    }
}
