package com.serverprogrammering.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.serverprogrammering.dataaccess.MovieNotFoundException;
import com.serverprogrammering.domain.Movie;
import com.serverprogrammering.service.MovieServiceLocal;

@ManagedBean(name="findMoviePageBean")
public class FindMoviePageBean {
	
	@EJB
	private MovieServiceLocal service;
	
	private String movieId;
	
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	
	public Movie getSelectedMovie() {
		int id = Integer.parseInt(movieId);
		try {
			return service.getById(id);
		} catch (MovieNotFoundException e) {
			return null;
		}
		
	}

}
