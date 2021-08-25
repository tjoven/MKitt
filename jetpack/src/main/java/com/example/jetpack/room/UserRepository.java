package com.example.jetpack.room;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {
    private Context mContext;
    private UserDao userDao;
    public UserRepository(Context mContext) {
        this.mContext = mContext;
        userDao = MyDataBase.getInstance(mContext).getUserDao();
    }

    public LiveData<List<UserEntity>> getAll(){
        return userDao.getAll();
    }

    public void insertUser(final UserEntity user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });

    }


    public void deleteUser(final UserEntity user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.deleteUser(user);
            }
        });

    }

    public void updateUser(final UserEntity user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.updateUser(user);
            }
        });

    }

    class SelectAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.getAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void listLiveData) {
            super.onPostExecute(listLiveData);
        }
    }
}
