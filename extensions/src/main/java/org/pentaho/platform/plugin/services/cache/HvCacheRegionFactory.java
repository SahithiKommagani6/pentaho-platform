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
 * Copyright (c) 2002-2023 Hitachi Vantara. All rights reserved.
 *
 */
package org.pentaho.platform.plugin.services.cache;

import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.cfg.spi.DomainDataRegionBuildingContext;
import org.hibernate.cache.cfg.spi.DomainDataRegionConfig;
import org.hibernate.cache.ehcache.internal.SingletonEhcacheRegionFactory;
import org.hibernate.cache.spi.DomainDataRegion;
import org.hibernate.cache.spi.QueryResultsRegion;
import org.hibernate.cache.spi.RegionFactory;
import org.hibernate.cache.spi.TimestampsRegion;
import org.hibernate.cache.spi.access.AccessType;
import org.hibernate.engine.spi.SessionFactoryImplementor;

import java.util.Map;

public class HvCacheRegionFactory implements RegionFactory {
  @Override
  public void start(SessionFactoryOptions sessionFactoryOptions, Map<String, Object> map) throws CacheException {

  }

  @Override
  public boolean isMinimalPutsEnabledByDefault() {
    return false;
  }

  @Override
  public AccessType getDefaultAccessType() {
    return null;
  }

  @Override
  public String qualify(String s) {
    return null;
  }

  @Override
  public long nextTimestamp() {
    return 0;
  }

  @Override
  public DomainDataRegion buildDomainDataRegion(DomainDataRegionConfig domainDataRegionConfig, DomainDataRegionBuildingContext domainDataRegionBuildingContext) {
    return null;
  }

  @Override
  public QueryResultsRegion buildQueryResultsRegion(String s, SessionFactoryImplementor sessionFactoryImplementor) {
    return null;
  }

  @Override
  public TimestampsRegion buildTimestampsRegion(String s, SessionFactoryImplementor sessionFactoryImplementor) {
    return null;
  }

  /*@Override
  public TimestampsRegion buildTimestampsRegion(
    String regionName, SessionFactoryImplementor sessionFactory) {
    verifyStarted();
    return new HvTimestampsRegion(
      regionName, this, createTimestampsRegionStorageAccess( regionName, sessionFactory ) );
  }*/

  @Override
  public void stop() {

  }
}
