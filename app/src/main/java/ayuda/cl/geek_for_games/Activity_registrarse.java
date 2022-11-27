package ayuda.cl.geek_for_games;


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


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Activity_registrarse extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;


    private EditText nombre, correo, contra, contra_verificar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        Button registrar = findViewById(R.id.Button_login);
        registrar.setOnClickListener(this);

        nombre = findViewById(R.id.Edit_text_registro_usuario);
        correo = findViewById(R.id.edit_text_registro_correo);
        contra = findViewById(R.id.Edit_text_login_contra);
        contra_verificar = findViewById(R.id.Edit_text_registro_contra_verificar);




        //cambio activity a login
        TextView btn=findViewById(R.id.Text_view_cuenta_registro);
        btn.setOnClickListener(view -> startActivity(new Intent(Activity_registrarse.this, Activity_login.class)));

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Button_login) {
            registrar_usuario();
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


        mAuth.createUserWithEmailAndPassword(Correo, Contra).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                Usuario usuario = new Usuario(Nombre, Correo, Contra);

                FirebaseDatabase.getInstance().getReference("Usuarios").child(FirebaseAuth.getInstance().
                        getCurrentUser().getUid()).setValue(usuario).addOnCompleteListener(task1 -> {
                             if(task1.isSuccessful()){
                                 Toast.makeText(Activity_registrarse.this, "Usuario creado de manera exitosa", Toast.LENGTH_LONG).show();
                                 startActivity(new Intent(Activity_registrarse.this, Activity_login.class));
                             }else{
                                 Toast.makeText(Activity_registrarse.this, "", Toast.LENGTH_LONG).show();
                             }
                        });
            }else{
                Toast.makeText(Activity_registrarse.this, "Se ha producido un error, vuelve a intentarlo", Toast.LENGTH_LONG).show();
            }
        });

    }
}