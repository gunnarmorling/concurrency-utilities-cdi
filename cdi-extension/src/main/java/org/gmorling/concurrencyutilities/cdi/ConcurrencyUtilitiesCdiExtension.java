/**
 *  Copyright 2018 The Concurrency Utilities CDI Extension Authors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.gmorling.concurrencyutilities.cdi;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.gmorling.concurrencyutilities.cdi.internal.ManagedExecutorServiceBean;

/**
 * A CDI portable extension for obtaining the JSR 236 resources such as
 * {@link ManagedExecutorService} via dependency injection ({@link Inject}).
 *
 * @author Gunnar Morling
 *
 */
public class ConcurrencyUtilitiesCdiExtension implements Extension {

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery abd, BeanManager bm) {
        abd.addBean( new ManagedExecutorServiceBean() );
    }
}
