package com.itheima.controller;

import com.itheima.dao.StudentDao;
import com.itheima.pojo.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

public class StudentController {
    public static void main(String[] args) {
        Scanner sca = new Scanner(System.in);
        System.out.println("请输入用户名");
        String name = sca.nextLine();
        ApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext-student.xml");
        // 获取AccountDao实例
        StudentDao studentDao =
                (StudentDao) applicationContext.getBean("studentDao");
        List<Student> students = studentDao.findAllStudent();
        // for循环输出集合中的对象，此处省略
        for (Student student : students) {
            if (name.equals(student.getUsername())) {
                System.out.println("请输入密码");
                String password = sca.nextLine();
                if (password.equals(student.getPassword())) {
                    System.out.println("用户登录成功");
                    System.out.println(name + "是" + student.getCourse() + "班的");
                    break;
                }
                else {

                    System.out.println("密码错误");
                }

            }else {
                System.out.println("没有该用户信息");
            }

        }
    }
}

