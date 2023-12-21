#ifndef INDEX_H
#define INDEX_H

#include <iostream>
#include <map>
#include <set>
#include "Location.h"

typedef std::string Word;
typedef std::set<Location> Locations;

class Index {
private:
    std::map<Word, Locations> _index;

public:
    void add_word(const Word& word, const Location& location);

    friend std::ostream& operator<<(std::ostream& os, const Index& index);
};

#endif 
