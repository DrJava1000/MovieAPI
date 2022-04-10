package com.example.movieapi;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MovieDAO {
    //PersistenceContext annotation used to specify there is a database source.
    // EntityManager is used to create and remove persistent entity instances,
    // to find entities by their primary key, and to query over entities.

    @PersistenceContext
    private EntityManager entMan;

    public MovieDAO(){}


    public List <Movie> getAllMovies()  {
        List<Movie> movies = entMan.createQuery("Select m from Movie m", Movie.class).getResultList();
        return movies;
    }

    public Movie getById(int id) {
        return entMan.find(Movie.class, id);
    }

    public Movie create(Movie movie) {
        entMan.persist(movie);
        entMan.flush();
        return movie;
    }

    public Movie updateMovie(Movie movie) {
        return entMan.merge(movie);
    }

    public boolean deleteByID(int id){
        Movie movie = getById(id);
        if(movie == null) {
            return false;
        }else {
            entMan.remove(movie);
            return true;
        }
    }
}

