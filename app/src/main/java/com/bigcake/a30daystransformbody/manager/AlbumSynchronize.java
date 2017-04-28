package com.bigcake.a30daystransformbody.manager;

/**
 * Created by Big Cake on 4/27/2017
 */

public class AlbumSynchronize {
    private static AlbumSynchronize mInstance;
    public static synchronized AlbumSynchronize getInstance() {
        if (mInstance == null)
            mInstance = new AlbumSynchronize();
        return mInstance;
    }
    private AlbumSynchronize() {}


}
