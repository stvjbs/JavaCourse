package com.andersenlab.hibernate.dao;

import com.andersenlab.dao.impl.UserCRUDable;
import com.andersenlab.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserDAOHibernate implements UserCRUDable {
    @Override
    public boolean saveUser(User user) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public User getUserById(int id) {
        return SessionFactoryProvider
                .getSessionFactory()
                .openSession()
                .get(User.class, id);
    }

    @Override
    public void deleteUserById(int id) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(session.get(User.class, id));
        tx.commit();
        session.close();
    }

    public void updateUser(int userId, String name) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        User user = session.get(User.class, userId);
        user.setName(name);
        session.update(user);
        tx.commit();
        session.close();
    }
}
