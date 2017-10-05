package challenge.controllers;

import challenge.entities.Character;
import challenge.services.PowerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rsanchpa on 27/09/2017.
 */
@Api(description = "Powers REST service")
@RestController
@RequestMapping("/power")
public class PowerController {

    @Autowired
    PowerService powerService;

    @RequestMapping(method= RequestMethod.GET, value="/search")
    @ApiOperation(value = "Obtain all possible Powers",
        notes = "The **userName** is a required parameter, the user must exists and must be active.")
    public List<String> findPowers()  {
        return this.powerService.obtainAllPowers();
    }
}
