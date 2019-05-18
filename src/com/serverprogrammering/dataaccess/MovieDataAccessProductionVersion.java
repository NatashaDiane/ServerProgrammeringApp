package com.serverprogrammering.dataaccess;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.serverprogrammering.domain.Movie;

@Stateless

public class MovieDataAccessProductionVersion implements MovieDataAccess {

	@PersistenceContext // entity manager annotation,, tells wildfly once the ejb has been deployed,
						// wildfly must create a fresh e manager evrytime a method in this class is
						// called
	private EntityManager em;

	@Override
	public void insert(Movie newMovie) {
		em.persist(newMovie);
	}

	@Override
	public List<Movie> findAll() {

		Query q = em.createQuery("select movie from Movie movie");
		List<Movie> movies = q.getResultList(); // returns list of movie objects
		return movies;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		Query q = em.createQuery("select movie from Movie movie where movie.title = :title");
		q.setParameter("title", title);
		return q.getResultList(); // returns list of movies
	}

	@Override
	public Movie findById(int id) throws MovieNotFoundException {
		Query q = em.createQuery("select movie from Movie movie where movie.id = :id");
		q.setParameter("id", id);
		try {
			return (Movie) q.getSingleResult();
		} catch (NoResultException e) {
			throw new MovieNotFoundException();
		}

	}
	
	@Override
	public void updateMovie(int id, String title, String director, String stars, String runningTime, String genre, int year, String description) throws MovieNotFoundException {
		Movie m = findById(id);
		m.setTitle(title);
		m.setDirector(director);
		m.setStars(stars);
		m.setRunningTime(runningTime);
		m.setGenre(genre);
		m.setYear(year);
		m.setDescription(description);

	}
	
	@Override
	public void deleteMovie(int id) throws MovieNotFoundException {
		Movie m = findById(id);
		em.remove(m);
	}

	@Override
	public int countVisitor(int value) {
		value = value + 1;
		return value;
	}

}
