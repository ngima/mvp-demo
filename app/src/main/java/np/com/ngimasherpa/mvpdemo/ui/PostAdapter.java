package np.com.ngimasherpa.mvpdemo.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import np.com.ngimasherpa.mvpdemo.R;
import np.com.ngimasherpa.mvpdemo.model.PostModel;
import np.com.ngimasherpa.mvpdemo.utils.Contants;

/**
 * Created by ngima on 4/28/17.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostModel> postModelList;
    private Context context;
    private PostAdapterCallback callback;

    public PostAdapter(Context context, PostAdapterCallback callback) {
        this.postModelList = new ArrayList<>();
        this.context = context;
        this.callback = callback;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        holder.textTitle.setText(postModelList.get(position).getTitle());
        holder.textBody.setText(postModelList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postModelList.size();
    }

    public List<PostModel> getPostModelList() {
        return postModelList;
    }

    public void setPostModelList(List<PostModel> postModelList) {
        this.postModelList = postModelList;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_title)
        TextView textTitle;

        @BindView(R.id.text_body)
        TextView textBody;

        public PostViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onPostItemClick(postModelList.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface PostAdapterCallback {
        void onPostItemClick(PostModel postModel);
    }
}
