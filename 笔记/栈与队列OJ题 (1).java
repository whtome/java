括号匹配:
class Solution {
    public boolean isValid(String s) {
        // str -> char[]
        char[] data = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : data) {
            // 碰到左括号入栈
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            }else {
                if (stack.isEmpty()) {
                    return false;
                }
                else if (c == '}') {
                    char temp = stack.peek();
                    if (temp == '{') {
                        stack.pop();
                        continue;
                    }
                    else
                        return false;
                }
                else if (c == ']') {
                    char temp = stack.peek();
                    if (temp == '[') {
                        stack.pop();
                        continue;
                    }
                    else
                        return false;
                }
                else if (c == ')') {
                    char temp = stack.peek();
                    if (temp == '(') {
                        stack.pop();
                        continue;
                    }
                    else
                        return false;
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }
}

用队列模拟栈:
class MyStack {
    private Queue<Integer> queueA = new LinkedList<>();
    private Queue<Integer> queueB = new LinkedList<>();
    /** Initialize your data structure here. */
    public MyStack() {

    }

    /** Push element x onto stack. */
    public void push(int x) {
        // 保证所有元素都在一个队列中
        if (queueA.isEmpty()) {
            queueB.add(x);
        }else {
            queueA.add(x);
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queueA.isEmpty()) {
            int len = queueB.size();
            for (int i = 0;i < len - 1;i++) {
                // 所有元素依次放入队列A中
                queueA.add(queueB.poll());
            }
            // 队列B的最后一个元素一定是最后进入的
            // 刚好是栈的栈顶元素
            int result = queueB.poll();
            return result;
        }
        else {
            int len = queueA.size();
            for (int i = 0;i < len - 1;i++) {
                // 所有元素依次放入队列A中
                queueB.add(queueA.poll());
            }
            // 队列B的最后一个元素一定是最后进入的
            // 刚好是栈的栈顶元素
            int result = queueA.poll();
            return result;
        }
    }

    /** Get the top element. */
    public int top() {
        if (queueA.isEmpty()) {
            int len = queueB.size();
            for (int i = 0;i < len - 1;i++) {
                // 所有元素依次放入队列A中
                queueA.add(queueB.poll());
            }
            // 队列B的最后一个元素一定是最后进入的
            // 刚好是栈的栈顶元素
            int result = queueB.poll();
            queueA.add(result);
            return result;
        }
        else {
            int len = queueA.size();
            for (int i = 0;i < len - 1;i++) {
                // 所有元素依次放入队列A中
                queueB.add(queueA.poll());
            }
            // 队列B的最后一个元素一定是最后进入的
            // 刚好是栈的栈顶元素
            int result = queueA.poll();
            queueB.add(result);
            return result;
        }
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queueA.isEmpty() && queueB.isEmpty();
    }
}
