package ro.pub.cs.systems.eim.test2;

import android.provider.DocumentsContract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

public class Communication extends Thread {
    private ServerEIM serverThread;
    private Socket socket;

    public Communication(ServerEIM serverEIM, Socket socket) {
        this.serverThread = serverEIM;
        this.socket = socket;
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        if (socket == null) {
            return;
        }

        try {
            BufferedReader bufferedReader = getReader(socket);
            PrintWriter printWriter = getWriter(socket);
            if (bufferedReader == null || printWriter == null) {
                return;
            }

            String city = bufferedReader.readLine();
            String info = bufferedReader.readLine();

            if (city.isEmpty() || info.isEmpty()) {
                return;
            }

            HashMap<String, WeatherInformation>data = serverThread.getData();
            WeatherInformation result;

            if (data.containsKey(city)) {
                result = data.get(city);
            } else {
                
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
