// function composition example from class

DList[A] = List[A] => List[A] // returns the first list with some stuff attached to the front

def empty[A]: DList[A] = list => list // it adds an empty list to the front so 
//it just returns the same thing
