java_binary(
    name = "Toy-Robot-Simulator",
    srcs = ["src/se/sthlm/jfw/toyrobot/Robot.java"],
    main_class = "se.sthlm.jfw.toyrobot.Robot",
    deps = [":data", ":model"],
)

java_library(
    name = "data",
    srcs = glob(["src/se/sthlm/jfw/toyrobot/data/*.java"]),
)

java_library(
    name = "model",
    srcs = glob(["src/se/sthlm/jfw/toyrobot/model/*.java"]),
)