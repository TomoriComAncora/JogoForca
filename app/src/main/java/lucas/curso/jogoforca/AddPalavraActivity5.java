package lucas.curso.jogoforca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddPalavraActivity5 extends AppCompatActivity {


    EditText editTextPalavra;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_palavra5);

        editTextPalavra = findViewById(R.id.editTextText);
        buttonSalvar = findViewById(R.id.bt_adicionar);

        buttonSalvar.setOnClickListener(v -> {
            String palavra = editTextPalavra.getText().toString();
            if (palavra.isEmpty()) {
                Toast.makeText(this, "Digite uma palavra", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Palavra salva", Toast.LENGTH_SHORT).show();
                LoginActivity2.getBancoDeDados().inserirPalavra(palavra);
                finish();
            }
        });
    }
}