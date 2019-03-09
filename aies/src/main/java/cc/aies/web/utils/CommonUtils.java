package cc.aies.web.utils;

import cc.aies.web.beans.PermUser;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Auther: qiuzp
 * @Date: 2018/7/28 23:37
 * @Description:
 */
public class CommonUtils {
    /**
     * 获取来访者ip
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {//下面的属于固定格式
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /***
     * 生成32位uuid
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    //
    public static String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }


    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request, String fileDir) throws IOException {
        String path = "/aiesfile/upload/"+fileDir;
        //为了避免文件名重复，使用UUID
        String uuidName = CommonUtils.getUUID();
        String fileName = uuidName + "-" + file.getOriginalFilename();
        File dir = new File(path, fileName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        file.transferTo(dir);
        //上传成功后返回
        return  "static/upload/"+fileDir+"/"+ fileName;

    }

    /**
     * 获取异常详情信息;
     * @param ex
     * @return
     */
    public static String getExceptionAllinformation(Exception ex){
        String sOut = "";
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            sOut += "\tat " + s + "\r\n";
        }
        return sOut;
    }
    /**
     * 生成临时用户
     */
    public static List<PermUser> addUser(String judgeTeacherNum, String finalTeacherNum, String finalCuttingNum, String cuttingMergeId){
        List<PermUser> permUserList=new ArrayList<PermUser>();
        for(Integer i=0;i<Integer.parseInt(judgeTeacherNum);i++)
        {
            PermUser permUser=new PermUser();
            permUser.setUserPermId(getUUID());
            permUser.setIsTemp("临时阅卷老师");
            permUser.setStatus("0");
            String username=createChar();
            //账号密码一致
            permUser.setUserName(username);
            permUser.setUserAccount("temp"+username);
            permUser.setUserPass(MD5Util.md5Encode(username));
            permUser.setUserWorkid(cuttingMergeId);
            permUserList.add(permUser);
        }
        //生成终审老师
        for(Integer i=0;i<Integer.parseInt(finalTeacherNum);i++)
        {
            PermUser permUser=new PermUser();
            permUser.setUserPermId(getUUID());
            permUser.setIsTemp("临时终审老师");
            permUser.setStatus("0");
            String username=createChar();
            //账号密码一致
            permUser.setUserName(username);
            permUser.setUserAccount("temp"+username);
            permUser.setUserPass(MD5Util.md5Encode(username));
            permUser.setUserWorkid(cuttingMergeId);
            permUserList.add(permUser);
        }
        //生成块管理员
        for(Integer i=0;i<Integer.parseInt(finalCuttingNum);i++)
        {
            PermUser permUser=new PermUser();
            permUser.setUserPermId(getUUID());
            permUser.setIsTemp("临时块管理员");
            permUser.setStatus("0");
            String username=createChar();
            //账号密码一致
            permUser.setUserName(username);
            permUser.setUserAccount("temp"+username);
            permUser.setUserPass(MD5Util.md5Encode(username));
            permUser.setUserWorkid(cuttingMergeId);
            permUserList.add(permUser);
        }
        return permUserList;
    }
    /**
     * 随机6位生成字符
     */
    public static String createChar(){
        String code=null;
        String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer();
        for (int j = 0; j < 6; j++)
        {
            flag.append(sources.charAt(rand.nextInt(9)) + "");
        }
        code=flag.toString();
        return code;
    }
}

