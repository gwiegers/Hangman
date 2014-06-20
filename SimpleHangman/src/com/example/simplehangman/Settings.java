package com.example.simplehangman;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.SeekBar; 
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class Settings extends Activity implements OnSeekBarChangeListener{

	 private SeekBar bar;
	 private SeekBar bar2;
	 private TextView textProgress;
	 private TextView textProgress2;
	 private int progressLength;
	 private int progressLives;
	 
	 /** Called when the activity is first created. */
	 	@Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        // load the layout
	        setContentView(R.layout.fragment_settings);     
	        bar = (SeekBar)findViewById(R.id.seekBar1); // make seekbar object
	        bar2 = (SeekBar)findViewById(R.id.seekBar2); // make seekbar object

	        bar.setOnSeekBarChangeListener(this); // set seekbar listener.
	        bar2.setOnSeekBarChangeListener(this); // set seekbar listener.
	        
	        // make text label for progress value
	        textProgress = (TextView)findViewById(R.id.textViewProgress);
	        textProgress2 = (TextView)findViewById(R.id.textViewProgress2);
	    }
	    
	 	@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater(); 
	    inflater.inflate(R.menu.settings, menu);
	    return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	 
	    	switch (item.getItemId()) {
		    	case R.id.reset:
		    		Intent intent = new Intent(this, MainActivity.class);
		    		intent.putExtra("progressLength", progressLength);
		    		intent.putExtra("progressLives", progressLives);
		    		startActivity(intent); 	     	 		    		
		    	default:
		    	return super.onOptionsItemSelected(item);
		    }
	   }    	     
	 	
	    @Override
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    	
	    	// change progress text label with current seekbar value
	    	if (seekBar.equals(bar)) {
	            textProgress.setText("Length word: "+(progress + 1));
	            progressLength = progress + 1;
	    	}

	        if (seekBar.equals(bar2)) {
       	    	textProgress2.setText("Lives: "+(progress + 1));
	            progressLives = progress + 1;
	        }
	    }  
	    
	    @Override
	    public void onStartTrackingTouch(SeekBar seekBar) {    	
	    }
	    
	    @Override
	    public void onStopTrackingTouch(SeekBar seekBar) {
	    	seekBar.setSecondaryProgress(seekBar.getProgress());  	
	    }
}