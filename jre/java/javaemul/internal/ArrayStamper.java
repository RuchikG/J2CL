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
package javaemul.internal;

import jsinterop.annotations.JsMethod;

/** A utility to provide array stamping. Provided as a separate class to simplify super-source. */
public class ArrayStamper {

  public static <T> T[] stampJavaTypeInfo(Object array, T[] referenceType) {
    T[] asArray = JsUtils.uncheckedCast(array);
    $copyType(asArray, referenceType);
    return asArray;
  }

  @JsMethod(namespace = "vmbootstrap.Arrays")
  public static native <T> void $copyType(T[] array, T[] referenceType);

  @JsMethod(namespace = "vmbootstrap.Arrays", name = "$isStamped")
  public static native boolean isStamped(Object array);
}
