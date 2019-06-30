package com.agarwal.arpit.robochat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agarwal.arpit.robochat.Utils.TextUtils;
import com.agarwal.arpit.robochat.database.UserDatabase;
import com.agarwal.arpit.robochat.database.UserEntity;
import com.agarwal.arpit.robochat.database.UserRepository;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.agarwal.arpit.robochat.Utils.AppConstants.USER_CHAT_TAG;
import static com.agarwal.arpit.robochat.Utils.AppConstants.externalID;

public class HomeScreen extends AppCompatActivity {

    @BindView(R.id.content_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.edit_text)
    EditText editText;

    @BindView(R.id.add_btn)
    Button addBtn;

    private List<UserEntity> userList= new ArrayList<>();
    private UserRecyclerAdapter adapter ;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        ButterKnife.bind(this);

        setUpController();
        initDatabase();

    }

    private void initDatabase() {
        userRepository = new UserRepository(getApplicationContext());
        List<UserEntity> databaseList = userRepository.getUserList();

        userList.clear();
        if (databaseList!=null & databaseList.size()>0){
            userList.addAll(databaseList);
        }

        adapter.notifyDataSetChanged();
    }

    private void setUpController() {
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUserToList();
            }
        });


        userList.clear();

        UserEntity entity = new UserEntity();
        entity.setName(externalID);
        userList.add(entity);//Added by default for testing of local database
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(false);

        adapter = new UserRecyclerAdapter(userList, view -> {
             int position = (int)view.getTag();
            Intent intent = new Intent(this, ChatScreenActivity.class);
            intent.putExtra(USER_CHAT_TAG,userList.get(position).getName());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }

    private void addUserToList() {
        String userName = editText.getText().toString().trim();

        if (userName.isEmpty()) {
            TextUtils.showToast(this, getString(R.string.empty_msg_error));
        } else {
            if (!userList.contains(userName)){

                UserEntity entity = new UserEntity();
                entity.setName(userName);

                userList.add(entity);
                userRepository.insert(entity);
                adapter.notifyDataSetChanged();
            }else {
                TextUtils.showToast(this,getString(R.string.user_present_error));
            }
            editText.setText("");
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        UserDatabase.destroyInstance();
    }
}
