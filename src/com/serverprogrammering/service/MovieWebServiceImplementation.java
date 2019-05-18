package com.serverprogrammering.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;

import com.serverprogrammering.dataaccess.MovieNotFoundException;
import com.serverprogrammering.domain.Movie;

@Stateless
@WebService(name="MovieWebService")
public class MovieWebServiceImplementation {
	
	@Inject
	private MovieServiceLocal service;

	public Movie getMovieById(int id) throws MovieNotFoundException {
		return service.getById(id);
	}
	
	public List<Movie> getAllMovies(){
		return service.getAllMovies();
	}
	
	public void addNewMovie(Movie movie) throws ServiceUnavailableException {
		service.addMovie(movie);
	}
}
