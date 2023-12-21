#ifndef RECTANGLE_H
#define RECTANGLE_H

#include "Shape.h"

class Rectangle : public Shape {
public:
    Rectangle(double width, double height);
    virtual std::string name() const;
    virtual double area() const;

private:
    double width;  
    double height; 
};

#endif




