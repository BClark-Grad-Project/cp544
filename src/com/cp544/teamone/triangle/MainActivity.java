package com.cp544.teamone.triangle;

import java.util.Locale;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v13.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements ActionBar.TabListener {

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

	// set up the variables here
	int a, b, c;
	Button gen, clear;
	EditText input1, input2, input3;
	String input1a, input2b, input3c;
	TextView display, display2;
	ImageView idisplay;
	
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		
		public void invalidInput() {
			display = (TextView) findViewById(R.id.textView5);
			idisplay = (ImageView) findViewById(R.id.display);
			display.setText("Error:Please try again, remember your number must be less than 100");
			idisplay.setImageResource(android.R.color.transparent);
		}	
		
		public ActionBar.TabListener getListener(int position) {
			return new ActionBar.TabListener() {
				public void onTabSelected(ActionBar.Tab tab,
						FragmentTransaction ft) {					
					// show the given tab
					if (tab.getPosition() == 0) {
						setContentView(R.layout.game);
						// assigning the values
						input1 = (EditText) findViewById(R.id.editText1);
						input2 = (EditText) findViewById(R.id.editText2);
						input3 = (EditText) findViewById(R.id.editText3);
						display = (TextView) findViewById(R.id.textView5);
						display2 = (TextView) findViewById(R.id.textView6);
						gen = (Button) findViewById(R.id.button1);
						clear = (Button) findViewById(R.id.button2);
						idisplay = (ImageView) findViewById(R.id.display);

						// setting up Gen button
						gen.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {								 
								// getting the text and converting it to string value
								input1a = input1.getText().toString();
								input2b = input2.getText().toString();
								input3c = input3.getText().toString();
								
								// converting those text values back into int
								a = Integer.parseInt(input1a);
								b = Integer.parseInt(input2b);
								c = Integer.parseInt(input3c);
				
								// setting images
								if (((a == b && b != c) || (a == c && b != c) || (b == c && a != c))
										&& (a <= 100 && b <= 100 && c <= 100)) {
									display.setText("Isosceles Triangle: Two Congruent Sides");
									idisplay.setImageResource(R.drawable.i_triangle);
								} else if ((a == b && a == c)
										&& (a <= 100 && b <= 100 && c <= 100)) {
									display.setText("Equilateral Triangle: All sides are equal");
									idisplay.setImageResource(R.drawable.e_triangle);
								} else if ((a != b && a != c && b != c)
										&& (a <= 100 && b <= 100 && c <= 100)) {
									display.setText("Scalene Triangle: No Congruent Sides");
									idisplay.setImageResource(R.drawable.s_triangle);
								} else {
									invalidInput();
								}
							}
						});

						// setting up the clear button

						clear.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								if (v.getId() == R.id.button2) {
									input1.setText("");
									input2.setText("");
									input3.setText("");
									display.setText("");
									idisplay.setImageResource(android.R.color.transparent);
								}
							}
						});
					}
					if (tab.getPosition() == 1) {
						setContentView(R.layout.guide);
					}
					if (tab.getPosition() == 2) {
						setContentView(R.layout.about);
					}
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
}
