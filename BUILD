java_binary(
    name = "Toy-Robot-Simulator",
    srcs = ["src/se/sthlm/jfw/toyrobot/Robot.java"],
    main_class = "se.sthlm.jfw.toyrobot.Robot",
    deps = [":data", 
            ":model",],
)

java_library(
    name = "data",
    srcs = glob(["src/se/sthlm/jfw/toyrobot/data/*.java"]),
)

java_library(
    name = "model",
    srcs = glob(["src/se/sthlm/jfw/toyrobot/model/*.java"]),
)

java_library(
    name = "Robot-Tests",
    srcs = ["src-test/se/sthlm/jfw/toyrobot/test/RobotNegativeTest.java",
            "src-test/se/sthlm/jfw/toyrobot/test/RobotTest.java",
            "src-test/se/sthlm/jfw/toyrobot/test/RobotTestSuite.java",
            "src-test/se/sthlm/jfw/toyrobot/test/RobotUnitTest.java",],
    deps = [":Toy-Robot-Simulator",
            ":data",
            ":model",
            "//third_party:junit4",]
)

java_test(
    name = "RobotTestSuite",
    test_class = "se.sthlm.jfw.toyrobot.test.RobotTestSuite",
    runtime_deps =  [":Robot-Tests"],
)

