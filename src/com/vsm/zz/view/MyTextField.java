package com.vsm.zz.view;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * @author Zhu
 * @createtime 2020/9/30-16:09
 */
public class MyTextField extends JTextField {

    public MyTextField() {
        this.setDocument(new NumberTextField());
    }


    class NumberTextField extends PlainDocument {
        public NumberTextField() {
            super();
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if (str == null) {
                return;
            }

            char[] s = str.toCharArray();
            int length = 0;
            // 过滤非数字
            for (int i = 0; i < s.length; i++) {
                if ((s[i] >= '0') && (s[i] <= '9')) {
                    s[length++] = s[i];
                }
                // 插入内容
                super.insertString(offs, new String(s, 0, length), a);
            }
        }

    }
}
