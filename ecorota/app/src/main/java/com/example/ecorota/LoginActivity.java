package com.example.ecorota;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.ecorota.api.AuthService;
import com.example.ecorota.models.LoginRequest;
import com.example.ecorota.models.ApiResponse;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout tilEmail;
    private TextInputLayout tilPassword;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private TextView tvForgotPassword;
    private AppCompatButton btnLogin;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicializar componentes da UI
        initUI();
        
        // Configurar listeners
        setupListeners();
        
        // Inicializar serviço de autenticação
        authService = new AuthService();
    }

    private void initUI() {
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        btnLogin = findViewById(R.id.btn_login);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    String email = etEmail.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    
                    // Aqui você irá integrar com o backend
                    performLogin(email, password);
                }
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implementar recuperação de senha
                // Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                // startActivity(intent);
                Toast.makeText(LoginActivity.this, "Funcionalidade em desenvolvimento", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        
        // Validação de email
        if (TextUtils.isEmpty(email)) {
            tilEmail.setError("E-mail é obrigatório");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.setError("E-mail inválido");
            isValid = false;
        } else {
            tilEmail.setError(null);
        }
        
        // Validação de senha
        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("Senha é obrigatória");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }
        
        return isValid;
    }

    private void performLogin(String email, String password) {
        // Será implementado quando integrar com o backend
        // Exemplo de chamada que será usada:
        /*
        LoginRequest loginRequest = new LoginRequest(email, password);
        authService.login(loginRequest, new AuthService.AuthCallback() {
            @Override
            public void onSuccess(ApiResponse response) {
                // Salvar token ou dados do usuário
                // Navegar para a tela principal
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(LoginActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        */
        
        // Por enquanto, vamos simular um login bem-sucedido
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}