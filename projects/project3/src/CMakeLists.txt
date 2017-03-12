cmake_minimum_required(VERSION 3.6)
project(project3)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES
        brute.cpp
        brute.hpp
        encrypt.cpp
        key.hpp
        symbol.cpp
        symbol.hpp
        timer.hpp)

add_executable(project3 ${SOURCE_FILES})