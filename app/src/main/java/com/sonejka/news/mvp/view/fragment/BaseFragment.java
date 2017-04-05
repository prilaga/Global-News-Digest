package com.sonejka.news.mvp.view.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ProgressBar;

import com.sonejka.news.R;
import com.sonejka.news.di.component.ActivityComponent;
import com.sonejka.news.di.component.FragmentComponent;
import com.sonejka.news.di.module.FragmentModule;
import com.sonejka.news.mvp.view.activity.BaseActivity;

import butterknife.BindView;
import butterknife.Optional;

/**
 * Created by Oleg Tarashkevich on 01.04.17.
 */

public class BaseFragment extends Fragment {

    @Nullable @BindView(R.id.progress_bar) ProgressBar progressBar;

    private FragmentComponent fragmentComponent;

    public FragmentComponent getFragmentComponent(){
         if (fragmentComponent == null){
             BaseActivity activity = (BaseActivity) getActivity();
             ActivityComponent activityComponent = activity.getActivityComponent();
             fragmentComponent = activityComponent.plus(new FragmentModule(activity));
         }
        return fragmentComponent;
    }

    protected void showProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.VISIBLE);
    }

    protected void hideProgress() {
        if (progressBar != null)
            progressBar.setVisibility(View.GONE);
    }
}
