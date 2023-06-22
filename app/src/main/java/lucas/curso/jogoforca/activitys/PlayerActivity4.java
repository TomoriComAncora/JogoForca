package lucas.curso.jogoforca.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import lucas.curso.jogoforca.R;

public class PlayerActivity4 extends AppCompatActivity {

    private MediaPlayer mp;
    private ImageView py1, py2, py3, py4;
    private Button btEnviar;

    private EditText editText;
    private int escolherIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player4);

        mp = MediaPlayer.create(this, R.raw.click);
        py1 = findViewById(R.id.imageNaruto);
        py2 = findViewById(R.id.imageSasuke);
        py3 = findViewById(R.id.imageSakura);
        py4 = findViewById(R.id.imageKakashi);
        editText = findViewById(R.id.editNome);
        btEnviar = findViewById(R.id.btPlay);

        //botão para escolha do avatar
        py1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                escolherIMG = R.drawable.naruto_1;
            }
        });

        //botão para escolha do avatar
        py2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                escolherIMG = R.drawable.sasuke_1;
            }
        });

        //botão para escolha do avatar
        py3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                escolherIMG = R.drawable.sakura;
            }
        });

        //botão para escolha do avatar
        py4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                escolherIMG = R.drawable.kakashi;
            }
        });

        //botão para enviar o nick e a foto para a tela de jogo
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                String nome = editText.getText().toString();
                if(escolherIMG != 0 && !TextUtils.isEmpty(nome)){
                    Intent in = new Intent(PlayerActivity4.this, MainActivity.class);
                    in.putExtra("imagem", escolherIMG);
                    in.putExtra("texto", nome);
                    startActivity(in);
                }else {
                    Toast.makeText(getApplicationContext(), "Preencha o avatar e o nick", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //versao6
}