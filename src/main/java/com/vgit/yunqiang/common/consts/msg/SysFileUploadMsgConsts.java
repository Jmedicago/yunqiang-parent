package com.vgit.yunqiang.common.consts.msg;

import com.vgit.yunqiang.common.consts.ConstName;
import com.vgit.yunqiang.common.consts.ICodes;

public interface SysFileUploadMsgConsts extends ICodes {

    public static final int BASE_CODE = 50000;

    @ConstName("文件上传成功")
    public static final int FILE_UPLOAD_SUCCESS = 0 + BASE_CODE;

    @ConstName("文件上传失败")
    public static final int FILE_UPLOAD_ERROR = 1 + BASE_CODE;

    @ConstName("文件上传异常")
    public static final int FILE_UPLOAD_EXCEPTION = 2 + BASE_CODE;

}
