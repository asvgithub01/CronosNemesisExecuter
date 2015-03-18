package com.gigigo.asv.akaawait;

/**
 * Created by Alberto on 17/03/2015.
 */
public interface Event4Raise<T> {
    void onEvent(T ta);
    void onFail(T ta);
    void onError(T ta);
    void onPreXeChute(T tas);//asv trollingtime
}


