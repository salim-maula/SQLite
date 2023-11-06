package com.example.databasesqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Edit extends Activity {

    protected Cursor cursor;
    sql dbHelper;
    Button btnEditSimpan;
    EditText edEditJudulBuku;
    EditText edEditNamaPengarang;
    EditText edEditTahunTerbit;
    EditText edEditPenerbit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_edit);
        dbHelper = new sql(this);
        edEditJudulBuku = (EditText) findViewById(R.id.edEditJudulBuku); edEditNamaPengarang = (EditText)
                findViewById(R.id.edEditNamaPengarang);
        edEditTahunTerbit = (EditText) findViewById(R.id.edEditTahunTerbit); edEditPenerbit = (EditText) findViewById(R.id.edEditPenerbit);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM buku WHERE judul_buku = '" +
                getIntent().getStringExtra("judul_buku") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0); edEditJudulBuku.setText(cursor.getString(1).toString()); edEditNamaPengarang.setText(cursor.getString(2).toString()); edEditTahunTerbit.setText(cursor.getString(3).toString()); edEditPenerbit.setText(cursor.getString(4).toString());
        }
        btnEditSimpan = (Button) findViewById(R.id.btnEditSimpan);
        btnEditSimpan.setOnClickListener(new Button.OnClickListener(){ @Override
        public void onClick(View arg0) {
// TODO Auto-generated method stub
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("update buku SET judul_buku='" +
                    edEditJudulBuku.getText().toString()+"', " +"nama_pengarang='"+
                    edEditNamaPengarang.getText().toString() +"'," +"tahun_terbit='"+
                    edEditTahunTerbit.getText().toString() +"'," +"penerbit='" +
                    edEditPenerbit.getText().toString() + "' WHERE " + " judul_buku = '" +
                    getIntent().getStringExtra("judul_buku") + "'");
            Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();

            MainActivity.ma.RefreshList(); finish();
        }

        }); }
}