package com.anonyplanet.controller;

import com.anonyplanet.service.PicService;
import com.anonyplanet.service.PicUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 邓启航
 */
@RestController
public class PicController {

    @Autowired
    private PicService picService;

    /**
     * 图片上传
     */
    @RequestMapping("/upload")
    public PicUploadResult picUpload(MultipartFile pic) {
        return picService.picUpload(pic);
    }

}
