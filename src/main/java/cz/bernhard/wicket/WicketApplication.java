package cz.bernhard.wicket;

import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.config.AtmosphereParameters;
import org.apache.wicket.atmosphere.config.AtmosphereTransport;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.time.Time;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see cz.bernhard.wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

        EventBus eventBus = new EventBus(this);
        AtmosphereParameters parameters = eventBus.getParameters();
        parameters.setTransport(AtmosphereTransport.LONG_POLLING);

    }
}
