package com.serverprogrammering.dataaccess;

import java.util.List;


import javax.ejb.Local;

import com.serverprogrammering.domain.Movie;

@Local
public interface MovieDataAccess {

	public void insert (Movie newMovie);
	public List<Movie> findAll();
	public List<Movie> findByTitle(String title);
	public Movie findById(int id) throws MovieNotFoundException;
	public int countVisitor(int value);
	void updateMovie(int id, String title, String director, String stars, String runningTime, String genre, int year, String description) throws MovieNotFoundException;
	void deleteMovie(int id) throws MovieNotFoundException;
}
