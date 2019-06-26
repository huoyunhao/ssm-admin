package cc.aies.web.controller;

import cc.aies.web.utils.Msg;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Auther: qiuzp
 * @Date: 18-9-27 下午4:57
 * @Description:
 */
@Controller
public class UtilsController {

    /**
     * 解决静态资源图片跨域问题
     * @param request
     * @param response
     * @param path
     */
    @RequestMapping(value = "/img",method = RequestMethod.GET)
    public void getAuthCode(HttpServletRequest request, HttpServletResponse response, @RequestParam("path") String path) {
        response.setDateHeader("Expires", 0);
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + "ff" + "\"");
        OutputStream out;
        try {
            out = response.getOutputStream();
            String path1 = new File("/aiesfile/", path.replace("static","")).getCanonicalPath();
            byte[] buf = new byte[1024];
            InputStream in = new FileInputStream(new File(path1));
            int tempbyte;
            while ((tempbyte = in.read(buf)) != -1) {
                out.write(buf);
            }
            out.flush();
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }


}
