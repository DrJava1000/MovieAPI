package com.example.movieapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class MovieController
{
    @Autowired
    private MovieDAO movieDAO = new MovieDAO();

    // GET ALL MOVIES ENDPOINT
    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    public List<Movie> getMovies()
    {
        List<Movie> resultMovies = movieDAO.getAllMovies();
        if(resultMovies.size() == 0)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found.");
        else
            return resultMovies;
    }

    // GET SPECIFIC MOVIE ENDPOINT
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.GET)
    public Movie getMovie(@PathVariable int id)
    {
        Movie resultMovie = movieDAO.getById(id);
        if(resultMovie == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified ID " + id + " does not have associated movie.");
        else
            return resultMovie;
    }

    // CREATE MOVIE ENDPOINT
    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    public Movie createMovie(@RequestBody Movie movie) {
        return movieDAO.create(movie);
    }

    // EDIT MOVIE ENDPOINT
    @RequestMapping(value = "/movies", method = RequestMethod.PUT)
    public Movie updateMovie(@RequestBody Movie movie) {
        return movieDAO.updateMovie(movie);
    }

    // DELETE MOVIE ENDPOINT
    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    public String deleteMovie(@PathVariable int id) {
        if(!movieDAO.deleteByID(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Specified ID " + id + " does not have associated movie.");
        }else{
            return "ID " + id + " deleted.";
        }
    }
}
