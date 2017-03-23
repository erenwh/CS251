#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <map>
#include <unordered_map>
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

void subsetsum(std::string s[], int length) {
    int passwordCount = 0;
    int index[length];

    while(true) {
        std::string tempPassword;
        for (int i = 0; i < length; ++i) {
            tempPassword.at(i) = ALPHABET[index[i]];
            s[passwordCount] = tempPassword;
        }
        for (int i = length - 1;; i--) {
            if (i < 0)
                goto end;
            index[i]++;
            if (index[i] == R)
                index[i] = 0;
            else
                break;
        }
        passwordCount++;
    }
    end:
    free(index);
}

void Symbol::decrypt(const std::string &encrypted) {
    // take a subset S of the table T
    int s1 = C / 2;
    int s2 = C - C/2;
    std::string s1Array[s1];
    std::string s2Array[s2];
    // compute all possible subset sums that can be made with
    /*for (int i = 0; i < s1passw; ++i) {
        // compute subset sums

        // put those values into a symbol table


    }
    for (int i = 0; i < s2passw; ++i) {
        // compute subset sums
        std::string temp;
        Key passkey(T[i]);
        Key sss = passkey.subset_sum(T,verbose);
        // put those values into a symbol table
        s1passw.push_back(sss);
    }*/
    std::thread s1t(subsetsum, &s1Array, s1);
    std::thread s2t(subsetsum, &s2Array, s2);
    s1t.join();
    s2t.join();


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

    Symbol s(table_filename);

    s.decrypt(encrypted);


    return 0;
}
