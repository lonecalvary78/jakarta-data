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
package jakarta.data.spi.expression.path;

import jakarta.data.expression.NavigableExpression;
import jakarta.data.messages.Messages;
import jakarta.data.metamodel.ComparableAttribute;

record ComparablePathRecord<T, U, C extends Comparable<?>>
        (NavigableExpression<T, U> expression,
         ComparableAttribute<U, C> attribute)
        implements ComparablePath<T, U, C> {

    ComparablePathRecord {
        Messages.requireNonNull(expression, "expression");
        Messages.requireNonNull(attribute, "attribute");
    }

    @Override
    public String toString() {
        String expr = expression.toString();
        String attrName = attribute.name();
        StringBuilder path =
                new StringBuilder(expr.length() + 1 + attrName.length());
        path.append(expr).append('.').append(attrName);
        return path.toString();
    }
}
