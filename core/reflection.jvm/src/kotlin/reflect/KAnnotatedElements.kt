/*
 * Copyright 2010-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("KAnnotatedElements")
package kotlin.reflect

/**
 * Returns all annotations of the given type on this element.
 * Note that the resulting list may contain more than one element if the requested annotation class is annotated with [Repeatable].
 */
inline fun <reified T : Annotation> KAnnotatedElement.findAnnotation(): List<T> =
        annotations.filterIsInstance<T>()
