package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firestore = FirebaseFirestore.getInstance();
    }
}