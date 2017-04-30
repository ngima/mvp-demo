package np.com.ngimasherpa.mvpdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.ngimasherpa.mvpdemo.R;
import np.com.ngimasherpa.mvpdemo.model.PostModel;

public class PostDetailActivity extends AppCompatActivity {

    private static final String KEY_TITLE = "key_title";
    private static final String KEY_BODY = "key_body";
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_body)
    TextView textBody;

    public static Intent setupIntent(Context context, PostModel postModel) {
        Intent intent = new Intent(context, PostDetailActivity.class);

        intent.putExtra(KEY_TITLE, postModel.getTitle());
        intent.putExtra(KEY_BODY, postModel.getBody());

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            textTitle.setText(extras.getString(KEY_TITLE));
            textBody.setText(extras.getString(KEY_BODY));
        }
    }
}
