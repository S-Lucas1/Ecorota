package com.example.ecorota.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ecorota.models.User;
import com.google.gson.Gson;

/**
 * Classe responsável por gerenciar os dados da sessão do usuário
 */
public class SessionManager {
    
    private static final String PREF_NAME = "EcoRotaSession";
    private static final String KEY_USER = "user";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    
    /**
     * Salva os dados do usuário na sessão
     * @param user usuário logado
     * @param token token de autenticação
     */
    public void createLoginSession(User user, String token) {
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        
        // Salva o objeto User como JSON
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        editor.putString(KEY_USER, userJson);
        
        // Salva o token de autenticação
        editor.putString(KEY_TOKEN, token);
        
        // Confirma as alterações
        editor.commit();
    }
    
    /**
     * Verifica se o usuário está logado
     * @return true se estiver logado, false caso contrário
     */
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false);
    }
    
    /**
     * Obtém o usuário logado
     * @return objeto User do usuário logado ou null se não houver
     */
    public User getUser() {
        String userJson = sharedPreferences.getString(KEY_USER, null);
        if (userJson != null) {
            Gson gson = new Gson();
            return gson.fromJson(userJson, User.class);
        }
        return null;
    }
    
    /**
     * Obtém o token de autenticação
     * @return token de autenticação ou null se não houver
     */
    public String getToken() {
        return sharedPreferences.getString(KEY_TOKEN, null);
    }
    
    /**
     * Limpa os dados da sessão (logout)
     */
    public void logout() {
        editor.clear();
        editor.commit();
    }
}