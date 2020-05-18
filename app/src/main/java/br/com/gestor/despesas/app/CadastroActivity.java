package br.com.gestor.despesas.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class CadastroActivity extends AppCompatActivity {
    EditText etEmail, etSenha;

    private FirebaseAuth auth;
    private FirebaseUser usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etEmail = findViewById(R.id.etEmailCad);
        etSenha = findViewById(R.id.etSenhaCad);

        Button btnCadastra = findViewById(R.id.btnCadastro);

        auth = FirebaseAuth.getInstance();

        btnCadastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }
    private void cadastrar(){
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        if( !email.isEmpty() && !senha.isEmpty() ){
            auth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            usuario = auth.getCurrentUser();
                        }
                    });
        }
    }
}
