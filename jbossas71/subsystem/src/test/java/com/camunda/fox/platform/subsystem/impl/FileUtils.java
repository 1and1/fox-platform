/**
 * Copyright (C) 2011, 2012 camunda services GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camunda.fox.platform.subsystem.impl;

import java.io.*;

/**
 *
 * @author nico.rehwaldt
 */
public class FileUtils {
  
  public static String readFile(String name) throws Exception {
    InputStream is = null;
    BufferedReader reader = null;
    
    StringBuilder fileContents = new StringBuilder();
    
    try {
      is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
      reader = new BufferedReader(new InputStreamReader(is));

      while (true) {
        String line = reader.readLine();
        if (line == null) {
          break;
        }
        
        fileContents.append(line);
      }
    } finally {
      try {
        if (is != null) is.close();
      } finally {
        ; // Cannot handle exception
      }
    }
    
    return fileContents.toString();
  }
}
