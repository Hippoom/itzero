package itzero.http;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexRestController {

    /**
     * list rels.
     *
     * @Return links to rels
     */
    @RequestMapping(value = "/rel/index", method = GET)
    public Resource index() {
        return new Resource<>("",
            linkTo(methodOn(IndexRestController.class).index()).withSelfRel());
    }

}
