package com.example.simplehangman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.GridView;
import android.app.Activity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.widget.EditText;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;
import com.example.simplehangman.R;
import android.util.TypedValue;


public class MainActivity extends Activity {

	static final String[] letters = new String[] {
		            "A", "B", "C", "D", "E",
		            "F", "G", "H", "I", "J",
		            "K", "L", "M", "N", "O",
		            "P", "Q", "R", "S", "T",
		            "U", "V", "W", "X", "Y", "Z"};


    private String[] words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private TextView[] ABC;
    private String ABCLet;
    private LinearLayout textView;
    private TextView livesView;
    private EditText Inputbar;
    int lives;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);
        settingUp();
    }
    
    public void settingUp() {     
         // Add letters to screen
        textView = (LinearLayout)findViewById(R.id.textView);
        ABC = new TextView[27];
        ABCLet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        textView.removeAllViews();
        for (int i = 0; i < ABCLet.length(); i++) {
        	ABC[i] = new TextView(this);
        	ABC[i].setText(""+ABCLet.charAt(i));
        	ABC[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, 28);
  		    textView.addView(ABC[i]);
        }
        lives = 3;
        // Add lives to the screen
        livesView = (TextView)findViewById(R.id.textLives);
        livesView.setText("Lives: "+lives);
        
        Resources res = getResources();
        words = res.getStringArray(R.array.wordz);
        rand = new Random();
        currWord = "";
        wordLayout = (LinearLayout)findViewById(R.id.word);
        playGame();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater(); 
    inflater.inflate(R.menu.main, menu);
    return true;
    }
    
    public boolean onOptionsItemSelected(MenuItem item) {
    	 
    	switch (item.getItemId()) {
	    	case R.id.reset:
	    		settingUp();    	     	 
	    	case R.id.title_activity_settings:
	    		Intent intent = new Intent(this, Settings.class);
                startActivity(intent);
	    		
	    	default:
	    	return super.onOptionsItemSelected(item);
	    }
   }
    	     
    private void playGame() {
    	//play a new game
    	String newWord = words[rand.nextInt(words.length)];
    	while(newWord.equals(currWord)) {
    		newWord = words[rand.nextInt(words.length)];
    	}
    	currWord = newWord;
    	charViews = new TextView[currWord.length()];
    	wordLayout.removeAllViews();
     	for (int c = 0; c < currWord.length(); c++) {
    		  charViews[c] = new TextView(this);
    		  charViews[c].setText(""+currWord.charAt(c));
     		  charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    		  charViews[c].setGravity(Gravity.CENTER);
    		  charViews[c].setTextColor(Color.WHITE);
    		  charViews[c].setBackgroundResource(R.drawable.letter_bg);
    		  //add to layout
    		  wordLayout.addView(charViews[c]);
    		}
    	}

    // Called when the user clicks the Send button 
    public void checkInput(View view) {
        // Do something in response to button
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String inputLetter = editText.getText().toString();
        editText.setText("");
        inputLetter = inputLetter.toUpperCase();
        char letterChar = inputLetter.charAt(0);
        boolean guessed = false;
        for (int i = 0; i < ABCLet.length(); i++)
        {
            if (letters[i].equals(inputLetter))
            {               
            	if (ABC[i].getCurrentTextColor() == Color.WHITE ) {
            		break;
            	}
            	//check if letter is in word
             	for (int j = 0; j < currWord.length(); j++) {
             		if (currWord.charAt(j) == letterChar) {
             			//show letter	
             			charViews[j].setTextColor(Color.BLACK);
             			guessed = true;
             			break;
             		}	
             	}
             	// if letter is not guessed correctly, take a live
             	if (guessed == false) {
             		lives = lives - 1; 
                    livesView.setText("Lives: "+lives);
                    if (lives == 0) {
                    	Intent intent = new Intent(this, YouLost.class);
                        startActivity(intent);
                    }
            	}	
            	//make letter white or disappear
             	ABC[i].setTextColor(Color.WHITE);
            	break;
            }
        }
    }  

}
