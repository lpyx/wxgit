package com.lc;

public class TestN {
    public static void main(String[] args) {
        boolean a = new TestN().isNumber("+.8");
        System.out.printf(a+"");
    }
    public boolean isNumber(String s) {
        //e 前面的数字
        //e 后面的数字

        // 前面有没有数字
        boolean hasNumBeforeZero = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '-' || c == '+') {
                if (i != 0) {
                    return false;
                }
                if (i == s.length() - 1) {
                    return false;
                }
                char d = s.charAt(i + 1);
                if ((d < '0' || d > '9')&& d != '.') {
                    return false;
                }

            } else if (c == '0') {
                if (!hasNumBeforeZero) {
                    //0后面必须是E或者是.或者没有数字
                    if (i == s.length() - 1) {
                        return true;
                    }
                    int next = s.charAt(i + 1);
                    if (next == '.' || next == 'e' || next == 'E') {

                    } else {
                        return false;
                    }

                }
            } else if (c == '.') {
                if (i == s.length() - 1) {
                    return false;
                }
                int next = s.charAt(i + 1);
                if (next >= '0' || next <= '9') {

                } else {
                    return false;
                }
            } else if (c >= '1' && c <= '9') {
                hasNumBeforeZero = true;
            } else if (c == 'e' || c == 'E') {
                if (i == 0) {
                    return false;
                }
                if (i == s.length() - 1) {
                    return false;
                }

                //有e之后后面必须为整数,不能为小数
                for (int j = i + 1; j < s.length(); j++) {
                    char c1 = s.charAt(j);
                    if (c1 == '-' || c1 == '+') {
                        if (j != i + 1) {
                            return false;
                        }
                        if (j == s.length() - 1) {
                            return false;
                        }
                        char d = s.charAt(j + 1);
                        if (d < '0' || d > '9') {
                            return false;
                        }

                    } else if (c1 == '0') {
                        if (!hasNumBeforeZero) {
                            //0后面必须是E或者是.或者没有数字
                            if (j == s.length() - 1) {
                                return true;
                            }
                            return false;

                        }
                    } else if (c1 >= '1' && c1 <= '9') {
                        hasNumBeforeZero = true;
                    } else {
                        return false;
                    }
                }

                break;
            } else {
                return false;
            }


        }
        return true;
    }
}
