load("//build_defs:rules.bzl", "j2cl_library")

package(
    default_visibility = ["//transpiler/javatests/com/google/j2cl/integration:__subpackages__"],
    licenses = ["notice"],
)

j2cl_library(
    name = "box2d-j2cl",
    srcs = glob(["*.java"]),
    tags = ["manual"],
    deps = ["//third_party:jbox2d-j2cl"],
)

java_library(
    name = "box2d",
    srcs = glob(["*.java"]),
    tags = ["manual"],
    deps = ["//third_party:jbox2d"],
)
