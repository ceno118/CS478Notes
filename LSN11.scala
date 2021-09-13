// this produces a new language (fred), just with an interpreter written in Scala
// using Scala's functions to implement fred's functions (metacircular interpreter)

// if there's a mismatch between fred and scala, you need to do something about it
// otherwise just use scala's stuff

enum Expr:
    case Num(n: Int)
    case Add(e1: Expr, e2: Expr)
    case Mul(e1: Expr, e2: Expr)
    // add a way to define a new name
    case Let(name: String, e: Expr, body = Expr) // let x(name) = 5(e) in x+x(body)
    case Var(name: String) // lets us use the variable we make with Let

// var env= Map.empty[String, Int] // environment used for keeping track of variables (easier but more limited way)
// works for small programs, stops working well with bigger ones
    // on big programs, you would need a brand new name for every variable (can't keep track)

type Env = Map[String, Int]

def eval(expr: Expr, env: Env): Int = expr match
    case Num(n) => n
    case Add(e1, e2) => eval(e1, env) + eval(e2, env)
    case Mul(e1, e2) => eval(e1, env) * eval(e2, env)
    case Let(nm, e, body) => 
        val x = eval(e, env)
        val env2 = env + (nm -> x)
        eval(body, env2)
        
        //env += (nm -> eval(e)) - bad way
        //eval(body)
    case Var(nm) => env(nm)

    // things that can refer to themselves in their definition:
        // functions, types, CFGs
    // things that can't refer to themselves in their definition:
        // pretty much everything else, including variables in these expressions
        // you can't do x = x + 1 because it would just keep referring to itself and never get defined