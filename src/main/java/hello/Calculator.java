package hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
public class Calculator {
    @GetMapping("/")
    public String index() {
        return """
                <html>
                    <body style='font-family: sans-serif; text-align: center; margin-top: 50px;'>
                        <h2> Калькулятор</h2>
                        <form action='/calc' method='get'>
                            <input type='text' name='expr' placeholder='2+2*2' size='20'/>
                            <button type='submit'>Обчислити</button>
                        </form>
                    </body>
                </html>
                """;
    }

    @GetMapping("/calc")
    public String calc(@RequestParam String expr) {
        try {
            Expression expression = new ExpressionBuilder(expr).build();
            double result = expression.evaluate();
            return String.format("""
                <html>
                    <body style='font-family:sans-serif;text-align:center;margin-top:50px;'>
                        <h3>Вираз: %s</h3>
                        <h2>Результат: %s</h2>
                        <a href='/'>Назад</a>
                    </body>
                </html>
                """, expr, result);
        } catch (Exception e) {
            return String.format("""
                <html><body style='text-align:center;'>
                    <h3>Помилка обчислення!</h3>
                    <p>%s</p>
                    <a href='/'>Назад</a>
                </body></html>
                """, e.getMessage());
        }
    }

    public static double evaluate(String expr) {
        Expression expression = new ExpressionBuilder(expr).build();
        return expression.evaluate();
    }
}
