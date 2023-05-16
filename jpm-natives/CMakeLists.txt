# Hopped onto CLion to make a few native methods

cmake_minimum_required(VERSION 3.25)
project(jpm_natives)

set(CMAKE_CXX_STANDARD 17)

# include Java
if (CMAKE_CXX_COMPILER MATCHES "mingw")
    include_directories("./jdk20-win")
    include_directories("./jdk20-win/win32")
elseif (CMAKE_CXX_COMPILER MATCHES "linux")
    include_directories("./jdk20-linux/")
    include_directories("./jdk20-linux/linux/")
endif ()

add_library(jpm SHARED src/AdminCheck.cpp)

# Kinda have to do that on Linux
if (CMAKE_CXX_COMPILER MATCHES "mingw")
    set_target_properties(jpm PROPERTIES PREFIX "")
    set_target_properties(jpm PROPERTIES SUFFIX ".dll")
endif ()