package ayuda.cl.geek_for_games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Activity_perfil_usuario extends AppCompatActivity {

    private FirebaseUser usuario;
    private DatabaseReference reference;

    private String userID;

    private Button logout, cambiar_contra;
    private TextView inicio;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        logout = (Button) findViewById(R.id.Button_salir);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Activity_perfil_usuario.this, Activity_login.class));
            }
        });

        inicio = (TextView) findViewById(R.id.Text_view_titulo_cuenta);
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_perfil_usuario.this, Activity_inicio.class));
            }
        });

        cambiar_contra = (Button) findViewById(R.id.Button_cambiar_contra);
        cambiar_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_perfil_usuario.this, Activity_olvide_contra.class));
            }
        });


        usuario = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Usuarios");
        userID = usuario.getUid();

        final TextView Nombre_usuario = (TextView) findViewById(R.id.nombre);
        final TextView Correo_usuario = (TextView) findViewById(R.id.correo);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario perfil_usuario = snapshot.getValue(Usuario.class);

                if(perfil_usuario != null){
                    String Nombre = perfil_usuario.nombre;
                    String Correo = perfil_usuario.correo;

                    Nombre_usuario.setText(Nombre);
                    Correo_usuario.setText(Correo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Activity_perfil_usuario.this, "Se ha producido un error", Toast.LENGTH_LONG).show();
            }
        });



    }
}