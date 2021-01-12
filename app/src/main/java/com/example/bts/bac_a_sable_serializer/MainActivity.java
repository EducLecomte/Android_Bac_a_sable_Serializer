package com.example.bts.bac_a_sable_serializer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //propriete
    Personne p;
    Button boutonSave;
    EditText edNom;
    EditText edPrenom;
    TextView tvEcho;

    String nomFichierSerialise = "personneSerialise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        boutonSave = findViewById(R.id.btn_save);
        edNom = findViewById(R.id.et_nom);
        edPrenom = findViewById(R.id.et_prenom);
        tvEcho = findViewById(R.id.tv_echo);


        ecoutebtn();

        checkSerialize();
    }

    private void ecoutebtn() {
        boutonSave.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                p =new Personne(edNom.getText().toString(), edPrenom.getText().toString());
                tvEcho.setText("Bonjour "+p.getNom()+" "+p.getPrenom()+".");

                Serializer.serialize(nomFichierSerialise, p, MainActivity.this);

                hideKeyboard(MainActivity.this);
            }
        });
    }


    private void recupSerialize(Context contexte){
        p = (Personne) Serializer.deserialize(nomFichierSerialise, contexte);
    }

    private void checkSerialize(){
        try{
            recupSerialize(this);
            ((TextView) findViewById(R.id.et_nom)).setText("" + p.getNom());
            ((TextView) findViewById(R.id.et_prenom)).setText("" + p.getPrenom());
           // findViewById(R.id.btn_save).performClick();
        }catch (Exception e){};
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}

