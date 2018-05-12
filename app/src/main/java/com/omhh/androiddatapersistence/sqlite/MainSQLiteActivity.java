package com.omhh.androiddatapersistence.sqlite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.omhh.androiddatapersistence.R;
import com.omhh.androiddatapersistence.sqlite.data.MessagesContract;
import com.omhh.androiddatapersistence.sqlite.data.MessagesOpenHelpers;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainSQLiteActivity extends AppCompatActivity {
    final MessagesOpenHelpers dbHelpers = new MessagesOpenHelpers(this, MessagesContract.MessagesEntry.TABLE_NAME);
    @BindView(R.id.edtMessage) EditText edtMessage;
    @BindView(R.id.rvMessages) RecyclerView rvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sqlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        show();
    }

    @OnClick(R.id.btnSend)
    public void save(){
        dbHelpers.insert(edtMessage.getText().toString());
        edtMessage.setText("");
        show();
    }

    private void show(){
        String[] collumns = new String[] {MessagesContract.MessagesEntry.COLLUMN_MESSAGE};
        List<String> messages = dbHelpers.query(collumns, null, null, null);
        MessageAdapter adapter = new MessageAdapter(messages);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvMessages.setAdapter(adapter);
        rvMessages.setLayoutManager(layoutManager);
    }

    class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
        List<String> messagesList;

        public MessageAdapter(List<String> messages) {
            this.messagesList = messages;
        }

        @NonNull
        @Override
        public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            TextView messageTextView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.message_viewholder, parent, false);
            MessageViewHolder viewHolder = new MessageViewHolder(messageTextView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull MessageViewHolder viewHolder, int position) {
            viewHolder.bind(messagesList.get(position));
        }

        @Override
        public int getItemCount() {
            return messagesList.size();
        }

        public class MessageViewHolder extends RecyclerView.ViewHolder {
            TextView messagesTextView;

            public MessageViewHolder(View itemView) {
                super(itemView);
                messagesTextView = (TextView) itemView;
            }

            public void bind(String message){
                messagesTextView.setText(message);
            }
        }
    }
}
