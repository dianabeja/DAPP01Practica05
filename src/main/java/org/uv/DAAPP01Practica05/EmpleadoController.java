package org.uv.DAAPP01Practica05;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {
    
    @Autowired
    private EmpleadoRepository repositoryEmpleado;
    
    @GetMapping("/empleado")
    public List<Empleado> list() {
        return repositoryEmpleado.findAll();
    }
    
    @GetMapping("/empleado/{id}")
    public Empleado get(@PathVariable Long id) {
        Optional<Empleado> optEmpleado=repositoryEmpleado.findById(id);
        if(optEmpleado.isPresent())
            return optEmpleado.get();
        else
            return null;
    }
    
    @PutMapping("/empleado/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody Empleado input) {
        try {
            Optional<Empleado> res = repositoryEmpleado.findById(id);
            if (res.isPresent()) {
                Empleado emp =res.get();
                emp.setClave(input.getClave());
                emp.setNombre(input.getNombre());
                emp.setDireccion(input.getDireccion());
                emp.setTelefono(input.getTelefono());
                
                repositoryEmpleado.save(emp);
                
                return ResponseEntity.ok("Actualizado ok");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("El ID del empleado no es válido");
        }
    }
    
    @PostMapping("/empleado/empleado")
    public ResponseEntity<?> post(@RequestBody Empleado input) {
        repositoryEmpleado.save(input);
        return ResponseEntity.ok("Guardado ok");
    }
    
    @DeleteMapping("/empleado/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repositoryEmpleado.deleteById(id);
        return ResponseEntity.ok("Eliminado ok");
    }
    
}
