package com.kosakorner.kosakore.net;

import java.io.File;

public interface IDownloadListener {

    public void onSuccess(File file);

    public void onFailure(File file);

}
