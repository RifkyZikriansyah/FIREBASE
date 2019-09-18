package com.example.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.ref.Reference;

public class MainActivity extends AppCompatActivity {

    EditText editTextName;
    Spinner spinnerGenres;
    Button buttonAddArtist;

    DatabaseReference databaseArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists = FirebaseDatabase.getInstance().getReference("artists");

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonAddArtist = (Button) findViewById(R.id.buttonAddArtist);
        spinnerGenres = (Spinner) findViewById(R.id.spinnerGenres);

        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addArtist();

            }
        });
    }

    private void addArtist(){
        String name = editTextName.getText().toString().trim();
        String genre = spinnerGenres.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){

            String id = databaseArtists.push().getKey();

            Artist artist = new Artist(id, name, genre);
            databaseArtists.child(id).setValue(artist);

            Toast.makeText(this,"Artis Telah Masuk", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(this, "Kamu harus memasukkan nama terlebih dahulu", Toast.LENGTH_LONG).show();
        }
    }
}
