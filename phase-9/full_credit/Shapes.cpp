#include "Shape.h"
#include "Rectangle.h"
#include "Circle.h"
#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <iomanip> 

int main() {
    std::vector<Shape*> shapes;
    srand(time(NULL)); 

    double radius, length, width;

    length = static_cast<double>(rand() % 15 + 1);
    width = static_cast<double>(rand() % 15 + 1);
    Rectangle* rectangle = new Rectangle(length, width);
    shapes.push_back(rectangle);

   
    radius = static_cast<double>(rand() % 5 + 1);
    Circle* circle = new Circle(radius);
    shapes.push_back(circle);

    for (const Shape* shape : shapes) {
        std::cout << std::fixed << std::setprecision(6); 
        std::cout << shape->to_string() << std::endl;
    }

    for (Shape* shape : shapes) {
        delete shape;
    }

    return 0;
}


