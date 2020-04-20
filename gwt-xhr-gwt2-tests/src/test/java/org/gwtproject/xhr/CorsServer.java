/*
 * Copyright © 2019 The GWT Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gwtproject.xhr;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.LocalConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.gwtproject.xhr.server.CorsServlet;

public class CorsServer {

  private static final Logger LOGGER = Logger.getLogger(CorsServer.class.getCanonicalName());

  public static void main(String[] args) {
    System.out.println("run server ...");
    try {
      Server server = new Server(Integer.parseInt(args[1]));
      Connector con = new LocalConnector(server);
      server.addConnector(con);
      WebAppContext webAppContext = new WebAppContext();
      webAppContext.setResourceBase("/");
      webAppContext.setContextPath("/");
      webAppContext.addServlet(new ServletHolder(new CorsServlet()), "/testCors");
      server.setHandler(webAppContext);
      server.start();
    } catch (Exception ex) {
      LOGGER.log(Level.SEVERE, "Error while starting CORS server");
      System.exit(0);
    }
  }
}
