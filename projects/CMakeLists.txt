cmake_minimum_required(VERSION 3.6)
project(CS251)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        project3/brute.cpp
        project3/brute.hpp
        project3/encrypt.cpp
        project3/key.hpp
        project3/symbol.cpp
        project3/symbol.hpp
        project3/timer.hpp)

add_executable(CS251 ${SOURCE_FILES})