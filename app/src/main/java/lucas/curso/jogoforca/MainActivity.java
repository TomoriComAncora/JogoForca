package lucas.curso.jogoforca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lucas.curso.jogoforca.banco.BancoDeDados;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> palavras;
    private String palavraSecreta;
    private StringBuilder palavraAdivinhada;
    private Thread cronometroThread;
    private boolean cronometrorodando;
    private long duracaoCronometro, duracaoInicial;
    private TextView textCronometro, textnome;
    private ImageView imagemForca, imageAvatar;
    private int tentativasRestantes;
    private ImageButton ltA, ltB, ltC, ltD, ltE, ltF, ltG, ltH, ltI, ltJ, ltK, ltL, ltM, ltN, ltO,
            ltP, ltQ, ltR, ltS, ltT, ltU, ltV, ltW, ltX, ltY, ltZ, confirmar;

    private TextView editLetra;
    private TextView textResult, textTentativas;

    private StringBuilder letrasTentadas;
    final static int TEMPO = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializando();
        verificarLetras();
        recuperarAvatar();
        carregarPalavras();
    }

    public void carregarPalavras() {
        palavras = LoginActivity2.getBancoDeDados().listPalavras();
    }

    public void verificarLetras(){
        editLetra.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0 && !letra(charSequence.charAt(0))) {
                    // Remover o texto não válido
                    editLetra.setText("");
                    Toast.makeText(MainActivity.this, "Digite apenas uma letra válida", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iniciar:
                inicalizarJogo();
                break;
            case R.id.recomecar:
                reiniciarJogo();
                break;
            case R.id.fechar:
                onDestroy();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean letra(char c) {
        return Character.isLetter(c);
    }

    @Override
    public void onClick(View view) {

        ImageButton button = (ImageButton) view;
        String letra = button.getTag().toString().toLowerCase();

        // Definir a letra capturada no EditText
        editLetra.setText(letra);
    }

    public void inicializando() {
        ltA = findViewById(R.id.letraa);
        ltB = findViewById(R.id.letrab);
        ltC = findViewById(R.id.letrac);
        ltD = findViewById(R.id.letrad);
        ltE = findViewById(R.id.letrae);
        ltF = findViewById(R.id.letraf);
        ltG = findViewById(R.id.letrag);
        ltH = findViewById(R.id.letrah);
        ltI = findViewById(R.id.letrai);
        ltJ = findViewById(R.id.letraj);
        ltK = findViewById(R.id.letrak);
        ltL = findViewById(R.id.letral);
        ltM = findViewById(R.id.letram);
        ltN = findViewById(R.id.letran);
        ltO = findViewById(R.id.letrao);
        ltP = findViewById(R.id.letrap);
        ltQ = findViewById(R.id.letraq);
        ltR = findViewById(R.id.letrar);
        ltS = findViewById(R.id.letras);
        ltT = findViewById(R.id.letrat);
        ltU = findViewById(R.id.letrau);
        ltV = findViewById(R.id.letrav);
        ltW = findViewById(R.id.letraw);
        ltX = findViewById(R.id.letrax);
        ltY = findViewById(R.id.letray);
        ltZ = findViewById(R.id.letraz);

        textCronometro = findViewById(R.id.textCronometro);
        imagemForca = findViewById(R.id.imgForca);
        confirmar = findViewById(R.id.checkBT);
        editLetra = findViewById(R.id.editLetra);
        textResult = findViewById(R.id.textResult);
        textTentativas = findViewById(R.id.letrasTentadas);
        imageAvatar = findViewById(R.id.imageAvatar);
        textnome = findViewById(R.id.textNome);

        letrasTentadas = new StringBuilder();
        clickLetras();

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = editLetra.getText().toString().toLowerCase();
                if (letra.length() == 1 && letra(letra.charAt(0))) {
                    verificarLetra(letra.charAt(0));
                    editLetra.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "digite apenas uma letra", Toast.LENGTH_LONG);
                }
            }
        });
        duracaoInicial = TEMPO;
        duracaoCronometro = duracaoInicial;
        inicalizarJogo();
        iniciarCronometro();
    }

    private void iniciarCronometro() {
        cronometrorodando = true;
        cronometroThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (cronometrorodando) {
                        Thread.sleep(1000);
                        duracaoCronometro--;


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textCronometro.setText(duracaoCronometro + "S");

                                if (duracaoCronometro <= 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                    builder.setTitle("Tempo esgotado").setIcon(R.drawable.ic_erro)
                                            .setMessage("Deseja reiniciar o jogo?")
                                            .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    reiniciarJogo();
                                                    dialogInterface.dismiss();
                                                }
                                            }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    onDestroy();
                                                }
                                            });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();
                                    pararCronometro();
                                }
                            }
                        });
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        cronometroThread.start();
    }

    private void pararCronometro() {
        cronometrorodando = false;
        cronometroThread.interrupt();
    }

    public void clickLetras() {
        ltA.setOnClickListener(this);
        ltB.setOnClickListener(this);
        ltC.setOnClickListener(this);
        ltD.setOnClickListener(this);
        ltE.setOnClickListener(this);
        ltF.setOnClickListener(this);
        ltG.setOnClickListener(this);
        ltH.setOnClickListener(this);
        ltI.setOnClickListener(this);
        ltJ.setOnClickListener(this);
        ltK.setOnClickListener(this);
        ltL.setOnClickListener(this);
        ltM.setOnClickListener(this);
        ltN.setOnClickListener(this);
        ltO.setOnClickListener(this);
        ltP.setOnClickListener(this);
        ltQ.setOnClickListener(this);
        ltR.setOnClickListener(this);
        ltS.setOnClickListener(this);
        ltT.setOnClickListener(this);
        ltU.setOnClickListener(this);
        ltV.setOnClickListener(this);
        ltW.setOnClickListener(this);
        ltX.setOnClickListener(this);
        ltY.setOnClickListener(this);
        ltZ.setOnClickListener(this);
    }

    private void inicalizarJogo() {
        Random random = new Random();
        this.palavras = LoginActivity2.getBancoDeDados().listPalavras();

        if (palavras.size() == 0) {
            Toast.makeText(MainActivity.this, "Não há palavras cadastradas", Toast.LENGTH_LONG).show();
            return;
        }

        Log.d("palavras", palavras.toString());
        int index = random.nextInt(palavras.size());
        palavraSecreta = palavras.get(index);
        Log.d("palavraSecreta", palavraSecreta);
        palavraAdivinhada = new StringBuilder();
        tentativasRestantes = 6;

        for (int i = 0; i < palavraSecreta.length(); i++) {
            palavraAdivinhada.append("_");
        }
        atualizarTela();
    }

    private void verificarLetra(char letra) {
        boolean encontrou = false;
        boolean letraDigitada = letrasTentadas.toString().contains(String.valueOf(letra));

        if(letraDigitada){
            Toast.makeText(MainActivity.this, "Letra já digitada", Toast.LENGTH_LONG).show();
            tentativasRestantes--;
            atualizarTela();
            return;
        }
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                encontrou = true;
                palavraAdivinhada.setCharAt(i, letra);
            }
        }

        if (!encontrou) {
            Toast.makeText(MainActivity.this, "Errou", Toast.LENGTH_LONG).show();
            letrasTentadas.append(letra).append(" ");
            textTentativas.setText(letrasTentadas.toString());
            tentativasRestantes--;
        }else {
            letrasTentadas.append(letra).append(" ");

        }
        textResult.setText(palavraAdivinhada.toString());
        textTentativas.setText(letrasTentadas.toString());
        atualizarTela();
    }


    private void atualizarTela() {
        String palavraExibida = palavraAdivinhada.toString();
        textResult.setText(palavraExibida);

        if (palavraExibida.equals(palavraSecreta)) {
            Toast.makeText(this, "Parabens", Toast.LENGTH_LONG).show();
            reiniciarJogo();
        } else if (tentativasRestantes == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Você perdeu").setIcon(R.drawable.ic_erro)
                    .setMessage("Deseja reiniciar o jogo?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            reiniciarJogo();
                            dialogInterface.dismiss();
                        }
                    }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pararCronometro();
                            onDestroy();
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else {
            int imagemForcaResouce = getResources().getIdentifier("forca_" + tentativasRestantes, "drawable", getOpPackageName());
            imagemForca.setImageResource(imagemForcaResouce);
        }
    }

    private void reiniciarJogo() {
        letrasTentadas.setLength(0);
        textTentativas.setText("");
        duracaoCronometro = duracaoInicial;
        inicalizarJogo();
        pararCronometro();
        iniciarCronometro();
    }

    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void recuperarAvatar(){
        String nome = getIntent().getStringExtra("texto");
        textnome.setText(nome);

        int imagem = getIntent().getIntExtra("imagem", 0);
        imageAvatar.setImageResource(imagem);
    }


}