package ayuda.cl.geek_for_games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_registrarse extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText nombre, correo, contra, contra_verificar;
    private Button registrar;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registrar = (Button) findViewById(R.id.Button_iniciar_sesion);
        registrar.setOnClickListener(this);

        nombre = (EditText) findViewById(R.id.Edit_text_registro_usuario);
        correo = (EditText) findViewById(R.id.edit_text_registro_correo);
        contra = (EditText) findViewById(R.id.Edit_text_registro_contra);
        contra_verificar = (EditText) findViewById(R.id.Edit_text_registro_contra_verificar);


        //cambio activity a login
        TextView btn=findViewById(R.id.Text_view_cuenta_registro);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_registrarse.this, Activity_login.class));
            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button_iniciar_sesion:
                registrar_usuario();
                break;

        }
    }

    private void registrar_usuario() {

        String Nombre = nombre.getText().toString().trim();
        String Correo = correo.getText().toString().trim();
        String Contra = contra.getText().toString().trim();
        String Contra_verificar = contra_verificar.getText().toString().trim();

        if (Nombre.isEmpty()){
            nombre.setError("Ingrese un nombre");
            nombre.requestFocus();
        }

        if (Correo.isEmpty()){
            correo.setError("Ingrese un correo");
            correo.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
            correo.setError("Ingrese un correo válido por favor");
            correo.requestFocus();
        }

        if (Contra.isEmpty()){
            contra.setError("Ingrese una contraseña");
            contra.requestFocus();
        }

        if (Contra_verificar.isEmpty()){
            contra_verificar.setError("Repita su contraseña por favor");
            contra_verificar.requestFocus();
        }

        if (Contra.length() < 6  && Contra_verificar.length() < 6){
            contra.setError("La contraseña debe contener al menos 6 carácteres");
        }


        mAuth.createUserWithEmailAndPassword(Correo, Contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Usuario usuario = new Usuario(Nombre, Correo, Contra);

                    FirebaseDatabase.getInstance().getReference("Usuarios").child(FirebaseAuth.getInstance().
                            getCurrentUser().getUid()).setValue(usuario).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){
                                 Toast.makeText(Activity_registrarse.this, "Usuario creado de manera exitosa", Toast.LENGTH_LONG).show();
                                 startActivity(new Intent(Activity_registrarse.this, Activity_inicio.class));
                             }else{
                                 Toast.makeText(Activity_registrarse.this, "", Toast.LENGTH_LONG).show();
                             }
                        }
                    });
                }else{
                    Toast.makeText(Activity_registrarse.this, "Se ha producido un error, vuelve a intentarlo", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}