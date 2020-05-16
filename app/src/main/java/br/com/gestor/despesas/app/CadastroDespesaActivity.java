package br.com.gestor.despesas.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroDespesaActivity extends AppCompatActivity {

    private EditText etValor, etDataEmissao, etDataVencimento, etDescricao;
    private Button btnSalvar;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_despesa);

        etValor = findViewById(R.id.etValor);
        etDataEmissao = findViewById(R.id.etDaraEmissao);
        etDataVencimento = findViewById(R.id.etDataVencimento);
        etDescricao = findViewById(R.id.etDescricao);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    private void salvar(){
        try {
            SimpleDateFormat dataFormat = new SimpleDateFormat();
            BigDecimal valor = new BigDecimal(etValor.getText().toString());
            Date dataEmissao = dataFormat.parse(etDataEmissao.getText().toString());
            Date dataVencimento = dataFormat.parse(etDataVencimento.getText().toString());
            String descricao = etDescricao.getText().toString();

            if (valor != null && dataEmissao != null && dataVencimento != null){
                Despesa despesa = new Despesa();
                despesa.setValor(valor);
                despesa.setDataEmissao(dataEmissao);
                despesa.setDataVencimento(dataVencimento);
                despesa.setDescricao(descricao);

                database = FirebaseDatabase.getInstance();
                databaseReference = database.getReference();
                databaseReference.child("despesa").push().setValue(despesa);
                finish();
            }
        }catch (ParseException e){

        }

    }
}
