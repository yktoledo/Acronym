package com.yendry.mvp.view;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yendry.mvp.App;
import com.yendry.mvp.R;
import com.yendry.mvp.presenter.MainPresenter;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    @Inject MainPresenter mainPresenter;
    RelativeLayout progressBar;
    TextView textView;
    Button search;
    EditText editText;
    RecyclerView recyclerView;
    private Adapter adapter;
    private String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((App)getApplication()).getComponent().inject(this);

        mainPresenter.attachView(this);
        progressBar = (RelativeLayout) findViewById(R.id.prsBar);
        textView = (TextView) findViewById(R.id.txt);
        editText = (EditText) findViewById(R.id.editText_id);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    requestData(editText.getText().toString());
                    return true;
                }

                return false;
            }
        });
        search = (Button) findViewById(R.id.btn);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestData(editText.getText().toString());
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
    }


    public void requestData(String data){
        textView.setVisibility(View.GONE);
        str = data;
        if (!TextUtils.isEmpty(str)){
            hideKeyboard();
            mainPresenter.getAcron(str);
        }else {
            showError("nothing to search, please type Acronym");
        }
    }
    @Override
    public void showText(String text) {
        textView.setVisibility(View.VISIBLE);
        textView.setText(text);
    }

    @Override
    public void showData(List<String> list) {
        //update adapter and hide texts
        adapter.setList(list, str);
        if (list.size()==0){
            showText("Nothing appear about: "+str);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mainPresenter != null)
            mainPresenter.detachView();
    }

    private void hideKeyboard(){
        // Check if no view has focus:
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
