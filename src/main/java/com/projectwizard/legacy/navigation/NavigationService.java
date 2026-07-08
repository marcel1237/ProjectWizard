package com.projectwizard.navigation;

public class NavigationService {

    private NavigationListener listener;

    public void setListener(NavigationListener listener) {

        this.listener = listener;

    }

    public void navigateTo(NavigationTarget target) {

        if (listener != null) {

            listener.navigateTo(target);

        }

    }

}
