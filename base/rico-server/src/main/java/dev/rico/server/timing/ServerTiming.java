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
package dev.rico.server.timing;

import dev.rico.core.functional.CheckedRunnable;
import dev.rico.core.functional.CheckedSupplier;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 * Service to add metrics of the server to a http response.
 */
@API(since = "2.0", status = EXPERIMENTAL)
public interface ServerTiming {

    /**
     * Create a new metric with the current time as start time and the given name
     *
     * @param name the name of the metric
     * @return the metric
     */
    default ServerTimer start(String name) {
        return start(name, null);
    }

    /**
     * Create a new metric with the current time as start time, the given name and description.
     *
     * @param name        the name of the metric
     * @param description the description of the metric
     * @return the metric
     */
    ServerTimer start(String name, String description);

    /**
     * Records a timing based on the duration of a task with the the given name and description.
     *
     * @param name     the name of the timing
     * @param runnable the task that will be executed and that duration will be recorded
     * @throws Exception if the tasks throws an exception
     */
    default void record(String name, CheckedRunnable runnable) throws Exception {
        record(name, null, runnable);
    }

    /**
     * Records a timing based on the duration of a task with the the given name and description.
     *
     * @param name        the name of the timing
     * @param description the description of the timing
     * @param runnable    the task that will be executed and that duration will be recorded
     * @throws Exception if the tasks throws an exception
     */
    default void record(String name, String description, CheckedRunnable runnable) throws Exception {
        final ServerTimer serverTimer = start(name, description);
        try (serverTimer) {
            runnable.run();
        }
    }

    /**
     * Records a timing based on the duration of a task with the the given name and description.
     *
     * @param name     the name of the timing
     * @param supplier the task that will be executed and that duration will be recorded
     * @param <R>      the result type of the task
     * @return the result of the task
     * @throws Exception if the tasks throws an exception
     */
    default <R> R record(String name, CheckedSupplier<R> supplier) throws Exception {
        return record(name, null, supplier);
    }

    /**
     * Records a timing based on the duration of a task with the the given name and description.
     *
     * @param name        the name of the timing
     * @param description the description of the timing
     * @param supplier    the task that will be executed and that duration will be recorded
     * @param <R>         the result type of the task
     * @return the result of the task
     * @throws Exception if the tasks throws an exception
     */
    default <R> R record(String name, String description, CheckedSupplier<R> supplier) throws Exception {
        final ServerTimer serverTimer = start(name, description);
        try (serverTimer) {
            return supplier.get();
        }
    }

}
