#ifndef TIME_H
#define TIME_H

#include <iostream>
#include <iomanip>

class Time {
private:
    int _totalSeconds;

    void rationalize();

public:
    Time(int hour, int minute, int second);
    Time();
    Time(int hour);

    Time operator+(const Time& other) const;
    Time operator+(int seconds) const;

    Time& operator++();
    Time operator++(int);

    bool operator==(const Time& other) const;
    bool operator!=(const Time& other) const;
    bool operator<(const Time& other) const;
    bool operator>(const Time& other) const;
    bool operator<=(const Time& other) const;
    bool operator>=(const Time& other) const;

    friend std::ostream& operator<<(std::ostream& os, const Time& time);
    friend std::istream& operator>>(std::istream& is, Time& time);
};

Time operator+(int seconds, const Time& time);

#endif 



