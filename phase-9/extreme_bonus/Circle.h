#ifndef CIRCLE_H
#define CIRCLE_H

#include "Shape.h"

class Circle : public Shape {
public:
    Circle(double radius);
    virtual std::string name() const;
    virtual double area() const;

private:
    double radius; 
};

#endif




