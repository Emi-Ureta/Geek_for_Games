package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Activity_escribir_opinion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escribir_opinion);

        //elemento spinner
        Spinner spinner_op = (Spinner) findViewById(R.id.spinner_opinion);

        //Listener
        spinner_op.setOnItemSelectedListener( this);

        //Elementos para spinner
        List<String> plataforma = new ArrayList<String>();
        plataforma.add("PS5");
        plataforma.add("Xbox X");
        plataforma.add("PS4");
        plataforma.add("Xbox 360");
        plataforma.add("Nintendo Switch");
        plataforma.add("PC");

        //adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plataforma);

        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_op.setAdapter(adaptador);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
}