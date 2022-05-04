load(
    "//transpiler/javatests/com/google/j2cl/integration:integration_test.bzl",
    "integration_test",
)

package(
    default_visibility = ["//transpiler/javatests/com/google/j2cl/integration:__subpackages__"],
    licenses = ["notice"],  # Apache 2.0
)

# Creates targets
# blaze build :optimized_js
# blaze test :compiled_test
# blaze test :uncompiled_test
integration_test(
    name = "autovalue",
    srcs = glob(["*.java"]),
    closure_defines = {"jre.classMetadata": "'STRIPPED'"},
    enable_kt = False,
    optimize_autovalue = True,
    deps = [
        "//jre/java:javaemul_internal_annotations-j2cl",
        "//third_party:auto_value-j2cl",
        "//third_party:gwt-jsinterop-annotations-j2cl",
    ],
)