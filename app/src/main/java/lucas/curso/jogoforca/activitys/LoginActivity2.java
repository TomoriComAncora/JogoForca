package lucas.curso.jogoforca.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import lucas.curso.jogoforca.R;
import lucas.curso.jogoforca.banco.BancoDeDados;
import lucas.curso.jogoforca.utils.musicaGeral;

public class LoginActivity2 extends AppCompatActivity {

    private static BancoDeDados bancoDeDados;
    private MediaPlayer mp;
    private Intent musicaFundoIntent;
    private Button bt1, bt2, bt3, btstop, btplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mp = MediaPlayer.create(this, R.raw.click);
        musicaFundoIntent = new Intent(this, musicaGeral.class);
        startService(musicaFundoIntent);
        bt1 = findViewById(R.id.bt_jogar);
        bt2 = findViewById(R.id.bt_sobre2);
        bt3 = findViewById(R.id.bt_addPalavra);
        btstop = findViewById(R.id.bt_stop);
        btplay = findViewById(R.id.bt_playmuica);
        bancoDeDados = new BancoDeDados(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent player = new Intent(LoginActivity2.this, PlayerActivity4.class);
                startActivity(player);
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent sobre = new Intent(LoginActivity2.this, SobreActivity3.class);
                startActivity(sobre);
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Intent add = new Intent(LoginActivity2.this, AddPalavraActivity5.class);
                startActivity(add);
            }
        });

        btstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                stopService(musicaFundoIntent);
            }
        });

        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                startService(musicaFundoIntent);
            }
        });
    }

    public static BancoDeDados getBancoDeDados() {
        return bancoDeDados;
    }

    protected void onDestroy(){
        super.onDestroy();
        stopService(musicaFundoIntent);
    }
}