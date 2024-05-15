package com.exer.springexer.service;

import com.exer.springexer.util.BCrypt;
import com.exer.springexer.dao.UsersDao;
import com.exer.springexer.dto.Result;
import com.exer.springexer.entity.Users;
import org.springframework.stereotype.Service;

import static com.exer.springexer.constance.Constance.*;


@Service
public class UserService {
    private UsersDao usersDao;

    public UserService(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public Result creatUser(Users users){
        if(usersDao.existsByMail(users.getMail())){
            return new Result<>(EMAIL_ALREADY_EXIST);
        }else {
//            ?這裡還看得到明碼密碼？
            String hashpw = BCrypt.hashpw(users.getSecret(), BCrypt.gensalt());
            users.setSecret(hashpw);
            Integer id=usersDao.save(users).getId();
            usersDao.flush();
            return new Result<>(SUCCESS,id);
        }
    }
    public Result updateUser(Users users){
        usersDao.save(users);
        return new Result<>(SUCCESS);
    }
    public Result deleteUser(Integer id){
        usersDao.deleteById(id);
        return new Result<>(SUCCESS);

    }
    public Result login(Users users){
        Users userRe=usersDao.findByMail(users.getMail());
        if(userRe == null){
            return new Result<>(EMAIL_NOT_EXIST);
        }
        if(!BCrypt.checkpw(users.getSecret(),userRe.getSecret())){
            System.out.println(userRe.getSecret());

            return new Result<>(EMAIL_NOT_EXIST);
        }
        return new Result<>(SUCCESS,userRe);
    }

    public Result ChangePass(Users users){
        Users nowUser=usersDao.findByMail(users.getMail());
        String newSecret = users.getSecret();
        if(nowUser != null && newSecret!=null){
            users.setSecret(BCrypt.hashpw(newSecret,BCrypt.gensalt()));
            usersDao.save(users);
            return new Result<>(SUCCESS);
        }else {
            return new Result<>(EMAIL_NOT_EXIST);
        }
    }
}
