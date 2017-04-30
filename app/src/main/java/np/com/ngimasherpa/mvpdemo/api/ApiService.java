package np.com.ngimasherpa.mvpdemo.api;

import java.util.List;

import io.reactivex.Observable;
import np.com.ngimasherpa.mvpdemo.model.PostModel;
import np.com.ngimasherpa.mvpdemo.utils.Contants;
import retrofit2.http.GET;

/**
 * Created by ngima on 4/27/17.
 */

public interface ApiService {

    @GET(Contants.Path.POSTS)
    Observable<List<PostModel>> getPostList();
}