package np.com.ngimasherpa.mvpdemo.view;

/**
 * Created by ngima on 4/26/17.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void showError(String errorMessage);
}
