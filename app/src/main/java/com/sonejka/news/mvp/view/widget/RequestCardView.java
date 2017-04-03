package com.sonejka.news.mvp.view.widget;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;

import com.sonejka.news.App;
import com.sonejka.news.R;
import com.sonejka.news.mvp.presenter.request.RequestPresenter;
import com.sonejka.news.mvp.view.activity.BaseActivity;
import com.sonejka.news.mvp.view.adapter.RequestAdapter;
import com.sonejka.news.util.ActivityUtil;
import com.sonejka.news.util.Logger;
import com.sonejka.news.util.ViewUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public class RequestCardView extends CardView implements IRequestCardView {

    @BindView(R.id.category_edit_text) AppCompatAutoCompleteTextView categoryEditText;
    @BindView(R.id.language_edit_text) AppCompatAutoCompleteTextView languageEditText;
    @BindView(R.id.country_edit_text) AppCompatAutoCompleteTextView countryEditText;

    @Inject RequestPresenter presenter;

    public RequestCardView(Context context) {
        this(context, null);
    }

    public RequestCardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RequestCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.cardview_request, this);
        ButterKnife.bind(this);

        Activity activity = ActivityUtil.getActivity(this);
        if (isInEditMode() || activity == null) return;

        BaseActivity baseActivity = (BaseActivity) activity;
        baseActivity.getActivityComponent().inject(this);

        presenter.setView(this);
    }

    // region IRequestCardView
    @Override
    public void setCategory(String[] categories, String category) {
        setupFiled(categoryEditText, categories, category);
    }

    @Override
    public void setLanguage(String[] languages, String language) {
        setupFiled(languageEditText, languages, language);
    }

    @Override
    public void setCountries(String[] countries, String country) {
        setupFiled(countryEditText, countries, country);
    }

    @Override
    public String getCategory() {
        return categoryEditText.getEditableText().toString();
    }

    @Override
    public String getLanguage() {
        return languageEditText.getEditableText().toString();
    }

    @Override
    public String getCountry() {
        return countryEditText.getEditableText().toString();
    }
    // endregion

    private void setupFiled(final AppCompatAutoCompleteTextView editText, final String[] array, String value) {
        RequestAdapter categoryAdapter = new RequestAdapter(array);
        editText.setAdapter(categoryAdapter);
        editText.setText(value);
        editText.setInputType(InputType.TYPE_NULL);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((RequestAdapter) parent.getAdapter()).getItem(position);
                editText.setText(item);
                if (presenter != null)
                    presenter.startRequestParam();
            }
        });

        editText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                ViewUtil.hideKeyboard(editText);
                editText.showDropDown();
                return false;
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (presenter != null)
            presenter.loadRequestParam();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (presenter != null)
            presenter.unSubscribe();
    }
}
