package com.example.clondenetflix.Services;

import com.example.clondenetflix.Entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @GET("users")
    Call<List<Usuario>> getUsuarios();
    @POST("users")
    Call<Usuario> crearUsuario(@Body Usuario usuario);
    @GET("/users/{id}")
    Call<Usuario> getUsuarioPorId(@Path("id") int id);

}
