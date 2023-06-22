package lucas.curso.jogoforca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lucas.curso.jogoforca.banco.BancoDeDados;

public class LoginActivity2 extends AppCompatActivity {

    private static BancoDeDados bancoDeDados;
    private Button bt1, bt2, bt3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        bt1 = findViewById(R.id.bt_jogar);
        bt2 = findViewById(R.id.bt_sobre2);
        bt3 = findViewById(R.id.bt_addPalavra);
        bancoDeDados = new BancoDeDados(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent player = new Intent(LoginActivity2.this, PlayerActivity4.class);
                startActivity(player);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sobre = new Intent(LoginActivity2.this, SobreActivity3.class);
                startActivity(sobre);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(LoginActivity2.this, AddPalavraActivity5.class);
                startActivity(add);
            }
        });
    }

    public static BancoDeDados getBancoDeDados() {
        return bancoDeDados;
    }
    //versao6
}