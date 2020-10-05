package work.aki7chyan.subgroup.Config;

/**
 * OSS存储指定子路径
 */
public class OSSFolderConst {

    /**
     * 自定义文件夹
     */
    private static String folder_customize = "Customize/";

    /**
     * 用户头像文件夹
     */
    public static final String folder_member_headPic = "MemberAvater/";

    /**
     * 获取自定义文件夹名
     * @return 文件夹名
     */
    public String getFolder_customize() {
        return folder_customize;
    }

    /**
     * 设置自定义文件夹
     * @param folder 名称
     */
    public void setFolder_customize(String folder){
        this.folder_customize = folder;
    }
}
