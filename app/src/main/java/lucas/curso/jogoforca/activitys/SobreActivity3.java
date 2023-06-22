package lucas.curso.jogoforca.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import lucas.curso.jogoforca.R;

public class SobreActivity3 extends AppCompatActivity {
    private List<String> palavrasBD;
    private TextView palavras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre3);

        palavras = findViewById(R.id.text_palavras_bd);

        carregarPalavras();

        this.palavrasBD = LoginActivity2.getBancoDeDados().listPalavras();
        palavras.setText(palavrasBD.toString());

    }

    public void carregarPalavras() {
        palavrasBD = LoginActivity2.getBancoDeDados().listPalavras();
    }
}