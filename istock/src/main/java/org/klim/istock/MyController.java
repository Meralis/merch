package org.klim.istock;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public Product getTestMessage() {
        return new Product();
    }
}
