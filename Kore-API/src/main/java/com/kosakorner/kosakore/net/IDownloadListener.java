package com.kosakorner.kosakore.net;

import java.io.File;

public interface IDownloadListener {

    void onSuccess(File file);

    void onFailure(File file);

}
