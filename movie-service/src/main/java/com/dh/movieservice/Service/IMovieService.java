package com.dh.movieservice.Service;

import com.dh.movieservice.Model.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getListByGenre(String genre);
    void save(Movie movie);
}
