load(
    "//transpiler/javatests/com/google/j2cl/readable:readable_example.bzl",
    "readable_example",
)

package(licenses = ["notice"])

readable_example(
    srcs = glob([
        "*.java",
        "*.native.js",
    ]),
    # TODO(b/204593842): [J2KT] Figure out how to deal with JS native types
    build_kt_readables = False,
    generate_wasm_readables = False,
    deps = ["//third_party:gwt-jsinterop-annotations-j2cl"],
)
