package by.bsuir.abmyotkashevtsov.controller;

/**
 * An instance of this class is used as return value of all Command classes.
 * The object of this class holds the path where you want to go and transfer method: forward or redirect.
 */
public class Router {
    public enum RouteType {
        FORWARD, REDIRECT
    }
    private String pagePath;
    private RouteType route = RouteType.FORWARD;

    public Router() {
    }

    public Router(String pagePath) {
        this.pagePath = pagePath;
    }

    public Router(String pagePath, RouteType route) {
        this.pagePath = pagePath;
        this.route = route;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if (route == null) {
            this.route = RouteType.FORWARD;
        }
        this.route = route;
    }
}
