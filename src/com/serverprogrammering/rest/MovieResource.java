package com.serverprogrammering.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
//jax-rs library..
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.serverprogrammering.dataaccess.MovieNotFoundException;
import com.serverprogrammering.domain.Movie;
import com.serverprogrammering.service.MovieServiceLocal;
import com.serverprogrammering.service.ServiceUnavailableException;

//Controller of rest webservice
@Stateless
@Path("/movies")
public class MovieResource {

	@Inject
	private MovieServiceLocal service;

	@Context
	private UriInfo uriInfo;

	@GET
	@Produces({ "application/JSON", "application/XML" })
	public List<Movie> getAllMovies() {
		return service.getAllMovies();
	}

	@GET
	@Produces({ "application/JSON", "application/XML" })
	@Path("{movieNumber}")
	public Response findMovieById(@PathParam("movieNumber") int id, @Context HttpHeaders headers) {
		System.out.println("HEADERS : " + headers.getRequestHeaders());
		try {
			Movie result = service.getById(id);
			Link selfLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("self").type("get").build();
			Link updateLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("update").type("put").build();
			Link deleteLink = Link.fromUri(uriInfo.getAbsolutePath()).rel("delete").type("delete").build();

			return Response.ok(result).links(selfLink, updateLink, deleteLink).build();
		} catch (MovieNotFoundException e) {
			return Response.status(404).build();
		}
	}
	
	@POST
	@Produces({ "application/JSON", "application/XML" })
	@Consumes({ "application/JSON", "application/XML" })
	public Response createMovie(Movie movie) {
		try {
			service.addMovie(movie);

			URI uri = null;

			try {
				uri = new URI(uriInfo.getAbsolutePath() + "/" + movie.getId());
			} catch (Exception e) {
			}

			return Response.created(uri).build();
		} catch (ServiceUnavailableException e) {
			return Response.status(504).build();
		}
	}

	@DELETE
	@Path("{movieNumber}")
	public Response deleteMovie(@PathParam("movieNumber") int id) {
		try {
			service.deleteMovie(id);
			return Response.status(204).build();
		} catch (MovieNotFoundException e) {
			return Response.status(404).build();
		}
	}

	@PUT
	@Path("{movieNumber}")
	@Produces({ "application/JSON", "application/XML" })
	@Consumes({ "application/JSON", "application/XML" })
	public Response updateMovie(@PathParam("movieNumber") int id, Movie m) {
		try {
			service.updateMovie(id, m.getTitle(), m.getDirector(), m.getStars(), m.getRunningTime(), m.getGenre(), m.getYear(), m.getDescription());
			return Response.ok(service.getById(id)).build();
		} catch (MovieNotFoundException e) {
			return Response.status(404).build();
		}
	}
}
