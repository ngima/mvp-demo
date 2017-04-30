package np.com.ngimasherpa.mvpdemo.api;

import android.accounts.NetworkErrorException;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import np.com.ngimasherpa.mvpdemo.R;
import np.com.ngimasherpa.mvpdemo.model.PostModel;
import np.com.ngimasherpa.mvpdemo.utils.Utils;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ngima on 4/27/17.
 */

public class ApiRequest {
    private static final String TAG = "ApiRequest";
    private static final String BASE_URL = "https://ngima.github.io/mvp-demo/";
    private static ApiRequest instance;
    private Retrofit retrofit;
    private ApiService apiService;
    Context context;


    public static ApiRequest getInstance(Context context) {
        if (instance == null) {
            instance = new ApiRequest();
            instance.retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS)
                            .build())
                    .build();
            instance.apiService = instance.retrofit.create(ApiService.class);
            instance.context = context;
        }
        return instance;
    }

    private ApiRequest() {
    }

    public Observable<List<PostModel>> getPostList() {
        if (Utils.isThereInternetConnection(context)) {
            return apiService.getPostList();
        }
        return Observable.error(new NetworkErrorException(context.getString(R.string.error_network_connection)));
    }
}
