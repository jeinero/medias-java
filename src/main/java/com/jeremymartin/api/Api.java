package com.jeremymartin.api;

import com.google.gson.Gson;
import com.jeremymartin.medias.Media;
import com.jeremymartin.medias.Movie;
import com.jeremymartin.medias.Serie;
import com.jeremymartin.ui.Displayer;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Api {

    private final static String url = "https://api.themoviedb.org/3/search/multi?api_key=9c08262321f4e984577c6abaa528b6b7&query=";

    OkHttpClient client = new OkHttpClient();


    public List<Media> getApi(String nameSearch) throws IOException {
        Request request = new Request.Builder()
                .url(url + nameSearch)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            MediasInDto medias = gson.fromJson(response.body().string(), MediasInDto.class);
            return medias.toProduct();
        }catch (Exception exception){
            new Displayer().writeError("error: No such link find");
            return Collections.emptyList();
        }
    }

    public Movie getApiMovie(String url) throws  IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            Movie movie = gson.fromJson(response.body().string(), Movie.class);
            return movie;
        }catch (Exception exception){
            new Displayer().writeError("ERROR: No such link find, empty movie was returned");
            return new Movie(0, null,null,null,null);
        }
    }
    public Serie getApiSerie(String url) throws  IOException{
        Request request = new Request.Builder()
                .url(url)
                .build();
        try (Response response = client.newCall(request).execute()) {
            Gson gson = new Gson();
            Serie serie = gson.fromJson(response.body().string(), Serie.class);
            return serie;
        }catch (Exception exception){
            new Displayer().writeError("ERROR: No such link find, empty movie was returned");
            return new Serie(null, null,null,null,null);    }
        }


}