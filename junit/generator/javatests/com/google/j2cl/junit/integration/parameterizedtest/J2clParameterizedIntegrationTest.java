/*
 * Copyright 2022 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.j2cl.junit.integration.parameterizedtest;

import static org.junit.Assume.assumeFalse;

import com.google.j2cl.junit.integration.IntegrationTestBase;
import com.google.j2cl.junit.integration.TestResult;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/** Integration test for j2cl JUnit4 parameterized test support. */
@RunWith(Parameterized.class)
public class J2clParameterizedIntegrationTest extends IntegrationTestBase {

  @Before
  public void assumeNonJ2wasm() {
    // TODO(b/233963223): J2wasm does not support parameterized test yet.
    assumeFalse(testMode.isJ2wasm());
  }

  @Test
  public void testSimpleParameterizedTest() throws Exception {
    String testClassName = "SimpleParameterizedTest";
    String[][] data = {{"1", "0"}, {"2", "1"}, {"2", "3"}};
    TestResult testResult =
        createTestResultForSimplePassingTest(testClassName, data, "test", "test2");

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testParameterizedTestInitializedByConstructor() throws Exception {
    String testClassName = "ParameterizedTestInitializedByConstructor";
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test[0]")
            .addTestSuccess("testGroup0_test2[0]")
            .addTestSuccess("testGroup1_test[1]")
            .addTestSuccess("testGroup1_test2[1]")
            .addJavaLogLineSequence("0abc", "1abc")
            .addJavaLogLineSequence("0", "2")
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testMultipleDataMethodsTest() throws Exception {
    String testClassName = "MultipleDataMethodsTest";
    String[][] data = {{"0", "0"}, {"1", "1"}};
    TestResult testResult =
        createTestResultForSimplePassingTest(testClassName, data, "test", "test2");

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  private TestResult createTestResultForSimplePassingTest(
      String testClassName, String[][] data, String... enclosingTestNames) {
    TestResult.Builder testResultBuilder = newTestResultBuilder().testClassName(testClassName);
    int numberOfTestCases = data.length;
    for (int j = 0; j < numberOfTestCases; j++) {
      for (String enclosingTestName : enclosingTestNames) {
        testResultBuilder.addTestSuccess(
            String.format("testGroup%d_%s[%d]", j, enclosingTestName, j));
      }
    }
    for (String[] eachTestCase : data) {
      testResultBuilder.addJavaLogLineSequence(eachTestCase);
    }
    return testResultBuilder.build();
  }

  @Test
  public void testMultipleIterableParametersTest() throws Exception {
    String testClassName = "MultipleIterableParametersTest";
    String[][] data = {{"3", "2", "5"}, {"4", "3", "6"}};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test1[0]")
            .addTestSuccess("testGroup0_test2[0]")
            .addTestSuccess("testGroup0_test3[0]")
            .addTestSuccess("testGroup1_test1[1]")
            .addTestSuccess("testGroup1_test2[1]")
            .addTestSuccess("testGroup1_test3[1]")
            .addJavaLogLineSequence(data[0])
            .addJavaLogLineSequence(data[1])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testSingleArrayParameterTest() throws Exception {
    String testClassName = "SingleArrayParameterTest";
    String[] data = {"0", "1"};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test[0]")
            .addTestSuccess("testGroup1_test[1]")
            .addJavaLogLineSequence(data[0])
            .addJavaLogLineSequence(data[1])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testSingleIterableParameterTest() throws Exception {
    String testClassName = "SingleIterableParameterTest";
    String[] data = {"0", "1"};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test[0]")
            .addTestSuccess("testGroup1_test[1]")
            .addJavaLogLineSequence(data[0])
            .addJavaLogLineSequence(data[1])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testSingleIterableParameterProvidedByArrayTest() throws Exception {
    String testClassName = "SingleIterableParameterProvidedByArrayTest";
    String[] data = {"0", "1"};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test[0]")
            .addTestSuccess("testGroup1_test[1]")
            .addJavaLogLineSequence(data[0])
            .addJavaLogLineSequence(data[1])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testSingleParameterOfArrayTypeTest() throws Exception {
    String testClassName = "SingleParameterOfArrayTypeTest";

    String[][] data = {{"3", "2", "5"}, {"4", "3", "6"}};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_test[0]")
            .addTestSuccess("testGroup1_test[1]")
            .addJavaLogLineSequence(Arrays.toString(data[0]))
            .addJavaLogLineSequence(Arrays.toString(data[1]))
            .build();
    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testParentTest() throws Exception {
    String testClassName = "ParentTest";
    String[][] data = {{"2", "3"}, {"3", "4"}};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_testParent[0]")
            .addTestSuccess("testGroup1_testParent[1]")
            .addJavaLogLineSequence(data[0][0])
            .addJavaLogLineSequence(data[1][0])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }

  @Test
  public void testChildTest() throws Exception {
    String testClassName = "ChildTest";
    String[][] data = {{"abc", "bcd"}, {"cde", "def"}};
    TestResult testResult =
        newTestResultBuilder()
            .testClassName(testClassName)
            .addTestSuccess("testGroup0_testChild1[0]")
            .addTestSuccess("testGroup0_testChild2[0]")
            .addTestSuccess("testGroup0_testParent[0]")
            .addTestSuccess("testGroup1_testChild1[1]")
            .addTestSuccess("testGroup1_testChild2[1]")
            .addTestSuccess("testGroup1_testParent[1]")
            .addJavaLogLineSequence(data[0][0], data[0][1], data[0][0])
            .addJavaLogLineSequence(data[1][0], data[1][1], data[1][0])
            .build();

    List<String> logLines = runTest(testClassName);
    assertThat(logLines).matches(testResult);
  }
}
