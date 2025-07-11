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
 * SPDX-License-Identifier: Apache-2.0
 */
package jakarta.data.repository;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.data.Limit;
import jakarta.data.Order;
import jakarta.data.Sort;
import jakarta.data.page.PageRequest;
import jakarta.data.restrict.Restriction;

/**
 * <p>Lifecycle annotation for repository methods which perform delete
 * operations; alternatively, annotates a repository
 * method as a parameter-based automatic query method which deletes
 * entities.</p>
 *
 * <p>The {@code Delete} annotation indicates that the annotated repository
 * method deletes the state of one or more
 * entities from the database. It may be used in one of two ways: as a lifecycle
 * annotation, to delete a given entity instance or instances, or as an
 * automatic query annotation, to delete all entities satisfying parameter-based
 * conditions.
 * </p>
 *
 * <p>A {@code Delete} method might accept an instance or instances of an entity
 * class. In this case, the method must
 * have exactly one parameter whose type is either:
 * </p>
 * <ul>
 *     <li>the class of the entity to be deleted, or</li>
 *     <li>{@code List<E>} or {@code E[]} where {@code E} is the class of the entities to be deleted.</li>
 * </ul>
 * <p>The annotated method must be declared {@code void}.
 * </p>
 * <p>All Jakarta Data providers are required to accept a {@code Delete} method which conforms to this signature.
 * </p>
 * <p>For example, consider an interface representing a garage:</p>
 * <pre>
 * &#64;Repository
 * interface Garage {
 *     &#64;Delete
 *     void unpark(Car car);
 * }
 * </pre>
 * <p>Deletes are performed by matching the unique identifier of the entity. If the entity is versioned, for example,
 * with {@code jakarta.persistence.Version}, the version is also checked for consistency. Attributes other than the
 * identifier and version do not need to match. If no entity with a matching identifier is found in the database, or
 * if the entity with a matching identifier does not have a matching version, the annotated method must raise
 * {@link jakarta.data.exceptions.OptimisticLockingFailureException}.
 * </p>
 * <p>
 * An event of type {@link jakarta.data.event.PreDeleteEvent} must be raised by the annotated lifecycle
 * method before each record is deleted. An event of type {@link jakarta.data.event.PostDeleteEvent}
 * must be raised by the annotated lifecycle method after each record is successfully deleted.
 * </p>
 *
 * <p>Alternatively, the {@code Delete} annotation may be used to annotate a repository method with no parameter of an
 * entity type. Then the repository method is interpreted as a parameter-based automatic query method. The entity type
 * to be deleted is the primary entity type of the repository. The method return type must be {@code void}, {@code int},
 * or {@code long}. Every parameter of the annotated method must have exactly the same type and name (the parameter name
 * in the Java source, or a name assigned by {@link By @By}) as an attribute of
 * the entity class. A single parameter of type {@link Restriction} is allowed
 * after the other parameters. Parameters of type {@link Limit}, {@link Order},
 * {@link PageRequest}, and {@link Sort} are prohibited.
 * </p>
 * <p>For example, consider an interface representing a garage:</p>
 * <pre>
 * &#64;Repository
 * interface Garage
 *         extends DataRepository&lt;Car,String&gt; {
 *     &#64;Delete
 *     void unparkAll();
 *
 *     &#64;Delete
 *     void unpark(String registration);
 * }
 * </pre>
 * <p>Here,{@code unparkAll()} deletes every {@code Car}, while {@code unpark(String)} deletes any {@code Car} with a
 * matching value of its {@code registration} attribute.
 * </p>
 * <p>An automatic query method annotated {@code Delete} removes every record which satisfies the parameter-based
 * conditions from the database. If the method return type is {@code int} or {@code long}, the method must return the
 * number of deleted records.
 * </p>
 *
 * <p>Annotations such as {@code @Find}, {@code @Query}, {@code @Insert}, {@code @Update}, {@code @Delete}, and
 * {@code @Save} are mutually-exclusive. A given method of a repository interface may have at most one {@code @Find}
 * annotation, lifecycle annotation, or query annotation.
 * </p>
 *
 * @see By
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Delete {
}
