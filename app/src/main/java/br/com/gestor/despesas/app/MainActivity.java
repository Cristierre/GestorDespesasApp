package br.com.gestor.despesas.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText etEmail, etSenha;
    Button btnLogin, btnCadastrar;

    private FirebaseAuth auth;
    private FirebaseUser usuario;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.etEmail);
        etSenha = findViewById(R.id.etSenha);

        btnLogin = findViewById(R.id.btnLogin);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                usuario = auth.getCurrentUser();
                if (usuario != null) {
                    Intent intent = new Intent(MainActivity.this, ListaDespesa.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //logar();
                Intent intent = new Intent(MainActivity.this, ListaDespesa.class);
                startActivity(intent);

            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });

    }
    private void logar () {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();

        if (!email.isEmpty() && !senha.isEmpty()) {
            auth.signInWithEmailAndPassword(email, senha).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Erro ao logar", Toast.LENGTH_LONG).show();
                                etSenha.setBackgroundColor(Color.argb(127, 255, 0, 0));
                                etEmail.setBackgroundColor(Color.argb(127, 255, 0, 0));
                            }
                        }
                    });
        }
    }
}
