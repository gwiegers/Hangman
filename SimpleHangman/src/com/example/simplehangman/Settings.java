package com.example.simplehangman;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.SeekBar; 
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;


public class Settings extends Activity implements OnSeekBarChangeListener{

	 private SeekBar bar; // declare seekbar object variable
	    // declare text label objects
	 private TextView textProgress;
	 private TextView textProgress2;
	 
	 /** Called when the activity is first created. */
	 	@Override
	    public void onCreate(Bundle savedInstanceState)
	    {
	        super.onCreate(savedInstanceState);
	        // load the layout
	        setContentView(R.layout.fragment_settings);     
	        bar = (SeekBar)findViewById(R.id.seekBar1); // make seekbar object
	        bar.setOnSeekBarChangeListener(this); // set seekbar listener.
	        // since we are using this class as the listener the class is "this"
	        
	        // make text label for progress value
	        textProgress = (TextView)findViewById(R.id.textViewProgress);
	        textProgress2 = (TextView)findViewById(R.id.textViewProgress2);
	    }
	    
	    @Override
	    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {    	
	    	// change progress text label with current seekbar value
	    	switch (bar.getId()) {

	        case R.id.seekBar1:
	            textProgress.setText("Length word: "+progress);
	            break;

	        case R.id.seekBar2:
       	    	textProgress2.setText("Lives: "+progress);
	            break;
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