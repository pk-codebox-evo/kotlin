/*
 * Copyright 2010-2015 JetBrains s.r.o.
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

package kotlin.jvm.internal

import kotlin.reflect.*

class ClassReference(override val jClass: Class<*>) : KClass<Any>, ClassBasedDeclarationContainer {
    override val simpleName: String?
        get() = error()

    override val qualifiedName: String?
        get() = error()

    override val members: Collection<KCallable<*>>
        get() = error()

    override val constructors: Collection<KFunction<Any>>
        get() = error()

    override val nestedClasses: Collection<KClass<*>>
        get() = error()

    override val annotations: List<Annotation>
        get() = error()

    override val objectInstance: Any?
        get() = error()

    override fun isInstance(value: Any?): Boolean = error()

    override val typeParameters: List<KTypeParameter>
        get() = error()

    override val supertypes: List<KType>
        get() = error()

    override val visibility: KVisibility?
        get() = error()

    override val isFinal: Boolean
        get() = error()

    override val isOpen: Boolean
        get() = error()

    override val isAbstract: Boolean
        get() = error()

    override val isSealed: Boolean
        get() = error()

    override val isData: Boolean
        get() = error()

    override val isInner: Boolean
        get() = error()

    override val isCompanion: Boolean
        get() = error()

    private fun error(): Nothing = throw KotlinReflectionNotSupportedError()

    override fun equals(other: Any?) =
            other is ClassReference && jClass == other.jClass

    override fun hashCode() =
            jClass.hashCode()

    override fun toString() =
            jClass.toString() + Reflection.REFLECTION_NOT_AVAILABLE
}
