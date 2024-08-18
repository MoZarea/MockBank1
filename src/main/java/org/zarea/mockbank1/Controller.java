package org.zarea.mockbank1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @PostMapping("/balance")
    public Response<BalanceResponse> balance() {
        BalanceResponse balanceResponse = new BalanceResponse(
                "30000"
        );
        return new Response<>(200,"done",balanceResponse);
    }
}
