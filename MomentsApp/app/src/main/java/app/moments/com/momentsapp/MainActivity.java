package app.moments.com.momentsapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import app.moments.com.momentsapp.mock.ThreadMock;
import app.moments.com.momentsapp.view.ThreadAdapter;

public class MainActivity extends Activity {

    RecyclerView mThreads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mThreads = (RecyclerView) findViewById(R.id.list_thread);
        ThreadAdapter adapter = new ThreadAdapter(MainActivity.this,ThreadMock.getThreads());
        mThreads.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mThreads.setAdapter(adapter);
    }
}
