package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity_login extends AppCompatActivity {

    FirebaseFirestore firestore;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firestore = FirebaseFirestore.getInstance();

        TextView usuario = (TextView) findViewById(R.id.Edit_text_login_usuario);
        TextView contra = (TextView) findViewById(R.id.Edit_text_login_contra);

        MaterialButton loginbtn = (MaterialButton)  findViewById(R.id.Button_login_entrar);



        TextView btn=findViewById(R.id.Text_view_crear_cuenta_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity_login.this, Activity_registrarse.class));
            }
        });
    }
}