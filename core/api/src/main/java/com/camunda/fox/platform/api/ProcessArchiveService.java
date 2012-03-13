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
package com.camunda.fox.platform.api;

import org.activiti.engine.ProcessEngine;

import com.camunda.fox.platform.spi.ProcessArchive;

/**
 * <p>The container-managed process archive service.</p>
 *
 * <p>This class allows to install and uninstall {@link ProcessArchive ProcessArchives} 
 * on the container managed process engine service and through that process, obtain an 
 * handle to a {@link ProcessEngine}.</p>
 * 
 * <p>Users of this class may look up an instance of the service through a lookup strategy
 * appropriate for the platform they are using (Examples: Jndi, OSGi Service Registry ...)</p>
 * 
 * @author Daniel Meyer
 */
public interface ProcessArchiveService {

  public ProcessEngine installProcessArchive(ProcessArchive processArchive);

  public void unInstallProcessArchive(ProcessArchive processArchive);

}