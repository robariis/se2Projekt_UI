package org.hbrs.se2.project.hellocar.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.hbrs.se2.project.hellocar.control.ManageCarControl;
import org.hbrs.se2.project.hellocar.dtos.impl.CarDTOImpl;
import org.hbrs.se2.project.hellocar.util.Globals;

@Route(value = "registration")
@PageTitle("User Registration")
@CssImport("./styles/views/entercar/enter-car-view.css")
public class RegistrationView extends Div {

    private TextField brand = new TextField("Benutzername");
    private TextField model = new TextField("E-Mail");
    private PasswordField password = new PasswordField("Passwort");
    private PasswordField passwordConfirmation = new PasswordField("Passwort (Wdh)");

    private Button register = new Button("Registrieren");

    private Binder<CarDTOImpl> binder = new Binder<>(CarDTOImpl.class);

    public RegistrationView(ManageCarControl carService) {
        addClassName("enter-car-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        register.addClickListener(e -> {
            if (password.getValue().equals(passwordConfirmation.getValue())) {
                Notification.show("Passwörter stimmen überein. User wurde registriert.");
                clearForm();
                UI.getCurrent().navigate(Globals.Pages.LOGIN_VIEW);
            } else {
                Notification.show("Die eingegebenen Passwörter stimmen nicht überein.");
            }
        });
    }

    private void clearForm() {
        binder.setBean(new CarDTOImpl());
        password.clear();
        passwordConfirmation.clear();
    }

    private H3 createTitle() {
        return new H3("Benutzer-Registration");
    }

    private FormLayout createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(brand, model, password, passwordConfirmation);
        return formLayout;
    }

    private HorizontalLayout createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        register.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(register);
        return buttonLayout;
    }
}
