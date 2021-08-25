def parser(str: String): Expr = 
    val stk = scala.collection.mutable.Stack.empty[Expr]
    for c <- str do
        if c.isDigit then
            stk.push(Num(c - '0')) // c is a character that has an ascii value 
            //the correct number away from the char '0's ascii value
        else if c == '+' then
            val arg2 = stk.pop()
            val arg1 = stk.pop()
            stk.pushAdd((arg1 + arg2))
        else if c == '*' then
            val arg2 = stk.pop()
            val arg1 = stk.pop()
            stk.push(Mul(arg1 * arg2))
        else if c == '-' then
            val arg2 = stk.pop()
            val arg1 = stk.pop()
            stk.push(arg1 - arg2)
    if stk.size > 1 then throw ???
    stk.pop()

// this takes a string in RPN and turns it into an 
// abstract syntax tree using the expr we defined before