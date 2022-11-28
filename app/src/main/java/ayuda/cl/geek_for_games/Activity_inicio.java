package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Activity_inicio extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);


        Button btn_opinion = findViewById(R.id.Button_inicio_escribir);
        btn_opinion.setOnClickListener(view -> startActivity(new Intent(Activity_inicio.this, Activity_escribir_opinion.class)));

        Button btn_favs = findViewById(R.id.Button_favoritos);
        btn_favs.setOnClickListener(view -> startActivity(new Intent(Activity_inicio.this, Activity_lista_deseos.class)));


        Button btn_mapa = findViewById(R.id.Button_mapa);
        btn_mapa.setOnClickListener(view -> {
            startActivity(new Intent(Activity_inicio.this, MapaActivity.class));
            myRef.setValue("Ingreso de usuario al mapa desde ventana inicio");
        });

        TextView cuenta = findViewById(R.id.Text_view_cuenta);
        cuenta.setOnClickListener(view -> startActivity(new Intent(Activity_inicio.this, Activity_perfil_usuario.class)));

        TextView prueba = findViewById(R.id.textView);
        prueba.setOnClickListener(view -> startActivity(new Intent(Activity_inicio.this, MainActivity2.class)));



    }




}