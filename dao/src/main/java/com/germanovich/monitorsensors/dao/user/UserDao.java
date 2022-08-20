package com.germanovich.monitorsensors.dao.user;

import com.germanovich.monitorsensors.dao.GenericDao;
import com.germanovich.monitorsensors.model.user.User;

public interface UserDao extends GenericDao<User, Long> {

    User getByLogin(final String login);
}
