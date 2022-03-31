package com.gzeinnumer.mypagingstyle.base;

public interface BaseDebugCallback<T> {
    void onClick(int type, int position, T data);
}
