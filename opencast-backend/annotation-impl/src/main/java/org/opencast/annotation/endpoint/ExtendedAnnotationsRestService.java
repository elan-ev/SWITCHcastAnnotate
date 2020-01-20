/**
 *  Copyright 2012, Entwine GmbH, Switzerland
 *  Licensed under the Educational Community License, Version 2.0
 *  (the "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *  http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 *
 */

package org.opencast.annotation.endpoint;

import static org.opencastproject.util.RestUtil.getEndpointUrl;

import org.opencast.annotation.impl.videointerface.ExternalApiVideoInterfaceProvider;
import org.opencast.annotation.impl.videointerface.ExternalApiVideoInterfaceProviderConfiguration;
import org.opencast.annotation.impl.videointerface.VideoInterfaceProvider;
import org.opencastproject.security.api.SecurityService;
import org.opencastproject.security.api.TrustedHttpClient;
import org.opencastproject.security.api.UserDirectoryService;
import org.opencastproject.util.UrlSupport;
import org.opencastproject.util.data.Tuple;

import org.opencast.annotation.api.ExtendedAnnotationService;

import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Path;

@Path("/")
public class ExtendedAnnotationsRestService extends AbstractExtendedAnnotationsRestService {
  private static final Logger logger = LoggerFactory.getLogger(ExtendedAnnotationsRestService.class);

  private ExtendedAnnotationService eas;
  // TODO Inject this via OSGi
  private VideoInterfaceProvider videoInterfaceProvider;
  private ExternalApiVideoInterfaceProviderConfiguration externalApiVideoInterfaceProviderConfiguration;
  private SecurityService securityService;
  private UserDirectoryService userDirectoryService;
  private TrustedHttpClient trustedHttpClient;
  private String endpointBaseUrl;

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void activate(ComponentContext cc) {
    logger.info("Start");
    final Tuple<String, String> endpointUrl = getEndpointUrl(cc);
    endpointBaseUrl = UrlSupport.concat(endpointUrl.getA(), endpointUrl.getB());

    videoInterfaceProvider = new ExternalApiVideoInterfaceProvider(externalApiVideoInterfaceProviderConfiguration,
            securityService, userDirectoryService, trustedHttpClient);
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void deactivate() {
    logger.info("Stop");
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void setExtendedAnnotationsService(ExtendedAnnotationService eas) {
    this.eas = eas;
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void setExternalApiVideoInterfaceProviderConfiguration(
          ExternalApiVideoInterfaceProviderConfiguration externalApiVideoInterfaceProviderConfiguration) {
    this.externalApiVideoInterfaceProviderConfiguration = externalApiVideoInterfaceProviderConfiguration;
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void setSecurityService(SecurityService securityService) {
    this.securityService = securityService;
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void setUserDirectoryService(UserDirectoryService userDirectoryService) {
    this.userDirectoryService = userDirectoryService;
  }

  /** OSGi callback. */
  @SuppressWarnings("unused")
  public void setTrustedHttpClient(TrustedHttpClient trustedHttpClient) {
    this.trustedHttpClient = trustedHttpClient;
  }

  @Override
  protected ExtendedAnnotationService getExtendedAnnotationsService() {
    return eas;
  }

  @Override
  protected SecurityService getSecurityService() {
    return securityService;
  }

  @Override
  protected VideoInterfaceProvider getVideoInterfaceProvider() {
    return videoInterfaceProvider;
  }

  @Override
  protected String getEndpointBaseUrl() {
    return endpointBaseUrl;
  }
}
