#313467441
#alimale

Div -
* i checked if the both arguments are the same (by comparing the strings calling "toString" function- in that case i return 1
* i checked if the right argument is 1(by comparing the argument(by calling to string) to the string "1") in that case i return the left argument
* i checked if the left argument is 0 (by comparing it to the string "0"), in that case i  return 0
* i handle comotative espression like the div of x+y and y+x, and xy and yx by creating a new  methods
1) is comatative 2) return the from right to left string - i checked if the expression types are comatative - if they are
i compare the strings with the from right to left string
* i handle number / (number*expression) and number / (expression*number) by returning a number/expression (to do so i checked if the left argument
is a number and the right argument is from type mult) - than i made another check to find which argument in the right argument
is the number - by that i calculated the expression.
examples: 5/(2*x) && 5/*(x*) . it works even if the x represent not a var but an expression. 
* i handle a situation of Div/Div by checking if both right ad left argument are from types Div. than i simplify the arguments and returned
a new Div expression with the simplify arguments

Log -
* i checked if the both arguments are the same (by comparing the strings calling "toString" function- in that case i return 1
in addition i handle with comatative expressions by checking if the left expression is comatative and compare it string from
right to left to the right argument's string
* i checked if the right argument is 1(by comparing the argument (calling to string) to the string "1") in that case i return 0

Minus - 
* i checked if the right argument is 0 (by comparing the argument's string to the string "0") - in that case i return the left argument
* i checked if the left argument is 0 (by comparing its string(calling toString method) to the string "0") - in that case i return the neg of the right argument
* i handle comatativ expression like (x+y)-(y+x) and (xy)-(yx)  by creating a new  methods
1) is comatative 2) return the from right to left string - i checked if the expression types are comatative - if they are
i compare the strings with the from right to left string
* i checked if the expression is from the type number - (Plus) - by checking the types of the arguments,
than i run another check to see if the plus is from (expression + number) or (number + expression) - in both
cases i return a number (calculated from num-num) - the expression.
examples : (x can represent not just a var but a complicated expression)
1) 5 - (x + 3)
2) 5 - (3 + x)
*  i checked if the expression is from the type  (Plus) - number - by checking the types of the arguments,
than i run another check to see if the plus is from (expression + number) or (number + expression) - in both
cases i return  the right expression. 
examples : (x can represent not just a var but a complicated expression)
1) (x + 3) - 5
2) (3 + x) - 5
*  i checked if the expression is from the type  (Plus) - expression- by checking the types of the arguments,
than i run another check to see if the plus is from (expression + number) or (number + expression) - in both
cases  if the expressions are equals (which can be checkes easily by comparing their strings) i return  the right expression. 
examples : (x can represent not just a var but a complicated expression)
1) (x + 5) - x
2) (5 + x ) - x
* i checked if the expression is from the type  (Mult) - (Mult)by checking the types of the arguments,
than i run another check to see if the right mult expression if expression*num or num*expression, the same checked was done
to the right argument.. than i checked if the expressions in both sides are equals (by calling the "toString" method)
and in that case i return a number(calulated base on the number in the both expression)* the expression
examples : (x can represent not just a var but a complicated expression)
1) 2*x - 3*x
2) 2*x - x*5
3) x*2 - 3*x
4) x*2 - x*5

Pow - 
* i checked if the left argument is 1 - by comparing its string to "1" - in that case i return 1
* i checked if the right argument is 0 by comparing its string to "0" - in that case i return 1
* i checked if the right argument is 1 by comparing its string to "1" - in that case i return the left expression
* i handle(expression1^expression2)^expression3  by
checking the type of the left argument.. if its type is pow i return expression1^(expression2*expression3)
example (x^y)^z - the vars can represent complex expressions.

Mult - 
* i checked if the left argument is 0 - by comparing its string to "0" - in that case i return 0
* i checked if the right  argument is 0 - by comparing its string to "0" - in that case i return 0
* i checked if the right argument is 1 by comparing its string to "1" - in that case i return the left expression
* i checked if the left argument is 0 - by comparing its string to "1" - in that case i return the right expression
* i checked if the both arguments represent the same expression by comparing their string
representations - in that case i return the expression^2
*  i checked if the expression is from the type  (number) * (Mult) - by checking the types of the arguments,
than i run another check to see if the Mult is from (expression * number) or (number * expression) - in both
i return  num*expression 
examples : (x can represent not just a var but a complicated expression)
1) 5*(x*3)
2) 5*(3*x)
*  i checked if the expression is from the type   (Mult) * num - by checking the types of the arguments,
than i run another check to see if the Mult is from (expression * number) or (number * expression) - in both
cases   i return  num*expression 
examples : (x can represent not just a var but a complicated expression)
1) (x*3)*5
2) (3*x)*5
*  i checked if the expression is from the type   (Mult) * (Mult) - by checking the types of the arguments,
than i run another check to see if the the first Mult is from (expression * number) or (number * expression) - and
if the second mult expression is from (expression * number) or (number * expression) .
i had to distinguish in the 4 cases - to see if it is the same expression ( in that case i return num*expression^2)
and else i return num*expression*expression
examples : (x and y  can represent not just a var but a complicated expression)
1) (3x)*(3x) && (3x)*(3y)
2) (3x)*(x3) && (3x)*(y3)
3) (x3)*(3x) && (x3)*(3y)
4) (x3)*(x3) && (x3)*(y3)

Plus - 
* i checked if the left argument is 0 - by comparing its string to "0" - in that case i return the right argument
* i checked if the right argument is 0 - by comparing its string to "0" - in that case i return the left argument
* i checked if the both arguments represent the same thing (i also handle representing in a comatitive way)
by comparing their strings.. in that case i return 2*expression
* i checked if the expression is from the type number + (Plus) - by checking the types of the arguments,
than i run another check to see if the plus is from (expression + number) or (number + expression) - in both
cases i return a number (calculated from num+num) + the expression.
examples : (x can represent not just a var but a complicated expression)
1) 5 + (x + 3)
2) 5 + (3 + x)
*  i checked if the expression is from the type  (Plus) + number - by checking the types of the arguments,
than i run another check to see if the plus is from (expression + number) or (number + expression) - in both
cases i return a number (calculated from num+num) + the expression.  
examples : (x can represent not just a var but a complicated expression)
1) (x + 3) + 5
2) (3 + x) + 5
* i checked if the expression is from the type  (Mult) + (Mult)by checking the types of the arguments,
than i run another check to see if the right mult expression if expression*num or num*expression, the same checked was done
to the right argument.. than i checked if the expressions in both sides are equals (by calling the "toString" method)
and in that case i return a number(calulated base on the number in the both expression)* the expression
examples : (x can represent not just a var but a complicated expression)
1) 2*x + 3*x
2) 2*x + x*5
3) x*2 + 3*x
4) x*2 + x*5
* i checked if the expression is from the type  Plus+ Plus by checking the types of the arguments,
than i run another check to see if the right mult expression if expression+num or num+expression, the same checked was done
to the right argument.. than i checked if the expressions in both sides are equals (by calling the "toString" method)
and in that case i return a number(calulated base on the number in the both expression) + 2*the expression
if the strings are not equal i return number + expression + exprssion
examples : (x and y can represent not just a var but a complicated expression)
1) (2+x) + (2+x) && (2+x) + (2+y)
2) (2+x) + (x+2) && (2+x) + (y+2)
3) (x+2) + (2+x) && (x+2) + (2+y)
4) (x+2) + (x+2) && (x+2) + (y+2)
* i handle with a nested expression like in the example in the assignment - by checking if the 
left argument is from type mult and right argument is from type plus.. than i check in the right argument
its right argument is from type mult. than i checked is the 2 mults expressions share the same var - than i return
a new plus of new mult (calculated on the 2 mults) and an expression

note : plus and mult are comatative -  i activated a mehod that return the expression with an inner hirarcy -
example: (b+a) will be returned as a string of "(a+b)". this helps me handling with complex expression that represents
the same thing but are written differently.. in this method i used the compareTo method to find the "lowest" 
expression and return a fixed and match string