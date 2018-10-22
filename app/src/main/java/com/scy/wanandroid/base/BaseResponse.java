package com.scy.wanandroid.base;

/**
 * Created by SCY on 2018/10/22 at 15:17.
 */
public class BaseResponse<D> {
    /**
     * errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
     * errorCode = -1001 代表登录失效，需要重新登录。
     */
    public static final int SUCCESS = 0;
    private D data;
    private int errorCode;
    private String errorMsg;

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
