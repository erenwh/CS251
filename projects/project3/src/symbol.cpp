#include <cstdlib>
#include <fstream>
#include <iostream>
#include <string>
#include <map>
#include <cmath>
#include <thread>

#include "key.hpp"
#include "symbol.hpp"

std::string me;
std::string encrypted;
std::string table_filename;
bool verbose = false;
std::map<Key, char *, std::less<Key>> st;


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
    int firstHalf = (int) pow(R, C / 2);
    int secondHalf = (int) pow(R, C - C / 2);
    std::vector<std::string> *firstHalfVector = new std::vector<std::string>[firstHalf];
    std::vector<std::string> *secondHalfVector = new std::vector<std::string>[secondHalf];


    std::thread firstThread(Symbol::getSubsetSum, &firstHalfVector, C / 2);
    std::thread secondThread(Symbol::getSubsetSum, &secondHalfVector, C - C / 2);

    firstThread.join();
    secondThread.join();


    for (int i = 0; i < firstHalf; ++i) {
        for (int j = 0; j < secondHalf; ++j) {
            char *password = (char *) malloc((C + 1) * sizeof(char));
            strcat(password, firstHalfVector[i]);
            strcat(password, secondHalfVector[j]);
            Key encryptedKey = Key(password);
            const Key k = encryptedKey.subset_sum(T);
            st[k] = password;
        }
    }
}

void Symbol::getSubsetSum(char ***array, int length) {
    int count = 0;
    int *indeces = (int *) calloc(length, sizeof(int));

    while (true) {
        char *pass = (char *) malloc((length + 1) * sizeof(char));
        for (int i = 0; i < length; ++i) {
            pass[i] = ALPHABET[indeces[i]];
            pass[length] = '\0';
            (*array)[count] = pass;
        }
        for (int i = length - 1;; i--) {
            if (i < 0) {
                goto end;
            }
            indeces[i]++;
            if (indeces[i] == R) {
                indeces[i] = 0;
            } else {
                break;
            }
        }
        count++;
    }
    end:
    free(indeces);
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
    // try to decrypt
    s.decrypt(s.(encrypted));
    // print result
    /*for (int i = 0; i < st.size(); ++i) {
        std::cout << st << '\n';
    }*/



    return 0;
}
