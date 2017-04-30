package np.com.ngimasherpa.mvpdemo.ui;

import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.ngimasherpa.mvpdemo.R;
import np.com.ngimasherpa.mvpdemo.model.PostModel;
import np.com.ngimasherpa.mvpdemo.utils.Contants;

import static np.com.ngimasherpa.mvpdemo.utils.Contants.Extra.*;

public class PostActivity extends AppCompatActivity implements PostAdapter.PostAdapterCallback,
        SwipeRefreshLayout.OnRefreshListener {

    private PostAdapter adapter;

    @BindView(R.id.empty_view)
    View emptyView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ButterKnife.bind(this);
        adapter = new PostAdapter(this, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onPostItemClick(PostModel postModel) {
        startActivity(PostDetailActivity.setupIntent(this, postModel));
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_POST_LIST, (ArrayList) adapter.getPostModelList());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState == null) return;
        adapter.setPostModelList(savedInstanceState.<PostModel>getParcelableArrayList(KEY_POST_LIST));
    }
}
