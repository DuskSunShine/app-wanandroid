/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.scy.wanandroid.base;



import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends AbsView> implements AbsPresenter<V> {

    protected V mView;

    private CompositeDisposable disposable;

    @Override
    public void attachView(V baseView) {
        this.mView=baseView;
        startInteractive();
    }

    @Override
    public void detachView() {
        this.mView=null;
        if (disposable!=null) {
            disposable.dispose();
            disposable.clear();
            disposable = null;
        }
    }

    @Override
    public boolean isAttachedView() {
        return this.mView != null;
    }

    @Override
    public void addRxBindingSubscribe(Disposable disposable) {
        addSubscribe(disposable);
    }

    @Override
    public void disposeSubscribe() {
        if (disposable!=null&&!disposable.isDisposed()) {
            disposable.dispose();
            disposable.clear();
            disposable = null;
        }
    }

    protected void addSubscribe(Disposable disposable) {
        if (this.disposable == null) {
            this.disposable = new CompositeDisposable();
        }
        this.disposable.add(disposable);
    }

    /**
     * 已经建立关系后就可以开始利用presenter
     * 进行数据和view的交互。
     */
    protected abstract void startInteractive();
}
