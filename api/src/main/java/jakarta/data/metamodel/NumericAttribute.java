/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
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
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package jakarta.data.metamodel;

import jakarta.data.metamodel.impl.NumericAttributeRecord;

import java.util.Objects;

public interface NumericAttribute<T,N extends Number & Comparable<N>>
        extends BasicAttribute<T,N>, NumericExpression<T, N> {
    static <T,N extends Number & Comparable<N>> NumericAttribute<T,N>
    of(Class<T> entityClass, String name, Class<N> attributeType) {
        Objects.requireNonNull(entityClass, "entity class is required");
        Objects.requireNonNull(name, "entity attribute name is required");
        Objects.requireNonNull(attributeType, "entity attribute type is required");

        return new NumericAttributeRecord<>(name);
    }
}

