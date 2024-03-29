package com.example.clienteproviderboagam.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.clienteproviderboagam.R;

import com.example.clienteproviderboagam.provider.MiProveedorContenidoContract;

public class SeleccionarUno extends AppCompatActivity {
    Cursor cursor;
    Button btnAceptar;

    String idUs;
    TextView txtNombre;
    TextView txtPas;
    TextView txtEmail;
    TextView txtTel;
    private static final String TAG="Prueba";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_uno);
        Intent intent= getIntent();
        idUs=intent.getStringExtra("id");
        txtNombre=findViewById(R.id.txtNombre);
        txtPas=findViewById(R.id.txtPassword);
        txtEmail=findViewById(R.id.txtEmail);
        txtTel=findViewById(R.id.txtTel);
        btnAceptar=findViewById(R.id.btnGuardar);
        consultar();
        txtNombre.setText(cursor.getString(cursor.getColumnIndex("nombre")));
        txtEmail.setText(cursor.getString(cursor.getColumnIndex("email")));
        txtPas.setText(cursor.getString(cursor.getColumnIndex("password")));
        txtTel.setText(cursor.getString(cursor.getColumnIndex("tel")));
        ContentResolver cr=getApplicationContext().getContentResolver();
        ContentValues cv=new ContentValues();
        btnAceptar.setOnClickListener(l->{
            finish();
        });
    }
    public void consultar(){
        Uri uri=Uri.parse(MiProveedorContenidoContract.Usuarios.CONTENT_URI.toString()+"/"+idUs);
        cursor = getBaseContext().getContentResolver().query(uri,null,null,null,null);
        cursor.moveToFirst();
        Log.d(TAG,cursor.getString(cursor.getColumnIndex("_id")));
    }
}