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
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Activity_escribir_opinion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_opinion);

        //elemento spinner
        Spinner spinner_op = findViewById(R.id.spinner_opinion);

        //Listener
        spinner_op.setOnItemSelectedListener( this);

        //Elementos para spinner
        List<String> plataforma = new ArrayList<>();
        plataforma.add("PS5");
        plataforma.add("Xbox X");
        plataforma.add("PS4");
        plataforma.add("Xbox 360");
        plataforma.add("Nintendo Switch");
        plataforma.add("PC");

        //adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plataforma);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_op.setAdapter(adaptador);


        Button btn_inicio = findViewById(R.id.Button_inicio_inicio);
        btn_inicio.setOnClickListener(view -> startActivity(new Intent(Activity_escribir_opinion.this, Activity_inicio.class)));

        Button btn_favs = findViewById(R.id.Button_favoritos);
        btn_favs.setOnClickListener(view -> startActivity(new Intent(Activity_escribir_opinion.this, Activity_lista_deseos.class)));


        Button btn_mapa = findViewById(R.id.Button_mapa);
        btn_mapa.setOnClickListener(view -> {
            startActivity(new Intent(Activity_escribir_opinion.this, MapaActivity.class));
            myRef.setValue("Ingreso de usuario al mapa desde ventana opinion");
        });

        Button publicar = findViewById(R.id.Button_opinion_publicar);
        publicar.setOnClickListener(view -> Toast.makeText(Activity_escribir_opinion.this, "Vamos a verificar su opinión ¡Gracias!", Toast.LENGTH_LONG).show());

        TextView cuenta = findViewById(R.id.Text_view_cuenta);
        cuenta.setOnClickListener(view -> startActivity(new Intent(Activity_escribir_opinion.this, Activity_perfil_usuario.class)));

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


}