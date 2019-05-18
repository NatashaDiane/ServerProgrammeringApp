package com.serverprogrammering.beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.serverprogrammering.domain.Movie;
import com.serverprogrammering.service.MovieServiceLocal;

@ManagedBean(name = "allMoviesPageBean")
public class AllMoviesPageBean {

	@EJB
	private MovieServiceLocal service;

	private Movie selectedMovie;

	public List<Movie> getAllMovies() {
		return service.getAllMovies();
	}

	public Movie getSelectedMovie() {
		return this.selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

}
