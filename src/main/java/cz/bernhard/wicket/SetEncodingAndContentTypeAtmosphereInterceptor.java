package cz.bernhard.wicket;

import org.atmosphere.cpr.Action;
import org.atmosphere.cpr.AtmosphereInterceptorAdapter;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResponse;

/**
 * Setting UTF-8 encoding otherwise atmosphere + wicket stops working on IE10, IE8 and in other browsers
 * (tested in Firefox 26 and Chrome 32) it has broken encoding.
 *
 * @author Michal Bernhard (michal@bernhard.cz) 2014
 */
public class SetEncodingAndContentTypeAtmosphereInterceptor extends AtmosphereInterceptorAdapter {

    @Override
    public Action inspect(AtmosphereResource resource) {

        AtmosphereResponse response = resource.getResponse();
        response.setCharacterEncoding("UTF-8");

        return Action.CONTINUE; // just continue
    }

}