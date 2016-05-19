var app = angular.module('myApp', ['ngResource','ngRoute', 'http-auth-interceptor']);

app.constant('USER_ROLES', {
    all: '*',
    admin: 'ADMIN',
    user: 'USER'
});

app.config( function($routeProvider, $locationProvider, USER_ROLES) {

    $routeProvider
        .when('/home', {
            templateUrl: '/views/home.html',
            controller: 'HomeController'
        })
        .when('/login', {
            templateUrl: '/views/login.html',
            controller: 'LoginController'
        })
        .when('/newOrder',{
            templateUrl: '/views/newOrder.html',
            controller: 'OrderController',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.all]
            }        })
        .when('/orders',{
            templateUrl: '/views/orders.html',
            controller: 'OrdersController',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.all]
            }
        })
        .when('/loading', {
            templateUrl: '/views/loading.html'
        })
        .when('/inventory',{
            templateUrl: '/views/inventory.html',
            controller: 'ItemsController',
            access: {
                loginRequired: true,
                authorizedRoles: [USER_ROLES.all]
            }
        })
        .when("/error/:code", {
            templateUrl: "/views/error.html",
            controller: "ErrorController"
        })
        .when("/logout", {
            template: " ",
            controller: "LogoutController"
        })
        .otherwise({
            redirectTo: '/home'
        });
});


app.run( function($rootScope, $location, AuthSharedService, Session, $timeout) {

    // Register listener to watch route changes
    $rootScope.$on("$routeChangeStart", function(event, next, current) {

        if(next.originalPath == "/login" && current ){
            $rootScope.previousPath = current.$$route.originalPath;
        }

        if(next.originalPath === "/login" && $rootScope.authenticated) {
            event.preventDefault();
        } else if (next.access && next.access.loginRequired && !$rootScope.authenticated) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-loginRequired", {});
        } else if (next.access && !AuthSharedService.isAuthorized(next.access.authorizedRoles)) {
            event.preventDefault();
            $rootScope.$broadcast("event:auth-forbidden", {});
        }
    });

    // Called when the the client is confirmed
    $rootScope.$on('event:auth-loginConfirmed', function (event, data) {

        $rootScope.loadingAccount = false;
        var nextLocation = "/home";
        var delay = $location.path() === "/loading" ? 1500 : 0;

        $timeout(function () {
            Session.create(data);
            $rootScope.account = Session;
            $rootScope.authenticated = true;
            $location.path(nextLocation).replace();
        }, delay);

    });

    // Called when the 401 response is returned by the server
    $rootScope.$on('event:auth-loginRequired', function (event, data) {

        if ($rootScope.loadingAccount && data.status !== 401) {
            $rootScope.requestedUrl = $location.path()
            $location.path('/login');
        } else {
            Session.invalidate();
            $rootScope.authenticated = false;
            $rootScope.loadingAccount = false;
            $location.path('/home');
        }
    });

    // Called when the 403 response is returned by the server
    $rootScope.$on('event:auth-forbidden', function (rejection) {
        $rootScope.$evalAsync(function () {
            $location.path('/error/403').replace();
        });
    });

    // Called when the user logs out
    $rootScope.$on('event:auth-loginCancelled', function () {
        $location.path('/login').replace();
    });

    // Get already authenticated user account
    AuthSharedService.getAccount();
});