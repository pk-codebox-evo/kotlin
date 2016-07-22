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

package org.jetbrains.kotlin.codegen.binding

import com.intellij.psi.util.PsiTreeUtil
import org.jetbrains.kotlin.codegen.AsmUtil
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.fileClasses.JvmFileClassesProvider
import org.jetbrains.kotlin.fileClasses.getFileClassInternalName
import org.jetbrains.kotlin.psi.*
import org.jetbrains.kotlin.resolve.DescriptorToSourceUtils.descriptorToDeclaration
import org.jetbrains.org.objectweb.asm.Type

object PsiCodegenPredictor {

    fun checkPredictedNameFromPsi(
            descriptor: DeclarationDescriptor,
            nameFromDescriptors: Type?,
            fileClassesManager: JvmFileClassesProvider): Boolean {
        val element = descriptorToDeclaration(descriptor)
        if (element is KtDeclaration) {
            val classNameFromPsi = getPredefinedJvmInternalName(element, fileClassesManager)
            assert(classNameFromPsi == null || Type.getObjectType(classNameFromPsi) == nameFromDescriptors) {
                String.format("Invalid algorithm for getting qualified name from psi! Predicted: %s, actual %s\n" +
                              "Element: %s", classNameFromPsi, nameFromDescriptors, element.text)
            }
        }

        return true
    }

    /**
     * @return null if no prediction can be done.
     */
    fun getPredefinedJvmInternalName(
            declaration: KtDeclaration,
            fileClassesProvider: JvmFileClassesProvider): String? {
        // TODO: Method won't work for declarations inside companion objects
        // TODO: Method won't give correct class name for traits implementations

        if (declaration is KtPropertyAccessor) {
            return getPredefinedJvmInternalName(declaration.property, fileClassesProvider)
        }
        val parentDeclaration = KtStubbedPsiUtil.getContainingDeclaration(declaration)

        val parentInternalName: String?
        if (parentDeclaration != null) {
            parentInternalName = getPredefinedJvmInternalName(parentDeclaration, fileClassesProvider)
            if (parentInternalName == null) {
                return null
            }
        }
        else {
            val containingFile = declaration.getContainingKtFile()

            if (declaration is KtNamedFunction || declaration is KtProperty) {
                val name = (declaration as KtNamedDeclaration).nameAsName
                return if (name == null) null else fileClassesProvider.getFileClassInternalName(containingFile) + "$" + name.asString()
            }

            parentInternalName = AsmUtil.internalNameByFqNameWithoutInnerClasses(containingFile.packageFqName)
        }

        if (!PsiTreeUtil.instanceOf(declaration, KtClass::class.java, KtObjectDeclaration::class.java, KtNamedFunction::class.java, KtProperty::class.java)
            || isEnumEntryWithoutBody(declaration)) {
            // Other subclasses are not valid for class name prediction.
            // For example JetFunctionLiteral
            return null
        }

        val name = (declaration as KtNamedDeclaration).nameAsName ?: return null

        if (declaration is KtNamedFunction) {
            if (!(parentDeclaration is KtClass || parentDeclaration is KtObjectDeclaration)) {
                // Can't generate predefined name for internal functions
                return null
            }
        }

        // NOTE: looks like a bug - for class in getter of top level property class name will be $propertyName$ClassName but not
        // PackageClassName$propertyName$ClassName
        if (declaration is KtProperty) {
            return parentInternalName + "$" + name.asString()
        }

        if (parentInternalName.isEmpty()) {
            return name.asString()
        }

        return parentInternalName + (if (parentDeclaration == null) "/" else "$") + name.asString()
    }

    private fun isEnumEntryWithoutBody(declaration: KtDeclaration): Boolean {
        if (declaration !is KtEnumEntry) {
            return false
        }
        val body = declaration.getBody()
        return body == null || body.declarations.size == 0
    }
}
