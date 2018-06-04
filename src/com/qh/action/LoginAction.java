package com.qh.action;

import com.qh.model.base.ErrorIp;
import com.qh.model.base.LoginUser;
import com.qh.model.base.OnlineUser;
import com.qh.service.LoginService;
import com.qh.service.base.ErrorIpService;
import com.util.Constants;
import com.util.CtxUtil;
import com.util.DateUtil;
import com.util.StringUtil;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

/**
 * 系统登录Action
 * @创建人 			王云川
 * @创建日期 		2017-09-25
 * @修改人 			王云川
 * @修改日期 		2017-09-25
 * @版本号 			1.0.0
 * @版权所有 		XX科技
 */
@Results({
        @Result(name = "login", type = "dispatcher", location = "/WEB-INF/jsp/login.jsp"),
      })
public class LoginAction extends BaseAction {

    @Autowired
    private LoginService loginService;
    @Autowired
    private ErrorIpService errorIpService;

    private String userName;//用户名
    private String password;//密码
    private String code;//验证码
    private Byte type;//登录类型1，PC，2移动端
    private Integer errarCode = 0;

    /**
     * PC版首页
     * @return
     */
    @Action("/login")
    public String index(){
//        System.out.println(ServletActionContext.getRequest().getSession().getId());
  //      System.out.println("login......");
        LoginUser loginUser= CtxUtil.getLoginUser();
        if(loginUser != null) {//如果已经登录则跳转到主页
            try {
                res.sendRedirect(Constants.PROJECT_ROOT + "/pc/home.action");
            } catch (IOException e) {
                return "login";
            }
            return NONE;
        }
        return "login";
    }


    /**
     * PC用户登录
     */
    @Action("/userLogin")
    public void userLogin(){
        Integer errorCountViewCode = 3;// 密码错误几次显示验证码
        Integer errorCountIp = 5;//输错10次之后ip禁用十分钟
//        Employee employee = new Employee();
//        employee.setPassword("123");
//        employee.setPhone("14787894216");
//        employee.setName("管理员");
//        loginService.getBaseDao().save(employee);

        //获取验证码
        String valCode= (String) req.getSession().getAttribute(Constants.POST_VALIDATE_CODE);
        //判断验证码是否需要验证
        if(req.getSession().getAttribute("viewCode")==null)
            req.getSession().setAttribute("viewCode",0);
        if("1".equals(req.getSession().getAttribute("viewCode").toString())) {
            if (code == null) {//验证码不能等于空
                writerJSON(REQUEST_OTHER_ERROR, "验证码不能为空");
                return;
            }
            //判断验证码是否正确
            if (!StringUtil.isNull(valCode) && !valCode.toLowerCase().equals(code.trim().toLowerCase())) {
                writerJSON(REQUEST_OTHER_ERROR, "请填写正确的验证码");
                return;
            }
        }
        String ip = req.getSession().getAttribute("_ip") != null ? req.getSession().getAttribute("_ip").toString() : req.getRemoteAddr();
        ErrorIp error = errorIpService.getObjectByIpDate(ip,DateUtil.format(new Date(),"yyyy-MM-dd"));
        Date date = new Date();
        if(error!=null&&error.getLastReq().getTime()>date.getTime()){
            writerJSON(REQUEST_ERROR_CODE, "输错次数过多，请稍后再试！");
            return;
        }
        //用户登录
        LoginUser loginUser=loginService.userLogin(userName,password,type);
        if(loginUser == null){//验证用户明密码是否正确
            // 获取登录失败次数
            if (req.getSession().getAttribute("errorCount") == null)
                req.getSession().setAttribute("errorCount", 1);
            int errorCount = Integer.parseInt(req.getSession().getAttribute("errorCount").toString());

            if(errorCount>errorCountIp){
                ErrorIp errorIp = new ErrorIp();
                errorIp.setIp(ip);
                errorIp.setLogin(DateUtil.format(new Date(),"yyyy-MM-dd"));
                errorIp.setLastReq(DateUtil.getAjustMin(new Date(),10));
                errorIpService.save(errorIp);
            }
            int err = errorCount+1;
            req.getSession().setAttribute("errorCount",err);
            //输错3次之后输入验证码
            if(errorCount>errorCountViewCode){
                req.getSession().setAttribute("viewCode",1);
                req.setAttribute("viewCode",1);
                writerJSON(REQUEST_VIEW_CODE,"用户名或则密码错误");
                return;
            }
            writerJSON(REQUEST_ERROR,"用户名或则密码错误");
            return;
        }
        req.getSession().setAttribute(Constants.LOGIN_USER_SESSION_NAME,loginUser);
        //写入登录日志
        loginService.saveLog(type,req.getSession().getId(),loginUser.getEmployee().getId(),req.getRemoteHost());

        //登录成功
        writerJSON(REQUEST_SUCCEED,"登录成功，系统正在跳转");
        req.getSession().setAttribute("viewCode",0);
        req.setAttribute("viewCode",0);
        //登录成功后如果存在错误记录则删除
        if(error!=null){
            errorIpService.delete(error);
        }
        return;
    }


    /**
     * 用户注销
     */
    @Action("/exit")
    public void exit(){
        if (null != getLoginUser()) {
            HttpSession session = req.getSession();
            OnlineUser ou = loginService.getObjectBySid(session.getId());
            //用户下线
            loginService.userLonger(ou);
            System.out.println("=================>用户注销");
            session.removeAttribute(Constants.LOGIN_USER_SESSION_NAME);
        }
        //注销成功
        writerJSON(REQUEST_SUCCEED,"注销成功，系统正在跳转");

    }


    /**
     * 获取验证码
     */
    @Action("/getCode")
    public void getCode() {
        int maxCount = 20;
        int viewCount = 0;
        res.setContentType("image/jpeg");
        res.setHeader("Pragma", "No-cache");
        res.setHeader("Cache-Control", "no-cache");
        res.setDateHeader("Expiresponse", 0L);

        int width = 90;
        int height = 26;
        int strLength = 4;
        BufferedImage image = new BufferedImage(width, height, 1);

        Graphics g = image.getGraphics();
        Random random = new Random();

        // 设置背景颜色 179.204.255
        g.setColor(new Color(220, 240, 229));
        g.fillRect(0, 0, width, height);

        // g.drawRect(0,0,width,height);
        g.setFont(new Font("Dialog", Font.ITALIC, 24));
        // 生成随机数
        String sRand = "";
        String str = "23456789abcdefghjkmnpqrstuvwxyABCDEFGHJKLMNPQRSTUVWXY23456789";
        int rr, gg, bb;
        rr = random.nextInt(180);
        gg = random.nextInt(50);
        bb = random.nextInt(180);
        Integer cc[] = new Integer[]{0xB30086, 0x8600B3, 0x2D00B3, 0xB3002D, 0x2E62FF, 0x00B386, 0xB32D00, 0x00B32D, 0xB38600,
                0xFF0000};
        g.setColor(new Color(cc[random.nextInt(cc.length)]));
        int si = 0;
        for (int xx = 5; si < strLength; ++si) {
            String rand = String.valueOf(str.charAt(random.nextInt(str.length())));
            sRand = sRand + rand;

            g.drawString(rand, xx, height - 2 - random.nextInt(height / 3));
            // 生成干扰线
            // if(si%2 == 0)
            // g.drawLine(0, random.nextInt(height*3),
            // random.nextInt(width), random.nextInt(height));

            xx += width / strLength - strLength / 2 + 2;
        }

        float lineWidth = 2.0f;
        ((Graphics2D) g).setStroke(new BasicStroke(lineWidth));
        g.drawLine(random.nextInt(width / 3), random.nextInt(height), random.nextInt(width + 5), random.nextInt(height)
                - random.nextInt(1));
        g.drawLine(random.nextInt(width / 2), random.nextInt(height), random.nextInt(width), random.nextInt(height)
                - random.nextInt(1));
        // g.drawLine(random.nextInt(width/2), random.nextInt(height),
        // random.nextInt(width), random.nextInt(height)-random.nextInt(1));

        req.getSession().setAttribute(Constants.POST_VALIDATE_CODE, sRand);
        req.getSession().setAttribute(Constants.POST_VALIDATE_CODE, sRand);

        g.dispose();
        try {
            ImageIO.write(image, "gif", res.getOutputStream());
        } catch (Exception e) {
            System.out.println("验证码生产错误！");
            e.printStackTrace();
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Integer getErrarCode() {
        return errarCode;
    }

    public void setErrarCode(Integer errarCode) {
        this.errarCode = errarCode;
    }
}
