load(
    "//transpiler/javatests/com/google/j2cl/readable:readable_example.bzl",
    "readable_example",
)

package(licenses = ["notice"])

readable_example(
    srcs = glob(["**/*.java"]),
    # TODO(b/202429123): Support escaping Kotlin Keywords.
    build_kt_readables = False,
)
