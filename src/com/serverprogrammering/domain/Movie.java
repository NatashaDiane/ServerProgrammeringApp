package com.serverprogrammering.domain;




import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Movie implements java.io.Serializable{
 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private static final long serialVersionUID = 1l;
	private String title;
	private String director;
	private String stars;
	private String runningTime;
	private String genre;
	private int year;
	private String description;

	

	
	public Movie() {
		//empty constructor required by JPA
	}
	
	public Movie(String title, String director, String stars, String runningTime, String genre, int year, String description) {
		super();
		this.title = title;
		this.director = director;
		this.stars = stars;
		this.runningTime = runningTime;
		this.genre = genre;		
		this.year = year;
		this.description = description;
	
	}
	
	
	public String toString() {
		return this.title + " : " + this.year + " : " + this.genre;
	}
	
	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
