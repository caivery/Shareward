package scu.miomin.com.shareward.activity.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scu.miomin.sharewardlib.constants.ActivityType;
import com.scu.miomin.sharewardlib.toolbar.ToolbarActivity;
import com.scu.miomin.sharewardlib.widgets.recyclerview.ShareOnItemClickListener;

import scu.miomin.com.shareward.R;
import scu.miomin.com.shareward.activity.recyclerview.adapter.ChatAdapter;
import scu.miomin.com.shareward.activity.recyclerview.bean.ChatMessage;

/**
 * Created by 莫绪旻 on 16/6/24.
 */
public class SampleChatActivity extends ToolbarActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_sample_recyclerview, ActivityType.MODE_TOOLBAR_BACK);
    }

    @Override
    protected void setUpView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {

        setUpTitle("Chat");
        ChatAdapter adapter = new ChatAdapter(this, ChatMessage.MOCK_DATAS);
        adapter.setOnItemClickListener(new ShareOnItemClickListener<ChatMessage>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, ChatMessage o, int position) {
                Toast.makeText(SampleChatActivity.this, "Click:" + position + " => " + o.getContent(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, ChatMessage o, int position) {
                Toast.makeText(SampleChatActivity.this, "LongClick:" + position + " => " + o.getContent(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mRecyclerView.setAdapter(adapter);
    }
}