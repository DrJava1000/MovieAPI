package com.example.movieapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
    private String baseURL = "http://localhost:8080";
    private Scanner input;
    private RestTemplate restTemplate = new RestTemplate();
    private final static int LAST_OPTION = 6;

    public static void main(String[] args)
    {
        Client cl = new Client();
        cl.input = new Scanner(System.in);
        boolean again = true;

        System.out.println("\nWelcome to the Movie API Client");
        System.out.println("-------------------------------");
        System.out.println("-------------------------------");

        do
        {
            int choice = cl.getChoice();
            switch(choice) {
                case 1: {
                    cl.getAllMovies();
                    break;
                }
                case 2: {
                    cl.getMovieByID();
                    break;
                }
                case 3: {
                    cl.createOrUpdateMovie("create");
                    break;
                }
                case 4: {
                    cl.createOrUpdateMovie("update");
                    break;
                }
                case 5: {
                    cl.deleteMovieByID();
                    break;
                }
                default: {
                    again = false;
                }
            }
        }while(again);
        cl.input.close();
    }

    private int getChoice()
    {
        int choice = 0;
        do
        {
            try
            {
                System.out.println("1.  Get all movies");
                System.out.println("2.  Get a movie by ID");
                System.out.println("3.  Create a movie");
                System.out.println("4.  Update a selected movie");
                System.out.println("5.  Delete a selected movie");
                System.out.println(LAST_OPTION + ".  Exit");
                System.out.print("\nPlease enter your choice: ");
                choice = input.nextInt();

                if(choice < 1 || choice > LAST_OPTION)
                {
                    System.out.println("\nInvalid Option. Try again...\n");
                }
            }
            catch (InputMismatchException ime)
            {
                System.out.println("\nError: You must enter an integer between 1 and " + LAST_OPTION + "\n");
            }
            finally
            {
                input.nextLine();
            }
        } while (choice < 1 || choice > LAST_OPTION);
        return choice;
    }

    private String parseResponseMessage(String message) {
        System.out.println("");
        String errMsg = message.substring(0, message.indexOf(':') + 1);
        int start = message.lastIndexOf(':');
        start = message.lastIndexOf(':', message.lastIndexOf(',')) + 1;
        int end = message.lastIndexOf(',');
        message = message.substring(start, end);
        errMsg += " " + message;
        return errMsg;
    }

    public void getAllMovies()
    {
        ParameterizedTypeReference<ArrayList<Movie>> movieRef =
                new ParameterizedTypeReference<>() {};

        try
        {
            ResponseEntity<ArrayList<Movie>> response =
                    restTemplate.exchange(baseURL + "/movies/", HttpMethod.GET, null, movieRef);

            ArrayList<Movie> movies = response.getBody();
            System.out.println("\nAvailable Movies");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");

            for(Movie currentMovie : movies){
                System.out.println(currentMovie + "\n");
            }

        }catch(HttpClientErrorException restEx)
        {
            System.out.println(parseResponseMessage(restEx.getMessage()) + "\n");
        }
    }

    public void getMovieByID()
    {
        int movieID = 0;

        try {
            System.out.print("\nEnter desired movie ID for movie you want to retrieve: ");
            movieID = input.nextInt();
            System.out.println("\n");
        } catch (InputMismatchException ime) {
            System.out.println("\nError: You must enter an integer ID. Exiting... \n");
            input.next();
            return;
        }

        ParameterizedTypeReference<Movie> movieRef = new ParameterizedTypeReference<>() {};

        try
        {
            ResponseEntity<Movie> response =
                    restTemplate.exchange(baseURL + "/movies/" + movieID, HttpMethod.GET, null, movieRef);

            Movie selectedMovie = response.getBody();
            System.out.println("\nSelected Movie");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");

            System.out.println(selectedMovie + "\n");

        }catch(HttpClientErrorException restEx)
        {
            System.out.println(parseResponseMessage(restEx.getMessage()) + "\n");
        }
    }

    public void createOrUpdateMovie(String desiredAction)
    {
        // Create new movie and add movie attributes
        // using what user has entered
        Movie newMovie = new Movie();

        // Prompt for id
        int id = 0;
        if(desiredAction.equals("update"))
        {
            try
            {
                System.out.print("\nEnter ID of movie you would like to update: ");
                id = input.nextInt();
                input.nextLine();
                newMovie.setId(id);
            }catch(InputMismatchException ime)
            {
                System.out.println("\nError: Not a number. Exiting... \n");
                input.next();
                return;
            }
        }

        // Prompt for title
        String title = "";
        System.out.print("\nEnter title: ");
        title = input.nextLine();
        if(!title.equals(""))
        {
            newMovie.setTitle(title);
        }

        // Prompt for Genre
        String genre = "";
        System.out.print("\nEnter Genre: ");
        genre = input.nextLine();
        if(!genre.equals(""))
        {
            newMovie.setGenre(genre);
        }

        // Prompt for Rate
        double rate = 0.0;
        try
        {
            System.out.print("\nEnter Rate (a number): ");
            String rateUnchecked = input.nextLine();
            if(!rateUnchecked.equals(""))
            {
                newMovie.setRate(Double.parseDouble(rateUnchecked));
            }
        }catch(NumberFormatException ime)
        {
            System.out.println("\nError: Not a number. Exiting... \n");
            return;
        }

        // Prompt for Description
        String description = "";
        System.out.print("\nEnter Description: ");
        description = input.nextLine();
        if(!description.equals(""))
        {
            System.out.println("Description set");
            newMovie.setDescription(description);
        }

        // Prompt for RateNum
        double rateNum = 0;
        try
        {
            System.out.print("\nEnter RateNum: ");
            String rateNumUnchecked = input.nextLine();
            if(!rateNumUnchecked.equals(""))
            {
                newMovie.setRateNum((int) Math.round(Double.parseDouble(rateNumUnchecked)));
            }
        }catch(NumberFormatException ime)
        {
            System.out.println("\nError: Not a number. Exiting... \n");
            return;
        }

        RequestEntity<Movie> movieManipulationRequest = null;
        if(desiredAction.equals("create"))
        {
            movieManipulationRequest = RequestEntity.post(baseURL + "/movie").body(newMovie);
        }
        if(desiredAction.equals("update"))
        {
            movieManipulationRequest = RequestEntity.put(baseURL + "/movies").body(newMovie);
        }

        try
        {
            ResponseEntity<Movie> response =
                    restTemplate.exchange(movieManipulationRequest, Movie.class);

            Movie resultMovie = response.getBody();

            if(desiredAction.equals("create"))
            {
                System.out.println("\nCreated Movie");
            }
            if(desiredAction.equals("update"))
            {
                System.out.println("\nUpdated Movie");
            }

            System.out.println("-------------------------------");
            System.out.println("-------------------------------");
            System.out.println(resultMovie + "\n");

        }catch(HttpClientErrorException restEx)
        {
            System.out.println(parseResponseMessage(restEx.getMessage()) + "\n");
        }
    }

    public void deleteMovieByID() {
        int movieID = 0;

        try
        {
            System.out.print("\nEnter desired movie ID for movie you want to delete: ");
            movieID = input.nextInt();
            System.out.println("\n");
        } catch (InputMismatchException ime) {
            System.out.println("\nError: You must enter an integer ID. Exiting... \n");
            input.next();
            return;
        }

        ParameterizedTypeReference<String> stringRef = new ParameterizedTypeReference<>() {};
        try
        {
            ResponseEntity<String> response =
                    restTemplate.exchange(baseURL + "/movies/" + movieID, HttpMethod.DELETE, null, stringRef);

            String successfulDeletionMessage = response.getBody();

            System.out.println(successfulDeletionMessage + "\n");
            System.out.println("-------------------------------");
            System.out.println("-------------------------------");

        } catch (HttpClientErrorException restEx) {
            System.out.println(parseResponseMessage(restEx.getMessage()) + "\n");
        }
    }
}
