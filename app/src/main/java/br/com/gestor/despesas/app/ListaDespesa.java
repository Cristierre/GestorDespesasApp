package br.com.gestor.despesas.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListaDespesa extends AppCompatActivity {
    private ListView lvDespesa;
    private List<Despesa> listaDespesas;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private ChildEventListener childEventListener;
    private Query query;
    private ArrayAdapter<Despesa> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_despesa);

        FloatingActionButton fab = findViewById(R.id.fbLista);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaDespesa.this, CadastroDespesaActivity.class);
                startActivity(intent);
            }
        });

        lvDespesa = findViewById(R.id.lvDespesa);
        listaDespesas = new ArrayList<>();
        adapter = new ArrayAdapter<>(ListaDespesa.this, android.R.layout.simple_list_item_1, listaDespesas);
        lvDespesa.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        listaDespesas.clear();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("despesa").orderByChild("dataVencimento");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Despesa despesa = new Despesa();

                despesa.setId(dataSnapshot.getKey());
                despesa.setValor(dataSnapshot.child("valor").getValue(Double.class));
                despesa.setDataVencimento(dataSnapshot.child("dataVencimento").getValue(String.class));
                despesa.setDataEmissao(dataSnapshot.child("dataEmissao").getValue(String.class));
                despesa.setDescricao(dataSnapshot.child("descricao").getValue(String.class));

                listaDespesas.add(despesa);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        query.addChildEventListener(childEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        query.removeEventListener(childEventListener);
    }
}
