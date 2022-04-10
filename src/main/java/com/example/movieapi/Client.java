package com.example.movieapi;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
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
                    System.out.println("\ncreateMovie() hasn't been implemented yet.\n");
                    break;
                }
                case 4: {
                    System.out.println("\nupdateMovie() hasn't been implemented yet.\n");
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