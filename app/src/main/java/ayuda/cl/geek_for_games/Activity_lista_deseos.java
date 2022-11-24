package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_lista_deseos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deseos);


        //elemento spinner
        Spinner spinner_lista_deseos = (Spinner) findViewById(R.id.spinner_deseos);

        //Listener
        spinner_lista_deseos.setOnItemSelectedListener(this);

        //Elementos para spinner
        List<String> plataforma_deseos = new ArrayList<String>();
        plataforma_deseos.add("PS5");
        plataforma_deseos.add("Xbox X");
        plataforma_deseos.add("PS4");
        plataforma_deseos.add("Xbox 360");
        plataforma_deseos.add("Nintendo Switch");
        plataforma_deseos.add("PC");

        //adaptador
        ArrayAdapter<String> adaptador_deseos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plataforma_deseos);

        adaptador_deseos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_lista_deseos.setAdapter(adaptador_deseos);


        Button btn_inicio = findViewById(R.id.Button_inicio_inicio);
        btn_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_lista_deseos.this, Activity_inicio.class));
            }
        });

        Button btn_opinion = findViewById(R.id.Button_inicio_escribir);
        btn_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_lista_deseos.this, Activity_escribir_opinion.class));
            }
        });

        Button btn_mapa = findViewById(R.id.Button_mapa);
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_lista_deseos.this, MapaActivity.class));
                myRef.setValue("Ingreso de usuario al mapa desde ventana favoritos");
            }
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
    }

