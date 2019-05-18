package com.serverprogrammering.service;

import java.util.List;

import javax.ejb.Local;

import com.serverprogrammering.dataaccess.MovieNotFoundException;
import com.serverprogrammering.domain.Movie;

@Local
public interface MovieServiceLocal {

	public void addMovie(Movie movie) throws ServiceUnavailableException;
	public List<Movie> getAllMovies();
	public List<Movie> searchByMovieTitle(String title);
	public Movie getById(int id) throws MovieNotFoundException;
	public int countVisitor(int value);
	void updateMovie(int id, String title, String director, String stars, String runningTime, String genre, int year, String description) throws MovieNotFoundException;
	void deleteMovie(int id) throws MovieNotFoundException;

	
}
