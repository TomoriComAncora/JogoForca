package lucas.curso.jogoforca.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class BancoDeDados extends SQLiteOpenHelper {

    public BancoDeDados(Context context) {
        super(context, "banco.db", null, 1);
        inserirPalavrasPreDefinidas();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS palavras (id INTEGER PRIMARY KEY AUTOINCREMENT, palavra TEXT NOT NULL);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean inserirPalavra(String palavra) {
        SQLiteDatabase db = getWritableDatabase();

        // Verificar se a palavra já existe no banco de dados
        String query = "SELECT * FROM palavras WHERE palavra = ?";
        Cursor cursor = db.rawQuery(query, new String[]{palavra});
        if (cursor.getCount() > 0) {
            // A palavra já existe, não é necessário inserir novamente
            cursor.close();
            return false;
        }

        // A palavra não existe, realizar a inserção
        ContentValues values = new ContentValues();
        values.put("palavra", palavra);
        db.insert("palavras", null, values);

        cursor.close();
        return true;
    }

    private void inserirPalavrasPreDefinidas() {
        String[] palavrasPreDefinidas = {"kotlin", "naruto", "sasuke", "sakura", "kakashi",
                "android", "java", "zelda", "mobile", "celular"};

        for (String palavra : palavrasPreDefinidas) {
            inserirPalavra(palavra);
        }
    }

    public List<String> listPalavras() {
        String query = "SELECT * FROM palavras;";
        List<String> palavras = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            palavras.add(cursor.getString(1));
        }

        cursor.close();
        return palavras;
    }
}