package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.pojo.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUpdateAccount {
    public static void main(String[] args) {
        ApplicationContext applicationContext =new
                ClassPathXmlApplicationContext("applicationContext.xml");
        AccountDao accountDao =
                (AccountDao) applicationContext.getBean("accountDao");
        Account account = new Account();
        account.setId(1);	account.setUsername("Tom");
        account.setBalance(2000.00);
        int num = accountDao.updateAccount(account);
        if (num > 0) {System.out.println("成功修改了" + num + "条数据！");
        } else {System.out.println("修改操作执行失败！");}	}
}
