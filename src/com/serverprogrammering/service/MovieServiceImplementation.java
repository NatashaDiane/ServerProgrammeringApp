package com.serverprogrammering.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.serverprogrammering.dataaccess.MovieDataAccess;
import com.serverprogrammering.dataaccess.MovieNotFoundException;
import com.serverprogrammering.domain.Movie;
@Stateless
public class MovieServiceImplementation implements MovieService, MovieServiceLocal {
	//connecting our data access class 
	
	@Inject
	private MovieDataAccess dao;

	@Override
	public void addMovie(Movie movie) throws ServiceUnavailableException{
		dao.insert(movie);
	}

	@Override
	public List<Movie> getAllMovies() {
		return dao.findAll();
	}

	@Override
	public List<Movie> searchByMovieTitle(String title) {
		return dao.findByTitle(title);
	}

	@Override
	public Movie getById(int id) throws MovieNotFoundException{
		return dao.findById(id);
	}

	@Override
	public void updateMovie(int id, String title, String director, String stars, String runningTime, String genre, int year, String description) throws MovieNotFoundException {
		dao.updateMovie(id, title, director, stars, runningTime, genre, year, description);
	}
	
	@Override
	public void deleteMovie(int id) throws MovieNotFoundException {
		dao.deleteMovie(id);
		
	}

	@Override
	public int countVisitor(int value) {
		return dao.countVisitor(value);
	}
}
