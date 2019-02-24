package com.interpreter.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.interpreter.lox.TokenType.*;

/*
 * Scanner to loop through the source code (as string) to add
 * each scanned token to a list.
 */

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }

        tokens.add(scanToken(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            default:
                Lox.error(line, "Unexpected character.");
                break;
        }
    }

    // Check if the loop reached the end of the source file.
    private boolean isAtEnd() {
        return current >= source.length();
    }

    // Return the next character in the source file.
    private char advance() {
        current++;
        return source.charAt(current -1);
    }

    // Create a new token for the current lexeme.
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    // For tokens with literal values.
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}

