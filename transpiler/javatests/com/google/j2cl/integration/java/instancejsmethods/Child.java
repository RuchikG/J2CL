/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package instancejsmethods;

import jsinterop.annotations.JsMethod;

public class Child extends Parent implements MyInterface {
  /**
   * Non-JsMethod that overrides a JsMethod with renaming.
   */
  @Override
  public int fun(int a, int b) {
    return a + b + 1;
  }

  /**
   * Non-JsMethod that overrides a JsMethod without renaming.
   */
  @Override
  public int bar(int a, int b) {
    return a * b + 1;
  }

  /**
   * JsMethod that overrides a JsMethod.
   */
  @Override
  @JsMethod(name = "myFoo")
  public int foo(int a) {
    return a + 1;
  }

  /**
   * JsMethod that overrides a non-JsMethod from its interface.
   */
  @Override
  @JsMethod
  public int intfFoo(int a) {
    return a;
  }
}
