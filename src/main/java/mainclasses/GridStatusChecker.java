package mainclasses;

import java.io.IOException;
import java.net.Socket;

public class GridStatusChecker {

	public static boolean checkGridStatus() {
		boolean isGridRunning = isPortInUse("localhost", 4444);

		if (isGridRunning) {
			System.out.println("Selenium Grid is running.");
			return true;
		} else {
			System.out.println("Selenium Grid is not running.");
			return false;
		}
	}

	public static boolean isPortInUse(String host, int port) {
		try {
			// Try to create a socket connection to the given host and port
			// If successful, the port is in use, indicating that Selenium Grid is running
			Socket socket = new Socket(host, port);
			socket.close(); // Close the socket
			return true;
		} catch (IOException e) {
			// Failed to connect to the port, indicating that Selenium Grid is not running
			return false;
		}
	}
}
