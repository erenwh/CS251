#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <map>
#include <cmath>
#include <thread>
#include <cstring>

#include "key.hpp"
#include "symbol.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;


Symbol::Symbol(const std::string &filename) {
    T.resize(N);
    std::string buffer;
    std::fstream input(filename.c_str(), std::ios::in);
    for (int i = 0; i < N; i++) {
        std::getline(input, buffer);
        T[i].set_string(buffer);
    }
    input.close();

    // insert your code here
}


void Symbol::decrypt(const std::string &encrypted) {
    // take a subset S of the table T
    int s1 = (int) pow(R, C / 2);
    int s2 = (int) pow(R, C - C/2);
    std::vector s1passw<std::string>[s1];
    std::vector s2passw<std::string>[s2];
    // compute all possible subset sums that can be made with
    for (int i = 0; i < s1passw->size(); ++i) {
        // compute subset sums
        // put those values into a symbol table
    }
    for (int i = 0; i < s2passw->size(); ++i) {
        // compute subset sums
        // put those values into a symbol table
    }

    std::map<Key, std::string, std::less> st;
    Key ssum;
    std::string password;
    // then use that symbol table to check all those possibilities with ideally one but at most a few lookups
    for (int i = 0; i < s1; ++i) {
        for (int j = 0; j < s2; ++j) {
            // combine password from both vectors
            // encrypt temp password
            // compute the subset sum using table T
        }
    }
}


void usage(const std::string &error_msg = "") {
    if (!error_msg.empty()) std::cout << "ERROR: " << error_msg << '\n';
    std::cout << me << ": Symbol table-based cracking of Subset-sum password"
              << " with " << B << " bits precision\n"
              << "USAGE: " << me << " <encrypted> <table file> [options]\n"
              << "\nArguments:\n"
              << " <encrypted>:   encrypted password to crack\n"
              << " <table file>:  name of file containing the table to use\n"
              << "\nOptions:\n"
              << " -h|--help:     print this message\n"
              << " -v|--verbose:  select verbose mode\n\n";
    exit(0);
}

void initialize(int argc, char *argv[]) {
    me = argv[0];
    if (argc < 3) usage("Missing arguments");
    encrypted = argv[1];
    table_filename = argv[2];
    for (int i = 3; i < argc; ++i) {
        std::string arg = argv[i];
        if (arg == "-h" || arg == "--help") usage();
        else if (arg == "-v" || arg == "--verbose") verbose = true;
        else usage("Unrecognized argument: " + arg);
    }
}


int main(int argc, char *argv[]) {
    initialize(argc, argv);
    // TODO



    return 0;
}
