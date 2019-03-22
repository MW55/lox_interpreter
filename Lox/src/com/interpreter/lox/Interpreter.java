package com.interpreter.lox;

class Interpreter implements Expr.Visitor<Object> {

    // Evaluate literals.
    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    // Evaluate unary expressions.
    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case MINUS:
                return -(double)right;
        }

        // Unreachable.
        return null;
    }

    // Evaluate parenthesis.
    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression)
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }
}