package com.fengshiqing.common.javase.Test;

import java.io.IOException;

//压栈出栈的方法实现字符串反转
public class StringReverserThroughStack {

    private final String input;

    public StringReverserThroughStack(String in) {// constron
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        Stack theStack = new Stack(stackSize);
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            theStack.push(ch);
        }
        StringBuilder output = new StringBuilder();
        while (!theStack.isEmpty()) {
            char ch = theStack.pop();
            output.append(ch);
        }
        return output.toString();
    }

    static class Stack {// 内部类：成员内部类
        private final char[] stackArray;
        private int top;

        public Stack(int max) {
            stackArray = new char[max];
            top = -1;
        }

        public void push(char j) {
            stackArray[++top] = j;
        }

        public char pop() {
            return stackArray[top--];
        }

        public char peek() {
            return stackArray[top];
        }

        public boolean isEmpty() {
            return (top == -1);
        }
    }

    public static void main(String[] args) throws IOException {
        String input = "www.w3cschool.cc";
        String output;
        StringReverserThroughStack theReverser = new StringReverserThroughStack(input);
        output = theReverser.doRev();
        System.out.println("反转前： " + input);
        System.out.println("反转后： " + output);
    }
}