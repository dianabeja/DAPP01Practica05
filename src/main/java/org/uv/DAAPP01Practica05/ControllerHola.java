
package org.uv.DAAPP01Practica05;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 *
 * @author bagdi
 */

@RestController
@RequestMapping("/api/")
public class ControllerHola {
    @GetMapping(path= "hola")
    public String sayHola(){
        return "Hola Mundo";
    }
}
