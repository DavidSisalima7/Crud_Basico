package com.david.sisalima.sqlite_productos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.david.sisalima.sqlite_productos.model.Producto;

public class Login extends AppCompatActivity {
    private static final String ADMIN_KEY = "admin";
    private static final String ADMIN_PASSWORD = "admin";
    private EditText txtuser,txtpassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtuser=(EditText) findViewById(R.id.txtCorreoR);
        txtpassword=(EditText) findViewById(R.id.txtContraseñaR);
        btnLogin= findViewById(R.id.btnLogin);
        // Agrega un escuchador de clic al botón de inicio de sesión
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene la clave y la contraseña ingresadas por el usuario
                String key = txtuser.getText().toString();
                String password = txtpassword.getText().toString();

                if (key.equals(ADMIN_KEY) && password.equals(ADMIN_PASSWORD)) {
                    // Redirige
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    // Muestra un mensaje de error al usuario
                    Toast.makeText(Login.this, "Las credenciales son incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}