package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

public class AccountDaoImpl implements AccountDao {
    // 定义JdbcTemplate属性，此处省略setter方法
    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 这里只展示（添加账户）的操作
    public int addAccount(Account account) {
        String sql = "insert into account(username,balance) value(?,?)";
        Object[] obj = new Object[] {// 定义数组来存放SQL语句中的参数
                account.getUsername(), account.getBalance() 	};
        // 执行添加操作，返回的是受SQL语句影响的记录条数
        return this.jdbcTemplate.update(sql, obj);}

    @Override
    public int updateAccount(Account account) {
        String sql ="update account set username=?,balance=? where id=?";
        Object[] obj =new Object[]{
                account.getUsername(),
                account.getBalance(),
                account.getId()
        };
        int num= this.jdbcTemplate.update(sql,obj);
        return num;
    }

    @Override
    public int deleteAccount(int id) {
        String sql ="delete from account where id=?";
        return this.jdbcTemplate.update(sql,id);
    }
    @Override
    public Account findAccountById(int id) {
        //定义SQL语句
        String sql = "select * from account where id = ?";
        // 创建一个新的BeanPropertyRowMapper对象
        RowMapper<Account> rowMapper =
                new BeanPropertyRowMapper<Account>(Account.class);
        // 将id绑定到SQL语句中，通过RowMapper返回Object类型的单行记录
        return this.jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Account> findAllAccount() {
        String sql="select * from account";
        RowMapper<Account> rowMapper = new BeanPropertyRowMapper<>(Account.class);
        return  this.jdbcTemplate.query(sql,rowMapper);
    }
    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT, readOnly = false)

    // 转账 inUser：收款人； outUser：汇款人； money：收款金额
    public void transfer(String outUser, String inUser, Double money) {
        // 收款时，收款用户的余额=现有余额+所汇金额
        this.jdbcTemplate.update("update account set balance = balance +? "
                + "where username = ?",money, inUser);
        // 模拟系统运行时的突发性问题
        //int i = 1/0;
        // 汇款时，汇款用户的余额=现有余额-所汇金额
        this.jdbcTemplate.update("update account set balance = balance-? "
                + "where username = ?",money, outUser);
    }



}

