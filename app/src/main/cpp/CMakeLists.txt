cmake_minimum_required(VERSION 3.4.1)

add_library(hello-jni SHARED
            hello-jni.c)

add_library(app-utils SHARED
            app-utils.c)

# Include libraries needed for hello-jni lib
target_link_libraries(hello-jni
                      android
                      log)

# Include libraries needed for app-utils lib
target_link_libraries(app-utils
                      android)