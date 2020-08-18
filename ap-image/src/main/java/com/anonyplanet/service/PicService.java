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

/**
 * @author 邓启航
 */
@Service
public class PicService {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 图片上传
     */
    public PicUploadResult picUpload(MultipartFile pic) {

        PicUploadResult picUploadResult = new PicUploadResult();

        System.out.println(" = ");
        try {
            // 获取文件原名称
            String oName = pic.getOriginalFilename();
            // 截取后缀
            String extName = oName.substring(oName.lastIndexOf("."));
            // 后缀正则
            String regex = ".(png|gif|jpg|jpeg)$";

            if (extName != null && !(extName.matches(regex))) {
                // 后缀存在，并且不再图片返回之内
                throw new RuntimeException("图片后缀名不合法");
            } else if (extName == null) {
                throw new RuntimeException("图片没有后缀");
            }
            //  为当前图片文件 生成公用路径
            String path = "/" + UploadUtil.getUploadPath(oName, "AnonyPlanet") + "/";
            //  静态资源根目录
//            String rootDir = "D:/img";
            String rootDir = "/www/wwwroot/www.anonyplanet.com/img";
            //  多级路径前缀
            String newFileName = UUID.randomUUID().toString() + extName;
            //  path+newFileName
            File dir = new File(rootDir + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 二进制输出到文件rootDir+path+newFileName
            File imgfile = new File(rootDir + path + newFileName);
            pic.transferTo(imgfile);

            // 裁剪  缩放
            BufferedImage bi = null;
            try {
                bi = ImageIO.read(imgfile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int width = bi.getWidth();
            int height = bi.getHeight();
            if (width > height) {
                int tmp = height;
                height = width;
                width = tmp;
            }
            File smallImage = new File(rootDir + path + "smallImage-" + newFileName);
            Thumbnails.of(imgfile)
                    .size(150, 150).sourceRegion(Positions.CENTER, width, width)
                    .toFile(smallImage);
            //    生成一个图片ID
            String picId = UUID.randomUUID().toString();

            String imgFull = "http://121.41.231.81/img/" + path + newFileName;
            String imgSmall = "http://121.41.231.81/img/" + path + "smallImage-" + newFileName;

            picUploadResult.setImgUrlFull(imgFull);
            picUploadResult.setImgUrlSmall(imgSmall);

            String imgUrl = objectMapper.writeValueAsString(picUploadResult);
            System.out.println(imgUrl);
            return picUploadResult;

        } catch (Exception e) {
            e.printStackTrace();
            picUploadResult.setError(1);
            return picUploadResult;
        }
    }
}