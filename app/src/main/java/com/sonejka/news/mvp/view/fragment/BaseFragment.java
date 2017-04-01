package com.sonejka.news.mvp.view.fragment;

import android.support.v4.app.Fragment;

import com.sonejka.news.di.component.ActivityComponent;
import com.sonejka.news.di.component.FragmentComponent;
import com.sonejka.news.di.module.FragmentModule;
import com.sonejka.news.mvp.view.activity.BaseActivity;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class BaseFragment extends Fragment {

    private FragmentComponent fragmentComponent;

    public FragmentComponent getFragmentComponent(){
         if (fragmentComponent == null){
             BaseActivity activity = (BaseActivity) getActivity();
             ActivityComponent activityComponent = activity.getActivityComponent();
             fragmentComponent = activityComponent.plus(new FragmentModule(activity));
         }
        return fragmentComponent;
    }
}
