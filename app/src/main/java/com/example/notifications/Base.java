package com.example.notifications;

public class Base {
    Integer a;
    Integer b;
    String c;
    private Base() {}

    static class Builder {
        private Base base;
        public Builder() {
            base = new Base();
        }

        public Builder setA(Integer a1) {
            base.a = a1;
            return this;
        }
        public Builder setB(Integer b1) {
            base.b = b1;
            return this;
        }
        public Builder setC(String c1) {
            base.c = c1;
            return this;
        }
        public Base build() {
            return base;
        }
    }
}
