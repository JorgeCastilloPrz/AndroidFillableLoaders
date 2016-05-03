package com.github.jorgecastillo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.github.jorgecastillo.FillableLoaderPage;

/**
 * As a fast workaround to make this work, i am going to retain all three pages in memory in order
 * to avoid problems when trying to call the resettable callback from the
 * OnPageChangeListener. (When it is called, the fragment could not be totally
 * created as its documentation says so.)
 *
 * @author jorge
 * @since 11/08/15
 */
public class FillablePagesAdapter extends FragmentStatePagerAdapter {

  private FillableLoaderPage firstPage;
  private FillableLoaderPage secondPage;
  private FillableLoaderPage thirdPage;
  private FillableLoaderPage fourthPage;
  private FillableLoaderPage fifthPage;
  private FillableLoaderPage sixthPage;
  private FillableLoaderPage seventhPage;
  private FillableLoaderPage eighthPage;

  public FillablePagesAdapter(FragmentManager fm) {
    super(fm);
    firstPage = FillableLoaderPage.newInstance(0);
    secondPage = FillableLoaderPage.newInstance(1);
    thirdPage = FillableLoaderPage.newInstance(2);
    fourthPage = FillableLoaderPage.newInstance(3);
    fifthPage = FillableLoaderPage.newInstance(4);
    sixthPage = FillableLoaderPage.newInstance(5);
    seventhPage = FillableLoaderPage.newInstance(6);
    eighthPage = FillableLoaderPage.newInstance(7);
  }

  @Override public Fragment getItem(int position) {
    return getFragmentForPosition(position);
  }

  @Override public int getCount() {
    return 8;
  }

  private Fragment getFragmentForPosition(int position) {
    switch (position) {
      case 0:
        return firstPage;
      case 1:
        return secondPage;
      case 2:
        return thirdPage;
      case 3:
        return fourthPage;
      case 4:
        return fifthPage;
      case 5:
        return sixthPage;
      case 6:
        return seventhPage;
      case 7:
        return eighthPage;
      default:
        return seventhPage;
    }
  }

  @Override public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return "Plain";
      case 1:
        return "Stroke";
      case 2:
        return "Rounded";
      case 3:
        return "Waves";
      case 4:
        return "Waves";
      case 5:
        return "Waves";
      case 6:
        return "Waves Percentage";
      case 7:
        return "Waves 40%";
      default:
        return "Waves Percentage";
    }
  }
}