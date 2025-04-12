package com.example.ecorota;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.example.ecorota.api.AuthService;
import com.example.ecorota.models.RegisterRequest;
import com.example.ecorota.models.ApiResponse;
import com.example.ecorota.models.User;
import com.example.ecorota.utils.SessionManager;

public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout tilName;
    private TextInputLayout tilEmail;
    private TextInputLayout tilPhone;
    private TextInputLayout tilPassword;
    private TextInputLayout tilConfirmPassword;
    private TextInputEditText etName;
    private TextInputEditText etEmail;
    private TextInputEditText etPhone;
    private TextInputEditText etPassword;
    private TextInputEditText etConfirmPassword;
    private AppCompatButton btnRegister;
    private AuthService authService;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Inicializar componentes da UI
        initUI();
        
        // Configurar listeners
        setupListeners();
        
        // Inicializar serviço de autenticação
        authService = new AuthService();
        sessionManager = new SessionManager(this);
    }

    private void initUI() {
        tilName = findViewById(R.id.til_name);
        tilEmail = findViewById(R.id.til_email);
        tilPhone = findViewById(R.id.til_phone);
        tilPassword = findViewById(R.id.til_password);
        tilConfirmPassword = findViewById(R.id.til_confirm_password);
        
        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPhone = findViewById(R.id.et_phone);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        
        btnRegister = findViewById(R.id.btn_register);
    }

    private void setupListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    String name = etName.getText().toString().trim();
                    String email = etEmail.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    
                    // Realizar o registro na API
                    performRegister(name, email, phone, password);
                }
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;
        
        String name = etName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();
        
        // Validação do nome
        if (TextUtils.isEmpty(name)) {
            tilName.setError("Nome é obrigatório");
            isValid = false;
        } else {
            tilName.setError(null);
        }
        
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
        
        // Validação de telefone
        if (TextUtils.isEmpty(phone)) {
            tilPhone.setError("Telefone é obrigatório");
            isValid = false;
        } else {
            tilPhone.setError(null);
        }
        
        // Validação de senha
        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("Senha é obrigatória");
            isValid = false;
        } else if (password.length() < 6) {
            tilPassword.setError("A senha deve ter pelo menos 6 caracteres");
            isValid = false;
        } else {
            tilPassword.setError(null);
        }
        
        // Validação de confirmação de senha
        if (TextUtils.isEmpty(confirmPassword)) {
            tilConfirmPassword.setError("Confirmação de senha é obrigatória");
            isValid = false;
        } else if (!password.equals(confirmPassword)) {
            tilConfirmPassword.setError("As senhas não coincidem");
            isValid = false;
        } else {
            tilConfirmPassword.setError(null);
        }
        
        return isValid;
    }

    private void performRegister(String name, String email, String phone, String password) {
        // Criar objeto de requisição de registro
        RegisterRequest registerRequest = new RegisterRequest(name, email, phone, password);
        
        // Chamar a API para realizar o registro
        authService.register(registerRequest, new AuthService.AuthCallback<User>() {
            @Override
            public void onSuccess(ApiResponse<User> response) {
                User user = response.getData();
                
                Toast.makeText(RegisterActivity.this, "Registro bem-sucedido!", Toast.LENGTH_SHORT).show();
                
                // Redirecionar para a tela de login após o registro bem-sucedido
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}