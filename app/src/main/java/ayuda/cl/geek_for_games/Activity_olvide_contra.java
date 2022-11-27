package ayuda.cl.geek_for_games;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_olvide_contra extends AppCompatActivity {

    private EditText correo;
    private Button reset;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_contra);

        correo = (EditText) findViewById(R.id.Edit_text_olvide_contra_usuario);
        reset = (Button) findViewById(R.id.Button_reset_contra);

        auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset_contra();
            }
        });
    }

    private void reset_contra(){
        String Correo = correo.getText().toString().trim();

        if (Correo.isEmpty()){
            correo.setError("Ingrese un correo por favor");
            correo.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(Correo).matches()){
            correo.setError("Ingrese un correo válido por favor");
            correo.requestFocus();
        }

        auth.sendPasswordResetEmail(Correo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Activity_olvide_contra.this, "Verifica tu correo", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Activity_olvide_contra.this, "Ha ocurrido un error, vuelve intentarlo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}