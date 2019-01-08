/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensilex.core.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author vincent
 */
@Api("/hello")
@Path("/hello")
public class HelloWorldService {

    @GET
    @ApiOperation(value = "Say hello !")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Hello message"),
        @ApiResponse(code = 500, message = "Internal error")
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", required = true,
                dataType = "string", paramType = "header",
                value = "Access token given",
                example = "Bearer ")
    })
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello World !";
    }
}