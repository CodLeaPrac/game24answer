package calclatour;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 使用后缀表达式完成计算器 支持 + - / * ()
 *
 */
public class Calculator {

    private Calc calc;


    public Calculator() {
        calc = new Calc();
    }


    public static void main(String[] args) {

        String exp = "( 5 / 3 ) * 6 * 4";
        Calculator calculator = new Calculator();
        int cal = calculator.cal(exp);
        System.out.println(cal);


    }


    /**
     * 根据表达式计算结果
     * @param s 要计算的表达式
     * @return 计算结果
     */
    public int cal(String s) {
        //中缀转后缀
        List<String> stringList = this.calc.convertTosuffixExpression(s);

        return this.calc.calcBySuffixExpression(stringList);
    }


    /**
     * 后缀表达式计算
     */
    static class Calc {

        private static final String ADD = "+";
        private static final String SUB = "-";
        private static final String MULTI = "*";
        private static final String DEVIDE = "/";

        /**
         * 中缀表达式 转换 后缀表达式
         *
         * @param expre 中缀表达式字符串
         * @return 后缀表达式
         */
        public List<String> convertTosuffixExpression(String expre) {

            Stack<String> stack = new Stack<>();
            List<String> stringList = new ArrayList<>();
            String[] s = expre.split(" ");
            for (String s1 : s) {
                if (s1.matches("\\d+")) {
                    stringList.add(s1);
                } else if ("(".equals(s1)) {
                    stack.push(s1);
                } else if (")".equals(s1)) {
                    while (! stack.peek().equals("(")) {
                        stringList.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (stack.size() != 0 && getPriority(stack.peek()) >= getPriority(s1)) {
                        stringList.add(stack.pop());
                    }
                    stack.push(s1);
                }
            }

            while (stack.size() != 0) {
                stringList.add(stack.pop());
            }

            return stringList;
        }

        /**
         * 根据后缀表达式计算结果
         *
         * @param suffixExpression 后缀表达式
         * @return 计算结果
         */
        public int calcBySuffixExpression(List<String> suffixExpression) {

            Stack<String> stack = new Stack<>();
            for (String s : suffixExpression) {

                //分割的字符串是否是数字
                if (s.matches("\\d+")) {
                    //yes
                    stack.push(s);

                } else {
                    String pop = stack.pop();
                    String pop1 = stack.pop();
                    try {
                        int calc = calc(Integer.parseInt(pop), Integer.parseInt(pop1), s);
                        stack.push("" + calc);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            return Integer.parseInt(stack.pop());
        }

        //计算 根据两个操作数和操作符
        public int calc(int num1, int num2, String oper) {

            int result = 0;
            if (ADD.equals(oper)) {
                //+
                result = num2 + num1;
            } else if (SUB.equals(oper)) {
                //-
                result = num2 - num1;
            } else if (MULTI.equals(oper)) {
                //*
                result = num2 * num1;
            } else if (DEVIDE.equals(oper)) {
                // /
                if(num1 != 0) {
                    result = num2 / num1;
                }
            } else {
                throw new RuntimeException("操作符不合法！");
            }

            return result;
        }

        public int getPriority(String oper) {
            int priority = 0;
            switch (oper) {
                case ADD:
                    priority = 1;
                    break;
                case SUB:
                    priority = 1;
                    break;
                case MULTI:
                    priority = 2;
                    break;
                case DEVIDE:
                    priority = 2;
                    break;
                default:
//                System.out.println("不存在操作符" + oper);
            }

            return priority;
        }

    }

}


