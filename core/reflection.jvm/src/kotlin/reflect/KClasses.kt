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

@file:JvmName("KClasses")
package kotlin.reflect

import org.jetbrains.kotlin.descriptors.ConstructorDescriptor
import org.jetbrains.kotlin.types.*
import org.jetbrains.kotlin.utils.DFS
import kotlin.reflect.jvm.internal.KClassImpl
import kotlin.reflect.jvm.internal.KFunctionImpl
import kotlin.reflect.jvm.internal.KTypeImpl
import kotlin.reflect.jvm.internal.KTypeParameterImpl

/**
 * Returns the primary constructor of this class, or `null` if this class has no primary constructor.
 * See the [Kotlin language documentation](http://kotlinlang.org/docs/reference/classes.html#constructors)
 * for more information.
 */
val <T : Any> KClass<T>.primaryConstructor: KFunction<T>?
    get() = (this as KClassImpl<T>).constructors.firstOrNull {
        ((it as KFunctionImpl).descriptor as ConstructorDescriptor).isPrimary
    }


/**
 * Returns a [KClass] instance representing the companion object of a given class,
 * or `null` if the class doesn't have a companion object.
 */
val KClass<*>.companionObject: KClass<*>?
    get() = nestedClasses.firstOrNull {
        (it as KClassImpl<*>).descriptor.isCompanionObject
    }

/**
 * Returns an instance of the companion object of a given class,
 * or `null` if the class doesn't have a companion object.
 */
val KClass<*>.companionObjectInstance: Any?
    get() = companionObject?.objectInstance


/**
 * Returns a type corresponding to the given class with type parameters of that class substituted as the corresponding arguments.
 * For example, for class `MyMap<K, V>` [defaultType] would return the type `MyMap<K, V>`.
 */
@Deprecated("This function creates a type which rarely makes sense for generic classes. " +
            "For example, such type can only be used in signatures of members of that class. " +
            "Use starProjectedType or createType() for clearer semantics.")
val KClass<*>.defaultType: KType
    get() = KTypeImpl((this as KClassImpl<*>).descriptor.defaultType) { jClass }


/**
 * Returns all functions declared in this class, including all non-static methods declared in the class
 * and the superclasses, as well as static methods declared in the class.
 */
val KClass<*>.functions: Collection<KFunction<*>>
    get() = members.filterIsInstance<KFunction<*>>()

/**
 * Returns static functions declared in this class.
 */
val KClass<*>.staticFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(staticScope, declaredOnly = false, nonExtensions = true, extensions = true)
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns non-extension non-static functions declared in this class and all of its superclasses.
 */
val KClass<*>.memberFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(memberScope, declaredOnly = false, nonExtensions = true, extensions = false)
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns extension functions declared in this class and all of its superclasses.
 */
val KClass<*>.memberExtensionFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(memberScope, declaredOnly = false, nonExtensions = false, extensions = true)
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns all functions declared in this class.
 * If this is a Java class, it includes all non-static methods (both extensions and non-extensions)
 * declared in the class and the superclasses, as well as static methods declared in the class.
 */
val KClass<*>.declaredFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(memberScope, declaredOnly = true, nonExtensions = true, extensions = true)
            .plus(getMembers(staticScope, declaredOnly = true, nonExtensions = true, extensions = true))
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns non-extension non-static functions declared in this class.
 */
val KClass<*>.declaredMemberFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(memberScope, declaredOnly = true, nonExtensions = true, extensions = false)
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns extension functions declared in this class.
 */
val KClass<*>.declaredMemberExtensionFunctions: Collection<KFunction<*>>
    get() = (this as KClassImpl)
            .getMembers(memberScope, declaredOnly = true, nonExtensions = false, extensions = true)
            .filterIsInstance<KFunction<*>>()
            .toList()

/**
 * Returns static properties declared in this class.
 * Only properties representing static fields of Java classes are considered static.
 */
val KClass<*>.staticProperties: Collection<KProperty0<*>>
    get() = (this as KClassImpl)
            .getMembers(staticScope, declaredOnly = false, nonExtensions = true, extensions = false)
            .filterIsInstance<KProperty0<*>>()
            .toList()

/**
 * Returns non-extension properties declared in this class and all of its superclasses.
 */
val <T : Any> KClass<T>.memberProperties: Collection<KProperty1<T, *>>
    get() = (this as KClassImpl<T>)
            .getMembers(memberScope, declaredOnly = false, nonExtensions = true, extensions = false)
            .filterIsInstance<KProperty1<T, *>>()
            .toList()

/**
 * Returns extension properties declared in this class and all of its superclasses.
 */
val <T : Any> KClass<T>.memberExtensionProperties: Collection<KProperty2<T, *, *>>
    get() = (this as KClassImpl<T>)
            .getMembers(memberScope, declaredOnly = false, nonExtensions = false, extensions = true)
            .filterIsInstance<KProperty2<T, *, *>>()
            .toList()

/**
 * Returns non-extension properties declared in this class.
 */
val <T : Any> KClass<T>.declaredMemberProperties: Collection<KProperty1<T, *>>
    get() = (this as KClassImpl<T>)
            .getMembers(memberScope, declaredOnly = true, nonExtensions = true, extensions = false)
            .filterIsInstance<KProperty1<T, *>>()
            .toList()

/**
 * Returns extension properties declared in this class.
 */
val <T : Any> KClass<T>.declaredMemberExtensionProperties: Collection<KProperty2<T, *, *>>
    get() = (this as KClassImpl<T>)
            .getMembers(memberScope, declaredOnly = true, nonExtensions = false, extensions = true)
            .filterIsInstance<KProperty2<T, *, *>>()
            .toList()


/**
 * Immediate superclasses of this class, in the order they are listed in the source code.
 * Includes superclasses and superinterfaces of the class, but does not include the class itself.
 */
val KClass<*>.superclasses: List<KClass<*>>
    get() = supertypes.mapNotNull { it.classifier as? KClass<*> }

/**
 * All supertypes of this class, including indirect ones, in no particular order.
 */
val KClass<*>.allSupertypes: Collection<KType>
    get() = DFS.dfs(
            supertypes,
            DFS.Neighbors { current ->
                val klass = current.classifier as? KClass<*> ?: throw KotlinReflectionInternalError("Supertype not a class: $current")
                val supertypes = klass.supertypes
                val typeArguments = current.arguments
                if (typeArguments.isEmpty()) supertypes
                else run {
                    val parameters = klass.typeParameters.map { (it as KTypeParameterImpl).descriptor }
                    if (parameters.size != typeArguments.size) {
                        throw KotlinReflectionInternalError("Wrong number of type arguments in a supertype: $current")
                    }
                    // TODO: be sure to test inner generics
                    // TODO: can this be done easier?
                    val substitution = IndexedParametersSubstitution(
                            parameters,
                            typeArguments.mapIndexed { index, projection ->
                                val kotlinType = (projection.type as KTypeImpl).type
                                when (projection) {
                                    is KTypeProjection.Invariant -> TypeProjectionImpl(Variance.INVARIANT, kotlinType)
                                    is KTypeProjection.In -> TypeProjectionImpl(Variance.IN_VARIANCE, kotlinType)
                                    is KTypeProjection.Out -> TypeProjectionImpl(Variance.OUT_VARIANCE, kotlinType)
                                    KTypeProjection.Star -> StarProjectionImpl(parameters[index])
                                }
                            }
                    )
                    supertypes.map { supertype ->
                        supertype as KTypeImpl

                        val substituted = TypeSubstitutor.create(substitution).substitute(supertype.type, Variance.INVARIANT)
                                          ?: throw KotlinReflectionInternalError("Substitution failed: $supertype")
                        KTypeImpl(substituted) {
                            // TODO
                            TODO("Java type for supertype")
                        }
                    }
                }
            },
            DFS.VisitedWithSet(),
            object : DFS.NodeHandlerWithSetResult<KType, KType>() {
                override fun beforeChildren(current: KType): Boolean {
                    result.add(current)
                    return true
                }
            }
    )

/**
 * All superclasses of this class, including indirect ones, in no particular order.
 * Includes superclasses and superinterfaces of the class, but does not include the class itself.
 */
val KClass<*>.allSuperclasses: Collection<KClass<*>>
    get() = allSupertypes.mapNotNull { it.classifier as? KClass<*> }
