/*
 * Copyright 2018-2019 Karakun AG.
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.rico.internal.client;

import dev.rico.internal.core.Assert;
import dev.rico.client.ClientConfiguration;
import dev.rico.client.spi.ServiceProvider;
import org.apiguardian.api.API;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static org.apiguardian.api.API.Status.INTERNAL;

@API(since = "0.x", status = INTERNAL)
public abstract class AbstractServiceProvider<S> implements ServiceProvider<S> {

    private final Lock creationLock = new ReentrantLock();

    private S service;

    private final Class<S> serviceClass;

    public AbstractServiceProvider(final Class<S> serviceClass) {
        this.serviceClass = Assert.requireNonNull(serviceClass, "serviceClass");
    }

    protected abstract S createService(final ClientConfiguration configuration);

    @Override
    public boolean isActive(final ClientConfiguration configuration) {
        return true;
    }

    @Override
    public final S getService(final ClientConfiguration configuration) {
        Assert.requireNonNull(configuration, "configuration");
        creationLock.lock();
        try {
            this.service = createService(configuration);
        } finally {
            creationLock.unlock();
        }
        return service;
    }

    @Override
    public final Class<S> getServiceType() {
        return serviceClass;
    }
}

