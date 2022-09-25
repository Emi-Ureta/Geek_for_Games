package ayuda.cl.geek_for_games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Activity_lista_deseos extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_deseos);


        //elemento spinner
        Spinner spinner_lista_deseos = (Spinner) findViewById(R.id.spinner_deseos);

        //Listener
        spinner_lista_deseos.setOnItemSelectedListener(this);

        //Elementos para spinner
        List<String> plataforma_deseos = new ArrayList<String>();
        plataforma_deseos.add("PS5");
        plataforma_deseos.add("Xbox X");
        plataforma_deseos.add("PS4");
        plataforma_deseos.add("Xbox 360");
        plataforma_deseos.add("Nintendo Switch");
        plataforma_deseos.add("PC");

        //adaptador
        ArrayAdapter<String> adaptador_deseos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, plataforma_deseos);

        adaptador_deseos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_lista_deseos.setAdapter(adaptador_deseos);

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // On selecting a spinner item
        parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }
    }

