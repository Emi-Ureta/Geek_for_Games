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
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity_login extends AppCompatActivity implements View.OnClickListener {

    FirebaseFirestore firestore;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseAuth mAuth;

    private Button loginbtn;
    private EditText correo, contra;
    private TextView olvide_contra;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();

        loginbtn = (Button)  findViewById(R.id.Button_reset_contra);
        loginbtn.setOnClickListener(this);

        correo = (EditText) findViewById(R.id.Edit_text_olvide_contra_usuario);
        contra = (EditText) findViewById(R.id.Edit_text_registro_contra);

        mAuth = FirebaseAuth.getInstance();


        olvide_contra = (TextView) findViewById(R.id.Text_view_olvide_contra);
        olvide_contra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_login.this, Activity_olvide_contra.class));
            }
        });

        TextView btn=findViewById(R.id.Text_view_crear_cuenta_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_login.this, Activity_registrarse.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button_reset_contra:
                Login_usuario();
                break;

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


        mAuth.signInWithEmailAndPassword(Correo, Contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    if(task.isSuccessful()){
                        startActivity(new Intent(Activity_login.this, Activity_inicio.class));
                    }else{
                        Toast.makeText(Activity_login.this, "Error al ingresar, vuelve a intentarlo", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}