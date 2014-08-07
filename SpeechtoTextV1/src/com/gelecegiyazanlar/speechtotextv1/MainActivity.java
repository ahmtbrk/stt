package com.gelecegiyazanlar.speechtotextv1;

import java.util.ArrayList;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	
	/*
	 * Coded by Ahmet Burak DEMIRKOPARAN
	 * https://twitter.com/lifesteal3r
	 * 07.08.2014
	 * Speech to Text Simple Version
	 * 
	 * */
	
	Button speechToTextButton;
	private final int VOICE_RECOGNITION_REQUEST_CODE = 1;
	private final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		speechToTextButton = (Button)findViewById(R.id.speechToTextButton);
		speechToTextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
				intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Bir þeyler söyleyin");
				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
				startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		 if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
			 ArrayList<String> speechResults  = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			 for (String speechResult : speechResults) {
				Log.i(TAG,speechResult);
			}
		 }

		super.onActivityResult(requestCode, resultCode, data);
	}
}
