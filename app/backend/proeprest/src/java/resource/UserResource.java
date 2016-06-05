/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import model.User;
import service.UserService;

/**
 * REST Web Service
 *
 * @author tycho
 */
@Path("user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    Response r;

    @GET
    @Path("name/{username}")
    public Response getUserByName(@PathParam("username") String username) {
        try {
            r = null;
            User u = UserService.getUserByName(username);
            if (u != null) {
                u.setPassword("");
                r = Response.ok(u).build();
            } else {
                r = Response.status(Response.Status.NOT_FOUND)
                        .entity("User does not exist")
                        .build();
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } finally {
            return r;
        }
    }

    @PUT
    @Path("update")
    public Response updateUser(User u) {
        r = null;
        try {
            if (UserService.updateUser(u)) {
                r = Response.noContent().build();
            } else {
                r = Response.status(Response.Status.NOT_FOUND)
                        .entity("Username not found")
                        .build();
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } finally {
            return r;
        }
    }

    @GET
    @Path("login/{username}")
    public Response login(@PathParam("username") String name, String pass) {
        r = null;
        try {
            int result = UserService.login(name, pass);
            switch (result) {
                case 1:
                    r = Response.ok().build();
                    break;
                case 0:
                    r = Response.status(Response.Status.UNAUTHORIZED)
                            .entity("Password incorrect")
                            .build();
                    break;
                case -1:
                    r = Response.status(Response.Status.NOT_FOUND)
                            .entity("Username not found")
                            .build();
                    break;
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } finally {
            return r;
        }
    }

    @POST
    @Path("signup")
    public Response signup(User u) {
        r = null;
        try {
            boolean result = UserService.createUser(u);
            if (result) {
                r = Response.status(Response.Status.CREATED).build();
            } else {
                r = Response.status(Response.Status.CONFLICT)
                        .entity("A user with that name already exists")
                        .build();
            }
        } catch (Exception e) {
            r = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(e.getMessage())
                    .build();
        } finally {
            return r;
        }
    }

}
