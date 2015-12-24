cmake_minimum_required(VERSION 3.3)
project(elements_prog_interview)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES
    main.cpp)

add_executable(elements_prog_interview ${SOURCE_FILES})