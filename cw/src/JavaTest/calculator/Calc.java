package javaTest.caculater;


import java.math.BigDecimal;
import java.util.Scanner;
import java.util.Stack;

public class CalculatorClass {

    public static void main(String[] args) {
    	System.out.println("Enter your equation");
        Scanner scanner = new Scanner(System.in);
        String item = scanner.nextLine();
        Stack stack = new Stack();
        Stack stack1 = new Stack();
        boolean key = false;
        
        /**check whether brackets match
         * program terminates when they do not match
         */
        if(!bracketMatch(item))
        {
            System.out.println("Brackets do not match");
            return;
        }
        
        for(int i = 0; i < item.length(); i++)
        {

            char c = item.charAt(i);
            //check whether the operands are valid symbols and numbers
            while (!validOperator(c) && !isNumber(c))
            {
                ++i;
                if(i < item.length())
                c = item.charAt(i);
                if( i == item.length())
                    break;
            }
            if( i == item.length())
                break;
            StringBuilder sb = new StringBuilder("");
            if (c == '-' && key)
            {
                ++i;
                if(i < item.length())
                    c = item.charAt(i);
                sb.append('-');
            }
            while (isNumber(c) || c == '.')
            {
                key = false;
                sb.append(c);
                if (++i >= item.length()) break;
                c = item.charAt(i);
            }
            if (!sb.length() == 0){
                stack.push(new BigDecimal(SB.toString()));

            }
            if (c == '^')
            {
	            if((!stack1.empty()) && ((char)stack1.peek() == '^'))
	        
		              {
		                 stack.push(stack1.pop());
		              }
	            }
            if (c == '*' || c == '/' || c == '+' || c == '-')
            {

                if((!stack1.empty()))
                    if( ((char)stack1.peek() == '*') || ((char)stack1.peek() == '/') || ((char)stack1.peek() == '^'))
                    {
                       stack.push(stack1.pop());
                    }
            }
            if( c == '+' || c == '-')
            {
                if((!stack1.empty()))
                    if( ((char)stack1.peek() == '*') || ((char)stack1.peek() == '/') || ((char)stack1.peek() == '+') || ((char)stack1.peek() == '-') || ((char)stack1.peek() == '^'))
                    {
                        stack.push(stack1.pop());
                    }
            }
            if (validOperator(c))
            {
                stack1.push(c);
                key = true;
            }
            if(c == ')')
            {
                char cha = c;
                while (cha != '(') {
                     cha = (char) stack1.pop();
                     if(cha != '(' && cha != ')')
                         stack.push(cha);
                }
            }
        }
        while (!stack1.empty())
        {
            stack.push(stack1.pop());
            
        }
        
        Stack stack2 = new Stack<>();

        while (!stack.empty())
        {
            stack2.push(stack.pop());
        }
        while (!stack2.empty()) {
            Object c = stack2.pop();
            if (c.getClass() == BigDecimal.class) {
                stack1.push((BigDecimal) c);
                continue;
            }
            else
            {
                if ((char) c == '*' && (stack1.size() != 1))
                    stack1.push( ( (BigDecimal)stack1.pop() ).multiply( (BigDecimal) stack1.pop() ) );
                if ((char) c == '^' && (stack1.size() != 1)) {
                    BigDecimal a = (BigDecimal) stack1.pop();
                if(a.intValue() >= 1)
                    stack1.push(((BigDecimal) stack1.pop()).pow(a.intValue()));
                    else {
                        stack1.push(new BigDecimal(Math.pow(((BigDecimal) (stack1.pop())).doubleValue(), a.doubleValue())));
                   }
                }
                if ((char) c == '/' && (stack1.size() != 1)) {
                    Double a = ((BigDecimal) stack1.pop()).doubleValue();
                    stack1.push (new BigDecimal( ( (Double) ( ( (BigDecimal)stack1.pop() ).doubleValue() / a)).toString()));
                }
                if ((char) c == '+' && (stack1.size() != 1))
                    stack1.push(( (BigDecimal)stack1.pop() ).add( (BigDecimal) stack1.pop() ));
                if ((char) c == '-')
                {
                    BigDecimal a = (BigDecimal) stack1.pop();
                    if(!stack1.empty() && stack1.peek().getClass() != char.class )
                    {
                        stack1.push(((BigDecimal) stack1.pop()).subtract(a));
                    }
                    else
                        stack1.push((a).multiply(new BigDecimal(-1)));
                }
            }
        }
        System.out.printf("%.6s",stack1.pop());
    }
    //method to check whether character is a number
    public static boolean isNumber(char c)
    {
        //if (c >= '0' && c <= '9')
          //  return true;
        //else
          //  return false;
	    return (c >= '0' && c<='9');
    }
    public static boolean validOperator(char c)
    {
       // if(c == '+' || c == '-' || c == '*'|| c == '/' || c == '(' || c == '^')
           // return true;
        //else return false;
	    return (c == '+' || c == '-' || c == '*'|| c == '/' || c == '(' || c == '^');
    }
    //check whether the braces match
    public static boolean bracketMatch(String string)
    {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < string.length(); i++)
        {

            if(string.charAt(i) == '(')
                stack.push('(');
            if(string.charAt(i) == ')')
                if(stack.empty() || stack.pop() != '(')
                    return false;
        }
       if(!stack.empty())
           return false;
        return true;
    }

}
