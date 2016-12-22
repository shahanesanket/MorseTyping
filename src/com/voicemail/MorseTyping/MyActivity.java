package com.voicemail.MorseTyping;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private static Button btnLeft,btnRight;
    private static TextView tv1;
    private static String code;
    private MorseCode morseCode;
    private Vibrator vibe;
    private TextToSpeech speaker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        InitializeVariables();
        OnStart();
    }

    private void InitializeVariables()
    {
        morseCode = new MorseCode();
        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        speaker = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.US);
                }
            }
        });
        speaker.setPitch((float)0.7);
        speaker.setSpeechRate((float)0.7);
        code = "";
    }

    public void OnStart()
    {
        btnLeft = (Button)findViewById(R.id.buttonLeft);
        btnRight = (Button)findViewById(R.id.buttonRight);
        tv1 = (TextView)findViewById(R.id.textView);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code += ".";
                vibe.vibrate(50);
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                code += "-";
                vibe.vibrate(150);
            }
        });

        btnRight.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                TranslateCodeToText();
                return true;
            }
        });
    }

    private void TranslateCodeToText()
    {
        int index = morseCode.ReturnCharacterIndex(code);
        if(index != -1) {
            char character = morseCode.characters[index];
            tv1.setText(tv1.getText() + String.valueOf(character));
            speaker.speak(String.valueOf(character),TextToSpeech.QUEUE_FLUSH,null);
            vibe.vibrate(100);
            code = "";
        }
        else
        {
            //Toast.makeText(this,"Not a morse code",Toast.LENGTH_SHORT);
            speaker.speak("Not morse code. Try again",TextToSpeech.QUEUE_FLUSH,null);
            code = "";
        }
    }
}