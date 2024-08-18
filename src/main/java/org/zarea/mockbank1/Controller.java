package org.zarea.mockbank1;

import org.springframework.web.bind.annotation.*;

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
    public Response<?> withdraw(@RequestHeader String token, @RequestBody WithdrawMoneyRequest request) {
        System.out.println(token);
        if (!token.equals("BOE-0112-XgF0")) {
            throw new IllegalArgumentException("Invalid token");
        }
        if (Integer.parseInt(request.getAmount()) > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= Integer.parseInt(request.getAmount());
        return new Response<>(200,"done",null);
    }
    @PostMapping("/deposit")
    public Response<?> deposit(@RequestHeader String token, @RequestBody DepositMoneyRequest request) {
        System.out.println(token);
        if (!token.equals("BOE-0112-XgF0")) {
            throw new IllegalArgumentException("Invalid token");
        }
        balance += Integer.parseInt(request.getAmount());
        return new Response<>(200,"done",null);
    }
}
