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

package org.jetbrains.kotlin.js.test.semantics;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/codegen/box/innerNested")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class InnerNestedTestGenerated extends AbstractInnerNestedTest {
    @TestMetadata("innerGenericClassFromJava.kt")
    public void ignoredInnerGenericClassFromJava() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/innerGenericClassFromJava.kt");
        doTest(fileName);
    }

    @TestMetadata("innerJavaClass.kt")
    public void ignoredInnerJavaClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/innerJavaClass.kt");
        doTest(fileName);
    }

    @TestMetadata("protectedNestedClass.kt")
    public void ignoredProtectedNestedClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/protectedNestedClass.kt");
        doTest(fileName);
    }

    @TestMetadata("protectedNestedClassFromJava.kt")
    public void ignoredProtectedNestedClassFromJava() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/protectedNestedClassFromJava.kt");
        doTest(fileName);
    }

    public void testAllFilesPresentInInnerNested() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("compiler/testData/codegen/box/innerNested"), Pattern.compile("^(.+)\\.kt$"), true);
    }

    @TestMetadata("createNestedClass.kt")
    public void testCreateNestedClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/createNestedClass.kt");
        doTest(fileName);
    }

    @TestMetadata("createdNestedInOuterMember.kt")
    public void testCreatedNestedInOuterMember() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/createdNestedInOuterMember.kt");
        doTest(fileName);
    }

    @TestMetadata("extensionFun.kt")
    public void testExtensionFun() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/extensionFun.kt");
        doTest(fileName);
    }

    @TestMetadata("extensionToNested.kt")
    public void testExtensionToNested() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/extensionToNested.kt");
        doTest(fileName);
    }

    @TestMetadata("importNestedClass.kt")
    public void testImportNestedClass() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/importNestedClass.kt");
        doTest(fileName);
    }

    @TestMetadata("innerGeneric.kt")
    public void testInnerGeneric() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/innerGeneric.kt");
        doTest(fileName);
    }

    @TestMetadata("innerLabeledThis.kt")
    public void testInnerLabeledThis() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/innerLabeledThis.kt");
        doTest(fileName);
    }

    @TestMetadata("innerSimple.kt")
    public void testInnerSimple() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/innerSimple.kt");
        doTest(fileName);
    }

    @TestMetadata("kt3132.kt")
    public void testKt3132() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/kt3132.kt");
        doTest(fileName);
    }

    @TestMetadata("kt3927.kt")
    public void testKt3927() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/kt3927.kt");
        doTest(fileName);
    }

    @TestMetadata("kt5363.kt")
    public void testKt5363() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/kt5363.kt");
        doTest(fileName);
    }

    @TestMetadata("kt6804.kt")
    public void testKt6804() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/kt6804.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedClassInObject.kt")
    public void testNestedClassInObject() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedClassInObject.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedClassObject.kt")
    public void testNestedClassObject() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedClassObject.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedEnumConstant.kt")
    public void testNestedEnumConstant() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedEnumConstant.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedGeneric.kt")
    public void testNestedGeneric() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedGeneric.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedInPackage.kt")
    public void testNestedInPackage() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedInPackage.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedObjects.kt")
    public void testNestedObjects() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedObjects.kt");
        doTest(fileName);
    }

    @TestMetadata("nestedSimple.kt")
    public void testNestedSimple() throws Exception {
        String fileName = KotlinTestUtils.navigationMetadata("compiler/testData/codegen/box/innerNested/nestedSimple.kt");
        doTest(fileName);
    }
}
