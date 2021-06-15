/*
 * Decompiled with CFR 0.150.
 */
package org.spongepowered.asm.lib;

import org.spongepowered.asm.lib.Label;

class Handler {
    Label start;
    Label end;
    Label handler;
    String desc;
    int type;
    Handler next;

    Handler() {
    }

    static Handler remove(Handler handler, Label label, Label label2) {
        int n2;
        if (handler == null) {
            return null;
        }
        handler.next = Handler.remove(handler.next, label, label2);
        int n3 = handler.start.position;
        int n4 = handler.end.position;
        int n5 = label.position;
        int n6 = n2 = label2 == null ? Integer.MAX_VALUE : label2.position;
        if (n5 < n4 && n2 > n3) {
            if (n5 <= n3) {
                if (n2 >= n4) {
                    handler = handler.next;
                } else {
                    handler.start = label2;
                }
            } else if (n2 >= n4) {
                handler.end = label;
            } else {
                Handler handler2 = new Handler();
                handler2.start = label2;
                handler2.end = handler.end;
                handler2.handler = handler.handler;
                handler2.desc = handler.desc;
                handler2.type = handler.type;
                handler2.next = handler.next;
                handler.end = label;
                handler.next = handler2;
            }
        }
        return handler;
    }
}

