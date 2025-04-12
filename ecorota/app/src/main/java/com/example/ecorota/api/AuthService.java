package com.example.ecorota.api;

import com.example.ecorota.models.ApiResponse;
import com.example.ecorota.models.LoginRequest;
import com.example.ecorota.models.RegisterRequest;
import com.example.ecorota.models.User;

/**
 * Serviço responsável pela autenticação com a API
 */
public class AuthService {

    private ApiClient apiClient;

    public AuthService() {
        this.apiClient = new ApiClient();
    }

    /**
     * Interface de callback para operações de autenticação
     */
    public interface AuthCallback<T> {
        void onSuccess(ApiResponse<T> response);
        void onFailure(String errorMessage);
    }

    /**
     * Realiza login do usuário na API
     * @param loginRequest dados de login
     * @param callback callback para resultado da operação
     */
    public void login(LoginRequest loginRequest, AuthCallback<User> callback) {
        // Quando integrar com o backend, aqui você irá fazer a chamada HTTP para a API
        // Por enquanto, vamos apenas simular a resposta
        
        // Simulação de uma resposta bem-sucedida
        User user = new User("1", "Usuário Teste", loginRequest.getEmail(), "123456789");
        user.setToken("jwt_token_exemplo");
        ApiResponse<User> response = new ApiResponse<>(true, "Login bem-sucedido", user);
        
        // Chama o callback de sucesso
        callback.onSuccess(response);
    }

    /**
     * Registra um novo usuário na API
     * @param registerRequest dados de registro
     * @param callback callback para resultado da operação
     */
    public void register(RegisterRequest registerRequest, AuthCallback<User> callback) {
        // Quando integrar com o backend, aqui você irá fazer a chamada HTTP para a API
        // Por enquanto, vamos apenas simular a resposta
        
        // Simulação de uma resposta bem-sucedida
        User user = new User("1", registerRequest.getName(), registerRequest.getEmail(), registerRequest.getPhone());
        ApiResponse<User> response = new ApiResponse<>(true, "Registro realizado com sucesso", user);
        
        // Chama o callback de sucesso
        callback.onSuccess(response);
    }

    /**
     * Efetua logout do usuário
     */
    public void logout() {
        // Limpar dados de autenticação quando integrado com o backend
    }
}