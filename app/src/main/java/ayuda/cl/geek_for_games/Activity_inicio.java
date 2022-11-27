package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class Activity_inicio extends AppCompatActivity {

    private Button logout;
    private TextView cuenta;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        TextView btn=findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_inicio.this, Activity_login.class));
            }
        });

        Button btn_opinion = findViewById(R.id.Button_inicio_escribir);
        btn_opinion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_inicio.this, Activity_escribir_opinion.class));
            }
        });

        Button btn_favs = findViewById(R.id.Button_favoritos);
        btn_favs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_inicio.this, Activity_lista_deseos.class));
            }
        });


        Button btn_mapa = findViewById(R.id.Button_mapa);
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_inicio.this, MapaActivity.class));
                myRef.setValue("Ingreso de usuario al mapa desde ventana inicio");
            }
        });

        cuenta = (TextView) findViewById(R.id.Text_view_cuenta);
        cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_inicio.this, Activity_perfil_usuario.class));
            }
        });




    }




}