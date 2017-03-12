cmake_minimum_required(VERSION 3.6)
project(project3)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        src/brute.cpp
        src/brute.hpp
        src/encrypt.cpp
        src/key.hpp
        src/symbol.cpp
        src/symbol.hpp
        src/timer.hpp)

add_executable(project3 ${SOURCE_FILES})