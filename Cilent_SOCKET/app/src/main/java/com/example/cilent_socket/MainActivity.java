package com.example.cilent_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                String host = "10.0.3.15";
                try {
                    Socket socket = new Socket(host, 2710);

                    if (socket.isConnected()) Log.e("STATUS_CLINET", "CONNECTED");

                    else Log.e("STATUS_CLINET", "NOT CONNECTED");

                    // tao du lieu gui
                    BufferedWriter os = new BufferedWriter(new
                            OutputStreamWriter(socket.getOutputStream()));

                    os.write("From Simulator, connection time(milliseconds): " + System.currentTimeMillis());
                    os.newLine();
                    os.flush();

                    os.close();
                    socket.close();

                } catch (Exception e) {
                    Log.e("ERROR IN ASYNCTASK", e.getMessage());
                }
                return null;
            }
        };
        asyncTask.execute();

    }
}