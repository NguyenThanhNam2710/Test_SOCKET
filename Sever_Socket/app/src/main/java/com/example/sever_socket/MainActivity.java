package com.example.sever_socket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                // khoi tao 1 server voi port = 2710
                ServerSocket serverSocket = null;
                try {
                    //tao sever
                    serverSocket = new ServerSocket(2710);
                    Log.e("STATUS_SEVER", "Waiting to connect....");
                    // tao vong lap vo han de lang nghe su kien ket noi
                    while (true) {
                        Socket socket = serverSocket.accept();
                        //gui va nhan du lieu
                        try {
                            Log.e("STATUS_SEVER", "New client connect!");
                            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            BufferedWriter os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            while (true) {
                                String line = is.readLine();
                                os.write(">>" + line);
                                os.newLine();
                                os.flush();
                                is.close();
                                os.close();
                                socket.close();
                                // in gia tri gui toi
                                Log.e("RESPONSE", line);
                            }
                        } catch (Exception e) {
//                            Log.e("ERROR IN GET RESPONSE", e.toString());
                        }
                    }

                } catch (Exception e) {
                    Log.e("ERROR IN ASYNCTASK", e.getMessage());
                }
                return null;
            }
        };

        asyncTask.execute();

    }
}