package work.aki7chyan.subgroup.Utils;

import ch.qos.logback.core.util.FileUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import work.aki7chyan.subgroup.Config.OSSClientConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 阿里云OSS文件存储工具类。
 */
public class AliYunOSSClientUtil {
    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    /**
     * 阿里云OSS API的外网域名
     */
    private static String ENDPOINT;
    /**
     * 阿里云OSS API的密钥Access Key ID
     */
    private static String ACCESS_KEY_ID;
    /**
     *阿里云OSS API的密钥Access Key Secret
     */
    private static String ACCESS_KEY_SECRET;
//    /**
//     * 阿里云OSS API的bucket名称
//     */
//    private static String BACKET_NAME;
//    /**
//     * 阿里云OSS API的主文件夹名称
//     */
//    private static String FOLDER;
    /**
     * 阿里云OSS存储访问头
     * */
    private static final String OSSURL_HEADER = "https://ossfilesplaus.oss-cn-beijing.aliyuncs.com/";

    //初始化属性
    static{
        ENDPOINT = OSSClientConfiguration.ENDPOINT;
        ACCESS_KEY_ID = OSSClientConfiguration.ACCESS_KEY_ID;
        ACCESS_KEY_SECRET = OSSClientConfiguration.ACCESS_KEY_SECRET;
//        BACKET_NAME = OSSClientConstants.BACKET_NAME;
//        FOLDER = OSSClientConstants.FOLDER;
    }
    /**
     * 获得阿里云OSS客户端对象
     * @return ossClient
     */
    public static OSSClient getOSSClient(){
        return new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
    }
    /**
     * 创建存储空间
     * @param ossClient OSS连接
     * @param bucketName 存储空间
     * @return
     */
    public static String createBucketName(OSSClient ossClient,String bucketName){
        //存储空间
        final String bucketNames = bucketName;
        if(!ossClient.doesBucketExist(bucketName)){
            //创建存储空间
            Bucket bucket=ossClient.createBucket(bucketName);
            logger.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    /**
     * 删除存储空间buckName
     * @param ossClient  oss对象
     * @param bucketName  存储空间
     */
    public static  void deleteBucket(OSSClient ossClient, String bucketName){
        ossClient.deleteBucket(bucketName);
        logger.info("删除" + bucketName + "Bucket成功");
    }
    /**
     * 根据key删除OSS服务器上的文件
     * @param ossClient  oss连接
     * @param bucketName  存储空间
     * @param folder  模拟文件夹名 如"qj_nanjing/"
     * @param key Bucket下的文件的路径名+文件名 如："upload/cake.jpg"
     */
    public static void deleteFile(OSSClient ossClient, String bucketName, String folder, String key){
        ossClient.deleteObject(bucketName, folder + key);
        logger.info("删除" + bucketName + "下的文件" + folder + key + "成功");
    }
    /**
     * 上传图片至OSS
     * @param ossClient  oss连接
     * @param multipartFile 上传文件
     * @param bucketName  存储空间
     * @param folder OSS存放文件夹
     * @return String 返回访问链接
     * */
    public static  String uploadObject2OSS(OSSClient ossClient, MultipartFile multipartFile, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = multipartFile.getInputStream();
            //文件名
            LocalDateTime localDateTime = LocalDateTime.now();
            String time_str = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = time_str + multipartFile.getOriginalFilename();
            //文件大小
            Long fileSize = multipartFile.getSize();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            System.out.println(putResult.toString());
            resultStr = OSSURL_HEADER + folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }

    /**
     * 上传图片至OSS（重载）
     * @param ossClient  oss连接
     * @param file 上传文件
     * @param bucketName  存储空间
     * @param folder OSS存放文件夹
     * @return String 返回访问链接
     * */
    public static String uploadObject2OSS(OSSClient ossClient, File file, String bucketName, String folder) {
        String resultStr = null;
        try {
            //以输入流的形式上传文件
            InputStream is = new FileInputStream(file);
            //文件名
            String fileName = file.getName();
            //文件大小
            Long fileSize = file.length();
            //创建上传Object的Metadata
            ObjectMetadata metadata = new ObjectMetadata();
            //上传的文件的长度
            metadata.setContentLength(is.available());
            //指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            //指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            //指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            //文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            //如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            //指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            //上传文件   (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(bucketName, folder + fileName, is, metadata);
            //解析结果
            System.out.println(putResult.toString());
            resultStr = OSSURL_HEADER + folder + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        return resultStr;
    }
    /**
     * 流式下载文件
     * @param ossClient
     * @param
     * @param folder
     */
    public static InputStream downloadFile(OSSClient ossClient,String bucketName,String folder) {
        InputStream is = null;
        try {
            OSSObject ossObject = ossClient.getObject(bucketName, folder);
            is = ossObject.getObjectContent();
            return is;
        }catch (OSSException oe) {
            return is;
        }catch(Exception e) {
            logger.error("下载文件失败"+e.getMessage());
            return is;
        }catch (Throwable e) {
            e.printStackTrace();
            return is;
        }
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    public static String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        switch(fileExtension) {
            case ".bmp":
                return "image/bmp";
            case ".gif":
                return "image/gif";
            case ".jpeg":
            case ".jpg":
            case ".png":
                return "image/jpeg";
            case ".html" :
                return "text/html";
            case ".txt":
                return "text/plain";
            case ".vsd":
                return "application/vnd.visio";
            case ".ppt":
            case ".pptx":
                return "application/vnd.ms-powerpoint";
            case ".doc":
            case ".docx":
                return "application/msword";
            case ".xml":
                return "text/xml";
            case ".mp4":
                return "video/mp4";
            case ".awf":
                return "application/vnd.adobe.workflow";
            case ".wav":
                return "audio/wav";
            case ".zip":
                return "application/zip";
            case ".pdf":
                return "application/pdf";
            case ".ogg":
                return "application/ogg";
            case ".js":
                return "application/javascript";
            default:
                return "multipart/form-data";
        }
    }

    /**
     * 获得url链接(临时文件夹)
     * @param key
     * @return
     */
    public static String getUrl(OSSClient ossClient, String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
        // 生成URL
        URL url = ossClient.generatePresignedUrl(OSSClientConfiguration.BACKET_NAME, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }
}
