package com.example.demo.controller;

import com.example.demo.dao.Publish_HomeworkDao;
import com.example.demo.dao.Submit_HomeworkDao;
import com.example.demo.model.*;
import com.example.demo.repository.Create_ClassRepository;
import com.example.demo.repository.Join_ClassRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class controller {
    @Resource
    UserRepository userRepository;

    @Resource
    Create_ClassRepository create_classRepository;

    @Resource
    Join_ClassRepository join_classRepository;

    @Resource
    Submit_HomeworkDao submit_homeworkDao;

    @Resource
    Publish_HomeworkDao publish_homeworkDao;

    //登录界面
    @RequestMapping(value = "/login")
    public String login(){
        return "/login";
    }

    //登陆检验账号密码
    @RequestMapping(value = "/gologin")
    public String gologin(Model model,
                          String account,
                          String password,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse){

        User user = userRepository.findById(account).orElse(null);
        if(user == null){//账号未注册
            return "/register";
        }
        else if(user.getPassword().equals(password)){//用户输入的密码与数据库密码一致
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user",user);
            model.addAttribute("user",user);
            return "/index";
        }
        else {//密码错误
            return "login";
        }
    }

    //注册界面
    @RequestMapping(value = "/register")
    public String register(){
        return "/register";
    }

    //进行注册
    @RequestMapping(value = "/goregister")
    public String goregister(User user,
                             HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse){
        //检查账号是否注册
        User myuser = userRepository.findById(user.getUser_id()).orElse(null);
        if(myuser == null){
            userRepository.save(user);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user",user);
            return "/create_class";
        }
        else {//账号注册过,回到登录界面
            return "/login";
        }
    }

    //创建班级界面
    @RequestMapping(value = "/create_class")
    public String create_class(HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse){
        //没有登录,跳转到登录界面
        if(httpServletRequest.getSession().getAttribute("user")==null){
            return "/login";
        }
        else {
            return "/create_class";
        }
    }

    //去创建班级
    @RequestMapping(value = "/gocreate_class")
    public String gocreate_class(Create_Class create_class,HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }
        else {
            //查看班级编号是否重复
            if(create_classRepository.findById(create_class.getClass_id()).orElse(null)==null){
                User user = (User) session.getAttribute("user");
                System.out.println(user.getUser_id());
                create_class.setUser_id(user.getUser_id());
                create_class.setClass_sum(0);//班级人数,不包括自己
                create_classRepository.save(create_class);//创建班级
                return "/success";//刷新主页
            }
            else {
                return "/false";
            }
        }
    }

    //查看创建班级界面
    @RequestMapping(value = "/check_create_class")
    public String check_create_class(Model model,Create_Class create_class,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        } else {
            User user = (User) session.getAttribute("user");
            List<Create_Class> create_classes = create_classRepository.findAll();
            List<Create_Class> my_create_classes=new ArrayList();
            for(Create_Class cc : create_classes){
                if(cc.getUser_id().equals(user.getUser_id())){
                    my_create_classes.add(cc);
                }
            }
            model.addAttribute("users",my_create_classes);
            return "/check_create_class";
        }
    }

    //加入班级页面
    @RequestMapping(value = "/join_class")
    public String join_class(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else {
            return "/join_class";
        }
    }

    //加入班级操作
    @RequestMapping(value = "/gojoin_class")
    public String gojoin_class(String class_id,String class_password,HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else {
            Create_Class create_class = create_classRepository.findById(class_id).orElse(null);
            if(create_class==null){
                return "/false";
            }else {
                if(create_class.getClass_password().equals(class_password)){
                    User user = (User) session.getAttribute("user");
                    //还要判断是否已经加入了班级
                    List<Join_Class> join_classes = join_classRepository.findAll();
                    for(Join_Class jc : join_classes){
                        if(jc.getClass_id().equals(class_id)&&jc.getUser_id().equals(user.getUser_id())){
                            return "/false";
                        }
                    }

                    create_class.setClass_sum(create_class.getClass_sum() + 1);
                    Join_Class join_class = new Join_Class();
                    join_class.setClass_id(class_id);
                    join_class.setUser_id(user.getUser_id());
                    join_classRepository.save(join_class);
                    return "success";
                }else {
                    System.out.println("3");
                    return "/false";
                }
            }
        }
    }

    //查看加入班级页面
    @RequestMapping(value = "/check_join_class")
    public String check_join_class(Model model,String class_id,String class_password,HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
            User user = (User) session.getAttribute("user");
            List<Join_Class> joinClasses = join_classRepository.findAll();
            List<Create_Class> mycreate_classes = new ArrayList();
            List<User> userts = new ArrayList();
            for (Join_Class jc : joinClasses){
                if(jc.getUser_id().equals(user.getUser_id())){
                    Create_Class create_class = create_classRepository.findById(jc.getClass_id()).orElse(null);
                    mycreate_classes.add(create_class);
                    User usert = userRepository.findById(jc.getClass_id()).orElse(null);
                    userts.add(usert);
                }
            }
            model.addAttribute("mycreate_classes",mycreate_classes);
            model.addAttribute("userts",userts);
            return "/check_join_class";
        }
    }

    //班级详情界面
    @RequestMapping(value = "/detail_class")
    public String detail_class(Model model,String class_id,HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
            //学生列表
            List<User> users = new ArrayList();
            List<Join_Class> join_classes= join_classRepository.findAll();
            for(Join_Class jc : join_classes){
                if(jc.getClass_id().equals(class_id)){
                    users.add(userRepository.findById(jc.getUser_id()).orElse(null));
                }
            }

            //加入学生信息
            model.addAttribute("classname",create_classRepository.findById(class_id).orElse(null).getClass_name());
            model.addAttribute("users",users);
            model.addAttribute("class_id",class_id);
        }
        return "/detail_class";
    }

    //把学生踢出班级
    @RequestMapping(value = "/kickuser")
    public String kickuse(Model model,String user_id,
                          String class_id,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
            List<Join_Class> join_classes = join_classRepository.findAll();
            for(Join_Class jc : join_classes){
                if(jc.getUser_id().equals(user_id)&&jc.getClass_id().equals(class_id)){
                    Create_Class create_class = create_classRepository.findById(jc.getClass_id()).orElse(null);
                    create_class.setClass_sum(create_class.getClass_sum() - 1);
                    join_classRepository.delete(jc);
                    break;
                }
            }
            return "success";
        }
    }

    //自己退出班级
    @RequestMapping(value = "/quitclass")
    public String quitclass(Model model,
                          String class_id,
                          HttpServletRequest httpServletRequest,
                          HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
            List<Join_Class> join_classes = join_classRepository.findAll();
            User user = (User) session.getAttribute("user");
            for(Join_Class jc : join_classes){
                if(jc.getUser_id().equals(user.getUser_id())&&jc.getClass_id().equals(class_id)){
                    Create_Class create_class = create_classRepository.findById(jc.getClass_id()).orElse(null);
                    create_class.setClass_sum(create_class.getClass_sum() - 1);
                    join_classRepository.delete(jc);
                    break;
                }
            }
            return "success";
        }
    }


    //发布作业页面
    @RequestMapping(value = "/publishhomework")
    public String publishhomework(Model model,
                            String class_id,
                            String user_id,
                            HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
//            System.out.println("kaishi");
//            System.out.println(user_id);
//            System.out.println(class_id);
            model.addAttribute("class_id",class_id);
            model.addAttribute("user_id",user_id);
            return "/publish_homework";
        }

    }

    //进行发布作业
    @RequestMapping(value = "/gopublishhomework")
    public String gopublishhomework(Model model,
                                  Publish_Homework publish_homework,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if (session.getAttribute("user")==null){
            return "/login";
        }else{
//            System.out.println("开始");
//            System.out.println(publish_homework.getUser_id());
//            System.out.println(publish_homework.getPh_name());
//            System.out.println(publish_homework.getPh_content());
//            System.out.println(publish_homework.getClass_id());
//            System.out.println(publish_homework.getPh_file());
//            System.out.println(publish_homework.getRiqi());
            System.out.println("发布开始！");
//            publish_homework.setPh_id("1");
            publish_homeworkDao.publish_homework(publish_homework);
            System.out.println("发布结束！");
            return "/success";
        }
    }

    //根据班级查看发布的作业
    @RequestMapping(value = "/checkpublishhomework")
    public String checkpublishhomework(Model model,
                                    String class_id,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            List<Publish_Homework> publish_homeworks = publish_homeworkDao.findByclass_id(class_id);
            model.addAttribute("publish_homeworks",publish_homeworks);
            return "/check_publish_homework";
        }
    }

    //根据班级查看要发布的作业
    @RequestMapping(value = "/checkwillsubmithomework")
    public String checkwillsubmithomework(Model model,
                                       String class_id,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            User user = (User) session.getAttribute("user");
            List<Publish_Homework> publish_homeworks = publish_homeworkDao.findByclass_id(class_id);
            model.addAttribute("publish_homeworks",publish_homeworks);
            model.addAttribute("user",user);
            return "/check_willsubmit_homework";
        }
    }

    //根据班级查看发布的详细作业
    @RequestMapping(value = "/detailhomework")
    public String detailhomework(Model model,
                                          String ph_id,
                                          String user_id,
                                          HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            User user = (User) session.getAttribute("user");
            Publish_Homework publish_homework = publish_homeworkDao.findByph_id(ph_id);
            Submit_Homework submit_homework = submit_homeworkDao.findhomework(ph_id,user.getUser_id());
            if(submit_homework==null){
                submit_homework = new Submit_Homework();
            }
            model.addAttribute("publish_homework",publish_homework);
            model.addAttribute("submit_homework",submit_homework);
            model.addAttribute("user",user);
            return "detail_homework";
        }
    }

    //提交作业
    @RequestMapping(value = "/submithomework")
    public String submithomework(Model model,
                                 Submit_Homework submit_homework,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            //判断是否提交过
            User user = (User) session.getAttribute("user");
            Submit_Homework homework = submit_homeworkDao.findhomework(submit_homework.getPh_id(),user.getUser_id());
            if (homework == null) {
                System.out.println("作业是空的");
                submit_homework.setUser_name(user.getName());
                submit_homeworkDao.submit_homework(submit_homework);
            }else {
                System.out.println("覆盖作业");
                submit_homework.setUser_name(user.getName());
                submit_homeworkDao.cover_homework(submit_homework);
            }
            return "success";
        }
    }

    //学生作业情况
    @RequestMapping(value = "/checkuserhomework")
    public String checkuserhomework(Model model,
                                 String ph_id,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            List<Submit_Homework> submit_homeworks = submit_homeworkDao.findbyph_id(ph_id);
            for(Submit_Homework sh :submit_homeworks){
                System.out.println(sh.getPh_id());
                System.out.println(sh.getUser_id());
                System.out.println(sh.getUser_name());
            }
            model.addAttribute("submit_homeworks",submit_homeworks);
            return "check_user_homework";
        }
    }

    //查看作业
    @RequestMapping(value = "/checkuserhomeworkdetail")
    public String checkuserhomeworkdetail(Model model,
                                    String ph_id,
                                    String user_id,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse){
        HttpSession session = httpServletRequest.getSession();
        if(session.getAttribute("user")==null){
            return "/login";
        }else{
            Submit_Homework submit_homework = submit_homeworkDao.findhomework(ph_id,user_id);
            Publish_Homework publish_homework = publish_homeworkDao.findByph_id(ph_id);
            model.addAttribute("publish_homework",publish_homework);
            model.addAttribute("submit_homework",submit_homework);
            return "check_user_homework_detail";
        }
    }

}
