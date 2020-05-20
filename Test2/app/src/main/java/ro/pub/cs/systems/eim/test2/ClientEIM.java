package ro.pub.cs.systems.eim.test2;

import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientEIM extends Thread{
    private int port;
    private String address;
    private String city;
    private String info;
    private TextView textView;

    private Socket socket;

    public ClientEIM(int port, String address, String city, String info, TextView textView) {
        this.port = port;
        this.address = address;
        this.city = city;
        this.info = info;
        this.textView = textView;
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            socket = new Socket(this.address, this.port);
            if (socket == null) {
                return;
            }

            BufferedReader bufferedReader = getReader(socket);
            PrintWriter printWriter = getWriter(socket);

            if (bufferedReader == null || printWriter == null) {
                return;
            }

            printWriter.println(city);
            printWriter.flush();
            printWriter.println(info);
            printWriter.flush();

            String weatherInfo;
            while ((weatherInfo = bufferedReader.readLine()) != null) {
                final String finalInfo = weatherInfo;
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(finalInfo);
                    }
                });
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
