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

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.PassivationCapable;
import javax.enterprise.util.AnnotationLiteral;

public class ManagedExecutorServiceBean implements Bean<ManagedExecutorService>, PassivationCapable {
    @Override
    public Class<?> getBeanClass() {
        return ManagedExecutorService.class;
    }

    @Override
    public Set<InjectionPoint> getInjectionPoints() {
        return Collections.emptySet();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    @SuppressWarnings("serial")
    public Set<Annotation> getQualifiers() {
        Set<Annotation> qualifiers = new HashSet<Annotation>();

        qualifiers.add( new AnnotationLiteral<Default>() {} );
        qualifiers.add( new AnnotationLiteral<Any>() {} );

        return qualifiers;
    }

    @Override
    public Class<? extends Annotation> getScope() {
        return ApplicationScoped.class;
    }

    @Override
    public Set<Class<? extends Annotation>> getStereotypes() {
        return Collections.emptySet();
    }

    @Override
    public Set<Type> getTypes() {
        Set<Type> types = new HashSet<Type>();
        types.add(ManagedExecutorService.class);
        types.add(Object.class);
        return types;
    }

    @Override
    public boolean isAlternative() {
        return false;
    }

    @Override
    public boolean isNullable() {
        return false;
    }

    @Override
    public ManagedExecutorService create(CreationalContext<ManagedExecutorService> ctx) {
        return JndiHelper.getFromJndi( ManagedExecutorService.class, "java:comp/DefaultManagedExecutorService" );
    }

    @Override
    public void destroy(ManagedExecutorService instance, CreationalContext<ManagedExecutorService> ctx) {
        ctx.release();
    }

    @Override
    public String getId() {
        return ManagedExecutorService.class + "_default";
    }
}
