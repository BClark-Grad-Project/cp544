package com.cp544.teamone.triangle;

import java.util.Locale;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Point;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

	public UnitTestGameGenerateTriangle unitTest;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a {@link FragmentPagerAdapter}
	 * derivative, which will keep every loaded fragment in memory. If this
	 * becomes too memory intensive, it may be best to switch to a
	 * {@link android.support.v13.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the activity.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(mSectionsPagerAdapter.getListener(i)));
		}
		
		//runUnitTest();
	}

	public void runUnitTest() {
		TextView displayName;
		ImageView displayImage;
		displayName = (TextView) findViewById(R.id.textView6);
		displayImage = (ImageView) findViewById(R.id.display);
		
		unitTest = new UnitTestGameGenerateTriangle(displayName, displayImage);

	}
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void exitApp(MenuItem position){
		finish();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public class Game {
		double minVal = 0;
		double maxVal = 100;
		double bottomLength, leftLength, rightLength;
		Button btnGenerate, btnClear;
		EditText inputBottom, inputLeft, inputRight;
		TextView disaplyTriangleName, labelResults;
		ImageView displayTriangleImage;
		
		LinearLayout disaplyTriangleSides;
		TextView valueLeft;
		TextView valueBottom;
		TextView valueRight;
		
		public void invalidInput(TextView tView, ImageView iView, int type) {
			clearFields();
			if (type == 0) tView.setText("Error:Please try again, remember your number must be less than " + maxVal + ".");
			if (type == 1) tView.setText("Error:Please try again, value must be more than " + minVal + ".");
			if (type == 2) tView.setText("Error:Please try again, missing input field.");
			if (type == 3) tView.setText("Error:Please try again, this doesn't equal a triangle.");
			iView.setImageResource(android.R.color.transparent);
			//Log.d("Triangle Generate", "Clear fields. Sucess!");
		}
		
		public void displaySides(double a, double b, double c) {
			valueLeft.setText(Double.toString(a));
			valueBottom.setText(Double.toString(b));
			valueRight.setText(Double.toString(c));			
			disaplyTriangleSides.setVisibility(LinearLayout.VISIBLE);
			
		}
		
		public void viewIsosceles(TextView tView, ImageView iView){
			tView.setText("Isosceles Triangle: Two Congruent Sides");
			iView.setImageResource(R.drawable.i_triangle);	
			//Log.d("Triangle Generate", "View Isoseles. Sucess!");
		}		
		
		public void viewEquilateral(TextView tView, ImageView iView){	
			tView.setText("Equilateral Triangle: All sides are equal");
			iView.setImageResource(R.drawable.e_triangle);
			//Log.d("Triangle Generate", "View Equilateral. Sucess!");
		}	
		
		public void viewScalene(TextView tView, ImageView iView){
			tView.setText("Scalene Triangle: No Congruent Sides");
			iView.setImageResource(R.drawable.s_triangle);
			//Log.d("Triangle Generate", "View Scalene. Sucess!");
		}
		
		public void clearFields(){
				inputBottom.setText("");
				inputLeft.setText("");
				inputRight.setText("");
				disaplyTriangleName.setText("");
				displayTriangleImage.setImageResource(android.R.color.transparent);
				disaplyTriangleSides.setVisibility(LinearLayout.GONE);
		}
		
	    public boolean isLarge(double a, double b, double c) {
	        return a >= maxVal || b >= maxVal || c >= maxVal;
	    }
		
	    public boolean isShort(double a, double b, double c) {
	        return a <= minVal || b <= minVal || c <= minVal;
	    }

	    public boolean notTriangle(double a, double b, double c) {
	        return a >= b + c || b >= a + c || c >= a + b;
	    }
	    
		public Boolean isEqualateral (double a, double b, double c){
			return  a == b && b == c;
		}
		
		public Boolean isIsosceles (double a, double b, double c){
			return a == b || b == c || c == a;
		}
		
		public int gameGenerateTriangle(TextView tView, ImageView iView, double a, double b, double c) {				
			if (isLarge(a,b,c)) {
				displaySides(a,b,c);
				invalidInput(tView, iView, 0);
				return 0;
			} else if (isShort(a,b,c)) {
				displaySides(a,b,c);
				invalidInput(tView, iView, 1);
				return 1;
			} else if (notTriangle(a,b,c)) {
				displaySides(a,b,c);
				invalidInput(tView, iView, 3);
				return 2;
			} else if (isEqualateral(a,b,c)) {
				displaySides(a,b,c);
				viewEquilateral(tView, iView);
				return 3;
			} else if (isIsosceles(a,b,c)) {
				displaySides(a,b,c);
				viewIsosceles(tView, iView);
				return 4;
			} else {
				displaySides(a,b,c);
				viewScalene(tView, iView);
				return 5;
			}
		}
		
		public void gameLogic(TextView tView, ImageView iView, EditText eText1, EditText eText2, EditText eText3){				
			// convert text to int
			if (eText1.getText().length() == 0 || 
					eText2.getText().length() == 0 || 
					eText3.getText().length() == 0){
				invalidInput(tView, iView, 2);				
				return;				
			}
			bottomLength = Double.parseDouble(eText1.getText().toString());
			leftLength = Double.parseDouble(eText2.getText().toString());
			rightLength = Double.parseDouble(eText3.getText().toString());

			// Choose triangle. 
			gameGenerateTriangle(tView, iView, bottomLength, leftLength, rightLength);			
		}
		
		public void gameLayout(){
			setContentView(R.layout.game);
			// assigning the values
			inputBottom = (EditText) findViewById(R.id.editText1);
			inputLeft = (EditText) findViewById(R.id.editText2);
			inputRight = (EditText) findViewById(R.id.editText3);
			btnGenerate = (Button) findViewById(R.id.button1);
			btnClear = (Button) findViewById(R.id.button2);
			
			disaplyTriangleName = (TextView) findViewById(R.id.textView6);
			displayTriangleImage = (ImageView) findViewById(R.id.display);
			disaplyTriangleSides = (LinearLayout) findViewById(R.id.enteredValues);
			valueLeft = (TextView) findViewById(R.id.valueLeft);
			valueBottom = (TextView) findViewById(R.id.valueBottom);
			valueRight = (TextView) findViewById(R.id.valueRight);

			// setting up Gen button
			btnGenerate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {								 
					gameLogic(disaplyTriangleName, displayTriangleImage, 
							inputBottom, inputLeft, inputRight);
				}
			});

			// setting up the clear button
			btnClear.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					clearFields();
				}
			});
		}
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a PlaceholderFragment (defined as a static inner class
			// below).
			return PlaceholderFragment.newInstance(position + 1);
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}
		
		
		public void selectTabLogic(ActionBar.Tab tab, FragmentTransaction ft){			
			// show the given tab
			if (tab.getPosition() == 0) {
				Game game = new Game(); 
				game.gameLayout();
			}
			if (tab.getPosition() == 1) {
				setContentView(R.layout.guide);
			}
			if (tab.getPosition() == 2) {
				setContentView(R.layout.about);
			}
		}
		
		public ActionBar.TabListener getListener(int position) {
			return new ActionBar.TabListener() {
				public void onTabSelected(ActionBar.Tab tab,
						FragmentTransaction ft) {	
					selectTabLogic(tab, ft);
				}

				public void onTabUnselected(ActionBar.Tab tab,
						FragmentTransaction ft) {
					// hide the given tab
				}

				public void onTabReselected(ActionBar.Tab tab,
						FragmentTransaction ft) {
					// probably ignore this event
				}
			};
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	public class UnitTestGameGenerateTriangle {
		// Outside Bounds returns 0
		// Isoselese returns 1
		// Equilateral returns 2
		// Scalene returns 3
		int value; 
		Game game = new Game();
		TextView tView;
		ImageView iView;
		// Bounds
		Point upperLower = new Point(0, 100);
		Point belowAbove = new Point(-1, 101);
		int center = 50;
		
		public UnitTestGameGenerateTriangle(TextView tv, ImageView iv){
			this.tView = tv;
			this.iView = iv;
		}
		
		public void testIsoselese(){
			Log.d("Boundry Test", "Begin");	
			Log.d("Boundry Test", "End");
		}
		public void TSmiddle(){
			if(game.gameGenerateTriangle(tView, iView, center + 25, center - 25, center) == 3){
				Log.d("TS In Bounds", "Passed!");
			} else {				
				Log.e("TS In Bounds", "Did not pass.");
			}			
		}
	}

}
