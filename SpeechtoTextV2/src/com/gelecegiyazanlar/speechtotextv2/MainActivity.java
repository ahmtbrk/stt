package com.gelecegiyazanlar.speechtotextv2;

import java.util.ArrayList;

import com.gelecegiyazanlar.speechtotextv2.R;

import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	/*
	 * Coded By Ahmet Burak DEMIRKOPARAN https://twitter.com/lifesteal3r
	 * 07.08.2014 Speech to Text Complex Version with Custom Dialog Dont forget
	 * add this permission in manifest file <uses-permission
	 * android:name="android.permission.RECORD_AUDIO"></uses-permission>
	 */

	SpeechRecognizer speechRecognizer;
	ProgressDialog loadingDialog;
	Context context;
	Button speechToTextButton;
	private final String TAG = getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		speechToTextButton = (Button) findViewById(R.id.speechToTextButton);
		speechToTextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				loadingDialog = new ProgressDialog(context);
				loadingDialog.setCancelable(false);
				loadingDialog.setMessage("Lütfen Konuþun");
				loadingDialog.show();
				speechRecognizer = SpeechRecognizer
						.createSpeechRecognizer(context);

				speechRecognizer
						.setRecognitionListener(new RecognitionListener() {

							@Override
							public void onRmsChanged(float arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onResults(Bundle results) {
								// TODO Auto-generated method stub
								loadingDialog.dismiss();
								ArrayList<String> speechResults= results
										.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
								for (String speechResult : speechResults) {
									Log.i(TAG,speechResult);
								}
							}

							@Override
							public void onReadyForSpeech(Bundle arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onPartialResults(Bundle arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onEvent(int arg0, Bundle arg1) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onError(int errorCode) {
								// TODO Auto-generated method stub
								loadingDialog.dismiss();
								Toast.makeText(
										getApplicationContext(),
										"Bir Hata Oluþtu Lütfen Tekrar Deneyin...",
										Toast.LENGTH_SHORT).show();

							}

							@Override
							public void onEndOfSpeech() {
								// TODO Auto-generated method stub
								loadingDialog
										.setMessage("Kayýt Bitti.Sonuçlar Getiriliyor");

							}

							@Override
							public void onBufferReceived(byte[] arg0) {
								// TODO Auto-generated method stub

							}

							@Override
							public void onBeginningOfSpeech() {
								// TODO Auto-generated method stub
								loadingDialog.setMessage("Kayýt Baþladý");
							}
						});

				Intent recognizerIntent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
				recognizerIntent.putExtra(
						RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass()
								.getPackage().getName());
				recognizerIntent.putExtra(
						RecognizerIntent.EXTRA_LANGUAGE_MODEL,
						RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
				recognizerIntent
						.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
				speechRecognizer.startListening(recognizerIntent);

			}
		});
	}

	@Override
	protected void onDestroy() {
		if (speechRecognizer != null) {
			speechRecognizer.stopListening();
			speechRecognizer.cancel();
			speechRecognizer.destroy();
		}
		super.onDestroy();
	}

}
