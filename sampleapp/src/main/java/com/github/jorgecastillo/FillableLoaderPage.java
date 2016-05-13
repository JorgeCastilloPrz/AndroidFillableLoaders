/*
 * Copyright (C) 2015 Jorge Castillo Pérez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.jorgecastillo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.github.jorgecastillo.clippingtransforms.WavesClippingTransform;
import com.github.jorgecastillo.listener.OnStateChangeListener;

/**
 * @author jorge
 * @since 11/08/15
 */
public class FillableLoaderPage extends Fragment implements OnStateChangeListener, ResettableView {

  @Bind(R.id.fillableLoader) @Nullable FillableLoader fillableLoader;
  private View rootView;
  private int pageNum;
  private int mPercentage = 20;

  public static FillableLoaderPage newInstance(int pageNum) {
    FillableLoaderPage page = new FillableLoaderPage();
    Bundle bundle = new Bundle();
    bundle.putInt("pageNum", pageNum);
    page.setArguments(bundle);

    return page;
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    pageNum = getArguments().getInt("pageNum", 0);

    switch (pageNum) {
      case 0:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_first_page, container, false);
        break;
      case 1:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_second_page, container, false);
        break;
      case 2:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_third_page, container, false);
        break;
      case 3:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_fourth_page, container, false);
        break;
      case 4:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_fifth_page, container, false);
        break;
      case 5:
        rootView = inflater.inflate(R.layout.fragment_fillable_loader_sixth_page, container, false);
        break;
      case 6:
        rootView =
                inflater.inflate(R.layout.fragment_fillable_loader_seventh_page, container, false);
        break;
      case 7:
        rootView =
                inflater.inflate(R.layout.fragment_fillable_loader_eighth_page, container, false);
        break;
      default:
        rootView =
            inflater.inflate(R.layout.fragment_fillable_loader_seventh_page, container, false);
        break;
    }

    return rootView;
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, rootView);
    setupFillableLoader(pageNum);
  }

  private void setupFillableLoader(int pageNum) {
    if (pageNum == 3) {
      int viewSize = getResources().getDimensionPixelSize(R.dimen.fourthSampleViewSize);
      FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
      params.gravity = Gravity.CENTER;

      FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
      fillableLoader = loaderBuilder.parentView((FrameLayout) rootView)
          .svgPath(Paths.JOB_AND_TALENT)
          .layoutParams(params)
          .originalDimensions(970, 970)
          .strokeColor(Color.parseColor("#1c9ade"))
          .fillColor(Color.parseColor("#1c9ade"))
          .strokeDrawingDuration(2000)
          .clippingTransform(new WavesClippingTransform())
          .fillDuration(10000)
          .build();
    } else if (pageNum == 6) {
      int viewSize = getResources().getDimensionPixelSize(R.dimen.fourthSampleViewSize);
      FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(viewSize, viewSize);
      params.gravity = Gravity.CENTER;

      SeekBar mSeekbar = (SeekBar) rootView.findViewById(R.id.PercentageSeekBar);
      mSeekbar.setProgress(mPercentage);
      mSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
      {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
          mPercentage = progress;
          fillableLoader.setPercentage(progress);
        }

        public void onStartTrackingTouch(SeekBar seekBar) { }

        public void onStopTrackingTouch(SeekBar seekBar) { }
      });

      FillableLoaderBuilder loaderBuilder = new FillableLoaderBuilder();
      fillableLoader = loaderBuilder.parentView((FrameLayout) rootView)
          .svgPath(Paths.JOB_AND_TALENT)
          .layoutParams(params)
          .percentage(mPercentage)
          .originalDimensions(970, 970)
          .strokeColor(Color.parseColor("#1c9ade"))
          .fillColor(Color.parseColor("#1c9ade"))
          .strokeDrawingDuration(2000)
          .clippingTransform(new WavesClippingTransform())
          .fillDuration(10000)
          .build();
    } else {
      fillableLoader.setSvgPath(pageNum == 0 ? Paths.INDOMINUS_REX : pageNum == 1 ? Paths.RONALDO
          : pageNum == 2 ? Paths.SEGA : pageNum == 4 ? Paths.COCA_COLA : Paths.GITHUB);
    }

    fillableLoader.setOnStateChangeListener(this);
  }

  @Override public void onStateChange(int state) {
    ((MainActivity) getActivity()).showStateHint(state);
  }

  @Override public void reset() {
    fillableLoader.reset();

    //We wait a little bit to start the animation, to not contaminate the drawing effect
    //by the activity creation animation.
    fillableLoader.postDelayed(new Runnable() {
      @Override public void run() {
        fillableLoader.start();
      }
    }, 250);
  }
}
