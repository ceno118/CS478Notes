enum YNMorB: // This is a sum type
	case YesNo(answer: Boolean)
	case YesNoMaybe(answer: Option[Boolean])*/

/* 
How to represent 3 options?
	can't use Bool, thats 2
	integer has too many options, leads to bugs
	you could create a new type
	you can also use an Option
*/
	
import YNMorB.{YesNo, YesNoMaybe} // this allows you to use the cases outside of the type itself

/* 
This is a sum type because the total number of options is the sum of possible options
in both cases. (first case yes no = 2, second yes no maybe = 3, 2+3 = 5)
A sum type is either one or the other case, not both 
*/

case class Name(first: String, last: String) // this is a product type
/* product because it must have both elements
	say there's 10 first names, 20 last names, theres 10x20 = 200 possible names (product)
*/

//2*5+1

enum Expr: // expression
    case Num(n: Int)
    case Add(e1: Expr, e2: Expr)
    case Mul(e1: Expr, e2: Expr)
    case Fact(e: Expr)
    //case Parens(e: Expr) - you wouldnt use a case for parentheses
/* This ^^^ is called an "abstract syntax tree"
abstract because it focuses on the important information (doesn't use parentheses)
tree bc it could be represented like this:
    2 * 5 + 1
        +
       / \
      *   1
     / \
    2   5
*/
// 2*5+1 is represented by:
/*Add(Mul(Num(2),Num(5)),Num(1))


    2 * (5 + 1)
        *
       / \
      2   +
         / \
        5   1
// 2*(5+1) is represented by:
Mul(Num(2), Add(Num(3), Num(1)))
*/

def eval(expr: Expr): Int = expr match
    case Num(n) => n
    case Add(e1, e2) => eval(e1) + eval(e2)
    case Mul(e1,e2) => eval(e1) * eval(e2)
    case Fact(e) => {
        val n = eval(e)
        (2 to n).product //multiplies all numbers to to n
    }

/* metacircular interpreter
you're interpreting something written in one language using things in the same language
*/