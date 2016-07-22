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

package org.jetbrains.kotlin.resolve.jvm

import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName


// Internal name:  kotlin/Map$Entry
// FqName:         kotlin.Map.Entry
class JvmClassName private constructor(val internalName: String) {

    private var fqName: FqName? = null

    /**
     * WARNING: internal name cannot be converted to FQ name for a class which contains dollars in the name
     */
    val fqNameForClassNameWithoutDollars: FqName
        get() {
            if (fqName == null) {
                this.fqName = FqName(internalName.replace('$', '.').replace('/', '.'))
            }
            return fqName!!
        }

    val packageFqName: FqName
        get() {
            val lastSlash = internalName.lastIndexOf("/")
            if (lastSlash == -1) return FqName.ROOT
            return FqName(internalName.substring(0, lastSlash).replace('/', '.'))
        }

    override fun toString(): String {
        return internalName
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return internalName == (other as JvmClassName).internalName
    }

    override fun hashCode(): Int {
        return internalName.hashCode()
    }

    companion object {
        @JvmStatic fun byInternalName(internalName: String): JvmClassName {
            return JvmClassName(internalName)
        }

        @JvmStatic fun byClassId(classId: ClassId): JvmClassName {
            val packageFqName = classId.packageFqName
            val relativeClassName = classId.relativeClassName.asString().replace('.', '$')
            return if (packageFqName.isRoot)
                JvmClassName(relativeClassName)
            else
                JvmClassName(packageFqName.asString().replace('.', '/') + "/" + relativeClassName)
        }

        /**
         * WARNING: fq name cannot be uniquely mapped to JVM class name.
         */
        @JvmStatic fun byFqNameWithoutInnerClasses(fqName: FqName): JvmClassName {
            val r = JvmClassName(fqName.asString().replace('.', '/'))
            r.fqName = fqName
            return r
        }

        @JvmStatic fun byFqNameWithoutInnerClasses(fqName: String): JvmClassName {
            return byFqNameWithoutInnerClasses(FqName(fqName))
        }
    }
}
