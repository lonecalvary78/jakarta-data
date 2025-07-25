/*
 * Copyright (c) 2023,2025 Contributors to the Eclipse Foundation
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
 *  SPDX-License-Identifier: Apache-2.0
 */
package jakarta.data.repository;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.data.constraint.EqualTo;

/**
 * <p>Annotates a parameter of a repository method, specifying a mapping to
 * an entity attribute:</p>
 * <ul>
 * <li>if an {@linkplain #value attribute name} is specified, the parameter maps
 *     to the entity attribute with the specified name, or
 * <li>if the special value {@value #ID} is specified, the parameter maps
 *     to the unique identifier attribute.
 * </ul>
 * <p>Arguments to the annotated parameter are compared to values of the
 * mapped attribute. The {@link EqualTo#value(Object) equality} comparison is
 * default. Use the {@link Is#value() @Is} annotation to choose a different
 * subtype of {@link jakarta.data.constraint Constraint} to be the comparison.
 * </p>
 *
 * <p>The attribute name may be a compound name like {@code address.city}.</p>
 *
 * <p>For example, for a {@code Person} entity with attributes {@code ssn},
 * {@code firstName}, {@code lastName}, and {@code address} we might have:</p>
 *
 * <pre>
 * &#64;Repository
 * public interface People {
 *
 *     &#64;Find
 *     Person findById(&#64;By(ID) String id); // maps to Person.ssn
 *
 *     &#64;Find
 *     List&lt;Person&gt; findNamed(&#64;By("firstName") String first,
 *                            &#64;By("lastName") String last);
 *
 *     &#64;Find
 *     List&lt;Person&gt; findByCity(&#64;By("address.city") String city);
 * }
 * </pre>
 *
 * <p>The {@code By} annotation is unnecessary when the method parameter name
 * matches the entity attribute name and the application is compiled with the
 * {@code -parameters} compiler option that makes parameter names available
 * at runtime.</p>
 *
 * <p>Thus, when this compiler option is enabled, the previous example may be
 * written without the use of {@code By}:</p>
 *
 * <pre>
 * &#64;Repository
 * public interface People {
 *
 *     &#64;Find
 *     Person findById(String ssn);
 *
 *     &#64;Find
 *     List&lt;Person&gt; findNamed(String firstName,
 *                            String lastname);
 *
 *     &#64;Find
 *     List&lt;Person&gt; findByCity(String address_city);
 * }
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface By {

    /**
     * The name of the entity attribute mapped by the annotated parameter, or
     * {@value #ID} to indicate the unique identifier attribute of the entity.
     *
     * @return the entity attribute name, or {@value #ID} to indicate the unique
     * identifier attribute.
     */
    String value();

    /**
     * <p>
     * The special value which indicates the unique identifier attribute. The
     * annotation {@code By(ID)} maps a parameter to the identifier.
     * </p>
     * <p>
     * Note that {@code id(this)} is the expression in JPQL for the unique
     * identifier of an entity with an implicit identification variable.
     * </p>
     */
    String ID = "id(this)";
}
