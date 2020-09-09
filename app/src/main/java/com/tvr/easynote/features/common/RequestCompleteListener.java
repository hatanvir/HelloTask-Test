package com.tvr.easynote.features.common;

public interface RequestCompleteListener<T> {
    void OnSuccessListener(T data);
    void OnFailedListener(String error);
}
