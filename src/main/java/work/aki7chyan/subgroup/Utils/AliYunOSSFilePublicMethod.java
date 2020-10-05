package work.aki7chyan.subgroup.Utils;

import org.springframework.web.multipart.MultipartFile;
import work.aki7chyan.subgroup.Config.OSSClientConfiguration;

import java.io.File;

/**
 * 阿里云OSS存储调用方法集中管理工具类。
 */
public class AliYunOSSFilePublicMethod {

    /**
     * 阿里云OSS存储上传MultipartFile类型文件。
     */
    public static String uploadFileToOSSServer(MultipartFile file, String folder) {
        try{
            AliYunOSSClientUtil.createBucketName(AliYunOSSClientUtil.getOSSClient(), OSSClientConfiguration.BACKET_NAME);
        }catch (Exception e){
            return null;
        }
        String url = AliYunOSSClientUtil.uploadObject2OSS(AliYunOSSClientUtil.getOSSClient(), file, OSSClientConfiguration.BACKET_NAME, OSSClientConfiguration.FOLDER + folder);
        return url;
    }

    /**
     * 阿里云OSS存储上传File类型文件。
     */
    public static String uploadFileToOSSServer(File file, String folder) {
        try{
            AliYunOSSClientUtil.createBucketName(AliYunOSSClientUtil.getOSSClient(), OSSClientConfiguration.BACKET_NAME);
        }catch (Exception e){
            return null;
        }
        String url = AliYunOSSClientUtil.uploadObject2OSS(AliYunOSSClientUtil.getOSSClient(), file, OSSClientConfiguration.BACKET_NAME, OSSClientConfiguration.FOLDER + folder);
        return url;
    }

    /**
     * 阿里云OSS存储删除文件-更新文件时调用。
     */
    public static void deleteFileInOSSServer(String folder, String fileName) {
        try{
            AliYunOSSClientUtil.createBucketName(AliYunOSSClientUtil.getOSSClient(), OSSClientConfiguration.BACKET_NAME);
        }catch (Exception e){
            return ;
        }
        AliYunOSSClientUtil.deleteFile(AliYunOSSClientUtil.getOSSClient(), OSSClientConfiguration.BACKET_NAME, OSSClientConfiguration.FOLDER + folder, fileName);
        return ;
    }
}
