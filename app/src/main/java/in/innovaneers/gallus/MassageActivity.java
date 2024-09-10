package in.innovaneers.gallus;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.innovaneers.gallus.model.MessageListAdapter;
import in.innovaneers.gallus.model.MessageModel;

public class MassageActivity extends AppCompatActivity {
    private RecyclerView recyclerChat;
    private EditText editChatMessage;
    private Button buttonChatSend;
    private MessageListAdapter messageListAdapter;
    private List<MessageModel> messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_massage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerChat = findViewById(R.id.recycler_gchat);
        editChatMessage = findViewById(R.id.edit_gchat_message);
        buttonChatSend = findViewById(R.id.button_gchat_send);

        messageList = new ArrayList<>();
        messageListAdapter = new MessageListAdapter(this, messageList);

        recyclerChat.setLayoutManager(new LinearLayoutManager(this));
        recyclerChat.setAdapter(messageListAdapter);

        buttonChatSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editChatMessage.getText().toString().trim();
                if (!messageText.isEmpty()) {
                    // Add the message to the list and update the RecyclerView
                    MessageModel message = new MessageModel(messageText, System.currentTimeMillis(), true);
                    messageList.add(message);
                    messageListAdapter.notifyItemInserted(messageList.size() - 1);
                    recyclerChat.scrollToPosition(messageList.size() - 1);

                    // Clear the input field
                    editChatMessage.setText("");
                }
            }
        });
    }
}