package com.gzeinnumer.mypagingstyle.base;

public interface BaseCallBack<T> {
    void onClick(int type, int position, T data);
}
