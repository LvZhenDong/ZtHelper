package com.egr.drillinghelper.utils.video;

/**
 * author 边凌
 * date 2017/6/6 16:29
 * desc $
 */

interface VideoCompressor {
    void compress(final VideoInfo videoInfo, final String resultPath, final OnVideoCompressListener onVideoCompressListener);
}
