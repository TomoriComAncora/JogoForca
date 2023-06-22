package lucas.curso.jogoforca.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lucas.curso.jogoforca.R;

public class AddPalavraActivity5 extends AppCompatActivity {


    EditText editTextPalavra;
    Button buttonSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_palavra5);

        editTextPalavra = findViewById(R.id.editTextText);
        buttonSalvar = findViewById(R.id.bt_adicionar);

        //botão para adicionar palavra no BD
        buttonSalvar.setOnClickListener(v -> {
            String palavra = editTextPalavra.getText().toString();
            if (palavra.isEmpty()) {
                Toast.makeText(this, "Digite uma palavra", Toast.LENGTH_SHORT).show();
            } else if(LoginActivity2.getBancoDeDados().inserirPalavra(palavra)) {
                Toast.makeText(this, "Palavra salva", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Essa palavra já existe", Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}