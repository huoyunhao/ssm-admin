package cc.aies.web.service;

import cc.aies.web.beans.PermUser;
import cc.aies.web.beans.PermUserExample;
import cc.aies.web.dao.PermUserMapper;
import cc.aies.web.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  liyang
 *
 *  功能两用,使用标示变量来分辨对哪个对象进行操作
 */
@Service
public class UserService extends BaseService {
    @Autowired
    PermUserMapper permUserMapper;
    /**
     * 根据用户名查找用户
     * @param userAccount
     * @return
     */
    public Object UserByAccount(String userAccount){
        Object users;
            PermUserExample permUserExample = new PermUserExample();
            PermUserExample.Criteria criteria = permUserExample.createCriteria();
            criteria.andStatusNotLike("-1");
            criteria.andUserAccountEqualTo(userAccount);
            users = permUserMapper.selectByExample(permUserExample);
        return users;
    }
}
