package com.example.jorav.actividad3aad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btAdd, btShow;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String FILE_NAME = "fichero.txt";

        editText = (EditText) findViewById(R.id.editText);
        btAdd = (Button) findViewById(R.id.btAdd);
        btShow = (Button) findViewById(R.id.btShow);
        textView = (TextView) findViewById(R.id.textView);

        //Añadimos el texto del editText al fichero cuando pulsamos el boton de añadir
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream fileOutputStream = openFileOutput(FILE_NAME,MODE_APPEND);
                    String cadenaOutput = editText.getText().toString();
                    DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                    dataOutputStream.writeBytes(cadenaOutput+"\n");
                    fileOutputStream.close();
                    editText.setText("");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        //Leemos el fichero cuando pulsamos Mostrar
        btShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fileInputStream = openFileInput(FILE_NAME);
                    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                    byte[] buff = new byte[1000];
                    dataInputStream.read(buff);
                    textView.setText(new String(buff));
                    fileInputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
