package com.example.t3_fn.Services;

import com.example.t3_fn.Clases.ImageResponse;
import com.example.t3_fn.Clases.ImageToSave;
import com.example.t3_fn.Clases.T4;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface T4Service {
    //todo esto depende del API( GET SET DELETE )
    @GET("Pokemones")
    Call<List<T4>> getAllUser();
    @POST("Pokemones")
    Call<Void> create(@Body T4 pokeApi);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body Contactos contactApi, @Path("id")int id);*/
    @PUT("Pokemones/{id}")
    Call<T4> update(@Body T4 pokeApi, @Path("id")int id);
    @DELETE("Pokemones/{id}")
    Call<T4> delete (@Path("id")int id);
    @GET("Pokemones/{id}")
    Call<T4> findUser(@Path("id") int id);
    @POST("image")
    Call<ImageResponse> subirImagen(@Body ImageToSave imagen);
}
