package com.agarwal.arpit.robochat.database;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class MessageRepositry {

    private MessageDao messageDao;
    private List<MessageEntity> messageEntityList;

    public MessageRepositry(Context context) {
        MessageDatabase db = MessageDatabase.getAppDatabase(context);
        messageDao = db.messageDao();
        messageEntityList = messageDao.getAll();
    }

    public List<MessageEntity> getMessageEntityList() {
        return messageEntityList;
    }

    public void insert(MessageEntity list) {
        new insertAsyncTask(messageDao).execute(list);
    }

    private static class insertAsyncTask extends AsyncTask<MessageEntity, Void, Void> {

        private MessageDao mAsyncTaskDao;

        insertAsyncTask(MessageDao dao) {
            mAsyncTaskDao = dao;
        }


        @Override
        protected Void doInBackground(MessageEntity... msg) {

            mAsyncTaskDao.insertAll(msg[0]);

            return null;
        }
    }
}
