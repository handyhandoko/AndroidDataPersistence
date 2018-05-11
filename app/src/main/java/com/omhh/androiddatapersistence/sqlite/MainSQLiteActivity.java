package com.omhh.androiddatapersistence.sqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.omhh.androiddatapersistence.R;
import com.omhh.androiddatapersistence.sqlite.data.MessagesContract;
import com.omhh.androiddatapersistence.sqlite.data.MessagesOpenHelpers;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSQLiteActivity extends AppCompatActivity {
    final MessagesOpenHelpers dbHelpers = new MessagesOpenHelpers(this, MessagesContract.MessagesEntry.TABLE_NAME);
    @BindView(R.id.edtMessage) EditText edtMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSend)
    public void save(){
        dbHelpers.insert(edtMessage.getText().toString());
    }
}
