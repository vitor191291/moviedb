package br.com.infosys.moviedb.core.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.infosys.moviedb.domain.entities.Movie;
import br.com.infosys.moviedb.domain.repositories.MovieRepository;

@Service
public class MovieService {

	private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

	private MovieRepository movieRepository;

	@Autowired
	public MovieService(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Transactional
	public Movie save(Movie entity) {
		return movieRepository.saveAndFlush(entity);
	}

	@Transactional
	public void delete(Movie entity) {
		movieRepository.delete(entity);
	}

	@Transactional(readOnly = true)
	public Movie findById(Long id) {
		Movie movie = movieRepository.findOne(id);
		return movie;
	}

	@Transactional(readOnly = true)
	public List<Movie> findAll() {
		List<Movie> movies = movieRepository.findAll();
		return movies;
	}

	@Transactional(readOnly = true)
	public boolean exists(Long id) {
		return movieRepository.exists(id);
	}

}