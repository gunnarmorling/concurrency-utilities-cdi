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
package org.gmorling.concurrencyutilities.cdi.internal;

import javax.enterprise.concurrent.ContextService;

/**
 * Bean representing the default {@link ContextService} bound to
 * java:comp/DefaultContextService.
 *
 * @author Gunnar Morling
 */
public class ContextServiceBean extends CurrencyUtilityBean<ContextService> {

    @Override
    public Class<?> getBeanClass() {
        return ContextService.class;
    }
}
