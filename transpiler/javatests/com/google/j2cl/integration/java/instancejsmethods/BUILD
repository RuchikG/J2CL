load("//build_defs:rules.bzl", "j2cl_library")
load(
    "//transpiler/javatests/com/google/j2cl/integration:integration_test.bzl",
    "integration_test",
)

package(licenses = ["notice"])

j2cl_library(
    name = "SuperParent",
    srcs = ["SuperParent.java"],
)

j2cl_library(
    name = "MyInterface",
    srcs = ["MyInterface.java"],
)

j2cl_library(
    name = "Parent",
    srcs = ["Parent.java"],
    deps = [
        ":SuperParent",
        "//third_party:gwt-jsinterop-annotations-j2cl",
    ],
)

j2cl_library(
    name = "Child",
    srcs = ["Child.java"],
    deps = [
        ":MyInterface",
        ":Parent",
        "//third_party:gwt-jsinterop-annotations-j2cl",
    ],
)

integration_test(
    name = "instancejsmethods",
    srcs = [
        "Main.java",
        "Main.native.js",
    ],
    # Contains JsInterop features which are only applicable for JS output.
    enable_jvm_test = False,
    enable_kt = False,
    enable_wasm = False,
    deps = [
        ":Child",
        ":MyInterface",
        ":Parent",
        ":SuperParent",
        "//third_party:gwt-jsinterop-annotations-j2cl",
    ],
)
