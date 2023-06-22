package lucas.curso.jogoforca.banco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BancoDeDados extends SQLiteOpenHelper {

    public BancoDeDados(Context context) {
        super(context, "banco.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS palavras (id INTEGER PRIMARY KEY AUTOINCREMENT, palavra TEXT NOT NULL);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //inserir palavra no BD
    public void inserirPalavra(String palavra) {
        String query = "INSERT INTO palavras (palavra) VALUES ('" + palavra + "');";
        getWritableDatabase().execSQL(query);
    }

    //Listagem de palavras no BD
    public List<String> listPalavras() {
        String query = "SELECT * FROM palavras;";
        List<String> palavras = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            palavras.add(cursor.getString(1));
        }

        return palavras;
    }

}
