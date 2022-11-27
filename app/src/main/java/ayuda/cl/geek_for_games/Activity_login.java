package ayuda.cl.geek_for_games;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;

public class Activity_login extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;

    private EditText correo, contra;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginbtn = findViewById(R.id.Button_login);
        loginbtn.setOnClickListener(this);

        correo = findViewById(R.id.Edit_text_correo_login);
        contra = findViewById(R.id.Edit_text_login_contra);

        mAuth = FirebaseAuth.getInstance();


        TextView olvide_contra = findViewById(R.id.Text_view_olvide_contra);
        olvide_contra.setOnClickListener(view -> startActivity(new Intent(Activity_login.this, Activity_olvide_contra.class)));

        TextView btn=findViewById(R.id.Text_view_crear_cuenta_login);
        btn.setOnClickListener(view -> startActivity(new Intent(Activity_login.this, Activity_registrarse.class)));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Button_login) {
            Login_usuario();
        }

    }

    private void Login_usuario() {
        String Correo = correo.getText().toString().trim();
        String Contra = contra.getText().toString().trim();

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


        mAuth.signInWithEmailAndPassword(Correo, Contra).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                    startActivity(new Intent(Activity_login.this, Activity_inicio.class));
                }else{
                    Toast.makeText(Activity_login.this, "Error al ingresar, vuelve a intentarlo", Toast.LENGTH_LONG).show();
                }
            });
    }
}