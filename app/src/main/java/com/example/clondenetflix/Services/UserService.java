package com.example.clondenetflix.Services;

import com.example.clondenetflix.Entidades.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("users")
    Call<List<Usuario>> getUsuarios();
}
