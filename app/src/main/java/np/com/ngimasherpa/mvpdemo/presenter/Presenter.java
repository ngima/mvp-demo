package np.com.ngimasherpa.mvpdemo.presenter;

import np.com.ngimasherpa.mvpdemo.view.MvpView;

/**
 * Created by ngima on 4/28/17.
 */

public interface Presenter<VIEW extends MvpView> {

    void attachView(VIEW view);

    void initialize();
    
    void dispose();
}
