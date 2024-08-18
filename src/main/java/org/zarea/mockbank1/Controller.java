package org.zarea.mockbank1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    int balance = 30000;
    @PostMapping("/balance")
    public Response<BalanceResponse> balance(@RequestHeader String token) {
        System.out.println(token);
        if (!token.equals("BOE-0112-XgF0")) {
            throw new IllegalArgumentException("Invalid token");
        }
        BalanceResponse balanceResponse = new BalanceResponse(
                String.valueOf(balance)
        );
        return new Response<>(200,"done",balanceResponse);
    }
    @PostMapping("/withdraw")
    public Response<?> withdraw(@RequestHeader String token, @RequestHeader int amount) {
        System.out.println(token);
        if (!token.equals("BOE-0112-XgF0")) {
            throw new IllegalArgumentException("Invalid token");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
        return new Response<>(200,"done",null);
    }
    @PostMapping("/deposit")
    public Response<?> deposit(@RequestHeader String token, @RequestHeader int amount) {
        System.out.println(token);
        if (!token.equals("BOE-0112-XgF0")) {
            throw new IllegalArgumentException("Invalid token");
        }
        balance += amount;
        return new Response<>(200,"done",null);
    }
}
