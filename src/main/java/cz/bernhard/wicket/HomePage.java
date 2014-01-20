package cz.bernhard.wicket;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormSubmitBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.atmosphere.EventBus;
import org.apache.wicket.atmosphere.Subscribe;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;

import java.util.Date;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    private final Label messageFromOthers;
    private final Label messageArrivalTime;
    private final TextField<String> textToSend;
    private final Form form;
    private final AjaxButton sendButton;


    public HomePage(final PageParameters parameters) {
		super(parameters);

        add(messageFromOthers = new Label("messageFromOthers", new Model<String>("")));
        messageFromOthers.setOutputMarkupId(true);

        add(messageArrivalTime = new Label("messageArrivalTime", new Model<Date>()));
        messageArrivalTime.setOutputMarkupId(true);

        add(form = new Form("form"));
        form.add(textToSend = new TextField<String>("textToSend", new Model<String>("")));
        textToSend.setOutputMarkupId(true);

        form.add(sendButton = new AjaxButton("send", form) {

            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

                EventBus.get().post(textToSend.getModelObject());

                textToSend.setModelObject(""); // empty text field
                target.add(textToSend);

            }
        });

    }

    @Subscribe
    public void onMessage(AjaxRequestTarget target, String message) {

        messageFromOthers.setDefaultModelObject(message);
        messageArrivalTime.setDefaultModelObject(/* now */ new Date());

        target.add(messageFromOthers);

    }
}
