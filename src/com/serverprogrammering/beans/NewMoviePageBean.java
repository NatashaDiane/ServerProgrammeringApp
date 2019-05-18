package com.serverprogrammering.beans;

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;

import com.serverprogrammering.domain.Movie;
import com.serverprogrammering.service.MovieServiceLocal;
import com.serverprogrammering.service.ServiceUnavailableException;

@ManagedBean(name = "newMovie")
public class NewMoviePageBean {

	private String title;
	private String director;
	private String stars;
	private String runningTime;
	private String genre;
	private int year;
	private String description;


	@EJB
	private MovieServiceLocal service;

	public String createMovie() {
		Movie newMovie = new Movie(title, director, stars, runningTime, genre, year, description);
		try {
			service.addMovie(newMovie);
			return "movieDetail2.jsf?movieId=" + newMovie.getId() + "&faces-redirect=true";
		} catch (ServiceUnavailableException e) {
			return "movieNotCreated";
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getRunningTime() {
		return runningTime;
	}

	public void setRunningTime(String runningTime) {
		this.runningTime = runningTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	

}
