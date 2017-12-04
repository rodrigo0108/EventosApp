package com.rramos.eventosapp.services;

import com.rramos.eventosapp.models.Evento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RODRIGO on 27/11/2017.
 */

public interface ApiService {

    String API_BASE_URL="https://eventos-api-rodrigoramosq.c9users.io/";

    @GET("eventos")
    Call<List<Evento>> getEventos();
}
