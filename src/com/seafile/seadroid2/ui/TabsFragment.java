package com.seafile.seadroid2.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.seafile.seadroid2.R;
import com.viewpagerindicator.IconPagerAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class TabsFragment extends SherlockFragment {
        private static final int[] ICONS = new int[] {
	            R.drawable.perm_group_library,
	            R.drawable.perm_group_activity,
	            R.drawable.perm_group_starred
	            
	    };

	    private int currentPosition = 0;
	    private FragmentPagerAdapter adapter;
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    }
	    
/*	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);
	        mActivity = (BrowserActivity)activity;
	    }
	    
	    @Override
	    public void onDetach() {
	        mActivity = null;
	        super.onDetach();
	    }*/
	    
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	    	
	    	final Context contextThemeWrapper = new ContextThemeWrapper(getActivity(), R.style.StyledIndicators);

	        // clone the inflater using the ContextThemeWrapper
	        LayoutInflater localInflater = inflater.cloneInContext(contextThemeWrapper);
	    	
	    	View root = localInflater.inflate(R.layout.tabs_main, container, false);
	    	adapter = new SeafileTabsAdapter(getChildFragmentManager());

	    	ViewPager pager = (ViewPager)root.findViewById(R.id.pager);
	    	pager.setAdapter(adapter);

	        TabPageIndicator indicator = (TabPageIndicator)root.findViewById(R.id.indicator);
	        indicator.setViewPager(pager);
	        indicator.setOnPageChangeListener(new OnPageChangeListener() {
	        	@Override
	        	public void onPageSelected(final int position) {
	        	            // TODO Auto-generated method stub
	        					currentPosition = position;
	        					getActivity().supportInvalidateOptionsMenu();
	        	        	}

				@Override
				public void onPageScrollStateChanged(int arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
					// TODO Auto-generated method stub
					
				}

	        });
	        return root;
	        
	    }
	    
	    public int getCurrentTabIndex() {
	    	return currentPosition;
	    }
	    
	    public Fragment getFragment(int index) {
	    	return adapter.getItem(index);
	    }
	    
	    class SeafileTabsAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
	        public SeafileTabsAdapter(FragmentManager fm) {
	            super(fm);
	        }

	        private ReposFragment reposFragment = null;
	        private ActivitiesFragment activitieFragment = null;
	        private StarredFragment starredFragment = null;
	        @Override
	        public Fragment getItem(int position) {
	        	switch(position) {
	        	case 0 :
	        		if(reposFragment == null) {
	        			reposFragment = new ReposFragment();
	        		}
	        		return reposFragment;
	        	case 1 :
	        		if(activitieFragment == null) {
	        			activitieFragment = new ActivitiesFragment();
	        		}
	        		return activitieFragment;
	        	case 2 :
	        		if(starredFragment == null) {
	        		    starredFragment = new StarredFragment();
	        		}
	        		return starredFragment;	
	        	default : 
	        		return new Fragment();
	        	}
	        }

	        @Override
	        public CharSequence getPageTitle(int position) {
	            switch (position) {         
	            case 0:
	                return getString(R.string.tabs_library).toUpperCase();       
	            case 1:
	                return getString(R.string.tabs_activity).toUpperCase();
	            case 2:
	                return getString(R.string.tabs_starred).toUpperCase();
	                
	                default:
	                    return null;
	            }
	        }

	        @Override 
	        public int getIconResId(int index) {
	          return ICONS[index];
	        }

	      @Override
	        public int getCount() {
	          return ICONS.length;
	        }
	    }
}