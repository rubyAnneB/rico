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
package dev.rico.core.functional;

/**
 * Functional interface like {@link java.util.function.Function} that can throw an exception at runtime
 * @param <T> input parameter type
 * @param <R> output parameter type
 */
@FunctionalInterface
public interface CheckedFunction<T, R> {

    /**
     * Method that handles the function
     * @param t input parameter
     * @return result of the function
     * @throws Exception if the handling of the function throws an exception
     */
    R apply(T t) throws Exception;

}
