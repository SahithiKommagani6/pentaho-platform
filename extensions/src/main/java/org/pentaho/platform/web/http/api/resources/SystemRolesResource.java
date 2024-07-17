/*!
 *
 * This program is free software; you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License, version 2.1 as published by the Free Software
 * Foundation.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this
 * program; if not, you can obtain a copy at http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 * or from the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 *
 * Copyright (c) 2002-2018 Hitachi Vantara. All rights reserved.
 *
 */

package org.pentaho.platform.web.http.api.resources;

import org.codehaus.enunciate.Facet;
import org.pentaho.platform.api.engine.IAuthorizationPolicy;
import org.pentaho.platform.engine.core.system.PentahoSystem;
import org.pentaho.platform.security.policy.rolebased.actions.AdministerSecurityAction;
import org.pentaho.platform.security.policy.rolebased.actions.RepositoryCreateAction;
import org.pentaho.platform.security.policy.rolebased.actions.RepositoryReadAction;
import org.pentaho.platform.web.http.api.resources.services.SystemService;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path( "/roles" )
@Facet( name = "Unsupported" )
public class SystemRolesResource extends AbstractJaxRSResource {

  /**
   * Returns the roles in the platform
   * 
   * @return list of roles
   * 
   * @throws Exception
   */
  @GET
  @Produces( { MediaType.APPLICATION_XML } )
  @Facet ( name = "Unsupported" )
  public Response getRoles() throws Exception {
    try {
      if ( canAdminister() ) {
        return Response.ok( SystemService.getSystemService().getRoles().asXML() ).type( MediaType.APPLICATION_XML ).build();
      } else {
        return Response.status( UNAUTHORIZED ).build();
      }
    } catch ( Throwable t ) {
      throw new WebApplicationException( t );
    }
  }

  private boolean canAdminister() {
    IAuthorizationPolicy policy = PentahoSystem.get( IAuthorizationPolicy.class );
    return policy.isAllowed( RepositoryReadAction.NAME ) && policy.isAllowed( RepositoryCreateAction.NAME )
        && ( policy.isAllowed( AdministerSecurityAction.NAME ) );
  }
}
