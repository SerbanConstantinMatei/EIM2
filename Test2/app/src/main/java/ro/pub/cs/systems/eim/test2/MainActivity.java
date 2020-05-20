package ro.pub.cs.systems.eim.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText serverPort, clientPort, city, address;
    private Button start, getForecast;
    private Spinner spinner;
    private ServerEIM serverThread = null;
    //private ClientEim serverClient = null;

    private ClientListener clientListener = new ClientListener();
    private class ClientListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }

    private ServerListener serverListener = new ServerListener();
    private class ServerListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            String port = serverPort.getText().toString();
            if (port.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No port number given", Toast.LENGTH_LONG).show();
                return;
            }

            int portNum = Integer.parseInt(port);
            serverThread = new ServerEIM(portNum);

            serverThread.start();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serverPort = (EditText)findViewById(R.id.serverPort);
        clientPort = (EditText)findViewById(R.id.clientPort);
        city = (EditText)findViewById(R.id.city);
        address = (EditText)findViewById(R.id.addr);
        start = (Button)findViewById(R.id.start);
        getForecast = (Button)findViewById(R.id.forecast);
        spinner = (Spinner)findViewById(R.id.spinner);

        start.setOnClickListener(serverListener);
        getForecast.setOnClickListener(clientListener);
    }

    @Override
    protected void onDestroy() {
        if (serverThread != null) {
            serverThread.stopThread();
        }

        super.onDestroy();
    }
}
