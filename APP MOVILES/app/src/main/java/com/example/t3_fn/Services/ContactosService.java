package com.example.t3_fn.Services;

import com.example.t3_fn.Clases.Contactos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactosService {
    //todo esto depende del API( GET SET DELETE )
    @GET("ContactosTelefono")
    Call<List<Contactos>> getAllUser();
    @POST("ContactosTelefono")
    Call<Void> create(@Body Contactos contactApi);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body Contactos contactApi, @Path("id")int id);*/
    @PUT("ContactosTelefono/{id}")
    Call<Contactos> update(@Body Contactos contactApi, @Path("id")int id);
    @DELETE("ContactosTelefono/{id}")
    Call<Contactos> delete (@Path("id")int id);
    @GET("ContactosTelefono/{id}")
    Call<Contactos> findUser(@Path("id") int id);
}
