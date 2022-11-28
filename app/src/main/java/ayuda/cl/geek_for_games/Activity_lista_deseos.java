package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_lista_deseos extends AppCompatActivity{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deseos);


        Button btn_inicio = findViewById(R.id.Button_inicio_inicio);
        btn_inicio.setOnClickListener(view -> startActivity(new Intent(Activity_lista_deseos.this, Activity_inicio.class)));

        Button btn_opinion = findViewById(R.id.Button_inicio_escribir);
        btn_opinion.setOnClickListener(view -> startActivity(new Intent(Activity_lista_deseos.this, Activity_escribir_opinion.class)));

        Button btn_mapa = findViewById(R.id.Button_mapa);
        btn_mapa.setOnClickListener(view -> {
            startActivity(new Intent(Activity_lista_deseos.this, MapaActivity.class));
            myRef.setValue("Ingreso de usuario al mapa desde ventana favoritos");
        });

        TextView cuenta = findViewById(R.id.Text_view_cuenta);
        cuenta.setOnClickListener(view -> startActivity(new Intent(Activity_lista_deseos.this, Activity_perfil_usuario.class)));


    }

    }

