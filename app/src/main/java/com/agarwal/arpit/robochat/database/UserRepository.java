package com.agarwal.arpit.robochat.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class UserRepository {

    private UserDao userDao;
    private List<UserEntity> userList;

    public UserRepository(Context context) {
        UserDatabase db = UserDatabase.getAppDatabase(context);
        userDao = db.userDao();
        userList = userDao.getAll();
    }

    public List<UserEntity> getUserList() {
        return userList;
    }

    public void insert(UserEntity item) {
        new insertAsyncTask(userDao).execute(item);
    }

    private static class insertAsyncTask extends AsyncTask<UserEntity, Void, Void> {

        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(UserEntity... msg) {

            mAsyncTaskDao.insertAll(msg[0]);

            return null;
        }
    }
}
