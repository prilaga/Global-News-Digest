package com.sonejka.news.mvp.view.widget;

import android.content.Context;
import android.support.annotation.ArrayRes;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sonejka.news.R;
import com.sonejka.news.mvp.model.Article;
import com.sonejka.news.mvp.model.RequestParam;
import com.sonejka.news.mvp.model.Source;
import com.sonejka.news.mvp.view.activity.BaseActivity;
import com.sonejka.news.mvp.view.adapter.RequestAdapter;
import com.sonejka.news.util.ListUtil;
import com.sonejka.news.util.TextUtil;
import com.sonejka.news.util.ViewUtil;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Oleg Tarashkevich on 02.04.17.
 */

public class RequestCardView extends CardView {

    @BindView(R.id.category_input_layout) TextInputLayout categoryTextInputLayout;
    @BindView(R.id.category_edit_text) AppCompatAutoCompleteTextView categoryEditText;
    @BindView(R.id.language_input_layout) TextInputLayout languageTextInputLayout;
    @BindView(R.id.language_edit_text) AppCompatAutoCompleteTextView languageEditText;
    @BindView(R.id.country_input_layout) TextInputLayout countryTextInputLayout;
    @BindView(R.id.country_edit_text) AppCompatAutoCompleteTextView countryEditText;

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
        populateViews();
    }

    private void populateViews() {
        setupFiled(categoryEditText, RequestParam.getCategories());
        setupFiled(languageEditText, RequestParam.getLanguages());
        setupFiled(countryEditText, RequestParam.getCountries());
    }

    private void setupFiled(final AppCompatAutoCompleteTextView editText, final String[] array) {
        RequestAdapter categoryAdapter = new RequestAdapter(array);
        editText.setAdapter(categoryAdapter);
        editText.setText(array[0]);
        editText.setInputType(InputType.TYPE_NULL);

        editText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = ((RequestAdapter) parent.getAdapter()).getItem(position);
                editText.setText(item);
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

    private void generateRequestParam() {
        @RequestParam.Category String category = categoryEditText.getEditableText().toString();
        @RequestParam.Language String language = languageEditText.getEditableText().toString();
        @RequestParam.Country String country = countryEditText.getEditableText().toString();
        Source.Param param = Source.param(category, language, country);
        EventBus.getDefault().post(param);
    }
}
