package com.anonyplanet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Service
public class PicService {
    @Autowired
    private ObjectMapper objectMapper;

    PicUploadResult picUploadResult = new PicUploadResult();

    /**
     * 图片上传
     */
    public String picUpload(MultipartFile pic) {
        try {
            // 获取文件原名称
            String oName = pic.getOriginalFilename();
            // 截取后缀
            String extName = oName.substring(oName.lastIndexOf("."));
            String regex = ".(png|gif|jpg|jpeg)$";//包含了图片后缀正则
            if (extName != null && !(extName.matches(regex))) {
                // 后缀存在，并且不再图片返回之内
                throw new RuntimeException("图片后缀名不合法");
            } else if (extName == null) {
                throw new RuntimeException("图片没有后缀");
            }
            //  为当前图片文件 生成公用路径
            String path = "/" + UploadUtil.getUploadPath(oName, "AnonyPlanet") + "/";
            //  静态资源根目录
            String rootDir = "D:/img";
            //  多级路径前缀
            String newFileName = UUID.randomUUID().toString() + extName;
            //  path+newFileName
            File dir = new File(rootDir + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            //  二进制输出到文件rootDir+path+newFileName
            File Imgfile = new File(rootDir + path + newFileName);
            pic.transferTo(Imgfile);

            //    裁剪  缩放
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(Imgfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int width = bi.getWidth(); // 像素
            int height = bi.getHeight(); // 像素
            if (width > height) {
                int tmp = height;
                height = width;
                width = tmp;
            }
            File smallImage = new File(rootDir + path + "smallImage-" + newFileName);
            Thumbnails.of(Imgfile)
                    .size(150, 150).sourceRegion(Positions.CENTER, width, width)
                    .toFile(smallImage);
            //    生成一个图片ID
            String picId = UUID.randomUUID().toString();
            // todo 根据存储的路径，生成URL地址
            String imgFull = "http://image.anonyplanet.com/" + path + newFileName;
            String imgSmall = "http://image.anonyplanet.com/" + path + "smallImage-" + newFileName;

            picUploadResult.setImgUrlFull(imgFull);
            picUploadResult.setImgUrlSmall(imgSmall);

            String imgUrl = objectMapper.writeValueAsString(picUploadResult);
            System.out.println(imgUrl);
            return imgUrl;

        } catch (Exception e) {
            e.printStackTrace();
            picUploadResult.setError(1);
            return "";
        }
    }
}