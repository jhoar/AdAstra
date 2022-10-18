package esa.sci.ops.adastra.spring;

import esa.sci.ops.adastra.spring.generated.api.WpApiDelegate;
import esa.sci.ops.adastra.spring.generated.model.WorkPackage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpApiImplementation implements WpApiDelegate {

    @Override
    public ResponseEntity<List<WorkPackage>> wpGet(String wpid) {
        List<WorkPackage> wps = List.of(new WorkPackage().id(1).wpid("WP-1000").description("Hello"));
        return new ResponseEntity<>(wps, HttpStatus.OK);
    }
}
