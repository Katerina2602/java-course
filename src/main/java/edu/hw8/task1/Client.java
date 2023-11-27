package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Client {
    private static final Logger LOGGER = LogManager.getLogger();
    private final int serverPort;
    private final String hostName;

    public Client(int serverPort, String hostName) {
        this.serverPort = serverPort;
        this.hostName = hostName;
    }

    public String getQuoteByWord(String word) {
        try (
            Socket socket = new Socket(InetAddress.getByName(hostName), serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            out.write(word + "\n");
            out.flush();
            String response = in.readLine();
            LOGGER.info("Got response from server: {}", response);

            return response;
        } catch (Exception ex) {
            throw new RuntimeException("Failed get quote from server", ex);
        }
    }
}
