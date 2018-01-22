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
package org.gmorling.concurrencyutilities.cdi.integrationtest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import javax.enterprise.concurrent.ContextService;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.concurrent.ManagedThreadFactory;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Test bean for injecting JSR 236 resources.
 *
 * @author Gunnar Morling
 */
@ApplicationScoped
public class TestBean {

    @Inject
    private ManagedExecutorService executorService;

    @Inject
    private ManagedScheduledExecutorService scheduledExecutorService;

    @Inject
    private ManagedThreadFactory threadFactory;

    @Inject
    private ContextService contextService;

    public String createGreetingViaManagedExecutorService(String name) {
        Future<String> task = executorService.submit( () -> "Hello, " + name + "!" );

        try {
            return task.get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }
    }

    public String createGreetingViaManagedScheduledExecutorService(String name) {
        Future<String> task = scheduledExecutorService.schedule(
                () -> "Hello, " + name + "!",
                1,
                TimeUnit.MILLISECONDS
        );

        try {
            return task.get();
        }
        catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException( e );
        }
    }

    public String createGreetingViaManagedThreadFactory(String name) {
        AtomicReference<String> greeting = new AtomicReference<String>();
        CountDownLatch barrier = new CountDownLatch(1);

        threadFactory.newThread(
                () -> {
                    greeting.set( "Hello, " + name + "!" );
                    barrier.countDown();
                }
        ).run();

        try {
            barrier.await();
        }
        catch (InterruptedException e) {
            throw new RuntimeException( e );
        }

        return greeting.get();
    }

    public boolean canInjectContextService() {
        return contextService != null;
    }
}
