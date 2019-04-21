package hexalpha.engine.network;

import hexalpha.controller.wait.WaitController;
import java.io.IOException;

/**
* waits for client connection on another thread.
*/
public class WaitForClient extends Thread {

  Server server;
  WaitController wait;

  /**
  * Creates a new thread to wait for connection
  *
  * @param server the server connecting
  * @param wait the current screen being used.
  */
  public WaitForClient(Server server, WaitController wait) {
    this.server = server;
    this.wait = wait;
  }

  /**
  * runs the thread waiting for input
  */
  public void run() {
    try {
      this.server.connect();
      this.wait.game();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }
}
