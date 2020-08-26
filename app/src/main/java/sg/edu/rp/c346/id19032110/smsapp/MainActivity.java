package sg.edu.rp.c346.id19032110.smsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSend;
    EditText etTo;
    EditText etContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        btnSend = findViewById(R.id.buttonSend);
        etTo = findViewById(R.id.editTextTo);
        etContent = findViewById(R.id.editTextContent);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String outoutTo = etTo.getText().toString();
                String content = etContent.getText().toString();

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(outoutTo, null, content, null, null);
                Toast toast = Toast.makeText(MainActivity.this, "Message Sent", Toast.LENGTH_LONG);
                toast.show();
            }
        });

    }
    private void checkPermission(){
        int permissionSendSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS);
        int permissionRecvSMS = ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS);
        if (permissionSendSMS != PackageManager.PERMISSION_GRANTED &&
                permissionRecvSMS != PackageManager.PERMISSION_GRANTED) {
            String[] permissionNeeded = new String[]{Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS};
            ActivityCompat.requestPermissions(this, permissionNeeded, 1);
        }

    }
}
