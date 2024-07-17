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
 * Copyright (c) 2020 Hitachi Vantara. All rights reserved.
 *
 */
package org.pentaho.platform.plugin.services.security.userrole;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

public class PentahoEhCacheBasedUserCache implements UserCache {
    private CacheManager cacheManager;
    private Cache<String, UserDetails> userCache;
    private boolean caseSensitive;

    public PentahoEhCacheBasedUserCache () {

    }

    public void setCacheManager (CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void setUserCache (Cache<String, UserDetails> userCache) {
        this.userCache = userCache;
    }

    public PentahoEhCacheBasedUserCache (boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder ().build (true);
        userCache = cacheManager.createCache ("userCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder (String.class, UserDetails.class, ResourcePoolsBuilder.heap (10)));
    }

    @Override
    public UserDetails getUserFromCache (String username) {
        if (caseSensitive) {
            return userCache.get (username);
        } else {
            for (Cache.Entry<String, UserDetails> entry : userCache) {
                String key = entry.getKey ();
                if (key.equalsIgnoreCase (username)) {
                    return entry.getValue ();
                }
            }
        }
        return null;
    }

    @Override
    public void putUserInCache (UserDetails user) {
        userCache.put (user.getUsername (), user);
    }

    @Override
    public void removeUserFromCache (String username) {
        userCache.remove (username);
    }
}